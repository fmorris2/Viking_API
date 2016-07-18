package viking.api.condition;

import org.osbot.rs07.utility.Condition;

/**
 * This is the class we use instead of the default Condition
 * class in the OSBot API. This object allowed for easy chaining
 * of Conditions with and, or, not operators.
 * 
 * @author The Viking
 *
 */
public abstract class VCondition extends Condition
{
	private final Condition ORIGINAL = this;
	
	/**
	 * Chains this condition with another via an AND relationship, 
	 * and returns the combined condition
	 * 
	 * @param c the condition to evaluate with this one
	 * @return the new VCondition object reflecting the 'and' relationship
	 */
	public VCondition and(VCondition c)
	{		
		return new VCondition()
		{
			@Override
			public boolean evaluate()
			{
				return ORIGINAL.evaluate() && c.evaluate();
			}
		};
	}
	
	/**
	 * Chains this condition with another via an OR relationship,
	 * and returns the combined condition
	 * 
	 * @param c the condition to evaluate with this one
	 * @return the new VCondition object reflecting the 'or' relationship
	 */
	public VCondition or(VCondition c)
	{
		return new VCondition()
		{
			@Override
			public boolean evaluate()
			{
				return ORIGINAL.evaluate() || c.evaluate();
			}
		};
	}
	
	/**
	 * Generates an inverse of this condition
	 * 
	 * @return the new VCondition object reflecting the inverse of this Condition
	 */
	public VCondition not()
	{
		return new VCondition()
		{
			@Override
			public boolean evaluate()
			{
				return !ORIGINAL.evaluate();
			}
		};
	}
	
	
}
