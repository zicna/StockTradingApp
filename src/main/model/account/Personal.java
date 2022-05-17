package main.model.account;


import main.model.trade.Trade;

public class Personal extends Account{

    public Personal(){
        super();
    }
    
    @Override
    public void sellTrades() {
       System.out.println("sell trades");
        
    }

    @Override
    public void buyTrades(Trade trade) {
        // * it should never get here with invalid funds 
        // if(trade.getStockPrice() * trade.getStockCount() > this.getFunds()){
        //    System.out.println("not enough funds for this trade ");
        // }
        this.setPortfolio(trade.getStock(), trade.getStockCount());
        this.setFunds(trade);
    }
}

