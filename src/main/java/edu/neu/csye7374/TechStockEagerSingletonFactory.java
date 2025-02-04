package edu.neu.csye7374;

public class TechStockEagerSingletonFactory implements StockFactory {

    // Eagerly created single instance
    private static final TechStockEagerSingletonFactory INSTANCE =
            new TechStockEagerSingletonFactory();

    // private constructor so no one can instantiate outside
    private TechStockEagerSingletonFactory() {
    }

    // global accessor
    public static TechStockEagerSingletonFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public StockAPI createStock(String id, String name, double price, String description) {
        // The "factory method" for a TechStock
        return new TechStock(id, name, price, description);
    }
}
