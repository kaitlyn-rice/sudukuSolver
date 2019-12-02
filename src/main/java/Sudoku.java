import java.util.*;

class Sudoku {
    // just in case you need tis method for testing
    public static void main(String[] args) {
        char[][] puzzle = SudokuP.puzzle(); 
        
        solve(puzzle); 
    }

    // print out one solution of the given puzzle
    // accepted parameter(s): 2D char array representing a sudoku board
    public static void solve(char[][] puzzle) {
        if(puzzle == null || puzzle.length == 0)
            return;
        if (solveSudoku(puzzle)) {
            /* print out one possible solution */
            for(char [] x: puzzle){
                for(char c: x){
                    System.out.print(c +", "); //prints out each char in the 2D array with a , after 
                }
                System.out.println();
            }

            
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
         for (int row = 0; row < puzzle.length; row++){
            for(int col = 0; col < puzzle[row].length; col++){
               if (puzzle[row][col] == '.'){
                  for(char i = '1'; i <= '9'; i++){
                     puzzle[row][col] = i; //checks each possible number in every open spot
                     if(check(puzzle) && solveSudoku(puzzle)){ //recalls method to find a variation for the entire board and checks board
                        return true;
                     }
                     puzzle[row][col] = '.';                     
                  }
                  return false;
               }
            }
         }
         return true;
    }
    
    // check if a given sudoku puzzle board is valid or not
    // return true if valid; otherwise return false
    // accepted parameter(s): a 2D char array representing a sudoku board
    // return type: boolean
    public static boolean check(char[][] puzzle) {
        for(int place = 0; place <= 8; place++){
            if(!isParticallyValid(puzzle, place, 0, place, 8)){return false;}
            //checks rows
            if(!isParticallyValid(puzzle, 0, place, 8, place)){return false;}
            //checks cols
        }//checks that each of the rows and cols are valid
        for(int row =0; row <= 6; row+=3){
            for(int col = 0; col <= 6; col +=3){
                if(!isParticallyValid(puzzle,row, col, row+2, col+2)){return false;}
            }
        }//checks each of the cubes
        
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
                if(puzzle[i][j] != '.') givens.add(puzzle[i][j]);
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
           int tempRow = row-(row%3); // top left corner of the square
           int tempCol = col-(col%3);
           if (isParticallyValid(temp, tempRow, tempCol, tempRow+2, tempCol+2)) { //checks entire square
               return true;
           }
        }
        return false;
    }
    
    
    public static  char [][] deepCopy(char[][] puzzle){
        char [][] temp = new char [puzzle.length][puzzle[0].length];
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp[i].length; j++){
                temp[i][j] = puzzle[i][j];
            }
        }
        
        return temp;
    }//returns a deep copy of the puzzle as a temp value
    
}
