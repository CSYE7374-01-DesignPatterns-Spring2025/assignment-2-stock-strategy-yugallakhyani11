package edu.neu.csye7374;

public interface StockFactory {
    StockAPI createStock(String id, String name, double price, String description);
}
