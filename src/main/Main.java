package main;

import java.util.Scanner;

import main.model.account.Account;
import main.model.account.Personal;
import main.model.account.TFSA;
import main.utils.Color;

public class Main{
    public static void main(String[] args) {
        Main.welcome();
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
}