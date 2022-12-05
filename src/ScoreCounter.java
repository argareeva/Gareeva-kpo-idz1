class ScoreCounter {

    static int currentPlayer1Score = 0;
    static int currentPlayer2Score = 0;
    private static int bestPlayerScore = 0;

    static void showScores() {
        System.out.println("Player 'x' scores: " + currentPlayer1Score
                + "\nPlayer 'o' scores: " + currentPlayer2Score + "\n");
    }

    static void showResultScores() {
        saveBestScoreOfPlayer1();
        if (currentPlayer1Score > currentPlayer2Score) {
            System.out.printf("Player 'x' won by %d scores!", currentPlayer1Score - currentPlayer2Score);
            saveBestScoreOfPlayer1();
            System.out.print("\n\n\n");
        } else if (currentPlayer1Score < currentPlayer2Score) {
            System.out.printf("Player 'o' won by %d scores!", currentPlayer2Score - currentPlayer1Score);
            saveBestScoreOfPlayer1();
            System.out.print("\n\n\n");
        } else {
            System.out.println("Wow! Dead head!");
            saveBestScoreOfPlayer1();
            System.out.print("\n\n\n");
        }
    }

    private static void saveBestScoreOfPlayer1() {
        if (currentPlayer1Score > bestPlayerScore) {
            bestPlayerScore = currentPlayer1Score;
        }
    }

    static void showBestScore() {
        System.out.printf("Player x has the best score %d in this session", bestPlayerScore);
        System.out.println();
    }
}
