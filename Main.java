//Assignment 1
//Adam Myrick
//755293
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[][] board = new char[19][19];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '.';
            }
        }
        displayBoard(board);
        
        int currentPlayer = 0;
        while ((!hasPlayerWon(board, currentPlayer) && isBoardFull(board))) {
            currentPlayer++;
            if (currentPlayer % 2 != 0) {
                System.out.println("Player 1's turn!");
                System.out.print("Enter row: ");
                int p1Row = input.nextInt();
                System.out.print("Enter col: ");
                int p1Col = input.nextInt();
                if (isValidMove(board, p1Row, p1Col)) //Move to while loop that returns boolean to stop loop once valid?
                {
                    board[p1Row][p1Col] = '●';
                    displayBoard(board);
                } else {
                    currentPlayer--;
                }
            } else {
                System.out.println("Player 2's turn!");
                System.out.print("Enter row: ");
                int p2Row = input.nextInt();
                System.out.print("Enter col: ");
                int p2Col = input.nextInt();
                if (isValidMove(board, p2Row, p2Col)) {
                    board[p2Row][p2Col] = '○';
                    displayBoard(board);
                } else {
                    currentPlayer--;
                }
            }
        }
    }

    public static void displayBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(char[][] board, int row, int column) {
        if ((row < 0 || row > 18) || (column < 0 || column > 18)) {
            System.out.println("Enter a number greater than 0 and less than 19.");
            return false;
        } else {
            if (board[row][column] == '●') {
                System.out.println("Invalid move! Try again!");
                System.out.println();
                return false;

            } else if (board[row][column] == '○') {
                System.out.println("Invalid move! Try again!");
                System.out.println();
                return false;
            } else {
                return true;
            }
        }

    }

    public static boolean isBoardFull(char[][] board) {
        int count = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == '●' || board[r][c] == '○') {
                    count++;
                    if (count == 361) {
                        System.out.println("Board is full");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean hasPlayerWon(char[][] board, int currentPlayer) {
        if (isHorizontalWin(board, currentPlayer)) {
            System.out.println("Player " + currentPlayer % 2 + " wins!");
            return true;
        }
        if (isVerticalWin(board, currentPlayer)) {
            System.out.println("Player " + currentPlayer % 2 + " wins!");
            return true;
        } else return false;
    }

    public static boolean isHorizontalWin(char[][] board, int currentPlayer) {
        int count = 0;
        boolean hWin = false;
        int moveRow;
        int moveCol;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (currentPlayer % 2 != 0) {
                    moveRow = r;
                    moveCol = c;
                    while (moveRow >= 0 && moveRow <= 18 && moveCol >= 0 && moveCol <= 18 && board[moveRow][moveCol] == '●') {
                        count++;
                        moveRow += 0;
                        moveCol += 1;
                    }
                    if (count == 5) {
                        hWin = true;
                        return hWin;
                    } else {
                        count = 0;
                    }
                } else {
                    moveRow = r;
                    moveCol = c;
                    while (moveRow >= 0 && moveRow <= 18 && moveCol >= 0 && moveCol <= 18 && board[moveRow][moveCol] == '○') {
                        count++;
                        moveRow += 0;
                        moveCol += 1;
                    }
                    if (count == 5) {
                        hWin = true;
                        return hWin;
                    } else {
                        count = 0;
                    }
                }
            }
        }
        return hWin;
    }

    public static boolean isVerticalWin(char[][] board, int currentPlayer) {
        int count = 0;
        boolean vWin = false;
        int moveRow;
        int moveCol;
        for (int r = 0; r < board.length; r++) { // The nested for loops runs row by row, checking each column within the row before moving on to the next row.
            for (int c = 0; c < board[r].length; c++) {
                if (currentPlayer % 2 != 0) {
                    moveRow = r;
                    moveCol = c;
                    while (moveRow >= 0 && moveRow <= 18 && moveCol >= 0 && moveCol <= 18 && board[moveRow][moveCol] == '●') {
                        count++;
                        moveRow += 1;
                        moveCol += 0;
                    } if (count == 5) {
                        vWin = true;
                        return vWin;
                    } else {
                        count = 0;
                    }
                } else {
                    moveRow = r;
                    moveCol = c;
                    while (moveRow >= 0 && moveRow <= 18 && moveCol >= 0 && moveCol <= 18 && board[moveRow][moveCol] == '○') {
                        count++;
                        moveRow += 1;
                        moveCol += 0;
                    }
                    if (count == 5) {
                        vWin = true;
                        return vWin;
                    } else {
                        count = 0;
                    }
                    }
                }
            }
        return vWin;
    }
}

