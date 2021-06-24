package flashcards;

public class Main {
    public static void main(String[] args) {
        String fileImport = null;
        String fileExport = null;

        for (int i = 0; i < args.length; i++) {
            if ("-import".equals(args[i])) {
                fileImport = args[i + 1];
            } else if ("-export".equals(args[i])) {
                fileExport = args[i + 1];
            }
        }

        UI.printUI(fileImport, fileExport);
    }
}

