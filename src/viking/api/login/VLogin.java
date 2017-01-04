package viking.api.login;

import java.awt.Color;

import org.osbot.rs07.api.Client.LoginState;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.listener.LoginResponseCodeListener;

import viking.api.Timing;
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
		
		if(isOnMainLoginScreen() || getToMainLoginScreen())
		{
			if(enterUserDetails(username, password) && clickLoginButton()
					&& Timing.waitCondition(() -> (client.getLoginState() != LoginState.LOADING), 5000))
			{
				script.log(this, false, "Done loading.... checking for lobby button");
				return Timing.waitCondition(() -> getLobbyButton() != null, 6500) && clickLobbyButton()
						&& Timing.waitCondition(() -> (getLobbyButton() == null && equipment.getItems() != null), 4000);
			}
		}
		
		return false;
	}
	
	private boolean isOnMainLoginScreen()
	{
		return client.getLoginUIState() == 2;
	}
	
	private boolean getToMainLoginScreen()
	{
		script.log(this, false, "Get to main login screen");
		final int UI_STATE = getClient().getLoginUIState();
		
		//check if on world selection
		if(isOnWorldSelectorScreen())
			return cancelWorldSelection() && clickExistingUserButton();
		if(UI_STATE == 0) //on initial screen
			return clickExistingUserButton();
		if(UI_STATE == 1) //there are already credentials typed in
			return clearCredentials();
		if(UI_STATE == 3) //we need to click try again
			return clickTryAgainButton() && clearCredentials();
		
		return false;
	}
	
	private boolean clearCredentials()
	{
		if(clickCancelLoginButton() && Timing.waitCondition(() -> client.getLoginUIState() == 2, 2000))
			return clickExistingUserButton();
		
		return false;
	}

	private boolean isOnWorldSelectorScreen()
	{
		return getColorPicker().isColorAt(50, 50, Color.BLACK);
	}

	private boolean cancelWorldSelection()
	{
		if (getMouse().click(new RectangleDestination(getBot(), 712, 8, 42, 8)))
			return Timing.waitCondition(() -> !isOnWorldSelectorScreen(), 3000);
		
		return false;
	}

	private boolean clickCancelLoginButton()
	{
		return getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
	}

	private boolean clickExistingUserButton()
	{
		if(getMouse().click(new RectangleDestination(getBot(), 400, 280, 120, 20)))
			return Timing.waitCondition(() -> client.getLoginUIState() == 2, 2000);
		
		return false;
	}

	private boolean clickLoginButton()
	{
		return getMouse().click(new RectangleDestination(getBot(), 240, 310, 120, 20));
	}

	private boolean enterUserDetails(String username, String password)
	{
		return getKeyboard().typeString(username) && getKeyboard().typeString(password);
	}

	private boolean clickTryAgainButton()
	{
		return getMouse().click(new RectangleDestination(getBot(), 318, 262, 130, 26));
	}

	public boolean clickLobbyButton()
	{
		if (getLobbyButton().interact())
			return Timing.waitCondition(() -> getLobbyButton() == null, 10000);
		
		return false;
	}

	public RS2Widget getLobbyButton()
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
