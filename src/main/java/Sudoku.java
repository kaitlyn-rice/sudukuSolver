import java.util.*;

class Sudoku {
    // just in case you need tis method for testing
    public static void main(String[] args) {
        char[][] puzzle = SudokuP.puzzle();
    }

    // print out one solution of the given puzzle
    // accepted parameter(s): 2D char array representing a sudoku board
    public static void solve(char[][] puzzle) {
        if(puzzle == null || puzzle.length == 0)
            return;
        if (solveSudoku(puzzle)) {
            /* print out one possible solution */
            // your code goes here

            
        } else {
            System.out.print("This puzzle is not solvable.");
        }
    }
    
    // solve a given sudoku puzzle board
    // additionally, return true if solvable; otherwise return false
    // accepted parameter(s): a 2D char array representing a sudoku board
    // return type: boolean
    // NOTE: you can assume that only valid sudoku board will be given as parameters to this method
    public static boolean solveSudoku(char[][] puzzle){
        // remove this line
        return true;
    }   

    // check if a given sudoku puzzle board is valid or not
    // return true if valid; otherwise return false
    // accepted parameter(s): a 2D char array representing a sudoku board
    // return type: boolean
    public static boolean check(char[][] puzzle) {
        // remove this line
        return true;
    }

    // check if the specified area of the given sudoku board is valid 
    // valid - following the 3 rules of sudoku
    // accepted parameters: puzzle - standing for a sudoku board in the representation of a 2D char array
    //                      four integers x1, y1, x2, y2
    //                      (x1, y1) stands for the top left corner of the area (inclusive)
    //                       x1 - row index; y1 - column index
    //                      (x2, y2) stands for the bottom right corner of the area (inclusive)
    //                       x2 - row index; y2 - colum index
    // return data type: boolean
    // if the specified area is valid, return true; otherwise false
    // e.g.1, isParticallyValid(puzzle,0,0,0,8) is used to check the 1st row of puzzle
    // e.g.2, isParticallyValid(puzzle,0,0,8,0) is used to check the 1st column of puzzle
    // e.g.3, isParticallyValid(puzzle,0,0,2,2) is used to check the top left 3*3 area
    // NOTE that this method will only be applied to every row, every column, and every 3*3 small areas (9 small areas in total)
    public static boolean isParticallyValid(char[][] puzzle, int x1, int y1,int x2,int y2){
        ArrayList<Character> givens = new ArrayList<Character>();
        //creates a carrier array list
        for(int i = x1; i <= x2; i++){
            //runs through the x values
            for(int j = y1; j <= y2; j++){
                //runs through the y values
                for(int n = 0; n<givens.size(); n++){
                    if(puzzle[i][j] == givens.get(n)) return false;
                    //checks to see if the value is the same as any of the other
                    //values that have already been checked and returns false if it is
                }
                givens.add(puzzle[i][j]);
                //adds the checked value into the array list to be checked against the
                //rest of the values
            }
        }
        return true; // returns if none of the numbers match
    }
    
    // check whether putting a digit c at the position (x, y) in a given sudoku board
    // will make the board invalid
    // accepted parameters: puzzle - standing for a sudoku board in the representation of a 2D char array
    //                      two integers x, y
    //                      x - row index; y - column index
    //                      c - a digit in the form of char to put at (x, y)
    // return data type: boolean
    // if putting c in puzzle is a valid move, return true; otherwise false
    public static boolean isSpotValid(char[][] puzzle, int row, int col, char c){
        char [][] temp = deepCopy(puzzle);
        temp[row][col] = c;
        if(isParticallyValid(temp, row, 0, row, 8)&&isParticallyValid(temp, 0, col, 8,col)){
           int cube1 = row/3;
           cube1 = (cube1+2*cube1);
           int cube2 = col/3;
           cube2 = (cube2+2*cube2);
           if(isParticallyValid(temp, cube1, cube2, cube1+2, cube2+2)){
               return true;
            }
           
        }
        return false;
    }
    
    public static  char [][] deepCopy(char[][] puzzle){
        char [][] temp = new char [puzzle.length][puzzle[0].length];
        for(int i = 0; i < puzzle.length; i++){
            for(int j = 0; j < puzzle[i].length; j++){
                temp[i][j] = puzzle[i][j];
            }
        }
        return temp;
    }//returns a deep copy of the puzzle as a temp value
}
