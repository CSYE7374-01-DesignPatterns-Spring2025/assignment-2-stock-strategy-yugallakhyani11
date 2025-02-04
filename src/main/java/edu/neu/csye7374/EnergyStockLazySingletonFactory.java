package edu.neu.csye7374;

public class EnergyStockLazySingletonFactory implements StockFactory {

    // Lazily initialized single instance
    private static EnergyStockLazySingletonFactory instance; // null at start

    // private constructor
    private EnergyStockLazySingletonFactory() {
    }

    public static synchronized EnergyStockLazySingletonFactory getInstance() {
        if (instance == null) {
            instance = new EnergyStockLazySingletonFactory();
        }
        return instance;
    }

    @Override
    public StockAPI createStock(String id, String name, double price, String description) {
        // The "factory method" for an EnergyStock
        return new EnergyStock(id, name, price, description);
    }
}
