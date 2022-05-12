package main.model.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

import main.model.stock.Stock;

public abstract class Account{
    private double funds;
    private HashMap<String, Integer> portfolio;

    public Account(){
        portfolio = new HashMap<>();
        this.funds = 4000;
    }

    public double getFunds() {
        return funds;
    }

    public HashMap<String, Integer> getPortfolio() {
        return portfolio;
    }

    public void setFunds(String chooice, double funds) {
        if(chooice.equals("buy")){
            this.funds -= funds;
        }else{
            this.funds += funds;
        }
    }
    
    public void setPortfolio(HashMap<String, Integer> portfolio) {
        this.portfolio = portfolio;
    }

    public void addStock(Stock stock, int numOfStocks){
        portfolio.merge(stock.getName(), numOfStocks, Integer::sum);
    }

    public abstract void sellTrades();
    public abstract void buyTrades();
}