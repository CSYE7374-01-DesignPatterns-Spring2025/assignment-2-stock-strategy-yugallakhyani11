package edu.neu.csye7374;

public class BearMarketStrategy implements StockPricingStrategy{
	
	    public double computeNewPrice(StockAPI stock, double newBid) {
	        double currentPrice = stock.getPrice();
	        double metric = stock.getMetric();
	        // Example: in a bear market, stocks might only capture 30% of that difference
	        // again multiplied by the stock-specific metric
	        double increment = 0.3 * (newBid - currentPrice) * metric;
	        return currentPrice + increment;
	    }

		

}
