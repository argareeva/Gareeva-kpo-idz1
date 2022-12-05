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

    protected void playerMoves(int playerType) {
        int x = 0, y = 0;
        boolean currentFlag = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                if (playerType == 1) {
                    System.out.println("Player 'x' please enter a move");
                } else {
                    System.out.println("Player 'o' please enter a move");
                }
                System.out.println("Enter a row : ");
                x = input.nextInt();
                System.out.println("Enter a column : ");
                y = input.nextInt();
                if (!board.correctPos(x - 1, y - 1)) {
                    currentFlag = false;
                } else {
                    currentFlag = true;
                }
            } catch (Exception e) {
                currentFlag = false;
                System.out.println("Wrong coordinates, try again");
            }
        } while (!currentFlag);
        board.makeMove(x - 1, y - 1, playerType);
        board.renewBoardAfterPlayer1Move();
        board.showBoard();
    }
}