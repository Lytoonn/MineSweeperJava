package start;

import classes.Board;
import classes.Player;

public class Main {
    public static void main(String[] args) {
        Board brd = new Board();
        Player plr = new Player(brd);

        plr.game();
    }

    private void InitialMenu(){
        System.out.print(" ____________________\n");
        System.out.print("| MineSweeper Game   |\n");
        System.out.print("|--------------------|\n");
        System.out.print("| 1. New Game        |\n");
        System.out.print("| 2. Last 10 Wins    |\n");
        System.out.print("| 3. Credits         |\n");
        System.out.print("| 4. Exit Game       |\n");
        System.out.print("|____________________|\n");
    }
}
