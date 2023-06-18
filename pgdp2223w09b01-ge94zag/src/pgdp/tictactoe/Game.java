package pgdp.tictactoe;

import pgdp.tictactoe.ai.HumanPlayer;
import pgdp.tictactoe.ai.SimpleAI;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Game {

    public Field[][] board;
    private PenguAI first;
    private PenguAI second;
    private boolean[] firstPlayedPieces;
    private boolean[] secondPlayedPieces;
    private boolean firstlose = false;
    private boolean secondlose = false;

    public Game(PenguAI first, PenguAI second) {
        board = new Field[3][3];    //Initialize new game board

        this.first = first;
        firstPlayedPieces = new boolean[9];

        this.second = second;
        secondPlayedPieces = new boolean[9];
    }

    public PenguAI getWinner() {
        //DRAW
        if (!checkWin() && piecesAll(firstPlayedPieces) && piecesAll(secondPlayedPieces)) {
            return null;
        }

        if (firstlose) {
            return second;
        } else if (secondlose){
            return first;
        }

        return null; //Draw
    }

    public boolean piecesAll(boolean[] a) {
        boolean AllTrue = true;
        for(int x = 0; x < a.length;++x) {
            if(!a[x]) {
                AllTrue=false;
                break;
            }
        }
        return AllTrue;
    }
    public void playGame() {
        while ((!firstlose || !secondlose) && !checkWin()) {

            //if both players have no pieces
            if (piecesAll(firstPlayedPieces) && piecesAll(secondPlayedPieces)) {
                checkWin();
                break;
            }

            if (!piecesAll(firstPlayedPieces)) {
                //Player 1
                if (!stillHasMoves(board, firstPlayedPieces)) {
                    firstlose = true;
                    break;
                }

                Move move = first.makeMove(board, true, firstPlayedPieces, secondPlayedPieces);
                if (move.value() == 123) {
                    secondlose = true;
                    break;
                }
                //Illegal move
                if (move.x() > 2 || move.x() < 0 || move.y() > 2 || move.y() < 0 || move.value() > 8 || move.value() < 0) {
                    firstlose = true;
                    break;
                }

                if (!firstPlayedPieces[move.value()]) {
                    firstPlayedPieces[move.value()] = true;
                } else {
                    firstlose = true;
                    break;
                }

                //Update the board
                if (board[move.x()][move.y()] == null) {
                    board[move.x()][move.y()] = new Field(move.value(), true);
                } else {
                    if (board[move.x()][move.y()].toString().contains("O")) {
                        if (board[move.x()][move.y()].value() < move.value()) {
                            board[move.x()][move.y()] = new Field(move.value(), true);
                        } else {    //KALO PLAYERNY HUMAN
                            firstlose = true;
                            break;
                        }
                    } else {        //TARO DI PUNYA SENDIRI
                        firstlose = true;
                        break;
                    }
                }

                //CHECK WIN
                if (checkWin()) {
                    break;
                }
            }

            if (!piecesAll(secondPlayedPieces)) {
                //Player 2
                if (!stillHasMoves(board, secondPlayedPieces)) {
                    secondlose = true;
                    break;
                }
                Move move2 = second.makeMove(board, false, firstPlayedPieces, secondPlayedPieces);

                //IF AI DOES NOT HAVE ANY VALID MOVE
                if (move2.value() == 123) {
                    secondlose = true;
                    break;
                }
                //Illegal move
                if (move2.x() > 2 || move2.x() < 0 || move2.y() > 2 || move2.y() < 0 || move2.value() > 8 || move2.value() < 0) {
                    secondlose = true;
                    break;
                }
                if (!secondPlayedPieces[move2.value()]) {
                    secondPlayedPieces[move2.value()] = true;
                } else {
                    secondlose = true;
                    break;
                }

                //Update the board
                if (board[move2.x()][move2.y()] == null) {
                    board[move2.x()][move2.y()] = new Field(move2.value(), false);
                } else {
                    if (board[move2.x()][move2.y()].toString().contains("X")) {
                        if (board[move2.x()][move2.y()].value() < move2.value()) {
                            board[move2.x()][move2.y()] = new Field(move2.value(), false);
                        } else {    //KALO PLAYERNYA HUMAN
                            secondlose = true;
                            break;
                        }
                    } else {
                        secondlose = true;
                        break;
                    }
                }

                //CHECK WIN
                if (checkWin()) {
                    break;
                }
            }

        }
    }

    public boolean stillHasMoves(Field[][] board, boolean[] playedPieces) {
        List<Integer> moves = new ArrayList<>(9);
        for (int a = 0; a < 9; a++) {
            if (!playedPieces[a]) {
                moves.add(a);
            }
        }

        boolean abbrechen = true;
        boolean isFull = true;
        for (int z = 0; z < 3; z++) {
            for (int y = 0; y < 3; y++) {
                if (board[z][y] == null) {
                    isFull = false;
                    break;
                }
            }
        }

        if (isFull) {
            for (int z = 0; z < 3; z++) {
                for (int o = 0; o < 3; o++) {
                    if (board[z][o].value() < Collections.max(moves)) {
                        return true;
                    }
                }
            }
            abbrechen = false;
        } else {
            return true;
        }

        return abbrechen;
    }
    public boolean checkWin() {
        //diagonal 1
        if ((board[0][0] != null && board[1][1] != null //PLAYER 1 WINS
                && board[2][2] != null) && (board[0][0].toString().contains("X") &&
                board[1][1].toString().contains("X") && board[2][2].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[0][0] != null && board[1][1] != null //PLAYER 2 WINS
                && board[2][2] != null) && (board[0][0].toString().contains("O") &&
                board[1][1].toString().contains("O") && board[2][2].toString().contains("O"))) {
            firstlose = true;
            return true;
        }

        //diagonal 2
        if ((board[2][0] != null && board[1][1] != null //PLAYER 1 WINS
                && board[0][2] != null) && (board[2][0].toString().contains("X") && board[1][1].toString().contains("X")
                && board[0][2].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[2][0] != null && board[1][1] != null //PLAYER 2 WINS
                && board[0][2] != null) && (board[2][0].toString().contains("O") && board[1][1].toString().contains("O")
                && board[0][2].toString().contains("O"))) {
            firstlose = true;
            return true;
        }

        //Horizontal (ZEILE 1)
        if ((board[0][0] != null && board[1][0] != null //PLAYER 1 WINS
                && board[2][0] != null) && (board[0][0].toString().contains("X") && board[1][0].toString().contains("X")
                && board[2][0].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[0][0] != null && board[1][0] != null //PLAYER 1 WINS
                && board[2][0] != null) && (board[0][0].toString().contains("O") && board[1][0].toString().contains("O")
                && board[2][0].toString().contains("O"))) {
            firstlose = true;
            return true;
        }
        //Horizontal (ZEILE 2)
        if ((board[0][1] != null && board[1][1] != null //PLAYER 1 WINS
                && board[2][1] != null) && (board[0][1].toString().contains("X") && board[1][1].toString().contains("X")
                && board[2][1].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[0][1] != null && board[1][1] != null //PLAYER 1 WINS
                && board[2][1] != null) && (board[0][1].toString().contains("O") && board[1][1].toString().contains("O")
                && board[2][1].toString().contains("O"))) {
            firstlose = true;
            return true;
        }
        //Horizontal (ZEILE 3)
        if ((board[0][2] != null && board[1][2] != null //PLAYER 1 WINS
                && board[2][2] != null) && (board[0][2].toString().contains("X") && board[1][2].toString().contains("X")
                && board[2][2].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[0][2] != null && board[1][2] != null //PLAYER 1 WINS
                && board[2][2] != null) && (board[0][2].toString().contains("O") && board[1][2].toString().contains("O")
                && board[2][2].toString().contains("O"))) {
            firstlose = true;
            return true;
        }

        //Vertical (SPALTE 1)
        if ((board[0][0] != null && board[0][1] != null //PLAYER 1 WINS
                && board[0][2] != null) && (board[0][0].toString().contains("X") && board[0][1].toString().contains("X")
                && board[0][2].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[0][0] != null && board[0][1] != null //PLAYER 2 WINS
                && board[0][2] != null) && (board[0][0].toString().contains("O") && board[0][1].toString().contains("O")
                && board[0][2].toString().contains("O"))) {
            firstlose = true;
            return true;
        }
        //Vertical (SPALTE 2)
        if ((board[1][0] != null && board[1][1] != null //PLAYER 1 WINS
                && board[1][2] != null) && (board[1][0].toString().contains("X") && board[1][1].toString().contains("X")
                && board[1][2].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[1][0] != null && board[1][1] != null //PLAYER 2 WINS
                && board[1][2] != null) && (board[1][0].toString().contains("O") && board[1][1].toString().contains("O")
                && board[1][2].toString().contains("O"))) {
            firstlose = true;
            return true;
        }
        //Vertical (SPALTE 3)
        if ((board[2][0] != null && board[2][1] != null //PLAYER 1 WINS
                && board[2][2] != null) && (board[2][0].toString().contains("X") && board[2][1].toString().contains("X")
                && board[2][2].toString().contains("X"))) {
            secondlose = true;
            return true;
        } else if ((board[2][0] != null && board[2][1] != null //PLAYER 2 WINS
                && board[2][2] != null) && (board[2][0].toString().contains("O") && board[2][1].toString().contains("O")
                && board[2][2].toString().contains("O"))) {
            firstlose = true;
            return true;
        }
        return false;
    }
    public static void printBoard(Field[][] board) {
        System.out.println("┏━━━┳━━━┳━━━┓");
        for (int y = 0; y < board.length; y++) {
            System.out.print("┃");
            for (int x = 0; x < board.length; x++) {
                if (board[x][y] != null) {
                    System.out.print(board[x][y] + "┃");
                } else {
                    System.out.print("   ┃");
                }
            }
            System.out.println();
            if (y != board.length - 1) {
                System.out.println("┣━━━╋━━━╋━━━┫");
            }
        }
        System.out.println("┗━━━┻━━━┻━━━┛");
    }

    public static void main(String[] args) {
        System.out.println("Wilkommen zu PenguTicTacToe!");
        System.out.println("");

        System.out.println("Wähle ein Spielmodus (Bitte geben Sie 1 - 4 ein) : ");
        System.out.println("1. PLAYER VS PLAYER");
        System.out.println("2. PLAYER VS AI");
        System.out.println("3. AI VS PLAYER");
        System.out.println("4. AI VS AI");
        System.out.print("Eingabe : ");
        Scanner input = new Scanner(System.in);

        PenguAI firstPlayer = null;
        PenguAI secondPlayer = null;
        Game game = null;
        try {
            String mode = input.nextLine();
            while (!mode.equals("1") || !mode.equals("2") || !mode.equals("3") || !mode.equals("4")) {
                if (mode.equals("1")) {
                    firstPlayer = new HumanPlayer();
                    secondPlayer = new HumanPlayer();
                    game = new Game(firstPlayer, secondPlayer);
                    break;
                } else if (mode.equals("2")) {
                    firstPlayer = new HumanPlayer();
                    secondPlayer = new SimpleAI();
                    game = new Game(firstPlayer, secondPlayer);
                    break;
                } else if (mode.equals("3")) {
                    firstPlayer = new SimpleAI();
                    secondPlayer = new HumanPlayer();
                    game = new Game(firstPlayer, secondPlayer);
                    break;
                } else if (mode.equals("4")) {
                    firstPlayer = new SimpleAI();
                    secondPlayer = new SimpleAI();
                    game = new Game(firstPlayer, secondPlayer);
                    break;
                } else {
                    System.out.println("\n");
                    System.out.println("Sie haben eine falsche Eingabe eingegeben. Bitte versuchen Sie nochmal! \n");
                    System.out.println("Wähle ein Spielmodus (Bitte geben Sie 1 - 4 ein) : ");
                    System.out.println("1. PLAYER VS PLAYER");
                    System.out.println("2. PLAYER VS AI");
                    System.out.println("3. AI VS PLAYER");
                    System.out.println("4. AI VS AI");
                    System.out.print("Eingabe : ");
                    mode = input.nextLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Game 1x
        game.playGame();
        System.out.println("");
        printBoard(game.board);
        if(firstPlayer == game.getWinner()) {
            System.out.println("Herzlichen Glückwunsch erster Spieler!");
        } else if(secondPlayer == game.getWinner()) {
            System.out.println("Herzlichen Glückwunsch zweiter Spieler!");
        } else {
            System.out.println("Unentschieden...");
        }

        System.out.print("\nZum Beenden eine beliebige Taste eingeben");

        String exit = input.nextLine();

    }
}
