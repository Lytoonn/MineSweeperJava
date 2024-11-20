package classes;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Player {

    public Player(Board brd){
        this.brd = brd;
        LoadTable();
    }

    private Scanner sc;
    private final Board brd;
    private boolean isOver = false;
    private String nickName;
    private String[] top10Wins = new String[10];

    public void game(){
        sc = new Scanner(System.in);
        getNickName();
        while(!isOver){
            brd.DisplayBoard(false);
            String cmd = sc.nextLine();

            Commands(cmd);
        }
    }

    private String getNickName(){
        System.out.print("Enter your nickname: ");
        return sc.nextLine();
    }

    private int[] parseCoordinates(String rowToken, String colToken){
        try{
            rowToken = rowToken.toUpperCase();
            int row = (int) rowToken.charAt(0) - '@';
            row--;
            int col = toInt(colToken)-1;
            if(row<0 || row >=9 || col<0 || col >=9){
                System.out.println("Invalid Movement.");
                return null;
            }
            return new int[]{row,col};
        }catch (NumberFormatException e){
            System.out.println("Invalid number format. Row and column must be integers.");
            return null;
        }
    }

    private void Commands(String command){
        String[] tokens = command.split(" ");
        switch(tokens[0]){
            case "open" -> {
                int[] coords = parseCoordinates(tokens[1], tokens[2]);
                if(coords != null){
                    brd.revealCells(coords[0],coords[1]);
                    if(!brd.revealCells(coords[0],coords[1])){
                        System.out.println("You hit a Bomb! Game Over.");
                        brd.DisplayBoard(true);
                        isOver = true;
                        enterContinue();
                    }
                    else if(brd.gameWon()) {
                        System.out.println("You won!");
                        brd.DisplayBoard(true);
                        isOver = true;
                        enterContinue();
                    }
                }
            }
            case "flag" -> System.out.println("Flagging");
            case "hint" -> System.out.println("Hint");
            case "cheat" -> System.out.println("Cheat");
        }
    }

    private int toInt(String num){
        try{
            return Integer.parseInt(num);
        }catch(NumberFormatException e){
            return -1;
        }
    }

    private void LoadTable() {
        File file = new File("Top10.dat");
        if (!file.exists()) {
            Arrays.fill(top10Wins, "No record");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            top10Wins = (String[]) ois.readObject();
        } catch (Exception _) {}
    }

    private void SaveTable(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Top10.dat"))) {
            oos.writeObject(top10Wins);
        }catch(Exception _){}
    }

    public void printTop(){
        System.out.println("------ Top 10 ------");
        for(int i = 0; i < top10Wins.length; i++){
            System.out.println("| "+(i+1)+". "+top10Wins[i] + "       |");
        }
        System.out.println("|____________________|");
        enterContinue();
    }

    public void enterContinue(){
        System.out.println("Press Enter to continue");
        try{
            System.in.read()
        }
        catch(Exception _){}
    }
}