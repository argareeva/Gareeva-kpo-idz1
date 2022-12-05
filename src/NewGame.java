import java.util.Scanner;

abstract class NewGame {
    Board board;
    protected boolean flag;
    protected int amountOfPlayerScores;

    NewGame() {
        board = new Board();
        amountOfPlayerScores = 0;
        flag = true;
    }

    protected abstract void makeMoves();

    /**
     * Interfaces a new game session
     *
     * @param mode - player/computer
     */
    protected void playerMoves(int mode) {
        int row = 0, column = 0;
        boolean flag;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                if (mode == 1) {
                    System.out.println("Player 'x' please enter a move");
                } else {
                    System.out.println("Player 'o' please enter a move");
                }
                System.out.println("Please, enter a row : ");
                row = scanner.nextInt();
                System.out.println("Please, enter a column : ");
                column = scanner.nextInt();
                flag = board.correctMove(row - 1, column - 1);
            } catch (Exception e) {
                System.out.println("Wrong coordinates, try again");
                break;
            }
        } while (!flag);
        board.makeMove(row - 1, column - 1, mode);
        board.renewBoardAfterPlayer1Move();
        board.showBoard();
    }
}