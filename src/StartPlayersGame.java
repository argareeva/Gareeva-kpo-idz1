class StartPlayersGame extends NewGame {
    boolean ifPlayer1CanMove;
    boolean ifPlayer2CanMove;

    StartPlayersGame() {
        super();
        ifPlayer1CanMove = true;
        ifPlayer2CanMove = true;
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
            ifPlayer1CanMove = true;
        } else {
            if (!ifPlayer2CanMove) {
                flag = false;
                return;
            }
            System.out.println("No possible moves to you");
            ifPlayer1CanMove = false;
        }
        board.calculatePossibleMoves(2, 1);
        board.fillPossibleMoves();
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
        board.calculatePossibleMoves(1, 2);
        board.fillPossibleMoves();
    }
}