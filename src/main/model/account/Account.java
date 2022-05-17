package main.model.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

// import main.model.stock.Stock;
// import main.model.stock.StockEnum;
import main.model.trade.Trade;
import main.model.trade.Trade.Stock;
import main.model.trade.Trade.Choice;
import main.utils.Color;

public abstract class Account{
    private double funds;
    private HashMap<Stock, Integer> portfolio;

    public Account(){
        portfolio = new HashMap<>();
        this.portfolio.put(Stock.AAPL, 0);
        this.portfolio.put(Stock.FB, 0);
        this.portfolio.put(Stock.GOOG, 0);
        this.portfolio.put(Stock.TSLA, 0);
        this.funds = 4000;
    }

    public double getFunds() {
        return funds;
    }

    public HashMap<Stock, Integer> getPortfolio() {
        return portfolio;
    }

    public void setFunds(Trade trade) {
        switch(trade.getChoice()){
            case BUY: 
                this.funds -= trade.getStockPrice() * trade.getStockCount();
                break;
                case SELL:
                this.funds += trade.getStockPrice() * trade.getStockCount();
                break;
        }
    }
    
    public void setPortfolio(Stock stock, Integer stockCount) {
        this.portfolio.put(stock, stockCount);
    }

    // public void addStock(Stock stock, int numOfStocks) {
    //     portfolio.merge(stock.getName(), numOfStocks, Integer::sum);
    // }

    // public void removeStock(Stock stock, int numberOfStocks){
    //     portfolio.merge(stock.getName(), numberOfStocks, (oldValue, newValue) -> oldValue - numberOfStocks);
    // }

    public void print(){
        System.out.println(Color.PURPLE + "\n\t********** P O R T F O L I O **********" + Color.RESET);
        System.out.println("\t\tStock" + "\tShares\n");
        
        portfolio.forEach((stock, value) -> {
            System.out.println("\t\t" + Color.BLUE + stock + Color.RESET + "\t" + Color.GREEN + value + Color.RESET);
        });

        System.out.println("\n\tFunds left\t" + Color.GREEN + "$" + this.getFunds() + Color.RESET);
        System.out.println(Color.PURPLE + "\n\t*************************************" + Color.RESET + "\n");

    }

    public abstract void sellTrades();
    public abstract void buyTrades(Trade trade);
}