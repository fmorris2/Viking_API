package viking.framework.antiban.reaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sun.net.ssl.HttpsURLConnection;
import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.TrustManager;
import com.sun.net.ssl.X509TrustManager;

import viking.framework.VMethodProvider;

@SuppressWarnings("deprecation")
public abstract class ReactionEvent
{
	private static final TrustManager[] TRUST_MANAGER = getTrustManager();
	
	//private final String ID_LOG_PATH = System.getProperty("user.home") + "/OSBot/Data/reaction/times/id/";
	//private final String NAME_LOG_PATH = System.getProperty("user.home") + "/OSBot/Data/reaction/times/name/";
	private final String ID_LOG_PATH = "https://vikingscripts.io/orion/downloads/reaction/times/id/";
	private final String NAME_LOG_PATH = "https://vikingscripts.io/orion/downloads/reaction/times/name/";
	private final String LOG_FILE_PATH;
	
	protected String entityName;
	protected int entityID;
	protected VMethodProvider api;
	
	private Queue<Integer> reactionTimes = new LinkedList<>();

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
	
	private void loadTimes()
	{
		HttpURLConnection urlConnection = null;
		
		try
		{
			URL url = new URL(LOG_FILE_PATH);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("User-Agent", "mUUgbWKTFeQW2W9BgcRzAbThTPc4YrEDAqdWJFnHXPygAufe");
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.connect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(urlConnection == null)
			return;
			
		try
		(
			InputStreamReader fr = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader br = new BufferedReader(fr);
		)
		{
			List<Integer> times = new ArrayList<>();
			String line;
			while((line = br.readLine()) != null)
				times.add(Integer.parseInt(line));
			
			System.out.println("Loaded " + times.size() + " reaction times");
			Collections.shuffle(times);
			reactionTimes.addAll(times);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getReactionTime()
	{
		if(reactionTimes.isEmpty())
			loadTimes();
		
		int time = reactionTimes.poll();
		reactionTimes.add(time);
		
		return time;
	}
	
	public void log(ReactionEntry entry)
	{
		api.log("Trying to write to: " + LOG_FILE_PATH);
		try
		(
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
	
	
	
	public static void installTrustManager()
	{
		try 
		{
		    SSLContext sc = SSLContext.getInstance("SSL"); 
		    sc.init(null, TRUST_MANAGER, new java.security.SecureRandom()); 
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} 
		catch (GeneralSecurityException e) 
		{
			e.printStackTrace();
		} 
	}
	
	private static TrustManager[] getTrustManager()
	{
		// Create a trust manager that does not validate certificate chains
		return new TrustManager[] 
		{ 
			new X509TrustManager()
			{
				public java.security.cert.X509Certificate[] getAcceptedIssuers()
				{
					return new X509Certificate[0];
				}
	
				@Override
				public boolean isClientTrusted(X509Certificate[] arg0)
				{
					return false;
				}

				@Override
				public boolean isServerTrusted(X509Certificate[] arg0)
				{
					return false;
				}
			} 
		};
	}
}
