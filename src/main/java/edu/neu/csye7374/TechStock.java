package edu.neu.csye7374;

public class TechStock extends StockAPI {
    public TechStock(String ID, String name, double price, String description) {
        super(ID, name, price, description);
        this.strategy = new BullMarketStrategy(); 
    }

    @Override
    public int getMetric() {
        if (bidHistory.size() < 2) return 0; // Not enough data for growth calculation

        int growthScore = 0;//A score for managing the performance of the stock
        for (int i = 1; i < bidHistory.size(); i++) {
            if (bidHistory.get(i) > bidHistory.get(i - 1)) {
                growthScore += 10; // Indicating growth in performance
            } else {
                growthScore -= 5; // Indicating decline in performance
            }
        }
        return growthScore;
    }
}
