package viking.api.login;

import java.awt.Color;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.listener.LoginResponseCodeListener;
import org.osbot.rs07.utility.ConditionalSleep;

import viking.framework.VMethodProvider;
import viking.framework.script.VikingScript;

public class VLogin extends VMethodProvider implements LoginResponseCodeListener
{
	public static final int BANNED_CODE = 4;
	
	private boolean isInvalid, isBanned, isLocked;
	private String username, password;
	
	public VLogin(VikingScript script)
	{
		script.bot.addLoginListener(this);
	}
	
	public boolean login(String username, String password)
	{
		script.log(this, false, "Attempting to login " + username + ":" + password);
		
		if(this.username == null || !this.username.equals(username) || this.password == null || !this.password.equals(password))
		{
			script.log(this, false, "We're trying to login with different credentials... Resetting");
			this.username = username;
			this.password = password;
			isInvalid = false;
			isBanned = false;
			isLocked = false;
		}
			
		if(isInvalid || isBanned || isLocked)
		{
			script.log(this, false, "isInvalid, isBanned, or isLocked");
			return false;
		}
		if (getClient().isLoggedIn() && getLobbyButton() == null)
		{
			script.log(this, false, "Success, logged in!");
			return true;
		}
		else if (getLobbyButton() != null)
		{
			script.log(this, false, "getLobbyButton() != null");
			clickLobbyButton();
		}
		else if (isOnWorldSelectorScreen())
		{
			script.log(this, false, "isOnWorldSelectorScreen()");
			cancelWorldSelection(); 
		}
		else if (!isPasswordEmpty())
		{
			script.log(this, false, "!isPasswordEmpty()");
			clickCancelLoginButton();
		}
		else
			handle();
		
		return login(username, password);
	}

	private boolean isOnWorldSelectorScreen()
	{
		return getColorPicker().isColorAt(50, 50, Color.BLACK);
	}

	private void cancelWorldSelection()
	{
		if (getMouse().click(new RectangleDestination(getBot(), 712, 8, 42, 8)))
		{
			new ConditionalSleep(3000)
			{
				@Override
				public boolean condition() throws InterruptedException
				{
					return !isOnWorldSelectorScreen();
				}
			}.sleep();
		}
	}

	private boolean isPasswordEmpty()
	{
		return !getColorPicker().isColorAt(350, 274, Color.WHITE);
	}

	private boolean clickCancelLoginButton()
	{
		return getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
	}

	private void handle()
	{
		switch (getClient().getLoginUIState())
		{
		case 0:
			clickExistingUsersButton();
			break;
		case 1:
			clickLoginButton();
			break;
		case 2:
			enterUserDetails(username, password);
			break;
		case 3:
			clickTryAgainButton();
			break;
		}
	}

	private void clickExistingUsersButton()
	{
		getMouse().click(new RectangleDestination(getBot(), 400, 280, 120, 20));
	}

	private void clickLoginButton()
	{
		getMouse().click(new RectangleDestination(getBot(), 240, 310, 120, 20));
	}

	private boolean enterUserDetails(String username, String password)
	{
		return getKeyboard().typeString(username) && getKeyboard().typeString(password);
	}

	private boolean clickTryAgainButton()
	{
		return getMouse().click(new RectangleDestination(getBot(), 318, 262, 130, 26));
	}

	private void clickLobbyButton()
	{
		if (getLobbyButton().interact())
		{
			new ConditionalSleep(10_000)
			{
				@Override
				public boolean condition() throws InterruptedException
				{
					return getLobbyButton() == null;
				}
			}.sleep();
		}
	}

	private RS2Widget getLobbyButton()
	{
		return getWidgets().getWidgetContainingText("CLICK HERE TO PLAY");
	}

	@Override
	public void onResponseCode(int code) throws InterruptedException
	{
		script.log(this, false, "Login response code caught: " + code);
		if(code == BANNED_CODE)
			isBanned = true;
		
	}
	
	public boolean isInvalid()
	{
		return isInvalid;
	}
	
	public boolean isBanned()
	{
		return isBanned;
	}
	
	public boolean isLocked()
	{
		return isLocked;
	}
}
