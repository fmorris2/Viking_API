package viking.api.position;

import org.osbot.rs07.api.map.Position;

import viking.framework.VMethodProvider;

public class PosUtils extends VMethodProvider
{
	public boolean isWalkable(Position p)
	{
		return localPathFinder.findPath(p) != null;
	}
}
