package projects;

import java.util.Scanner;

/*
 *@author Muhammed Enes Onal / 20170808007
 */

public class TicTacToe {

    public static void main(String[] args){
        System.out.println("Tic-Tac-Toe GAME");
        TicTacToe game = new TicTacToe();
        game.printBoard();

        System.out.println("Player will play first. Enter a index number.");
        Scanner scr = new Scanner(System.in);

        while(game.getWinner() == EMPTY){
            try{
                System.out.println("Player 1");
                game.putMark(scr.nextInt(),scr.nextInt());
                System.out.println("Player 2");
                game.putMark(scr.nextInt(),scr.nextInt());
                game.printBoard();
            }catch(IllegalArgumentException ex){
                System.out.println("Invalid board position, re-enter slot number.");
                continue;
            }

            if(game.getWinner() == X){
                System.out.println("Winner is Player 1!");
            }else if(game.getWinner() == O){
                System.out.println("Winner is Player 2!");
            }else{
                continue;
            }
        }
    }

    public static final int X = 1, O = -1, EMPTY = 0;
    private int[][] board;
    private int currentPlayer;

    public TicTacToe() {
        this.board = new int[3][3];
        this.currentPlayer = X;
    }

    private final static int[][][] winnerIndices = {
            {{0,0}, {0,1}, {0,2}},
            {{1,0}, {1,1}, {1,2}},
            {{2,0}, {2,1}, {2,2}},

            {{0,0}, {1,0}, {2,0}},
            {{0,1}, {1,1}, {2,1}},
            {{0,2}, {1,2}, {2,2}},

            {{0,0}, {1,1}, {2,2}},
            {{0,2}, {1,1}, {2,0}},
    };

    public void putMark(int i, int j) throws IllegalArgumentException {
        if ((i < 0) || (i > 2) || (j < 0) || (j > 2))
            throw new IllegalArgumentException("Invalid board position");
        if (board[i][j] != EMPTY)
            throw new IllegalArgumentException("Board position occupied");

        board[i][j] = currentPlayer;
        currentPlayer = -1 * currentPlayer;
    }

    public boolean isWinner(int player) {

        for(int[][] positions: winnerIndices){
            int sum = 0;
            for(int[] position: positions){
                sum += board[position[0]][position[1]];
            }
            if(sum == player *3)
                return true;
        }
        return false;
    }


    public int getWinner(){
        if (isWinner(X))
            return X;
        else if (isWinner(O))
            return O;
        else
            return EMPTY;
    }

    public void printBoard() {
        for(int[] line: this.board){
            for(int cell: line){
                if(cell == X){
                    System.out.print("X");
                }
                else if(cell == O){
                    System.out.print("O");
                }
                else if(cell == EMPTY){
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }


    public boolean isAvailablePositionExists(){
        for(int[] line: this.board){
            for(int cell: line){
                if(cell == 0)
                    return true;
            }
        }
        return false;
    }
}
