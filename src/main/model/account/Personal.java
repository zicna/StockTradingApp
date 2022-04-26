package main.model.account;

public class Personal extends Account{

    public Personal(){
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

