class Stock {
    private String stockSymbol;
    private String companyName;
    private double currentPrice;

    public Stock(String stockSymbol, String companyName, double currentPrice) {
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    public boolean validateStock() {
        return stockSymbol != null && !stockSymbol.isEmpty() && currentPrice > 0;
    }

    public void updatePrice(double newPrice) {
        if (newPrice > 0) {
            currentPrice = newPrice;
        }
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }
}

class Order {
    private String orderId;
    private Stock stock;
    private String orderType;
    private int quantity;
    private double orderPrice;

    public Order(String orderId, Stock stock, String orderType, int quantity, double orderPrice) {
        this.orderId = orderId;
        this.stock = stock;
        this.orderType = orderType;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public boolean validateOrder() {
        return orderId.startsWith("ORD-")
                && stock != null
                && stock.validateStock()
                && quantity > 0
                && (orderType.equalsIgnoreCase("BUY")
                || orderType.equalsIgnoreCase("SELL"));
    }

    public double calculateOrderValue() {
        return orderPrice * quantity;
    }

    public String getOrderType() {
        return orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public Stock getStock() {
        return stock;
    }
}

class Trader {
    private String traderId;
    private String traderName;
    private double balance;
    private int ownedQuantity;
    private double totalInvestment;
    private Stock currentStock;

    public Trader(String traderId, String traderName, double balance) {
        this.traderId = traderId;
        this.traderName = traderName;
        this.balance = balance;
        this.ownedQuantity = 0;
        this.totalInvestment = 0;
        this.currentStock = null;
    }

    public boolean validateTrader() {
        return traderId.startsWith("TRD-") && balance >= 0;
    }

    public void buyStock(Order order) {
        if (!validateTrader() || !order.validateOrder()
                || !order.getOrderType().equalsIgnoreCase("BUY")) {
            System.out.println("Invalid Buy Order");
            return;
        }

        double orderValue = order.calculateOrderValue();

        if (balance >= orderValue) {
            balance = balance - orderValue;
            ownedQuantity = ownedQuantity + order.getQuantity();
            totalInvestment = totalInvestment + orderValue;
            currentStock = order.getStock();
            System.out.println("Stock Purchased Successfully");
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public void sellStock(Order order) {
        if (!validateTrader() || !order.validateOrder()
                || !order.getOrderType().equalsIgnoreCase("SELL")) {
            System.out.println("Invalid Sell Order");
            return;
        }

        double orderValue = order.calculateOrderValue();

        if (ownedQuantity >= order.getQuantity()) {
            balance = balance + orderValue;
            ownedQuantity = ownedQuantity - order.getQuantity();
            System.out.println("Stock Sold Successfully");
        } else {
            System.out.println("Insufficient Stock Quantity");
        }
    }

    public double calculateProfitLoss() {
        if (currentStock == null) {
            return 0.0;
        }

        double currentValue = ownedQuantity * currentStock.getCurrentPrice();
        return currentValue - totalInvestment;
    }

    public void displayPortfolio() {
        System.out.println();
        System.out.println("Trader ID: " + traderId);
        System.out.println("Trader Name: " + traderName);
        System.out.println("Current Balance: " + balance);
        System.out.println("Owned Quantity: " + ownedQuantity);
        System.out.println("Total Investment: " + totalInvestment);
        System.out.println("Profit/Loss: " + calculateProfitLoss());
    }
}

class Market {
    private String marketName;
    private Stock[] stocks;

    public Market(String marketName, Stock[] stocks) {
        this.marketName = marketName;
        this.stocks = stocks;
    }

    public void updateMarketPrices() {
        for (int i = 0; i < stocks.length; i++) {
            double oldPrice = stocks[i].getCurrentPrice();
            double priceChange = oldPrice * 0.05;
            double newPrice = oldPrice + priceChange;

            stocks[i].updatePrice(newPrice);
        }
    }

    public Stock findStock(String stockSymbol) {
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i].getStockSymbol().equalsIgnoreCase(stockSymbol)) {
                return stocks[i];
            }
        }

        return null;
    }

    public String getMarketName() {
        return marketName;
    }
}

public class RealTimeStockTradingSimulator {
    public static void main(String[] args) {
        Stock stock = new Stock("INFY", "Infosys", 1500);

        Stock[] stocks = {stock};

        Market market = new Market("Indian Stock Market", stocks);

        Trader trader = new Trader("TRD-101", "Aman", 100000);

        Stock selectedStock = market.findStock("INFY");

        Order buyOrder = new Order(
                "ORD-101",
                selectedStock,
                "BUY",
                10,
                selectedStock.getCurrentPrice()
        );

        trader.buyStock(buyOrder);

        selectedStock.updatePrice(1600);

        trader.displayPortfolio();
    }
}