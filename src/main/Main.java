package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import main.model.account.Account;
import main.model.account.Personal;
import main.model.account.TFSA;
// import main.model.stock.Stock;
// import main.model.stock.StockEnum;
import main.model.trade.Trade;
import main.model.trade.Trade.Stock;
import main.model.trade.Trade.Choice;
import main.utils.Color;

public class Main{

    static Scanner scanner = new Scanner(System.in);
    static Account account;
    public static void main(String[] args) {
        welcome();
       
        switch(accountChoice()){
            case "a": account = new Personal(); break;
            case "b": account = new TFSA(); break;
        }

        accCreateAnnouncement(account);
        
        System.out.print("Enter anything to start trading: ");
        scanner.nextLine();
        for (int day = 1; day < 2161; day++) {
            diplayStockPrice(day);

            String choice = buyOrSell();
            String stockName = chooseAStock();
            int stockCount = numberOfStocks();

            double stockPrice = Double.parseDouble(getStock(stockName, day));
            // Stock stock = new Stock(stockName, stockPrice);

            switch(choice){
                case "BUY": 
                    if (account.getFunds() < stockPrice * stockCount){
                        unsuccessfulTrade(account);
                        continue;
                    }else {
                        Trade trade = new Trade(
                            Stock.valueOf(stockName),
                            stockPrice, 
                            stockCount, 
                            Choice.valueOf(choice));
                        // account.addStock(stock, stockCount);
                        account.buyTrades(trade);
                        // account.setFunds(choice, stockPrice, stockCount);
                        // successfulTrade(account);
                    }
                    break;
                case "SELL":
                    if(account.getPortfolio().get(stockName) < stockCount){
                        unsuccessfulTrade(account);
                        continue;
                    }
                    // else{
                    //     account.removeStock(stock, stockCount);
                    //     account.setFunds(choice, stockPrice, stockCount);
                    //     successfulTrade(account);
                    // }
                break;
            }

            System.out.print("Enter " + Color.RED_BACKGROUND +  " exit " + Color.RESET + " to exit or anything else to continue: ");
            String procceed = scanner.nextLine();

            if(procceed.equals("exit")){
                break;
            }else{
                continue;
            }
        }
        
        scanner.close();
    }

    public static void welcome(){
        System.out.println("\n");
        System.out.println(Color.BLUE + "- PERSONAL: " + Color.YELLOW  + "Every sale made in a personal account is charged a 5% fee.\n");
        System.out.println(Color.BLUE + "- TFSA: " + Color.YELLOW  + "Every trade(buy/sell) made from a TFSA is charged a 1% fee.\n");
        System.out.println(Color.BLUE + "- Neither acount has a limit on the amount of trades that can be made.\n" + Color.RESET);
    }

    public static String accountChoice(){
        System.out.print("Respectively, type '" + Color.GREEN + "a" + Color.RESET + "' or '" + Color.GREEN + "b" + Color.RESET + "' to create a Personal account or TFSA: \t");
        String choice = scanner.nextLine();
        while(!(choice.equals("a") || choice.equals("b"))){
            System.out.print("Respectively, type '" + Color.GREEN + "a" + Color.RESET + "' or '" + Color.GREEN + "b" + Color.RESET + "' to create a Personal account or TFSA: \t");
            choice = scanner.nextLine();
        }
        return choice;
    }

    public static void diplayStockPrice(int day){
        System.out.println("\n");
        System.out.println(Color.RED + "\t\tDAY: " + Color.GREEN + day + Color.RED + " PRICES" + Color.RESET + "\n");
        System.out.println(Color.GREEN + "\t" + Stock.AAPL.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(Stock.AAPL.toString(), day) + Color.RESET);
        System.out.println(Color.GREEN + "\t" + Stock.FB.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(Stock.FB.toString(), day)+ Color.RESET);
        System.out.println(Color.GREEN + "\t" + Stock.GOOG.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(Stock.GOOG.toString(), day)+ Color.RESET);
        System.out.println(Color.GREEN + "\t" + Stock.TSLA.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(Stock.TSLA.toString(), day)+ Color.RESET);
    }

    public static String buyOrSell(){
        System.out.print("\n\nWould you like to '" +  Color.GREEN + "BUY" + Color.RESET + "' or '" +  Color.GREEN + "SELL" + Color.RESET + "'?\t ");
        String input = scanner.nextLine();
        while(!(input.equals("BUY") || input.equals("SELL"))){
            System.out.print("\n\nWould you like to '" +  Color.GREEN + "BUY" + Color.RESET + "' or '" +  Color.GREEN + "SELL" + Color.RESET + "'?\t ");
            input = scanner.nextLine();
        }
        return input;
    }

    public static String chooseAStock(){
        System.out.print("Please choose a stock to trade:\t\t ");
        String stock = scanner.nextLine();
        while(!(stock.equals("AAPL") || stock.equals("FB") || stock.equals("GOOG") || stock.equals("TSLA"))){
            System.out.print("Please choose a stock to stade:\t\t ");
            stock = scanner.nextLine();
        }
        return stock;
    }

    public static int numberOfStocks(){
        System.out.print("Enter number of stocks:\t\t\t ");
        while(!scanner.hasNextInt()){
            System.out.print("Enter number of stocks:\t\t\t ");
            scanner.next();
        }
        int stockNumber = scanner.nextInt();
        scanner.nextLine();
        return stockNumber;
    }

    public static void unsuccessfulTrade(Account account){
        System.out.print("\nThe trade was " + Color.RED_BACKGROUND + " UNSUCCESSFUL " + Color.RESET + ". Here is your portfolio: \n");
        account.print();
    }

    public static void successfulTrade(Account account){
        System.out.print("\nThe trade was " + Color.GREEN_BACKGROUND + " SUCCESSFUL " + Color.RESET + ". Here is your portfolio: \n");
        account.print();
    }


    public static <T extends Account> void accCreateAnnouncement(T source){
        if(!(source instanceof Account)){
            throw new IllegalArgumentException("arguments must be instance of Account class");
        }
            System.out.println("\nYou created a " + Color.YELLOW + source.getClass().getSimpleName() + Color.RESET + " account. Your account balance is " + Color.GREEN + source.getFunds() + Color.RESET + "\n");
    }

    public static Path getPath(String stock){
        try {
            return Paths.get(Thread.currentThread().getContextClassLoader().getResource("main/data/"+stock+".csv").toURI());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String getStock(String stock, int day){
        Path path = getPath(stock);
        try {
            return Files.lines(path)
                    .skip(1)
                    .filter(line -> Integer.valueOf(line.split(",")[0]) == day)
                    .map(line -> line.split(",")[1])
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}
