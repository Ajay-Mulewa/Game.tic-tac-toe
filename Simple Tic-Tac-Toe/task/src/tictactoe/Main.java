package tictactoe;

import java.util.Scanner;

public class Main {
    private final static int SIZE = 3;
    private final static char PLAYER_X = 'X';
    private final static char PLAYER_O = 'O';
    public static int COUNTER = 0;
    public static int MOVES = 0;

    public static void main(String[] args) {
        char[][] board = getBoard();
        printBoard(board);
        while (true) {
            firstMove(board);
            printBoard(board);
            String boardEval = checkBoard(board);
            if ("X wins".equals(boardEval) || "O wins".equals(boardEval) || "Draw".equals(boardEval)) {
                System.out.println(boardEval);
                break;
            }
        }
    }

    public static void firstMove(char[][] board) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the coordinates: ");

        String[] input = sc.nextLine().split(" ");

        int i = 0;
        int j = 0;

        try {
            i = Integer.parseInt(input[0]) - 1;
            j = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            firstMove(board);
        }

        if (i < 0 || i > 2 || j < 0 || j > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            firstMove(board);
        } else if(board[i][j] != ' ') {
            System.out.println("This cell is occupied! choose another one!");
            firstMove(board);
        } else {
            if (COUNTER % 2 == 0) {
                board[i][j] = PLAYER_X;
            } else {
                board[i][j] = PLAYER_O;
            }
            COUNTER++;
            MOVES++;
        }
    }

    public static char[][] getBoard() {
        char[][] board = new char[SIZE][SIZE];

        for (int i = 0; i < 9; i++) {
            board[i / SIZE][i % SIZE] = ' ';
        }

        return board;
    }

    public static void printBoard(char[][] board) {
        printLine();

        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");

            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }

            System.out.println(" |");
        }

        printLine();
    }

    public static void printLine() {
        System.out.println("---------");
    }

    public static String checkBoard(char[][] board) {

        boolean XRows = checkRows(board, PLAYER_X);
        boolean XCols = checkColumns(board, PLAYER_X);
        boolean XDiagonals = checkDiagonals(board, PLAYER_X);

        boolean ORows = checkRows(board, PLAYER_O);
        boolean OCols = checkColumns(board, PLAYER_O);
        boolean ODiagonals = checkDiagonals(board, PLAYER_O);

        if (XRows || XCols || XDiagonals) {
            return "X wins";
        } else if (ORows || OCols || ODiagonals) {
            return "O wins";
        } else if (!XRows && !XCols && !XDiagonals && !ORows && !OCols && !ODiagonals && MOVES == 9){
            return "Draw";
        }
        return "";

    }

    public static boolean checkRows(char[][] board, char player) {
        for (int i = 0; i < SIZE; i++) {
            int counter = 0;

            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == player) {
                    counter++;
                }
            }

            if (counter == SIZE) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkColumns(char[][] board, char player) {
        for (int i = 0; i < SIZE; i++) {
            int counter = 0;

            for (int j = 0; j < SIZE; j++) {
                if (board[j][i] == player) {
                    counter++;
                }
            }

            if (counter == SIZE) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkDiagonals(char[][] board, char player) {
        int diagonal = 0;
        int antidiagonal = 0;

        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] == player) {
                diagonal++;
            }

            if (board[i][SIZE - 1 - i] == player) {
                antidiagonal++;
            }
        }

        return diagonal == SIZE || antidiagonal == SIZE;
    }
}
