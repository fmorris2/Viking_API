package viking.api.grand_exchange;

import org.osbot.rs07.api.GrandExchange.Box;
import org.osbot.rs07.api.GrandExchange.Status;
import org.osbot.rs07.api.ui.RS2Widget;

import viking.api.Timing;
import viking.framework.VMethodProvider;

public class VGrandExchange extends VMethodProvider
{
	private static final int INTER_MASTER = 465;
	private static final int BOX_ONE_CHILD = 7;
	private static final int CREATE_BUY_OFFER = 0, CREATE_SELL_OFFER = 1;
	
	private static final int SEARCH_MASTER = 162;
	private static final int SEARCH_CHILD = 38;
	private static final int SEARCH_COMPONENT = 2;

	//private methods
	private RS2Widget getSearchResult(int id)
	{
		RS2Widget w = widgets.get(SEARCH_MASTER, SEARCH_CHILD, SEARCH_COMPONENT);
		return (w != null && w.getItemId() == id) ? w : null;
	}
	
	private boolean getToMainScreen()
	{
		if(grandExchange.isBuyOfferOpen() || grandExchange.isSellOfferOpen())
			return grandExchange.goBack();
		
		return grandExchange.isOpen() && !grandExchange.isBuyOfferOpen() && !grandExchange.isSellOfferOpen(); 
	}
	private Box getOpenBox()
	{
		for(Box b : Box.values())
			if(b != null && grandExchange.getStatus(b) == Status.EMPTY)
				return b;
		
		return null;
	}
	
	private RS2Widget getCreateBuyButton(Box b)
	{
		return widgets.get(INTER_MASTER, BOX_ONE_CHILD + b.ordinal(), CREATE_BUY_OFFER);
	}
	
	private RS2Widget getCreatesSellButton(Box b)
	{
		return widgets.get(INTER_MASTER, BOX_ONE_CHILD + b.ordinal(), CREATE_SELL_OFFER);
	}
	
	
	//public API
	public boolean buyItem(int itemId, String searchTerm, int price, int quantity)
	{
		if(getToMainScreen())
		{
			script.log(this, false, "On main GE screen...");
			
			Box open = getOpenBox();
			if(open != null)
			{
				script.log(this, false, "Open box found");
				
				RS2Widget buy = getCreateBuyButton(open);
				if(buy != null && buy.interact() && Timing.waitCondition(() -> grandExchange.isBuyOfferOpen(), 4000))
				{
					waitMs(random(1200, 2400));
					script.log(this, false, "Typing search term...");
					if(keyboard.typeString(searchTerm) && Timing.waitCondition(() -> getSearchResult(itemId) != null, 5500))
					{
						waitMs(random(1200, 2400));
						script.log(this, false, "Search result found");
						RS2Widget searchRes = getSearchResult(itemId);
						if(searchRes.interact() && Timing.waitCondition(() -> grandExchange.getOfferPrice() > 0, 5000))
						{
							script.log(this, false, "Successfully clicked search result");
							if(grandExchange.setOfferPrice(price) && (quantity == 1 || grandExchange.setOfferQuantity(quantity)))
							{
								if(Timing.waitCondition(() -> {return (grandExchange.getOfferPrice() == price 
										&& grandExchange.getOfferQuantity() == quantity);}, 4500))
								{
									script.log(this, false, "Price & quantity verified...");
									return grandExchange.confirm() 
											&& Timing.waitCondition(() -> grandExchange.getStatus(open) != Status.EMPTY, 5000);
								}
										
							}
						}
						
					}
				}
			}
		}
		
		return false;
	}
}
