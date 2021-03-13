package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Scanner scanner = new Scanner(System.in);            stage 4
        // System.out.print("Enter cells: ");                   stage 4
        // String xoStr = scanner.nextLine();                   stage 4

        String xoStr = "         ";
        Griglia griglia = new Griglia(xoStr);
        griglia.start();
    }
}

class Griglia {

    Scanner scanner = new Scanner(System.in);
    private final String xoStr;
    private final char[][] xo = new char[3][3];
    private boolean xWin = false;
    private boolean oWin = false;
    private boolean game = true;
    private static int turn = 0;

    /**
     * Costruttore.
     *
     * @param xo stringa
     */
    protected Griglia(String xo) {
        this.xoStr = xo;
    }

    /**
     * Chiama il metodo per convertire la stringa in array 2D. <br>
     * Chiama il metodo per stampare la griglia. <br>
     * Chiama il metodo per chiedere all'utente di inserire la prima mossa.
     */
    protected void start() {
        stringTo2DArray();
        printGriglia();

        while (game) {
            firstMove();
            fieldSituation();
        }
    }

    /**
     * Chiede all'utente la prima mossa.
     */
    private void firstMove() {

        boolean wrongInput = true;
        int xCord;
        int yCord;

        while (wrongInput) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();

            try {
                String[] pieces = input.split(" ");
                xCord = Integer.parseInt(pieces[0]);
                yCord = Integer.parseInt(pieces[1]);

                if (xCord < 1 || xCord > 3 || yCord < 1 || yCord > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (xo[xCord - 1][yCord - 1] == 'X' || xo[xCord - 1][yCord - 1] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        chooseTurn(xCord, yCord);
                        wrongInput = false;
                        printGriglia();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    /**
     * Scegli il simbolo da inserire nella griglia
     * @param xCord coordinata x
     * @param yCord coordinata y
     */
    private void chooseTurn(int xCord, int yCord) {
        if (turn % 2 == 0) {
            xo[xCord - 1][yCord - 1] = 'X';
        } else {
            xo[xCord - 1][yCord - 1] = 'O';
        }
        turn++;
    }

    /**
     * Converte la stringa in un 2D array.
     */
    private void stringTo2DArray() {

        int count = 0;

        for (int i = 0; i < xo.length; i++) {
            for (int j = 0; j < xo.length; j++) {
                if (xoStr.charAt(count) == '_') {
                    xo[i][j] = ' ';
                } else {
                    xo[i][j] = xoStr.charAt(count);
                }
                count++;
            }
        }
    }

    /**
     * Stampa la griglia.
     */
    protected void printGriglia() {
        System.out.println("---------");
        System.out.println("| " + xo[0][0] + " " + xo[0][1] + " " + xo[0][2] + " |");
        System.out.println("| " + xo[1][0] + " " + xo[1][1] + " " + xo[1][2] + " |");
        System.out.println("| " + xo[2][0] + " " + xo[2][1] + " " + xo[2][2] + " |");
        System.out.println("---------");
    }

    /**
     * Stampa la situazione sul campo.
     */
    protected void fieldSituation() {

        int i;
        int j;
        int countO = 0;
        int countX = 0;

        // Conta X e O
        for (char[] c : xo) {
            for (i = 0; i < xo.length; i++) {
                if (c[i] == 'X') {
                    countX++;
                }
                if (c[i] == 'O') {
                    countO++;
                }
            }
        }

        // X wins obliquo
        if (!xWin && xo[0][0] == 'X' && xo[1][1] == 'X' && xo[2][2] == 'X') {
            xWin = true;
        }
        if (!xWin && xo[0][2] == 'X' && xo[1][1] == 'X' && xo[2][0] == 'X') {
            xWin = true;
        }

        // X wins orizzontale
        for (i = 0; i < xo.length; i++) {
            for (j = 0; j < 1; j++) {
                if (!xWin && xo[i][j] == 'X' && xo[i][j + 1] == 'X' && xo[i][j + 2] == 'X') {
                    xWin = true;
                }
            }
        }

        // X wins verticale
        for (i = 0; i < 1; i++) {
            for (j = 0; j < xo.length; j++) {
                if (!xWin && xo[i][j] == 'X' && xo[i + 1][j] == 'X' && xo[i + 2][j] == 'X') {
                    xWin = true;
                }
            }
        }

        // O wins obliquo
        if (!oWin && xo[0][0] == 'O' && xo[1][1] == 'O' && xo[2][2] == 'O') {
            oWin = true;
        }
        if (!oWin && xo[0][2] == 'O' && xo[1][1] == 'O' && xo[2][0] == 'O') {
            oWin = true;
        }

        // O wins orizzontale
        for (i = 0; i < xo.length; i++) {
            for (j = 0; j < 1; j++) {
                if (!oWin && xo[i][j] == 'O' && xo[i][j + 1] == 'O' && xo[i][j + 2] == 'O') {
                    oWin = true;
                }
            }
        }

        // O wins verticale
        for (i = 0; i < 1; i++) {
            for (j = 0; j < xo.length; j++) {
                if (!oWin && xo[i][j] == 'O' && xo[i + 1][j] == 'O' && xo[i + 2][j] == 'O') {
                    oWin = true;
                }
            }
        }

        // Controllo varie situazioni
        if (xWin && oWin || countX - countO > 1 || countO - countX > 1) {
            System.out.println("Impossible");
            game = false;
        } else if (xWin) {
            System.out.println("X wins");
            game = false;
        } else if (oWin) {
            System.out.println("O wins");
            game = false;
        } else if ((xo[0][0] == 'X' || xo[0][0] == 'O') && (xo[0][1] == 'X' || xo[0][1] == 'O') &&
                   (xo[0][2] == 'X' || xo[0][2] == 'O') && (xo[1][0] == 'X' || xo[1][0] == 'O') &&
                   (xo[1][1] == 'X' || xo[1][1] == 'O') && (xo[1][2] == 'X' || xo[1][2] == 'O') &&
                   (xo[2][0] == 'X' || xo[2][0] == 'O') && (xo[2][1] == 'X' || xo[2][1] == 'O') &&
                   (xo[2][2] == 'X' || xo[2][2] == 'O')) {
            System.out.println("Draw");
            game = false;
        } else {
            // Game not finished
            for (i = 0; i < xo.length; i++) {
                for (j = 0; j < xo.length; j++) {
                    if (xo[i][j] == '_') {
                        System.out.println("Game not finished");
                    }
                }
            }
        }
    }

}