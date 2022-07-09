package KnightsTour_C;

import java.util.*;

public class KnightHeuristic {

    //instance variables
    private int[][] board;
    final int NUM_ROWS=8, NUM_COLS=8;
    private int moveCounter=1;
    private int locRow, locCol;
    private boolean done;
    private Random randNum = new Random();


    //shift arrays used to move knight around board
    private final int[] H_SHIFT={2,1,-1,-2,-2,-1,1,2};
    private final int[] V_SHIFT={-1,-2,-2,-1,1,2,2,1};

    private final int[][] ACCESS ={{2,3,4,4,4,4,3,2},
            {3,4,6,6,6,6,4,3},
            {4,6,8,8,8,8,6,4},
            {4,6,8,8,8,8,6,4},
            {4,6,8,8,8,8,6,4},
            {4,6,8,8,8,8,6,4},
            {3,4,6,6,6,6,4,3},
            {2,3,4,4,4,4,3,2} };

    public KnightHeuristic(){
        //randomizes starting position
        locRow=randNum.nextInt(7);
        locCol=randNum.nextInt(7);

        //board is initialized
        board=new int[NUM_ROWS][NUM_COLS];
        //piece is placed with values the user entered. if the values are too big or small, the program instantly ends
        placePiece(locRow,locCol);
        //initialize boolean done as false for use later
        done = false;
    }

    public void runKnight(){
        //while done is false, this will keep running
        while(!done){
            //makeMove will keep running while done is false
            makeMove();
            //prints out the moves each time
            printBoard();
        }
        //tells the user the number of moves that were made
        System.out.printf("\nA total of %d moves was made this tour\n", moveCounter-1);
    }

    public boolean makeMove(){
        boolean moveMade=false;
        //get all available moves and then sorts moves by lowest to highest
        ArrayList<Integer> moves=getAvailableMoves(locRow,locCol);
        Collections.sort(moves);
        //pick a move - pick first available or lowest in the list
        if(moves.size()>0){
            int move=moves.get(0);
            //place piece
            moveMade=placePiece(locRow+V_SHIFT[move],locCol+H_SHIFT[move]);
            locRow=locRow+V_SHIFT[move];
            locCol=locCol+H_SHIFT[move];
        }
        //if no more moves are available, done will be true and the system will tell the user no moves are available
        else{
            done=true;
            System.out.println("\nThere are no more moves available");
        }
        return  moveMade;
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
            //if 64 moves were made, then the done is true and the program will end
            if(moveCounter==64) {
                done = true;
            }
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
