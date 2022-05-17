package main.model.trade;

public class Trade {
    private Stock stock;
    private double stockPrice;
    private int stockCount;
    private Choice choice;

    public enum Stock {FB, TSLA, AAPL, GOOG};
    public enum Choice {BUY, SELL}
    
    public Trade(Stock stock, double stockPrice, int stockCount, Choice choice){
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.stockCount = stockCount;
        this.choice = choice;
    }
    
    public Trade(Trade source){
        this.stock = source.stock;
        this.stockPrice = source.stockPrice;
        this.stockCount = source.stockCount;
        this.choice = source.choice;
    }

    public int getStockCount() {
        return stockCount;
    }

    public Stock getStock() {
        return stock;
    }

    public double getStockPrice() {
        return stockPrice;
    }
    
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public void setStockName(Stock stock) {
        this.stock = stock;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    
}
