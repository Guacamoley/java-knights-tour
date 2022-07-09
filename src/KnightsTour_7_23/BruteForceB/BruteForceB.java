package KnightsTour_7_23.BruteForceB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BruteForceB {
    //instance variables used throughout the program
    private int[][] board;
    private int[] totals;
    final int NUM_ROWS=8, NUM_COLS=8;
    private int moveCounter=0;
    private int locRow, locCol;
    private boolean done;
    private Random randNum = new Random();

    //shift arrays used to move knight around board
    private final int[] H_SHIFT={2,1,-1,-2,-2,-1,1,2};
    private final int[] V_SHIFT={-1,-2,-2,-1,1,2,2,1};

    public BruteForceB(){
        locRow = randNum.nextInt(8);
        locCol = randNum.nextInt(8);
        //board is initialized
        board=new int[NUM_ROWS][NUM_COLS];
        totals=new int [65];
        //piece is placed with values the user entered. if the values are too big or small, the program instantly ends
        placePiece(locRow,locCol);
        //initialize boolean done as false for use later
        done = false;
    }

    public void runKnight() {
        for (int i = 0; i < 1000; i++) {

            done=false;
            while(!done){
                makeMove();
                clearBoard();
            }

        }
        printResult();
    }

    private void printResult() {
        for(int i=1; i<33; i++){
            /**Prints out a table type format for the number of times
             * a tour ended on that specific number.
             * if theres a number next to 64, then that means
             * it ended on 64 moves however much that number is
             * */
            System.out.printf("%-15d%-9d%-15d%d\n",totals[i],i,totals[i+32],(i+32));
        }
    }

    public boolean makeMove(){
        boolean moveMade=false;
        //get all available moves
        ArrayList<Integer> moves=getAvailableMoves(locRow,locCol);
        //pick a move - pick first available
        if(moves.size()>0){
            int move=moves.get(randNum.nextInt(moves.size()));
            //place piece
            moveMade=placePiece(locRow+V_SHIFT[move],locCol+H_SHIFT[move]);
            locRow=locRow+V_SHIFT[move];
            locCol=locCol+H_SHIFT[move];
        }
        //if no more moves are available, done will be true and the system will tell the user no moves are available
        if(moveCounter==64) {
            done = true;
            System.out.println("A full tour has been completed");
            ++totals[moveCounter];
        }
        else{
            done=true;
            System.out.println("\nThere are no more moves available");
        }
        return  moveMade;
    }

    public void clearBoard(){
        for(int i=0; i<board.length; i++)
            for(int x=0; x<board[i].length; x++)
                board[i][x] = 0;
    }

    //creates a new arraylist for every available move
    public ArrayList<Integer> getAvailableMoves(int row, int col){
        ArrayList<Integer> moves=new ArrayList<>();

        for(int i=0; i<H_SHIFT.length; i++){
            int rowOffset = V_SHIFT[i]+row;
            int colOffset = H_SHIFT[i]+col;
            if(validMove(rowOffset, colOffset)){
                moves.add(i);
            }
        }
        return moves;
    }

    //places a piece onto the board
    public boolean placePiece(int row, int col){
        boolean piecePlaced=false;
        //if a valid move was made, then moveCounter is increased
        if(validMove(row,col)){
            board[row][col]=moveCounter;
            moveCounter++;
            piecePlaced=true;
        }
        return piecePlaced;
    }

    //checks if a move is valid
    public boolean validMove(int row, int col){
        boolean validTop=row>=0;
        boolean validBottom=row<board.length;
        boolean validLeft=col>=0;

        return validTop && validBottom && validLeft && col<board[row].length && board[row][col]<1;
    }

    //prints the board
    public void printBoard(){
        for(int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
