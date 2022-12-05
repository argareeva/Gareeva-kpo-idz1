import java.util.Scanner;

public class Reversi {

    /**
     * Driver method for the program. Starts the game.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("Welcome to Reversi Game!\nPlease make your choice and press Enter:");
            System.out.println("1. Easy (comp vs you)");
            System.out.println("2. Medium (comp vs you)");
            System.out.println("3. Player1 vs Player2");
            System.out.println("4. Exit");

            command = scanner.nextLine();

            switch (command) {
                case "1" -> {
                    StartEasyGame easyLevel = new StartEasyGame();
                    easyLevel.game();
                }
                case "2" -> System.out.println("Sorry, no medium level");
                case "3" -> {
                    StartPlayersGame playersLevel = new StartPlayersGame();
                    playersLevel.game();
                }
                case "4" -> ScoreCounter.showBestScore();
                default -> System.out.println("Command not recognized! Please try again");
            }
        }
        while (!command.equals("4"));
    }
}