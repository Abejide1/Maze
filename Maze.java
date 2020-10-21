/*
 * Write a program that contains a recursive method (mazeTraversal) 
 * to walk through the maze. The method should receive as arguments 
 * a 12 by 12 character array representing the maze and the current 
 * location in the maze * 
 */
import java.util.Scanner;

public class Maze {

    //declare and initialize vairable
    private static int X_axis = 2;
    private static int Y_axis = 0;
    private static int move = 0;
    static Scanner scan = new Scanner(System.in);
    private static final char[][] maze = new char[][]{
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#'},
        {'.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#'},
        {'#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#'},
        {'#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '.'},
        {'#', '#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
        {'#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
        {'#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
        {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    /**
     * traverse the maze
     *
     * @param x axis position
     * @param new_maze
     * @param y current y position
     * @return
     */
    public boolean mazeTraversal(int x, int y, char[][] new_maze) {
        maze[x][y] = 'x';
        move++;

        printMaze();//prints maze first
        if ((x == X_axis) && (y == Y_axis) && (move > 1)) {
            System.out.println("begin");
            return false;
        } else if (terminateMaze(x, y) && move > 1) {
            System.out.println("Maze exited"); //check maze moves if are valid
            return true;
        } else {
            System.out.print("\nEnter ‘y’ to continue, ‘n’ to exit: ");

            char response = scan.next().charAt(0);//get user input

            if (response == 'n') {
                System.exit(0); // exit
            }

            int counter = 0;
            while (counter < 4) {
                switch (counter) {
                    case 0://right
                        if (checkMove(x + 1, y)) {
                            if (mazeTraversal(x + 1, y, new_maze)) {
                                return true;
                            }

                        }
                        break;
                    case 1: //down
                        if (checkMove(x, y - 1)) {
                            if (mazeTraversal(x, y - 1, new_maze)) {
                                return true;
                            }

                        }
                        break;
                    case 2://left
                        if (checkMove(x - 1, y)) {
                            if (mazeTraversal(x - 1, y, new_maze)) {
                                return true;
                            }

                        }
                        break;

                    case 3://up
                        if (checkMove(x, y + 1)) {
                            if (mazeTraversal(x, y + 1, new_maze)) {
                                return true;
                            }

                        }
                        break;

                }
                counter++;
            }
            //failed trackback moves
            new_maze[x][y] = 'B';
            return false;
        }
    }

    /**
     * check moves
     */
    public void possibleMove() {
        boolean result = mazeTraversal(X_axis, Y_axis, maze);
        if (!result) {
            System.out.println("maze has no solution");
        }
    }

    /**
     * check/validate move
     *
     * @param row
     * @param column
     * @return
     */
    public static boolean checkMove(int row, int column) {
        return (row >= 0) && (row <= 11) && (column >= 0) && (column <= 11) && (maze[row][column] == '.');
    }

    /**
     * check maze exit
     *
     * @param row
     * @param column
     * @return
     */
    public static boolean terminateMaze(int row, int column) {
        return (row == 0) || (row == 11) || (column == 0) || (column == 11);
    }

    //display the maze    
    public void printMaze() {
        int row = 0;
        while (row < 12) {
            int column = 0;
            while (column < 12) {

                System.out.print(" " + maze[row][column]);
                column++;
            }
            System.out.println();//create new line/row
            row++;
        }
    }

    public static void main(String args[]) {
        Maze game = new Maze();
        game.possibleMove();
    }
}
