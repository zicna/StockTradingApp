package main;

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
    public static void main(String[] args) {
        welcome();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("Respectively, type 'a' or 'b' to create a Personal account of TFSA: ");
            String accountCreate = scan.nextLine();

            if(accountCreate.equals("a")){
                Personal personal = new Personal();
                accCreateAnnouncement(personal);
                break;
            }else if(accountCreate.equals("b")){
                TFSA TFSA = new TFSA();
                accCreateAnnouncement(TFSA);
                break;
            }else {
            continue;
            }
        }
        
        System.out.print("Enter anything to start trading:");
        scan.nextLine();

        dailyStockPrices();

        for (int i = 0; i < 2160; i++) {
            System.out.println("DAY " + i);
            dailyStockPrices();
        }
        scan.close();
    }

    public static void welcome(){
        System.out.println("\n");
        System.out.println(Color.BLUE + "- PERSONAL: " + Color.YELLOW  + "Every sale made in a personal account is charged a 5% fee.\n");
        System.out.println(Color.BLUE + "- TFSA: " + Color.YELLOW  + "Every trade(buy/sell) made from a TFSA is charged a 1% fee.\n");
        System.out.println(Color.BLUE + "- Neither acount has a limit on the amount of trades that can be made.\n" + Color.RESET);
    }

    public static <T extends Account> void accCreateAnnouncement(T source){
        if(!(source instanceof Account)){
            throw new IllegalArgumentException("arguments must be instance of Account class");
        }
            System.out.println("\nYou created a " + Color.YELLOW + source.getClass().getSimpleName() + Color.RESET + " account. Your account balance is " + Color.GREEN + source.getFunds() + Color.RESET + "\n");
    }

    public static void dailyStockPrices(){

    //     Stock stock = new Stock();
    //     Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("src/main/data/" + "stock" + ".csv").toURI());
    // * try make this work with AAPL stock only
    // Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("src/main/data/AAPL.csv").toURI());
    Path path = Paths.get("src/main/data/AAPL.csv");
    try {
        Stream<String> stock = Files.lines(path);
        stock
            .map(line -> line.split(","))
            .filter(lineArray -> lineArray[0] == "2")
            .forEach(line -> System.out.print(line));
        
    } catch (Exception e) {
        //TODO: handle exception
    }
    }
}