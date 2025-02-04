package edu.neu.csye7374;

public class EnergyStockFactory implements StockFactory {

    @Override
    public StockAPI createStock(String id, String name, double price, String description) {
        // The "factory method" for an EnergyStock
        return new EnergyStock(id, name, price, description);
    }
}
