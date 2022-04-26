package main.model.account;

import java.util.ArrayList;

import main.model.stock.Stock;

public abstract class Account{
    private double funds;
    private ArrayList<Stock> portfolio;

    public Account(double funds){
        portfolio = new ArrayList<>();
        this.funds = funds;
    }

    public double getFunds() {
        return funds;
    }

    public ArrayList<Stock> getPortfolio() {
        return portfolio;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
    public void setPortfolio(ArrayList<Stock> portfolio) {
        this.portfolio = portfolio;
    }

    public abstract void sellTrades();
    public abstract void buyTrades();
}