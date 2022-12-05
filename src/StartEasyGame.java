class StartEasyGame extends NewGame {

    private boolean playerMove;

    /**
     * Class constructor
     * Sets the player mode, if it is possible to go
     */
    StartEasyGame() {
        super();
        playerMove = true;
    }

    /**
     * New game begins
     */
    void game() {
        while (flag) {
            makeMoves();
        }
        ScoreCounter.showResultScores();
    }

    /**
     * A player does some moves until the game ends
     */
    public void makeMoves() {
        if (board.flag) {
            board.showBoard();
            playerMoves(1);
            playerMove = true;
        } else {
            if (!playerMove) {
                flag = false;
                return;
            }
            System.out.println("No possible moves to you");
            playerMove = false;
        }
        board.countPossibleMoves(2, 1);
        if (board.flag) {
            computerMoves();
        } else {
            if (!playerMove) {
                flag = false;
                return;
            }
        }
        board.countPossibleMoves(1, 2);
        board.changePossibleMoves();
    }

    void computerMoves() {
        board.makeComputerMove();
        board.renewBoardAfterCompMove();
    }
}