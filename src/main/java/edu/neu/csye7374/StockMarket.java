package edu.neu.csye7374;


import java.util.ArrayList;
import java.util.List;



//Implementing Singleton class
public class StockMarket {
    private static StockMarket instance; // Lazy Singleton instance
    private List<StockAPI> stocks = new ArrayList<>();

    // Private constructor to implement lazy initialization 
    private StockMarket() {
    	instance = null;
    }

    //Method to get the singleton instance
    public static synchronized StockMarket getInstance() {
        if (instance == null) {
            instance = new StockMarket();
        }
        return instance;
    }

    public void addStock(StockAPI stock) {
        stocks.add(stock);
    }

    public void tradeStock(String stockName, String bid) {
        for (StockAPI stock : stocks) {
            if (stock.ID.equals(stockName)) {
                stock.setBid(bid);
                System.out.println("Traded " + stockName + " with bid: " + bid);
                System.out.println(stockName + " Metric update:" + stock.getMetric());
            }
        }
    }

    public void removeStock(String stockName) {
        stocks.removeIf(stock -> stock.ID.equals(stockName));
    }

    public void showAllStocks() {
        for (StockAPI stock : stocks) {
            System.out.println(stock);
        }
    }
    
    // public static void demo() {
    	
    // 	StockMarket stockExchange = StockMarket.getInstance();
  
    // 	TechStock techStock = new TechStock("AAPL", "Apple Inc.", 200.0, "Innovative consumer electronics company");
    //     EnergyStock energyStock = new EnergyStock("PFE", "Pfizer Inc.", 75.0, "Global pharmaceutical leader");
    //     EnergyStock energyStock2 = new EnergyStock("BP", "BP Plc", 50.0, "Multinational oil and gas company");

     
    //     stockExchange.addStock(techStock);
    //     stockExchange.addStock(energyStock);
    //     stockExchange.addStock(energyStock2);
       

  
    //     System.out.println("Simulating bids for Apple Inc. (AAPL):");
    //     stockExchange.tradeStock("AAPL", "210");
    //     stockExchange.tradeStock("AAPL", "220");
    //     stockExchange.tradeStock("AAPL", "230");
    //     stockExchange.tradeStock("AAPL", "225");
    //     stockExchange.tradeStock("AAPL", "240");
    //     stockExchange.tradeStock("AAPL", "250");
        
    //     System.out.println("\nSimulating bids for Pfizer Inc. (PFE):");
    //     stockExchange.tradeStock("PFE", "76");
    //     stockExchange.tradeStock("PFE", "78");
    //     stockExchange.tradeStock("PFE", "80");
    //     stockExchange.tradeStock("PFE", "79");
    //     stockExchange.tradeStock("PFE", "81");
    //     stockExchange.tradeStock("PFE", "82");
        
    //     System.out.println("\nSimulating bids for BP Plc (BP):");
    //     stockExchange.tradeStock("BP", "52");
    //     stockExchange.tradeStock("BP", "54");
    //     stockExchange.tradeStock("BP", "53");

      
    //     stockExchange.showAllStocks();


    //     stockExchange.removeStock("BP");
    //     System.out.println("Showing stocks after remove call");

  
    //     stockExchange.showAllStocks();
        
    // }

    public static void demo() {
        StockMarket market = StockMarket.getInstance();

        // Factories
        StockFactory normalTech = new TechStockFactory();                   // normal factory for TechStock
        StockFactory normalEnergy = new EnergyStockFactory();               // normal factory for EnergyStock
        StockFactory eagerTech = TechStockEagerSingletonFactory.getInstance(); 
        StockFactory lazyEnergy = EnergyStockLazySingletonFactory.getInstance();

        // Create stock instances via factories
        StockAPI apple = normalTech.createStock("AAPL", "Apple Inc.", 200.0, "Consumer electronics");
        StockAPI pfizer = normalEnergy.createStock("PFE", "Pfizer Inc.", 75.0, "Pharmaceutical leader");
        StockAPI microsoft = eagerTech.createStock("MSFT", "Microsoft Corporation", 250.0, "Software/cloud");
        StockAPI bp = lazyEnergy.createStock("BP", "BP Plc", 50.0, "Oil and gas");

        // Cast to concrete classes so we can set strategies
        TechStock appleTech = (TechStock) apple;
        EnergyStock pfizerEnergy = (EnergyStock) pfizer;
        TechStock microsoftTech = (TechStock) microsoft;
        EnergyStock bpEnergy = (EnergyStock) bp;

        // Example: single Bull/Bear strategies (could also do four separate if you prefer)
        StockPricingStrategy bullStrategy = new BullMarketStrategy();
        StockPricingStrategy bearStrategy = new BearMarketStrategy();

        // Assign strategies
        // (You can mix/match however you want: below is just a sample assignment)
        appleTech.setStrategy(bullStrategy);     // Apple => Bull
        pfizerEnergy.setStrategy(bearStrategy);  // Pfizer => Bear
        microsoftTech.setStrategy(bearStrategy); // Microsoft => Bear
        bpEnergy.setStrategy(bullStrategy);      // BP => Bull

        // Add to the market
        market.addStock(apple);
        market.addStock(pfizer);
        market.addStock(microsoft);
        market.addStock(bp);

        // Trades
        System.out.println("Trading AAPL... bull strategy");
        market.tradeStock("AAPL", "210");  // calls apple.setBid("210"), applies bull strategy
        market.tradeStock("AAPL", "220");

        System.out.println("\nTrading PFE...bear strategy");
        market.tradeStock("PFE", "76");    // calls pfizer.setBid("76"), applies bear strategy
        market.tradeStock("PFE", "78");

        System.out.println("\nTrading MSFT...bear strategy");
        market.tradeStock("MSFT", "255");  // bear strategy
        market.tradeStock("MSFT", "260");
        
        System.out.println("\nSwitching Apple from Bull to Bear...");
        appleTech.setStrategy(bearStrategy);

       
        System.out.println("Trading AAPL (now Bear)...");
        market.tradeStock("AAPL", "190");
        market.tradeStock("AAPL", "185");

        System.out.println("\nTrading BP...bull strategy");
        market.tradeStock("BP", "52");     // bull strategy
        market.tradeStock("BP", "54");

        System.out.println("\nAll Stocks:");
        market.showAllStocks();

        // Remove BP
        market.removeStock("BP");
        System.out.println("\nAfter removing BP:");
        market.showAllStocks();
    }



}
