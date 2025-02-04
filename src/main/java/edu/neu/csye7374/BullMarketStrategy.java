package edu.neu.csye7374;

public class BullMarketStrategy implements StockPricingStrategy{
	@Override
    public double computeNewPrice(StockAPI stock, double newBid) {
        double currentPrice = stock.getPrice();
        double metric = stock.getMetric(); 
        // Example: in a bull market, stocks might capture 80% of (newBid - currentPrice), 
        // multiplied by the stock-specific metric
        double increment = 0.8 * (newBid - currentPrice) * metric;
        return currentPrice + increment;
    }

}
