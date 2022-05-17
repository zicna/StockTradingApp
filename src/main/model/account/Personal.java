package main.model.account;


import main.model.trade.Trade;

public class Personal extends Account{

    public Personal(){
        super();
    }
    
    @Override
    public void sellTrades(Trade trade) {
        System.out.println("sell trades");
        this.setPortfolio(trade);
        this.setFunds(trade);
    }
    
    @Override
    public void buyTrades(Trade trade) {
        this.setPortfolio(trade);
        this.setFunds(trade);
    }
}

