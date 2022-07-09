package KnightsTour_7_23.BruteForceC;

import java.util.Random;

public class KnightC {
    //instance variables
    Random randomNumbers = new Random();
    //constants for board initialization
    final int NUM_ROWS = 8, NUM_COLS = 8;
    int board[][] = new int[NUM_ROWS][NUM_COLS];

    // move shifts for the knight movement
    private final int[] H_SHIFT = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int[] V_SHIFT = {-1, -2, -2, -1, 1, 2, 2, 1};

    //array used to track the total amount of times a tour has been completed
    private int totals[] = new int[65];

    //the method which will run everything
    public void runKnight() {
        //creating variable for knight position
        int locRow;
        int locCol;

        //test positions for use later
        int testRow;
        int testColumn;

        //to help determine if a fulltour has been completed
        boolean fullTour = false;

        while (!fullTour) {
            clearBoard();
            int numMoves = 0; // the current move number

            // randomize knight placement position
            locRow = randomNumbers.nextInt(8);
            locCol = randomNumbers.nextInt(8);

            //places the knight at specified location and then increments numMoves
            board[locRow][locCol] = ++numMoves;

            //boolean used to see if the program has finished or not. If theres no more moves available, then done becomes true later
            boolean done = false;

            // continue until knight can no longer move
            while (!done) {
                //boolean used to determine if a move was valid or not
                boolean validMove = false;

                //randomizes the move that will be selected
                int move = randomNumbers.nextInt(8);

                //checks all moves to find a valid move
                for (int count = 0; count < 8 && !validMove; ++count) {
                    testRow = locRow + V_SHIFT[move];
                    testColumn = locCol + H_SHIFT[move];
                    validMove = validMove(testRow, testColumn);

                    //if validMove, then the knight is moved and numMoves gets incremented
                    if (validMove) {
                        locRow = testRow;
                        locCol = testColumn;
                        board[locRow][locCol] = ++numMoves;
                    }
                    move = (move + 1) % 8;
                }
                //if no more moves available, then done is true and the tour ends
                if (!validMove)
                    done = true;

                    //if 64 moves were made, then the tour ends and the program ends as well
                else if (numMoves == 64) {
                    done = true;
                    fullTour = true;
                }
            }
            ++totals[numMoves]; // update the statistics
        }
        printResults();
    }

    // checks for valid move
    public boolean validMove(int row, int col) {
        boolean validTop = row >= 0;
        boolean validBottom = row < board.length;
        boolean validLeft = col >= 0;

        return validTop && validBottom && validLeft && col < board[row].length && board[row][col] < 1;
    }

    public void printResults() {
        int totalTours = 0;

        /**Prints out a table type format for the number of times
         * a tour ended on that specific number.
         * if theres a number next to 64, then that means
         * it ended on 64 moves however much that number is
         * */
        for (int row = 1; row < 33; row++) {
            System.out.printf("%-10d%-9d%-10d%d\n", totals[row], row,
                    totals[row + 32], (row + 32));

            totalTours += totals[row] + totals[row + 32];
        }

        System.out.printf("\nIt took %d tries to get a full tour\n",
                totalTours);
    }

    //resets the board and is called on after a tour has failed
    public void clearBoard() {
        for (int j = 0; j < board.length; j++)
            for (int k = 0; k < board[j].length; k++)
                board[j][k] = 0;
    }
}

