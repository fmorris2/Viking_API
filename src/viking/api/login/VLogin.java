package viking.api.login;

import viking.api.Timing;
import viking.framework.VMethodProvider;
import viking.framework.script.VikingScript;

/**
 * VLogin - A script utility class that will handle
 * 		the login process
 * @author Rick
 *
 */
public class VLogin extends VMethodProvider
{
	
	public boolean login(String username, String password)
	{
			script.log(this, false, "Logging in account: " + username + " password: " + password);
			
			script.log(this, false, "LoginUIState: " + client.getLoginUIState());
			
			switch(client.getLoginUIState())
			{
			
			}
			
			if(conditions == null)
				script.log(this, false, "conditions is null");
			else if(conditions.LOGGED_IN == null)
				script.log(this, false, "LOGGED_IN is null");
			else 
				return Timing.waitCondition(conditions.LOGGED_IN, 4000);
			
			return false;
	}
}
