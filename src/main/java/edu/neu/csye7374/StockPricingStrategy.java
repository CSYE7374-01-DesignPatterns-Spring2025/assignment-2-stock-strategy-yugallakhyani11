package edu.neu.csye7374;

public interface StockPricingStrategy {
	 double computeNewPrice(StockAPI stock, double newBid);

}
