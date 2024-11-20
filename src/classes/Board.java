package classes;

import java.util.Random;

public class Board {

    public Board() {
        StartBoard();
        Mines();
        Numbers();
    }

    private char[][] board = new char[9][9];
    private boolean[][] mines = new boolean[9][9];
    private boolean[][] revealed = new boolean[9][9];

    private void StartBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = '*';
            }
        }
    }

    public void DisplayBoard(boolean showMines) {
        System.out.print("  ");
        for (int i = 0; i < 9; i++) {
            System.out.print("||"+(i+1));
        }
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print((char)(i+'A')+" | ");
            for (int j = 0; j < 9; j++) {
                if(revealed[i][j]) {
                    System.out.print(board[i][j] + "  ");
                }
                else if(mines[i][j] && showMines){
                    System.out.print("b  ");
                }
                else{
                    System.out.print("*  ");
                }
            }
            System.out.println();
        }
    }

    private void Mines() {
        Random rnd = new Random();
        int placedMines = 0;

        while (placedMines < 10) {
            int row = rnd.nextInt(9);
            int col = rnd.nextInt(9);

            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }

    private char countMines(int row, int col) {
        int count = 0;
        int[] dir = {-1, 0, 1};

        for (int dr : dir) {
            for (int dc : dir) {
                int newRow = row + dr;
                int newCol = col + dc;

                if (isValid(newRow, newCol) && mines[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count == 0 ? '0' : (char) (count+'0');
    }

    private void Numbers() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!mines[i][j]) {
                    board[i][j] = countMines(i, j);
                }
            }
        }
    }

    public boolean revealCells(int row, int col) {
        if (mines[row][col]) {
            return false;
        }
        if (!revealed[row][col]) {
            revealed[row][col] = true;

            if (board[row][col] == '0') {
                int[] dir = {-1, 0, 1};

                for (int dr : dir) {
                    for (int dc : dir) {
                        int newRow = row + dr;
                        int newCol = col + dc;
                        if (isValid(newRow, newCol))
                            revealCells(newRow, newCol);
                    }
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < 9 && col >= 0 && col < 9;
    }

    public boolean gameWon(){
        int revealedCells = 0;
        int total = 9*9;

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(revealed[i][j])
                    revealedCells++;
            }
        }
        return revealedCells == total - 10;
    }
}