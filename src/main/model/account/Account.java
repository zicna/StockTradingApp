package main.model.account;

import java.util.ArrayList;
import java.util.HashSet;

import main.model.stock.Stock;

public abstract class Account{
    private double funds;
    private HashSet<Stock> portfolio;

    public Account(){
        portfolio = new HashSet<>();
        this.funds = 4000;
    }

    public double getFunds() {
        return funds;
    }

    public HashSet<Stock> getPortfolio() {
        return portfolio;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
    public void setPortfolio(HashSet<Stock> portfolio) {
        this.portfolio = portfolio;
    }

    public abstract void sellTrades();
    public abstract void buyTrades();
}