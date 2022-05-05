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
        
        // System.out.println(getStock("AAPL", 1));


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
