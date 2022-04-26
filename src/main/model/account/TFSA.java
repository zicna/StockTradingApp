package main.model.account;

public class TFSA extends Account{
    public TFSA(){
        super();
    }
    
    @Override
    public void sellTrades() {
       System.out.println("sell trades");
        
    }

    @Override
    public void buyTrades() {
        System.out.println("buy trades");
        
    }
}