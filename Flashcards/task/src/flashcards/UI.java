package flashcards;

import java.util.Scanner;

public class UI {
    final static String ADD = "add";
    final static String REMOVE = "remove";
    final static String IMPORT = "import";
    final static String EXPORT = "export";
    final static String ASK = "ask";
    final static String EXIT = "exit";
    final static String LOG = "log";
    final static String HARDEST_CARD = "hardest card";
    final static String RESET_STATS = "reset stats";

    static void printUI(String fileImport, String fileExport) {
        Logic logic = new Logic();
        Scanner scanner = new Scanner(System.in);
        boolean isLooping = true;

        if(fileImport != null) {
            logic.importCard(fileImport);
        }

        while (isLooping) {
            logic.addToLogAndPrint("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String userInput = scanner.nextLine();
            logic.addToLog(userInput);

            switch (userInput) {
                case ADD:
                    logic.addCard();
                    break;
                case REMOVE:
                    logic.removeCard();
                    break;
                case IMPORT:
                    logic.importCard();
                    break;
                case EXPORT:
                    logic.exportCard();
                    break;
                case ASK:
                    logic.ask();
                    break;
                case LOG:
                    logic.log();
                    break;
                case HARDEST_CARD:
                    logic.getHardestCard();
                    break;
                case RESET_STATS:
                    logic.resetStats();
                    break;
                case EXIT:
                    isLooping = false;
                    break;
            }
        }
        System.out.println("Bye bye!");
        if(fileExport != null) {
            logic.exportCard(fileExport);
        }

    }
}
