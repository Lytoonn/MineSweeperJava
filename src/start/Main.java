package start;

import classes.Board;
import classes.Player;

import java.util.Scanner;

public class Main {

    private static Player plr;
    private static Board brd;

    public static void main(String[] args) {
        brd = new Board();
        plr = new Player(brd);
        Scanner sc = new Scanner(System.in);

        while(true) {
            String aux = null;
            int choice;

            InitialMenu();
            do{
                aux = sc.nextLine();
                try{
                    choice = Integer.parseInt(aux);
                }catch(NumberFormatException e){
                    System.out.println("Please enter a valid Input.");
                    choice = -1;
                }
            }while(choice <= 0 && choice > 4);

            switch(choice) {
                case 1 -> plr.game();
                case 2 -> {
                    plr.printTop();
                    plr.enterContinue();
                }
                case 3 -> credits();
                case 4 -> System.exit(0);
            }
        }
    }

    private static void InitialMenu(){
        System.out.print("""
                 ____________________
                | MineSweeper Game   |
                |--------------------|
                | 1. New Game        |
                | 2. Last 10 Wins    |
                | 3. Credits         |
                | 4. Exit Game       |
                |____________________|
                """);
    }

    private static void credits(){
        System.out.print("""
                 ____________________
                | MineSweeper Game   |
                |--------------------|
                |       Made By      |
                |       Lytoon       |
                |____________________|
                """);
        plr.enterContinue();
    }
}