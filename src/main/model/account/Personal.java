package main.model.account;


import main.model.trade.Trade;

public class Personal extends Account{

    private static final double SELL_FEE = 0.95;

    public Personal(){
        super();
    }
    public void setFunds(Trade trade) {
        switch(trade.getChoice()){
            case BUY: 
                this.funds -= trade.getStockPrice() * trade.getStockCount();
                break;
            case SELL:
                this.funds += trade.getStockPrice() * trade.getStockCount() * SELL_FEE;
                break;
        }
    }
}

