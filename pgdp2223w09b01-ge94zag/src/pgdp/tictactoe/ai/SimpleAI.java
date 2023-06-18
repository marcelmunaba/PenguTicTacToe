package pgdp.tictactoe.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import pgdp.tictactoe.Field;
import pgdp.tictactoe.Game;
import pgdp.tictactoe.Move;
import pgdp.tictactoe.PenguAI;

public class SimpleAI extends PenguAI {

    private Random random;
    private Field[][] boardd;
    private String symbol1; //AI's Symbol
    private String symbol2;
    private List<Integer> AIpieces;
    private int xtarget;
    private int ytarget;

    public SimpleAI() {
        random = new Random();
        this.AIpieces = new ArrayList<>(9);
    }

    @Override
    public Move makeMove(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces,
            boolean[] secondPlayedPieces) {
        boardd = board;
        //IF AI IS THE FIRST PLAYER
        if (firstPlayer) {
            symbol1 = "X";
            symbol2 = "O";
            AIpieces.add(5);
            AIpieces.clear();
            for (int a = 0; a < 9; a++) {
                if (!firstPlayedPieces[a]) {
                    AIpieces.add(a);
                }
            }
        }
        //IF AI IS THE SECOND PLAYER
        else {
            symbol1 = "O";
            symbol2 = "X";
            AIpieces.add(5);
            AIpieces.clear();
            for (int a = 0; a < 9; a++) {
                if (!secondPlayedPieces[a]) {
                    AIpieces.add(a);
                }
            }
        }
        //IF AI IS THE SECOND PLAYER
        Move toMove;
        if (!firstPlayer) {
            //Winning move
            if (winChance(board)) {
                Integer randomvalue = AIpieces.get(random.nextInt(AIpieces.size()));
                toMove = new Move(xtarget, ytarget, randomvalue);
                AIpieces.remove(randomvalue);
                return toMove;
            }
            if (winChance2(board)) {
                Integer randomvalue = AIpieces.get(AIpieces.size() - 1);
                toMove = new Move(xtarget, ytarget, randomvalue);
                boolean lanjut = board[toMove.x()][toMove.y()].value() < toMove.value();

                if (lanjut) {
                    AIpieces.remove(randomvalue);
                    return toMove;
                }
            }
            if (interceptChance(board)) {
                Integer randomvalue = AIpieces.get(AIpieces.size() - 1);
                toMove = new Move(xtarget, ytarget, randomvalue);
                AIpieces.remove(randomvalue);
                return toMove;
            }
            if (interceptChance2(board)) {
                Integer randomvalue = AIpieces.get(AIpieces.size() - 1);
                toMove = new Move(xtarget, ytarget, randomvalue);
                boolean lanjut = board[toMove.x()][toMove.y()].value() < toMove.value();

                if (lanjut) {
                    AIpieces.remove(randomvalue);
                    return toMove;
                }
            }

            int xrand = 0;
            int yrand = 0;
            boolean exitcode = false;
            if (!boardisFull(board)) {  //BOARD BELOM PENUH
                while (!exitcode) {
                    xrand = random.nextInt(board.length);
                    yrand = random.nextInt(board.length);
                    if (!spotFull(xrand, yrand)) {
                        exitcode = true;
                        toMove = new Move(xrand, yrand, AIpieces.get(random.nextInt(AIpieces.size())));
                        return toMove;
                    }
                }
            }

            boolean exitcode2 = false;  //BOARD UDH PENUH
            if (boardisFull(board)) {
                for (int z = 0; z < 3; z++) {
                    for (int y = 0; y < 3; y++) {
                        if (board[z][y].value() < AIpieces.get(AIpieces.size() - 1)) {
                            exitcode2 = true;
                            Integer randomvalue = AIpieces.get(AIpieces.size() - 1);
                            toMove = new Move(z, y, randomvalue);
                            AIpieces.remove(randomvalue);
                            return toMove;
                        }
                    }
                }
            }

            toMove = new Move(1, 1, 123);   //errorcode (no moves left)

            return toMove;

        } else {
            //IF AI IS THE FIRST PLAYER
            //Winning move
            if (winChance(board)) {
                Integer randomvalue = AIpieces.get(random.nextInt(AIpieces.size()));
                toMove = new Move(xtarget, ytarget, randomvalue);
                AIpieces.remove(randomvalue);
                return toMove;
            }
            if (winChance2(board)) {
                Integer randomvalue = AIpieces.get(AIpieces.size()-1);
                toMove = new Move(xtarget, ytarget, randomvalue);
                boolean lanjut = board[toMove.x()][toMove.y()].value() < toMove.value();

                if (lanjut) {
                    AIpieces.remove(randomvalue);
                    return toMove;
                }

            }
            if (interceptChance(board)){
                Integer randomvalue = AIpieces.get(AIpieces.size()-1);
                toMove = new Move(xtarget, ytarget, randomvalue);
                AIpieces.remove(randomvalue);
                return toMove;
            }
            if (interceptChance2(board)) {
                Integer randomvalue = AIpieces.get(AIpieces.size()-1);
                toMove = new Move(xtarget, ytarget, randomvalue);
                boolean lanjut = board[toMove.x()][toMove.y()].value() < toMove.value();

                if (lanjut) {
                    AIpieces.remove(randomvalue);
                    return toMove;
                }
            }

            int xrand = 0;
            int yrand = 0;
            boolean exitcode = false;
            boolean exitcode2 = false;

            if (!boardisFull(board)) {
                while (!exitcode) { //BOARD BELOM PENUH
                    xrand = random.nextInt(board.length);
                    yrand = random.nextInt(board.length);
                    if(!spotFull(xrand, yrand)) {
                        exitcode = true;
                        toMove = new Move(xrand, yrand, AIpieces.get(random.nextInt(AIpieces.size())));
                        return toMove;
                    }
                }

            } else if (boardisFull(board)) {
                for (int z = 0; z < 3; z++) {
                    for (int y = 0; y < 3; y++) {
                        if (board[z][y].value() < AIpieces.get(AIpieces.size() - 1)) {
                            exitcode2 = true;
                            Integer randomvalue = AIpieces.get(AIpieces.size() - 1);
                            toMove = new Move(z, y, randomvalue);
                            AIpieces.remove(randomvalue);
                            return toMove;
                        }
                    }
                }

                Integer randomvalue = AIpieces.get(random.nextInt(AIpieces.size()));
                toMove = new Move(xrand, yrand, randomvalue);
                AIpieces.remove(randomvalue);
                return toMove;
            }

            toMove = new Move(1, 1, 123); //errorcode (no moves left)

            return toMove;
        }

    }

