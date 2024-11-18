package classes;

import java.util.Scanner;

public class Player {

    public Player(Board brd){
        this.brd = brd;
    }

    private Scanner sc;
    private Board brd;
    private boolean isOver = false;
    private String nickName;

    public void game(){
        sc = new Scanner(System.in);
        getNickName();
        while(!isOver){
            brd.DisplayBoard(false);

            int row = getRow()-1;
            int col = getCol()-1;

            if(row < 0 || row >=9 || col < 0 || col >=9){
                System.out.println("Invalid Movement.");
                continue;
            }

            if(!brd.revealCells(row,col)){
                System.out.println("You hitted a Bomb! Game Over.");
                brd.DisplayBoard(true);
                isOver = true;
            }
            else if(brd.gameWon()) {
                System.out.println("You won!");
                brd.DisplayBoard(true);
                isOver = true;
            }
        }
        sc.close();
    }

    private String getNickName(){
        System.out.print("Enter your nickname: ");
        return sc.nextLine();
    }

    private int toInt(String num){
        try{
            return Integer.parseInt(num);
        }catch(NumberFormatException e){
            return -1;
        }
    }

    private int getRow(){
        System.out.print("\nEnter the row number: ");
        int aux = toInt(sc.next());
        return aux;
    }

    private int getCol(){
        System.out.print("Enter the col number: ");
        int aux = toInt(sc.next());
        return aux;
    }

    //PLAYER COMMANDS

}