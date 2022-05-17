package main.model.account;

import java.util.HashMap;

import main.model.trade.Trade;
import main.model.trade.Trade.Stock;
import main.model.trade.Trade.Choice;
import main.utils.Color;

public abstract class Account{
    double funds;
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
    
    public void setPortfolio(Trade trade) {
        switch(trade.getChoice()){
            case BUY: 
                this.portfolio.replace(trade.getStock(), (trade.getStockCount() + this.portfolio.get(trade.getStock())));
                break;
                case SELL:
                this.portfolio.replace(trade.getStock(), (trade.getStockCount() - this.portfolio.get(trade.getStock())));
                break;
        }
    }

    public void print(){
        System.out.println(Color.PURPLE + "\n\t********** P O R T F O L I O **********" + Color.RESET);
        System.out.println("\t\tStock" + "\tShares\n");
        
        portfolio.forEach((stock, value) -> {
            System.out.println("\t\t" + Color.BLUE + stock + Color.RESET + "\t" + Color.GREEN + value + Color.RESET);
        });

        System.out.println("\n\tFunds left\t" + Color.GREEN + "$" + this.getFunds() + Color.RESET);
        System.out.println(Color.PURPLE + "\n\t*************************************" + Color.RESET + "\n");
    }

    public abstract void setFunds(Trade trade);
}