    public boolean boardisFull(Field[][] board) {
        boolean isFull = true;
        for (int z = 0; z < 3; z++) {
            for (int y = 0; y < 3; y++) {
                if (board[z][y] == null) {
                    isFull = false;
                }
            }
        }
        return isFull;
    }
    public boolean spotFull(int x, int y) {
        if (boardd[x][y] != null) {
            return true;
        } else if (boardd[x][y] == null){
            return false;
        }
        return false;
    }
    public boolean winChance(Field[][] boardd) {
        //CHECKS IF THERE IS A WINNING CHANCE FOR THE AI TO WIN
        //DIAGONAL 1
        if (boardd[0][0] != null && boardd[1][1] != null && boardd[2][2] == null &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[0][0] != null && boardd[2][2] != null && boardd[1][1] == null &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[2][2] != null && boardd[0][0] == null &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        }

        //DIAGONAL 2
        if (boardd[2][0] != null && boardd[1][1] != null && boardd[0][2] == null &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[2][0] != null && boardd[0][2] != null && boardd[1][1] == null &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[0][2] != null && boardd[2][0] == null &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        }

        //HORIZONTAL (ZEILE 1)
        if (boardd[0][0] != null && boardd[1][0] != null && boardd[2][0] == null &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[1][0].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        } else if (boardd[1][0] != null && boardd[2][0] != null && boardd[0][0] == null &&
                boardd[1][0].toString().contains(symbol1) &&
                boardd[2][0].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        } else if (boardd[0][0] != null && boardd[2][0] != null && boardd[1][0] == null &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[2][0].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 0;
            return true;
        }

        //HORIZONTAL (ZEILE 2)
        if (boardd[0][1] != null && boardd[1][1] != null && boardd[2][1] == null &&
                boardd[0][1].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[2][1] != null && boardd[0][1] == null &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[2][1].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 1;
            return true;
        } else if (boardd[0][1] != null && boardd[2][1] != null && boardd[1][1] == null &&
                boardd[0][1].toString().contains(symbol1) &&
                boardd[2][1].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        }

        //HORIZONTAL (ZEILE 3)
        if (boardd[0][2] != null && boardd[1][2] != null && boardd[2][2] == null &&
                boardd[0][2].toString().contains(symbol1) &&
                boardd[1][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[1][2] != null && boardd[2][2] != null && boardd[0][2] == null &&
                boardd[1][2].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[0][2] != null && boardd[2][2] != null && boardd[1][2] == null &&
                boardd[0][2].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 2;
            return true;
        }

        //VERTICAL (SPALTE 1)
        if (boardd[0][0] != null && boardd[0][1] != null && !spotFull(0, 2) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[0][1].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[0][1] != null && boardd[0][2] != null && !spotFull(0, 0) &&
                boardd[0][1].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        } else if (boardd[0][0] != null && boardd[0][2] != null && !spotFull(0, 1) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 1;
            return true;
        }

        //VERTICAL (SPALTE 2)
        if (boardd[1][0] != null && boardd[1][1] != null && boardd[1][2] == null &&
                boardd[1][0].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 2;
            return true;
        } else if (boardd[1][1] != null && boardd[1][2] != null && boardd[1][0] == null &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[1][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 0;
            return true;
        } else if (boardd[1][0] != null && boardd[1][2] != null && boardd[1][1] == null &&
                boardd[1][0].toString().contains(symbol1) &&
                boardd[1][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        }

        //VERTICAL (SPALTE 3)
        if (boardd[2][0] != null && boardd[2][1] != null && boardd[2][2] == null &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[2][1].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[2][1] != null && boardd[2][2] != null && boardd[2][0] == null &&
                boardd[2][1].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        } else if (boardd[2][0] != null && boardd[2][2] != null && boardd[2][1] == null &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 1;
            return true;
        }

        return false;
    } //Winning move (if the cell is null)
    public boolean winChance2(Field[][] boardd) {
        //DIAGONAL 1
        if (boardd[0][0] != null && boardd[1][1] != null && boardd[2][2] != null
                && (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[0][0] != null && boardd[2][2] != null && boardd[1][1] != null
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[2][2] != null && boardd[0][0] != null
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        }

        //DIAGONAL 2
        if (boardd[2][0] != null && boardd[1][1] != null && boardd[0][2] != null
                && (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[2][0] != null && boardd[0][2] != null && boardd[1][1] != null
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[0][2] != null && boardd[2][0] != null
                && (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        }

        //HORIZONTAL (ZEILE 1)
        if (boardd[0][0] != null && boardd[1][0] != null && boardd[2][0] != null
                && (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[1][0].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        } else if (boardd[1][0] != null && boardd[2][0] != null && boardd[0][0] != null
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][0].toString().contains(symbol1) &&
                boardd[2][0].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        } else if (boardd[0][0] != null && boardd[2][0] != null && boardd[1][0] != null
                && (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[2][0].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 0;
            return true;
        }

        //HORIZONTAL (ZEILE 2)
        if (boardd[0][1] != null && boardd[1][1] != null && boardd[2][1] != null
                && (boardd[2][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][1].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[2][1] != null && boardd[0][1] != null &&
                (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[2][1].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 1;
            return true;
        } else if (boardd[0][1] != null && boardd[2][1] != null && boardd[1][1] != null &&
                (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][1].toString().contains(symbol1) &&
                boardd[2][1].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        }

        //HORIZONTAL (ZEILE 3)
        if (boardd[0][2] != null && boardd[1][2] != null && boardd[2][2] != null &&
                (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][2].toString().contains(symbol1) &&
                boardd[1][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[1][2] != null && boardd[2][2] != null && boardd[0][2] != null &&
                (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][2].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[0][2] != null && boardd[2][2] != null && boardd[1][2] != null &&
                (boardd[1][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][2].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 2;
            return true;
        }

        //VERTICAL (SPALTE 1)
        if (boardd[0][0] != null && boardd[0][1] != null && spotFull(0, 2) &&
                (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[0][1].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[0][1] != null && boardd[0][2] != null && spotFull(0, 0) &&
                (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][1].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        } else if (boardd[0][0] != null && boardd[0][2] != null && spotFull(0, 1) &&
                (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[0][2].toString().contains(symbol1)) {
            xtarget = 0;
            ytarget = 1;
            return true;
        }

        //VERTICAL (SPALTE 2)
        if (boardd[1][0] != null && boardd[1][1] != null && boardd[1][2] != null &&
                (boardd[1][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][0].toString().contains(symbol1) &&
                boardd[1][1].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 2;
            return true;
        } else if (boardd[1][1] != null && boardd[1][2] != null && boardd[1][0] != null &&
                (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][1].toString().contains(symbol1) &&
                boardd[1][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 0;
            return true;
        } else if (boardd[1][0] != null && boardd[1][2] != null && boardd[1][1] != null &&
                (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[1][0].toString().contains(symbol1) &&
                boardd[1][2].toString().contains(symbol1)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        }

        //VERTICAL (SPALTE 3)
        if (boardd[2][0] != null && boardd[2][1] != null && boardd[2][2] != null &&
                (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[2][1].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[2][1] != null && boardd[2][2] != null && boardd[2][0] != null &&
                (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[2][1].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        } else if (boardd[2][0] != null && boardd[2][2] != null && boardd[2][1] != null &&
                (boardd[2][1].value() < AIpieces.get(AIpieces.size()-1)) &&
                boardd[2][0].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol1)) {
            xtarget = 2;
            ytarget = 1;
            return true;
        }

        return false;
    } //Winning move (if the cell is not null)
    public boolean interceptChance(Field[][] boardd) {
        //CHECKS IF THERE IS A WINNING CHANCE FOR THE AI TO INTERCEPT OTHER PLAYER'S WIN
        //DIAGONALS, HORIZONTAL, VERTICAL, FORK
        //SPECIAL CASE ujung2
        if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol2)) &&
                boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            } else if (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 2;
                return true;
            } else {
                xtarget = 2;
                ytarget = 2;
                return true;
            }
        } else if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol2)) &&
                boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            } else if (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 2;
                return true;
            } else {
                xtarget = 2;
                ytarget = 0;
                return true;
            }
        } else if ((boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol2)) &&
                boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)
                && (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 2;
                return true;
            } else if (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 2;
                return true;
            } else {
                xtarget = 0;
                ytarget = 0;
                return true;
            }
        } else if ((boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)) &&
                (boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)
                && (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 0;
                return true;
            } else if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            } else {
                xtarget = 2;
                ytarget = 2;
                return true;
            }
        }

        //SPECIAL CASE ujung2 2
        if ((boardd[0][1] != null && boardd[0][1].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol2)) &&
                boardd[1][2] != null && boardd[1][2].toString().contains(symbol2)
                && (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 2;
                return true;
            }
        } else if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[1][0] != null && boardd[1][0].toString().contains(symbol2)) &&
                boardd[0][1] != null && boardd[0][1].toString().contains(symbol2)
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][1].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            }
        } else if ((boardd[2][1] != null && boardd[2][1].toString().contains(symbol2)) &&
                (boardd[1][2] != null && boardd[1][2].toString().contains(symbol2)) &&
                boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)
                && (boardd[2][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 2;
                return true;
            }
        } else if ((boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)) &&
                (boardd[1][0] != null && boardd[1][0].toString().contains(symbol2)) &&
                boardd[2][1] != null && boardd[2][1].toString().contains(symbol2)
                && (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][1].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 0;
                return true;
            }
        }

        //SPECIAL CASE 3
        if ((boardd[0][0] == null && boardd[1][0] != null && boardd[1][0].toString().contains(symbol2)) &&
                (boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)) &&
                (boardd[0][1] != null && boardd[0][1].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol2))) {
            xtarget = 0;
            ytarget = 0;
            return true;
        } else if ((boardd[2][0] == null && boardd[1][0] != null && boardd[1][0].toString().contains(symbol2)) &&
                (boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[2][1] != null && boardd[2][1].toString().contains(symbol2)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol2))) {
            xtarget = 2;
            ytarget = 0;
            return true;
        } else if ((boardd[0][2] == null && boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[0][1] != null && boardd[0][1].toString().contains(symbol2)) &&
                (boardd[1][2] != null && boardd[1][2].toString().contains(symbol2)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol2))) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if ((boardd[2][2] == null && boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)) &&
                (boardd[2][1] != null && boardd[2][1].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol2)) &&
                (boardd[1][2] != null && boardd[1][2].toString().contains(symbol2))) {
            xtarget = 2;
            ytarget = 2;
            return true;
        }

        //SPECIAL CASE 4
        if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[0][1] != null && boardd[0][1].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol2)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol1))
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1))) {
            xtarget = 0;
            ytarget = 1;
            return true;
        } else if ((boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)) &&
                (boardd[2][1] != null && boardd[2][1].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol1))
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1))) {
            xtarget = 2;
            ytarget = 1;
            return true;
        } else if ((boardd[2][0] != null && boardd[2][0].toString().contains(symbol1)) &&
                (boardd[0][1] != null && boardd[0][1].toString().contains(symbol2)) &&
                (boardd[0][2] != null && boardd[0][2].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol2))
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1))) {
            xtarget = 0;
            ytarget = 1;
            return true;
        } else if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol1)) &&
                (boardd[2][1] != null && boardd[2][1].toString().contains(symbol2)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol2))
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1))) {
            xtarget = 2;
            ytarget = 1;
            return true;
        }

        //DIAGONAL 1
        if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol2)) &&
                boardd[2][2] == null) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)) &&
                boardd[1][1] == null) {
            xtarget = 1;
            ytarget = 1;
            return true;
        } else if ((boardd[1][1] != null && boardd[1][1].toString().contains(symbol2)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)) &&
                boardd[0][0] == null) {
            xtarget = 0;
            ytarget = 0;
            return true;
        }

        //DIAGONAL 2
        if ((boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol2)) &&
                boardd[0][2] == null) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[2][0] != null && boardd[0][2] != null && boardd[1][1] == null &&
                boardd[2][0].toString().contains(symbol2)
                && boardd[0][2].toString().contains(symbol2)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[0][2] != null && boardd[2][0] == null &&
                boardd[1][1].toString().contains(symbol2) &&
                boardd[0][2].toString().contains(symbol2)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        }

        //HORIZONTAL (ZEILE 1)
        if (boardd[0][0] != null && boardd[1][0] != null && boardd[2][0] == null &&
                boardd[0][0].toString().contains(symbol2) &&
                boardd[1][0].toString().contains(symbol2)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        } else if (boardd[1][0] != null && boardd[2][0] != null && boardd[0][0] == null &&
                boardd[1][0].toString().contains(symbol2) &&
                boardd[2][0].toString().contains(symbol2)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        } else if (boardd[0][0] != null && boardd[2][0] != null && boardd[1][0] == null &&
                boardd[0][0].toString().contains(symbol2) &&
                boardd[2][0].toString().contains(symbol2)) {
            xtarget = 1;
            ytarget = 0;
            return true;
        }

        //HORIZONTAL (ZEILE 2)
        if (boardd[0][1] != null && boardd[1][1] != null && boardd[2][1] == null &&
                boardd[0][1].toString().contains(symbol2) &&
                boardd[1][1].toString().contains(symbol2)) {
            xtarget = 2;
            ytarget = 1;
            return true;
        } else if (boardd[1][1] != null && boardd[2][1] != null && boardd[0][1] == null &&
                boardd[1][1].toString().contains(symbol2) &&
                boardd[2][1].toString().contains(symbol2)) {
            xtarget = 0;
            ytarget = 1;
            return true;
        } else if (boardd[0][1] != null && boardd[2][1] != null && boardd[1][1] == null &&
                boardd[0][1].toString().contains(symbol2) &&
                boardd[2][1].toString().contains(symbol2)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        }

        //HORIZONTAL (ZEILE 3)
        if (boardd[0][2] != null && boardd[1][2] != null && boardd[2][2] == null &&
                boardd[0][2].toString().contains(symbol2) &&
                boardd[1][2].toString().contains(symbol2)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[1][2] != null && boardd[2][2] != null && boardd[0][2] == null &&
                boardd[1][2].toString().contains(symbol2) &&
                boardd[2][2].toString().contains(symbol2)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[0][2] != null && boardd[2][2] != null && boardd[1][2] == null &&
                boardd[0][2].toString().contains(symbol2) &&
                boardd[2][2].toString().contains(symbol2)) {
            xtarget = 1;
            ytarget = 2;
            return true;
        }

        //VERTICAL (SPALTE 1)
        if (boardd[0][0] != null && boardd[0][1] != null && boardd[0][2] == null &&
                boardd[0][0].toString().contains(symbol2) &&
                boardd[0][1].toString().contains(symbol2)) {
            xtarget = 0;
            ytarget = 2;
            return true;
        } else if (boardd[0][1] != null && boardd[0][2] != null && boardd[0][0] == null &&
                boardd[0][1].toString().contains(symbol2) &&
                boardd[0][2].toString().contains(symbol2)) {
            xtarget = 0;
            ytarget = 0;
            return true;
        } else if (boardd[0][0] != null && boardd[0][2] != null && boardd[0][1] == null &&
                boardd[0][0].toString().contains(symbol2) &&
                boardd[0][2].toString().contains(symbol2)) {
            xtarget = 0;
            ytarget = 1;
            return true;
        }

        //VERTICAL (SPALTE 2)
        if (boardd[1][0] != null && boardd[1][1] != null && boardd[1][2] == null &&
                boardd[1][0].toString().contains(symbol2) &&
                boardd[1][1].toString().contains(symbol2)) {
            xtarget = 1;
            ytarget = 2;
            return true;
        } else if (boardd[1][1] != null && boardd[1][2] != null && boardd[1][0] == null &&
                boardd[1][1].toString().contains(symbol2) &&
                boardd[1][2].toString().contains(symbol2)) {
            xtarget = 1;
            ytarget = 0;
            return true;
        } else if (boardd[1][0] != null && boardd[1][2] != null && boardd[1][1] == null &&
                boardd[1][0].toString().contains(symbol2) &&
                boardd[1][2].toString().contains(symbol2)) {
            xtarget = 1;
            ytarget = 1;
            return true;
        }

        //VERTICAL (SPALTE 3)
        if (boardd[2][0] != null && boardd[2][1] != null && boardd[2][2] == null &&
                boardd[2][0].toString().contains(symbol2) &&
                boardd[2][1].toString().contains(symbol2)) {
            xtarget = 2;
            ytarget = 2;
            return true;
        } else if (boardd[2][1] != null && boardd[2][2] != null && boardd[2][0] == null &&
                boardd[2][1].toString().contains(symbol2) &&
                boardd[2][2].toString().contains(symbol2)) {
            xtarget = 2;
            ytarget = 0;
            return true;
        } else if (boardd[2][0] != null && boardd[2][2] != null && boardd[2][1] == null &&
                boardd[2][0].toString().contains(symbol2) &&
                boardd[2][2].toString().contains(symbol2)) {
            xtarget = 2;
            ytarget = 1;
            return true;
        }
        return false;
    }  //Intercept winning move (if a cell is empty)
    public boolean interceptChance2(Field[][] boardd) {
        //DIAGONAL 1
        if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol1)) &&
                boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            } else if (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 2;
                return true;
            }

        } else if ((boardd[0][0] != null && boardd[0][0].toString().contains(symbol1)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol2)) &&
                boardd[1][1] != null && boardd[1][1].toString().contains(symbol2)
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 1;
                return true;
            } else if (boardd[2][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 2;
                return true;
            }

        } else if ((boardd[1][1] != null && boardd[1][1].toString().contains(symbol2)) &&
                (boardd[2][2] != null && boardd[2][2].toString().contains(symbol1)) &&
                boardd[0][0] != null && boardd[0][0].toString().contains(symbol2)
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)))   {
            if (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 1;
                return true;
            } else if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            }
        }

        //DIAGONAL 2
        if ((boardd[2][0] != null && boardd[2][0].toString().contains(symbol2)) &&
                (boardd[1][1] != null && boardd[1][1].toString().contains(symbol1)) &&
                boardd[0][2] != null && boardd[0][2].toString().contains(symbol2)
                && (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 0;
                return true;
            } else if(boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 2;
                return true;
            }
        } else if (boardd[2][0] != null && boardd[0][2] != null && boardd[1][1] != null &&
                boardd[2][0].toString().contains(symbol1)
                && boardd[0][2].toString().contains(symbol2) && boardd[1][1].toString().contains(symbol2)
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 1;
                return true;
            } else if (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 2;
                return true;
            }
        } else if (boardd[1][1] != null && boardd[0][2] != null && boardd[2][0] != null &&
                boardd[1][1].toString().contains(symbol2) &&
                boardd[0][2].toString().contains(symbol1) && boardd[2][0].toString().contains(symbol2)
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 1;
                return true;
            } else if (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 0;
                return true;
            }
        }

        //HORIZONTAL (ZEILE 1)
        if (boardd[0][0] != null && boardd[1][0] != null && boardd[2][0] != null &&
                boardd[0][0].toString().contains(symbol2) &&
                boardd[1][0].toString().contains(symbol1) && boardd[2][0].toString().contains(symbol2)
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            } else if (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 0;
                return true;
            }
        } else if (boardd[1][0] != null && boardd[2][0] != null && boardd[0][0] != null &&
                boardd[1][0].toString().contains(symbol2) &&
                boardd[2][0].toString().contains(symbol1) && boardd[0][0].toString().contains(symbol2)
                && (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 0;
                return true;
            } else {
                xtarget = 0;
                ytarget = 0;
                return true;
            }
        } else if (boardd[0][0] != null && boardd[2][0] != null && boardd[1][0] != null &&
                boardd[0][0].toString().contains(symbol1) &&
                boardd[2][0].toString().contains(symbol2) && boardd[1][0].toString().contains(symbol2)
                && (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 0;
                return true;
            } else {
                xtarget = 2;
                ytarget = 0;
                return true;
            }
        }

        //HORIZONTAL (ZEILE 2)
        if (boardd[0][1] != null && boardd[1][1] != null && boardd[2][1] != null &&
                boardd[0][1].toString().contains(symbol2) &&
                boardd[1][1].toString().contains(symbol1) && boardd[2][1].toString().contains(symbol2)
                && (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][1].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 1;
                return true;
            } else {
                xtarget = 2;
                ytarget = 1;
                return true;
            }

        } else if (boardd[1][1] != null && boardd[2][1] != null && boardd[0][1] != null &&
                boardd[1][1].toString().contains(symbol2) &&
                boardd[2][1].toString().contains(symbol1) && boardd[0][1].toString().contains(symbol2)
                && (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][1].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 1;
                return true;
            } else {
                xtarget = 0;
                ytarget = 1;
                return true;
            }
        } else if (boardd[0][1] != null && boardd[2][1] != null && boardd[1][1] != null &&
                boardd[0][1].toString().contains(symbol1) &&
                boardd[2][1].toString().contains(symbol2) && boardd[1][1].toString().contains(symbol2)
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][1].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 1;
                return true;
            } else {
                xtarget = 2;
                ytarget = 1;
                return true;
            }

        }

        //HORIZONTAL (ZEILE 3)
        if (boardd[0][2] != null && boardd[1][2] != null && boardd[2][2] != null &&
                boardd[0][2].toString().contains(symbol2) &&
                boardd[1][2].toString().contains(symbol1) && boardd[2][2].toString().contains(symbol2)
                && (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 2;
                return true;
            } else {
                xtarget = 2;
                ytarget = 2;
                return true;
            }
        } else if (boardd[1][2] != null && boardd[2][2] != null && boardd[0][2] != null &&
                boardd[1][2].toString().contains(symbol2) &&
                boardd[2][2].toString().contains(symbol1) && boardd[0][2].toString().contains(symbol2)
                && (boardd[0][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 2;
                return true;
            } else {
                xtarget = 0;
                ytarget = 2;
                return true;
            }

        } else if (boardd[0][2] != null && boardd[2][2] != null && boardd[1][2] != null &&
                boardd[0][2].toString().contains(symbol1) &&
                boardd[2][2].toString().contains(symbol2) && boardd[1][2].toString().contains(symbol2)
                && (boardd[1][2].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][2].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 2;
                return true;
            } else {
                xtarget = 2;
                ytarget = 2;
                return true;
            }
        }

        //VERTICAL (SPALTE 1)
        if (boardd[0][0] != null && boardd[0][1] != null && boardd[0][2] != null &&
                boardd[0][0].toString().contains(symbol2) &&
                boardd[0][1].toString().contains(symbol1) && boardd[0][2].toString().contains(symbol2)
                && (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 0;
                return true;
            } else {
                xtarget = 0;
                ytarget = 2;
                return true;
            }

        } else if (boardd[0][1] != null && boardd[0][2] != null && boardd[0][0] != null &&
                boardd[0][1].toString().contains(symbol2) &&
                boardd[0][2].toString().contains(symbol2) && boardd[0][0].toString().contains(symbol1)
                && (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 1;
                return true;
            } else {
                xtarget = 0;
                ytarget = 2;
                return true;
            }

        } else if (boardd[0][0] != null && boardd[0][2] != null && boardd[0][1] != null &&
                boardd[0][0].toString().contains(symbol2) &&
                boardd[0][2].toString().contains(symbol1) && boardd[0][1].toString().contains(symbol2)
                && (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[0][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[0][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 0;
                ytarget = 1;
                return true;
            } else {
                xtarget = 0;
                ytarget = 0;
                return true;
            }
        }

        //VERTICAL (SPALTE 2)
        if (boardd[1][0] != null && boardd[1][1] != null && boardd[1][2] != null &&
                boardd[1][0].toString().contains(symbol2) &&
                boardd[1][1].toString().contains(symbol1) && boardd[1][2].toString().contains(symbol2)
                && (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 0;
                return true;
            } else {
                xtarget = 1;
                ytarget = 2;
                return true;
            }

        } else if (boardd[1][1] != null && boardd[1][2] != null && boardd[1][0] != null &&
                boardd[1][1].toString().contains(symbol2) &&
                boardd[1][2].toString().contains(symbol2) && boardd[1][0].toString().contains(symbol1)
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 1;
                return true;
            } else {
                xtarget = 1;
                ytarget = 2;
                return true;
            }

        } else if (boardd[1][0] != null && boardd[1][2] != null && boardd[1][1] != null &&
                boardd[1][0].toString().contains(symbol2) &&
                boardd[1][2].toString().contains(symbol1) && boardd[1][1].toString().contains(symbol2)
                && (boardd[1][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[1][0].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[1][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 1;
                ytarget = 0;
                return true;
            } else {
                xtarget = 1;
                ytarget = 1;
                return true;
            }
        }

        //VERTICAL (SPALTE 3)
        if (boardd[2][0] != null && boardd[2][1] != null && boardd[2][2] != null &&
                boardd[2][0].toString().contains(symbol2) &&
                boardd[2][1].toString().contains(symbol1) && boardd[2][2].toString().contains(symbol2)
                && (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 0;
                return true;
            } else {
                xtarget = 2;
                ytarget = 2;
                return true;
            }

        } else if (boardd[2][1] != null && boardd[2][2] != null && boardd[2][0] != null &&
                boardd[2][1].toString().contains(symbol2) &&
                boardd[2][2].toString().contains(symbol2) && boardd[2][0].toString().contains(symbol1)
                && (boardd[2][1].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][2].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 1;
                return true;
            } else {
                xtarget = 2;
                ytarget = 2;
                return true;
            }

        } else if (boardd[2][0] != null && boardd[2][2] != null && boardd[2][1] != null &&
                boardd[2][0].toString().contains(symbol2) &&
                boardd[2][2].toString().contains(symbol1) && boardd[2][1].toString().contains(symbol2)
                && (boardd[2][0].value() < AIpieces.get(AIpieces.size()-1) ||
                boardd[2][1].value() < AIpieces.get(AIpieces.size()-1))) {
            if (boardd[2][1].value() < AIpieces.get(AIpieces.size()-1)) {
                xtarget = 2;
                ytarget = 1;
                return true;
            } else {
                xtarget = 2;
                ytarget = 0;
                return true;
            }
        }
        return false;
    }  //Intercept winning move (uses no empty cells)
}