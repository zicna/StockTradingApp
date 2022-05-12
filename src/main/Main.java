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
import main.model.stock.Stock;
import main.model.stock.StockEnum;
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
        
        System.out.print("Enter anything to start trading...");
        scanner.nextLine();
        for (int day = 1; day < 2161; day++) {
            diplayStockPrice(day);

            String first = buyOrSell();
            String second = chooseAStock();
            int third = numberOfStocks();

            double stockPrice = Double.parseDouble(getStock(second, day));
            Stock stock = new Stock(second, stockPrice);
            
            if (first.equals("buy")){
                if (account.getFunds() < stockPrice * third){
                    unsuccessfulTrade(account);
                    continue;
                }else {
                    account.addStock(stock, third);
                    account.setFunds(first, stockPrice, third);
                }
            }else if(first.equals("sell")){
                if(account.getPortfolio().get(second) < third){
                    unsuccessfulTrade(account);
                    continue;
                }else{
                    account.removeStock(stock, third);
                    account.setFunds(first, stockPrice, third);
                }
            }

            account.print();

            System.out.print("Press 'exit' to exit this app or anything else to continue: ");
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
        System.out.print("Respectively, type 'a' or 'b' to create a Personal account of TFSA: ");
        String choice = scanner.nextLine();
        while(!(choice.equals("a") || choice.equals("b"))){
            System.out.print("Respectively, type 'a' or 'b' to create a Personal account of TFSA: ");
            choice = scanner.nextLine();
        }
        return choice;
    }

    public static void diplayStockPrice(int day){
        System.out.println("\n");
        System.out.println(Color.RED + "\t\tDAY: " + Color.GREEN + day + Color.RED + " PRICES" + Color.RESET + "\n");
        System.out.println(Color.GREEN + "\t" + StockEnum.AAPL.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(StockEnum.AAPL.toString(), day) + Color.RESET);
        System.out.println(Color.GREEN + "\t" + StockEnum.FB.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(StockEnum.FB.toString(), day)+ Color.RESET);
        System.out.println(Color.GREEN + "\t" + StockEnum.GOOG.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(StockEnum.GOOG.toString(), day)+ Color.RESET);
        System.out.println(Color.GREEN + "\t" + StockEnum.TSLA.toString() + Color.RESET + " " + Color.BLUE + "\t" + getStock(StockEnum.TSLA.toString(), day)+ Color.RESET);
    }

    public static String buyOrSell(){
        System.out.print("\n\nWould you like to 'buy' or 'sell'? ");
        String input = scanner.nextLine();
        while(!(input.equals("buy") || input.equals("sell"))){
            System.out.print("\n\nWould you like to 'buy' or 'sell'?");
            input = scanner.nextLine();
        }
        return input;
    }

    public static String chooseAStock(){
        System.out.print("Please choose a stock to stade (AAPL, FB, GOOG, or TSLA): ");
        String stock = scanner.nextLine();
        while(!(stock.equals("APPL") || stock.equals("FB") || stock.equals("GOOG") || stock.equals("TSLA"))){
            System.out.print("Please choose a stock to stade (AAPL, FB, GOOG, or TSLA): ");
            stock = scanner.nextLine();
        }
        return stock;
    }

    public static int numberOfStocks(){
        System.out.print("Enter number of stocks: ");
        while(!scanner.hasNextInt()){
            System.out.print("Enter number of stocks: ");
            scanner.next();
        }
        int stockNumber = scanner.nextInt();
        scanner.nextLine();
        return stockNumber;
    }

    public static void unsuccessfulTrade(Account account){
        System.out.print("\nThe trade was " + Color.RED + "unsuccessful" + Color.RESET + ". Here is your portfolio: \n");
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
