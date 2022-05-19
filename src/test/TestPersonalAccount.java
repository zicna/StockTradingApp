package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import main.model.account.Account;
import main.model.account.Personal;
import main.model.trade.Trade;
import main.model.trade.Trade.Stock;

public class TestPersonalAccount {

    @Test
    public void testSetFunds(){
        Account personal = new Personal();
        Trade trade = new Trade(Trade.Stock.AAPL, 25.00, 4, Trade.Choice.BUY);
        personal.setFunds(trade);
        assertEquals(3900.00, personal.getFunds());
    }
    
}
