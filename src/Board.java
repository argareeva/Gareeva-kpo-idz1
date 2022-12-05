import java.util.ArrayList;
import java.util.List;

class Board {

    private int[][] board;
    private final List<Cell> possibleMoves;
    private final List<List<Cell>> movesToFlip;
    public boolean flag;

    final int SIZE = 8;

    /**
     * Class constructor.
     * Sets the board with starting chips
     */
    Board() {
        newBoard();
        movesToFlip = new ArrayList<>();
        ArrayList<Cell> arr = new ArrayList<>();
        arr.add(new Cell(3, 3));
        movesToFlip.add(arr);
        arr = new ArrayList<>();
        arr.add(new Cell(3, 3));
        movesToFlip.add(arr);
        arr = new ArrayList<>();
        arr.add(new Cell(4, 4));
        movesToFlip.add(arr);
        arr = new ArrayList<>();
        arr.add(new Cell(4, 4));
        movesToFlip.add(arr);
        possibleMoves = new ArrayList<>();
        possibleMoves.add(new Cell(2, 3));
        possibleMoves.add(new Cell(3, 2));
        possibleMoves.add(new Cell(4, 5));
        possibleMoves.add(new Cell(5, 4));
        flag = true;
    }

    private void newBoard() {
        board = new int[8][8];
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][4] = 2;
        board[4][3] = 1;
        board[2][3] = 3;
        board[3][2] = 3;
        board[4][5] = 3;
        board[5][4] = 3;
    }

    /**
     * Creates the outlines and the row and column numbers of the board
     */
    void showBoard() {
        System.out.println("       1    2    3    4    5    6    7    8");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("  " + (i + 1) + "  ");
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.print("[   ]");
                } else if (board[i][j] == 1) {
                    System.out.print("[ x ]");
                } else if (board[i][j] == 3) {
                    System.out.print("[ ? ]");
                } else {
                    System.out.print("[ o ]");
                }
            }
            System.out.print("\n");
        }

        ScoreCounter.firstPlayerScore = countPossibleFlips(1);
        ScoreCounter.secondPlayerScore = countPossibleFlips(2);
        ScoreCounter.showScores();
    }

    /**
     * Places a chip on the board for a player
     *
     * @param move - the player's move
     * @param row - the row played
     * @param column - the column played
     */
    void makeMove(int row, int column, int move) {
        board[row][column] = move;
        int i = 0;
        while (possibleMoves.get(i).getRow() != row || possibleMoves.get(i).getColumn() != column) {
            i++;
        }
        for (Cell cell : movesToFlip.get(i)) {
            board[cell.getRow()][cell.getColumn()] = move;
        }
    }
    
    /**
     * Updates the board after player's move
     */
    void renewBoardAfterPlayer1Move() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
        possibleMoves.clear();
        movesToFlip.clear();
    }
    
    /**
     * Calculates the computer's move based on the best one
     */
    void makeComputerMove() {
        int bestMove = evaluationFunction();
        board[possibleMoves.get(bestMove).getRow()][possibleMoves.get(bestMove).getColumn()] = 2;
        for (Cell cell : movesToFlip.get(bestMove)) {
            board[cell.getRow()][cell.getColumn()] = 2;
        }
    }

    /**
     * Updates the board after computer's move
     */
    void renewBoardAfterCompMove() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
        possibleMoves.clear();
        movesToFlip.clear();
    }
    
    /**
     * Counts scores players can get
     *
     * @param firstPlayer - first player position
     * @param secondPlayer - second player position
     */
    void countPossibleMoves(int firstPlayer, int secondPlayer) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isMoveFree(firstPlayer, secondPlayer, i, j)) {
                    possibleMoves.add(new Cell(i, j));
                }
            }
        }
        flag = !possibleMoves.isEmpty();
    }

    /**
     * Calculates how many flips can player make
     *
     * @param move - chip mode(1, 2, 3)
     */
    int countPossibleFlips(int move) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == move) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Fills color of chip
     */
    void changePossibleMoves() {
        for (Cell cell : possibleMoves) {
            board[cell.getRow()][cell.getColumn()] = 3;
        }
    }

    /**
     * Checks if it is possible to move to the given cell
     *
     * @param player1 - first player chip's value(1,2,3)
     * @param player2 - second player chip's value(1,2,3)
     * @param row - current row move
     * @param column - current column move
     */
    boolean isMoveFree(int player1, int player2, int row, int column) {
        ArrayList<Cell> tmp = new ArrayList<>();
        if (board[row][column] != 0 || !correctPosition(row, column)) {
            return false;
        }
        board[row][column] = player1;
        int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int newRow, newColumn;
        for (int[] direction : directions) {
            newRow = row + direction[0];
            newColumn = column + direction[1];
            if (!correctPosition(newRow, newColumn)) {
                continue;
            }
            while (board[newRow][newColumn] == player2) {
                newRow += direction[0];
                newColumn += direction[1];
                if (!correctPosition(newRow, newColumn)) {
                    break;
                }
            }
            if (!correctPosition(newRow, newColumn)) {
                continue;
            }
            if (board[newRow][newColumn] == player1) {
                while (true) {
                    newRow -= direction[0];
                    newColumn -= direction[1];
                    if (newRow == row && newColumn == column) {
                        break;
                    }
                    tmp.add(new Cell(newRow, newColumn));
                }
            }
        }
        board[row][column] = 0;
        if (tmp.isEmpty()) {
            return false;
        }
        movesToFlip.add(tmp);
        return true;
    }

    /**
     * Checks if a move is in a board
     */
    private boolean correctPosition(int row, int column) {
        return (row >= 0 && row < SIZE && column >= 0 && column < SIZE);
    }

    /**
     * Checks if a token move is a possible move
     */
    boolean correctMove(int row, int column) {
        return correctPosition(row, column) && board[row][column] == 3;
    }

    /**
     * Checks if a move is on the boarders
     */
    private boolean isEnd(int row, int column) {
        return row == 0 || column == 0 || row == SIZE || column == SIZE;
    }

    /**
     * Calculates the best computer move based on evaluation function
     */
    int evaluationFunction() {
        int bestScore = 0;
        int bestMove = 0;
        for (int i = 0; i < possibleMoves.size(); i++) {
            int currentScore = 0;
            for (int j = 0; j < movesToFlip.get(i).size(); j++) {
                if (isEnd(movesToFlip.get(i).get(j).getRow(), movesToFlip.get(i).get(j).getColumn())) {
                    currentScore += 2;
                } else {
                    currentScore += 1;
                }
            }
            if (movesToFlip.get(i).size() == 1) {
                if (movesToFlip.get(i).get(0).getRow() == possibleMoves.get(i).getRow() ||
                        movesToFlip.get(i).get(0).getColumn() == possibleMoves.get(i).getColumn()) {
                    currentScore += 0.4;
                } else {
                    currentScore += 0.8;
                }
            }
            if (currentScore > bestScore) {
                bestMove = i;
                bestScore = currentScore;
            }
        }
        return bestMove;
    }
}
