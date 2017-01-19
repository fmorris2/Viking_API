package viking.api.dax_webwalker;

import java.util.ArrayList;

import org.osbot.rs07.api.map.Position;

import viking.api.dax_webwalker.shared.GetPathResponseContainer;
import viking.api.dax_webwalker.shared.PlayerInformation;
import viking.framework.VMethodProvider;

public class DaxPath extends VMethodProvider {
	
    /**
     *
     * @param destination
     * @return Path from current player position to destination
     */
    public ArrayList<Position> getPath(Position destination){
        return getPath(myPosition(), destination).getRSTilePath();
    }

    private  GetPathResponseContainer getPath(Position start, Position end){
        PlayerInformation playerInformation = PlayerInformation.generatePlayerInformation();
        return WebPathCore.getPath(start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ(), playerInformation);
    }
}
