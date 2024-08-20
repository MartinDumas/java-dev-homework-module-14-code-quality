import java.util.Arrays;
import java.util.Scanner;

public class App {
    private static final int TOTAL_BOXES = 9;
    private static final char EMPTY_BOX = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final byte NO_WINNER = 0;
    private static final byte PLAYER_X_WINS = 1;
    private static final byte PLAYER_O_WINS = 2;
    private static final byte DRAW = 3;

    private static final char[] board = new char[TOTAL_BOXES];
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        resetBoard();
        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            printBoard();
            playerTurn();
            if (isGameOver()) break;
            computerTurn();
            if (isGameOver()) break;
        }
    }

    private static void resetBoard() {
        Arrays.fill(board, EMPTY_BOX);
    }

    private static void printBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static void playerTurn() {
        while (true) {
            System.out.print("Your move (1-9): ");
            byte playerMove = scanner.nextByte();
            if (playerMove > 0 && playerMove <= TOTAL_BOXES) {
                if (board[playerMove - 1] == EMPTY_BOX) {
                    board[playerMove - 1] = PLAYER_X;
                    break;
                } else {
                    System.out.println("That one is already in use. Enter another.");
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private static void computerTurn() {
        while (true) {
            int computerMove = (int) (Math.random() * TOTAL_BOXES);
            if (board[computerMove] == EMPTY_BOX) {
                board[computerMove] = PLAYER_O;
                break;
            }
        }
    }

    private static boolean isGameOver() {
        byte winner = checkWinner();
        if (winner == PLAYER_X_WINS) {
            System.out.println("You won the game!\nThanks for playing!");
            return true;
        } else if (winner == PLAYER_O_WINS) {
            System.out.println("You lost the game!\nThanks for playing!");
            return true;
        } else if (winner == DRAW) {
            System.out.println("It's a draw!\nThanks for playing!");
            return true;
        }
        return false;
    }

    private static byte checkWinner() {
        if (isWinningCombination(PLAYER_X)) {
            return PLAYER_X_WINS;
        } else if (isWinningCombination(PLAYER_O)) {
            return PLAYER_O_WINS;
        } else if (isBoardFull()) {
            return DRAW;
        }
        return NO_WINNER;
    }

    private static boolean isWinningCombination(char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    private static boolean isBoardFull() {
        for (char box : board) {
            if (box == EMPTY_BOX) {
                return false;
            }
        }
        return true;
    }
}
