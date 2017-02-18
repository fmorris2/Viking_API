package viking.framework.antiban.reaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import viking.framework.VMethodProvider;

public abstract class ReactionEvent
{
	private final String ID_LOG_PATH = System.getProperty("user.home") + "/OSBot/Data/reaction/times/id/";
	private final String NAME_LOG_PATH = System.getProperty("user.home") + "/OSBot/Data/reaction/times/name/";
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
	
	public ReactionEvent(VMethodProvider api, String entityName)
	{
		this.api = api;
		this.entityName = entityName;
		LOG_FILE_PATH = entityName == null ? ID_LOG_PATH + entityID : NAME_LOG_PATH + entityName;
		System.out.println(LOG_FILE_PATH);
	}
	
	public ReactionEvent(VMethodProvider api, int entityID)
	{
		this.api = api;
		this.entityID = entityID;
		LOG_FILE_PATH = entityName == null ? ID_LOG_PATH + entityID : NAME_LOG_PATH + entityName;
		System.out.println(LOG_FILE_PATH);
	}
	
	public void log(ReactionEntry entry)
	{
		api.log("Trying to write to: " + LOG_FILE_PATH);
		try(
			FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
			BufferedWriter bw = new BufferedWriter(fw);
		)
		{
			bw.write(""+entry.getLength());
			bw.newLine();
			bw.flush();
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
