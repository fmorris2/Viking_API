package viking.api.condition;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Character;
import org.osbot.rs07.api.model.Entity;

import viking.api.ScriptUtil;
import viking.framework.script.VikingScript;

/**
 * This class holds all of the preset VConditions in
 * the Viking API. This cuts down on having to create
 * Condition code in the actual scripts, and also offers
 * a performance boost due to a lot of the CONDITIONS being
 * cached in constants
 *
 * @author The Viking
 */
public class VConditions extends ScriptUtil {

    public final VCondition IN_COMBAT = inCombat();
    public final VCondition BANK_OPEN = isBankOpen();
    public final VCondition LOGGED_IN = isLoggedIn();

    public VConditions(VikingScript script) {
        super(script);
    }

    public VCondition inAreaCondition(Area a) {
        return new VCondition() {
            @Override
            public boolean evaluate() {
                return a.contains(script.myPosition());
            }

        };
    }

    public VCondition stolenEntity(Character<?> e) {
        return new VCondition() {
            @Override
            public boolean evaluate() {
                Character<?> interacting = e.getInteracting();
                return interacting != null && !interacting.equals(myPlayer());
            }

        };
    }

    public VCondition onScreenCondition(Entity e) {
        return new VCondition() {
            @Override
            public boolean evaluate() {
                return e == null || e.isVisible();
            }

        };
    }

    private VCondition inCombat() {
        return new VCondition() {
            @Override
            public boolean evaluate() {
                return combat.isFighting() || myPlayer().isUnderAttack();
            }
        };
    }

    private VCondition isBankOpen() {
        return new VCondition() {
            @Override
            public boolean evaluate() {
                return bank.isOpen();
            }
        };
    }
    
    private VCondition isLoggedIn()
    {
    	return new VCondition()
		{
    		@Override
    		public boolean evaluate()
    		{
    			return client.isLoggedIn();
    		}
		};
    }

}
