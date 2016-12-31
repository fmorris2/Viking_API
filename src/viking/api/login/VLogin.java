package viking.api.login;

import viking.api.ScriptUtil;
import viking.api.Timing;
import viking.framework.script.VikingScript;

/**
 * VLogin - A script utility class that will handle
 * 		the login process
 * @author Rick
 *
 */
public class VLogin extends ScriptUtil
{

	public VLogin(VikingScript script)
	{
		super(script);
	}
	
	public boolean login(String username, String password)
	{
			script.log(this, false, "Logging in account: " + username + " password: " + password);
			
			script.log(this, false, "LoginUIState: " + client.getLoginUIState());
			
			switch(client.getLoginUIState())
			{
			
			}
			
			return Timing.waitCondition(conditions.LOGGED_IN, 4000);
	}
}
