package start;

import classes.Board;
import classes.Player;

import java.util.Scanner;

public class Main {

    private static Player plr;
    private static Board brd;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            brd = new Board();
            plr = new Player(brd);
            String aux = null;
            int choice = -99;

            InitialMenu();

            aux = sc.nextLine();
            try {
                choice = Integer.parseInt(aux);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Input.");
                choice = -1;
            }

            switch(choice) {
                case 1 -> plr.game();
                case 2 -> plr.printTop();
                case 3 -> credits();
                case 4 -> System.exit(0);
                default -> {
                    System.out.println("Please enter a valid Input.");
                    plr.enterContinue();
                }
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