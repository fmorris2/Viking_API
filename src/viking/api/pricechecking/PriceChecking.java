package viking.api.pricechecking;

import viking.framework.VMethodProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public class PriceChecking extends VMethodProvider {

    /**
     * Gets the price of the itemID from the Runescape website.
     *
     * @param id The id of the item.
     * @return The price of the item.
     */
    public static int getGEPrice(int id) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + id).openStream()))) {
        	String line = reader.readLine();
        	if(line == null)
        		return -1;
        	
        	Matcher matcher = Pattern.compile(".*\"price\":\"?(\\d+\\,?\\.?\\d*)([k|m]?)\"?},\"today\".*").matcher(line);
            reader.close();
            if (matcher.matches()) {
                final double price = Double.parseDouble(matcher.group(1).replace(",", ""));
                final String suffix = matcher.group(2);
                return (int) (suffix.isEmpty() ? price : price * (suffix.charAt(0) == 'k' ? 1000 : 1000000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Gets the price of the itemID from the OSBuddy website.
     *
     * @param id The id of the item.
     * @return The price of the item.
     */
    public static int getOSBuddyPrice(int id) {
        try {
            URL url = new URL("https://api.rsbuddy.com/grandExchange?a=guidePrice&i=" + id);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                final String line = reader.readLine();
                reader.close();
                return line == null ? -1 : Integer.parseInt(line.substring(11, line.indexOf(',')));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

