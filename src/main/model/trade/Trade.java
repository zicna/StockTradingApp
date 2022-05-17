package main.model.trade;

public class Trade {
    private String stockName;
    private double stockPrice;
    private int stockCount;
    
    public Trade(String stockName, double stockPrice, int stockCount){
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockCount = stockCount;
    }

    public Trade(Trade source){
        this.stockName = source.stockName;
        this.stockPrice = source.stockPrice;
        this.stockCount = source.stockCount;
    }

    public int getStockCount() {
        return stockCount;
    }

    public String getStockName() {
        return stockName;
    }

    public double getStockPrice() {
        return stockPrice;
    }
    
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    
}
