package viking.framework.antiban.reaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import viking.framework.VMethodProvider;

public abstract class ReactionEvent
{
	private final String ID_LOG_PATH = System.getProperty("user.home") + "/Desktop/reaction/times/id/";
	private final String NAME_LOG_PATH = System.getProperty("user.home" + "Desktop/reaction/times/name/");
	//private final String ID_LOG_PATH = getClass().getResource("/viking/framework/antiban/reaction/times/id/").getFile();
	private final String LOG_FILE_PATH;
	
	protected String entityName;
	protected int entityID;
	protected VMethodProvider api;

	public ReactionEvent(VMethodProvider api)
	{
		this.api = api;
		entityName = getEntityName();
		entityID = getEntityID();
		LOG_FILE_PATH = entityName == null ? ID_LOG_PATH + entityID : NAME_LOG_PATH + entityName;
		System.out.println(LOG_FILE_PATH);
	}
	
	public void log(ReactionEntry entry)
	{
		try(
			FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
			FileWriter f2 = new FileWriter(LOG_FILE_PATH.replace("bin", "src"), true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedWriter b2 = new BufferedWriter(f2);
		)
		{
			bw.write(""+entry.getLength());
			bw.newLine();
			bw.flush();
			
			b2.write(""+entry.getLength());
			b2.newLine();
			b2.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean isValid(Object identifier)
	{
		if(identifier == null)
			return false;
		
		if(identifier instanceof String)
			return ((String)(identifier)).equals(entityName);
		else if(identifier instanceof Integer)
			return ((Integer)(identifier)).intValue() == entityID;
		
		return false;
	}
	
	public abstract boolean isDoing();
	
	protected abstract int getEntityID();
	protected abstract String getEntityName();
}
