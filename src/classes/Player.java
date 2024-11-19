package classes;

import java.io.*;
import java.util.Scanner;

public class Player {

    public Player(Board brd){
        this.brd = brd;
        LoadTable();
    }

    private Scanner sc;
    private Board brd;
    private boolean isOver = false;
    private String nickName;
    private String[] top10Wins = new String[10];

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

    private void LoadTable() {
        File file = new File("Top10.dat");
        if (!file.exists()) {
            System.out.println("Top10.dat not found. Initializing default leaderboard.");
            for (int i = 0; i < top10Wins.length; i++) {
                top10Wins[i] = "No record";
            }
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            top10Wins = (String[]) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaveTable(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Top10.dat"))) {
            oos.writeObject(top10Wins);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printTop(){
        System.out.println("------ Top 10 ------");
        for(int i = 0; i < top10Wins.length; i++){
            System.out.println("| "+(i+1)+". "+top10Wins[i] + "       |");
        }
        System.out.println("|____________________|");
    }

    public void enterContinue(){
        System.out.println("Pressione enter para continuar...");
        sc = new Scanner(System.in);
        sc.nextLine();
    }
}