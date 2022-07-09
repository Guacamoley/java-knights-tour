package KnightsTour_7_23;

public class RecursiveKnightsTour {

    //instance variables
    private int board[][];
    private final int BOARD_SIZE = 8, NUM_MOVES=8;
    private final int[] H_SHIFT={2,1,-1,-2,-2,-1,1,2};
    private final int[] V_SHIFT={1,2,2,1,-1,-2,-2,-1};

    public RecursiveKnightsTour(){
        //instantiating the board
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        //for statements to set cell value
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int x = 0; x < BOARD_SIZE; x++)
                //sets every value in the cell a minimum value which means the cell has not been visited
                board[i][x] = Integer.MIN_VALUE;
    }

    public void solveKnightTour(){
        //starting position of the knight. [0][0] is the first step
        board[0][0] = 1;

        //calls the solve method with the next count with the coordinates of (0,0)
        if(solve (2,0,0)){
            printSolution();
        }
        else{
            //if no solution was found, then it returns a sout statement
            System.out.println("No solution found");
        }
    }

    private boolean solve(int count, int x, int y) {

        //returns true if the knight has visited every cell because no cell is a min value anymore
        if(count==BOARD_SIZE*BOARD_SIZE+1){
            return true;
        }
        //try to make a move with the following possible shift values in parallel
        for(int i=0; i<NUM_MOVES; i++){
            int nextX = x + H_SHIFT[i];
            int nextY = y + V_SHIFT[i];

            //if a move is valid
            if(isValid(nextX,nextY)){
                //the board count is moved to the updated coordinate
                board[nextX][nextY] = count;

                //recursive if function to solve the next move by updating the count and cords
                if(solve(count+1,nextX,nextY)){
                    return true;
                }
                //sets the previous move as min value to try another move
                board[nextX][nextY] = Integer.MIN_VALUE;
            }
        }
        //if there is no solution, the method will return false
        return false;
    }

    private boolean isValid(int x, int y) {
        //checks if a move is valid. If the cord is smaller or bigger than the array, it'll return false
        if(x<0 || x >= BOARD_SIZE) return false;
        if(y<0 || y >= BOARD_SIZE) return false;
        //if a move onto [x][y] is not empty or min value, then it returns false
        if(board[x][y] != Integer.MIN_VALUE) return false;

        //otherwise the move returns as true or valid
        return true;
    }

    private void printSolution() {
        //prints the board and every move made
        for(int i=0; i<BOARD_SIZE; i++) {
            for (int x = 0; x < BOARD_SIZE; x++)
                System.out.print(board[i][x] + " ");

            System.out.println();
        }
    }

}
