package main.model.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

import main.model.stock.Stock;
import main.model.stock.StockEnum;
import main.utils.Color;

public abstract class Account{
    private double funds;
    private HashMap<String, Integer> portfolio;

    public Account(){
        portfolio = new HashMap<>();
        this.portfolio.put(StockEnum.AAPL.toString(), 0);
        this.portfolio.put(StockEnum.FB.toString(), 0);
        this.portfolio.put(StockEnum.GOOG.toString(), 0);
        this.portfolio.put(StockEnum.TSLA.toString(), 0);
        this.funds = 4000;
    }

    public double getFunds() {
        return funds;
    }

    public HashMap<String, Integer> getPortfolio() {
        return portfolio;
    }

    public void setFunds(String chooice, double funds, int numberOfStocks) {
        if(chooice.equals("buy")){
            this.funds -= funds * numberOfStocks;
        }else{
            this.funds += funds * numberOfStocks;
        }
    }
    
    public void setPortfolio(HashMap<String, Integer> portfolio) {
        this.portfolio = portfolio;
    }

    public void addStock(Stock stock, int numOfStocks) {
        portfolio.merge(stock.getName(), numOfStocks, Integer::sum);
    }

    public void removeStock(Stock stock, int numberOfStocks){
        portfolio.merge(stock.getName(), numberOfStocks, (oldValue, newValue) -> oldValue - numberOfStocks);
    }

    public void print(){
        System.out.println("\n");
        System.out.println("Stock" + "\tShares");
        System.out.println("\n");
        
        portfolio.forEach((stock, value) -> {
            System.out.println(Color.BLUE + stock + Color.RESET + "\t" + Color.GREEN + value + Color.RESET);
        });
        System.out.println("\n");
        System.out.println("Funds left\t" + Color.GREEN + "$" + this.getFunds() + Color.RESET);
        System.out.println("\n");

    }

    public abstract void sellTrades();
    public abstract void buyTrades();
}