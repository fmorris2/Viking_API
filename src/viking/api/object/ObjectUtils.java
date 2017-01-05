package viking.api.object;

import java.util.List;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;

import viking.framework.VMethodProvider;

public class ObjectUtils extends VMethodProvider
{
	public boolean isOnTile(String obj, Position tile)
	{
		List<RS2Object> objs = objects.get(tile.getX(), tile.getY());
		for(RS2Object o : objs)
		{
			String name = o.getName();
			if(name != null && name.equals(obj))
				return true;
		}
		
		return false;
	}
}
