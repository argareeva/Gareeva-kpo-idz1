class ScoreCounter {

    static int firstPlayerScore = 0;
    static int secondPlayerScore = 0;
    private static int bestPlayerScore = 0;

    static void showScores() {
        System.out.println("Player 'x' scores: " + firstPlayerScore
                + "\nPlayer 'o' scores: " + secondPlayerScore + "\n");
    }

    static void showResultScores() {
        saveBestScoreOfPlayer1();
        if (firstPlayerScore > secondPlayerScore) {
            System.out.printf("Player 'x' won by %d scores!", firstPlayerScore - secondPlayerScore);
            saveBestScoreOfPlayer1();
            System.out.print("\n\n\n");
        } else if (firstPlayerScore < secondPlayerScore) {
            System.out.printf("Player 'o' won by %d scores!", secondPlayerScore - firstPlayerScore);
            saveBestScoreOfPlayer1();
            System.out.print("\n\n\n");
        } else {
            System.out.println("Wow! Dead head!");
            saveBestScoreOfPlayer1();
            System.out.print("\n\n\n");
        }
    }

    private static void saveBestScoreOfPlayer1() {
        if (firstPlayerScore > bestPlayerScore) {
            bestPlayerScore = firstPlayerScore;
        }
    }

    static void showBestScore() {
        System.out.printf("Player x has the best score %d in this session", bestPlayerScore);
        System.out.println();
    }
}
