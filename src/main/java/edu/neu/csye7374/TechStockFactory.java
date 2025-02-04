package edu.neu.csye7374;

public class TechStockFactory implements StockFactory {

    @Override
    public StockAPI createStock(String id, String name, double price, String description) {
        // The "factory method" for a TechStock
        return new TechStock(id, name, price, description);
    }
}
