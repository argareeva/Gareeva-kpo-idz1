class StartPlayersGame extends NewGame {
    boolean ifPlayer1CanMove;
    boolean ifPlayer2CanMove;

    /**
     * Class constructor
     * Sets the players modes, if it is possible to go
     */
    StartPlayersGame() {
        super();
        ifPlayer1CanMove = true;
        ifPlayer2CanMove = true;
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
     * Two players do some moves until the game ends
     */
    public void makeMoves() {
        if (board.flag) {
            board.showBoard();
            playerMoves(1);
            ifPlayer1CanMove = true;
        } else {
            if (!ifPlayer2CanMove) {
                flag = false;
                return;
            }
            System.out.println("No possible moves to you");
            ifPlayer1CanMove = false;
        }
        board.countPossibleMoves(2, 1);
        board.changePossibleMoves();
        if (board.flag) {
            board.showBoard();
            playerMoves(2);
            ifPlayer2CanMove = true;
        } else {
            if (!ifPlayer1CanMove) {
                flag = false;
                return;
            } else {
                System.out.println("No possible moves to you");
                ifPlayer2CanMove = false;
            }
        }
        board.countPossibleMoves(1, 2);
        board.changePossibleMoves();
    }
}