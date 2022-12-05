class StartEasyGame extends NewGame {

    private boolean playerMadeAMove;
    private static final int EASY_GAME = 1;

    StartEasyGame() {
        super();
        playerMadeAMove = true;
    }

    void game() {
        while (flag) {
            makeMoves();
        }
        ScoreCounter.showResultScores();
    }

    public void makeMoves() {
        if (board.flag) {
            board.showBoard();
            playerMoves(1);
            playerMadeAMove = true;
        } else {
            if (!playerMadeAMove) {
                flag = false;
                return;
            }
            System.out.println("No possible moves to you");
            playerMadeAMove = false;
        }
        board.calculatePossibleMoves(2, 1);
        if (board.flag) {
            computerMoves();
        } else {
            if (!playerMadeAMove) {
                flag = false;
                return;
            }
        }
        board.calculatePossibleMoves(1, 2);
        board.fillPossibleMoves();
    }

    void computerMoves() {
        board.makeComputerMove();
        board.renewBoardAfterCompMove();
    }
}

