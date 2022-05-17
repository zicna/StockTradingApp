package main.model.account;

import main.model.trade.Trade;

public class TFSA extends Account{

    public static final double BUY_FEE = 1.01;
    public static final double SELL_FEE = 0.99;

    public TFSA(){
        super();
    }
    public void setFunds(Trade trade) {
        switch(trade.getChoice()){
            case BUY: 
                this.funds -= trade.getStockPrice() * trade.getStockCount() * BUY_FEE;
                break;
            case SELL:
                this.funds += trade.getStockPrice() * trade.getStockCount() * SELL_FEE;
                break;
        }
    }
}