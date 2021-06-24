package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Logic {
    private Map<String, String> flashcards = new LinkedHashMap<>();;
    private Map<String, Integer> stats = new TreeMap<>();
    private ArrayList<String> logs = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public void addCard() {
        addToLogAndPrint("The card:");
        String card = scanner.nextLine();
        logs.add(card);

        if (flashcards.containsKey(card)) {
            addToLogAndPrint("The card \"" + card + "\" already exists.");
        } else {
            addToLogAndPrint("The definition of the card:");
            String definition = scanner.nextLine();
            if (flashcards.containsValue(definition)) {
                addToLogAndPrint("The definition \"" + definition + "\" already exists.");
            } else {
                flashcards.put(card, definition);
                stats.put(card, 0);
                addToLogAndPrint("The pair (\"" + card + "\":\"" + definition + "\") has been added.");
            }
        }
    }

    public void removeCard() {
        addToLogAndPrint("Which card?");
        String card = scanner.nextLine();
        logs.add(card);

        if (flashcards.containsKey(card)) {
            flashcards.remove(card);
            stats.remove(card);
            addToLogAndPrint("The card has been removed.");
        } else {
            addToLogAndPrint("Can't remove \"" + card + "\": there is no such card.");
        }
    }

    public void ask() {
        String cardTerm;
        String cardDefinition;
        int numberOfMistakes;

        addToLogAndPrint("How many times to ask?");
        int numberOfQuestions = Integer.valueOf(scanner.nextLine());
        logs.add(String.valueOf( numberOfQuestions));

        List<String> terms = new ArrayList<>(flashcards.keySet());
        for (int i = 0; i < numberOfQuestions; i++) {
            Random random = new Random();
            cardTerm = terms.get(random.nextInt(terms.size()));
            cardDefinition = flashcards.get(cardTerm);
            numberOfMistakes = stats.get(cardTerm);

            addToLogAndPrint("Print the definition of \"" + cardTerm + "\":");
            String answer = scanner.nextLine();
            logs.add(answer);

            if (cardDefinition.equals(answer)) {
                addToLogAndPrint("Correct!");
            } else if (flashcards.containsValue(answer)) {
                String correctKey= getCorrectKey(answer);
                addToLogAndPrint("Wrong. The right answer is \"" + cardDefinition + "\", " +
                        "but your definition is correct for \"" + correctKey + "\".");
                numberOfMistakes++;
            } else {
                addToLogAndPrint("Wrong. The right answer is \"" + cardDefinition + "\".");
                numberOfMistakes++;
            }
            stats.put(cardTerm, numberOfMistakes);
        }
    }

    private String getCorrectKey(String answer) {
        String result = "";
        for (var entry: flashcards.entrySet()) {
            if (entry.getValue().equals(answer)) {
                result = entry.getKey();
            }
        }
        return result;
    }

    public void exportCard() {
        addToLogAndPrint("File name:");
        String fileName = scanner.nextLine();
        logs.add(fileName);
        exportCard(fileName);
    }
    public void exportCard(String exportCard) {

        File file = new File(exportCard);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (Map.Entry<String, String> entry : flashcards.entrySet()) {
                printWriter.println(entry.getKey());
                printWriter.println(entry.getValue());
                printWriter.println(stats.get(entry.getKey()));
            }
        } catch (IOException e) {
            addToLogAndPrint("ERROR: An exception occurs " + e.getMessage());
        }
        addToLogAndPrint((flashcards.size() + " cards have been saved."));
    }

    public void importCard() {
        addToLogAndPrint("File name:");
        String fileName = scanner.nextLine();
        logs.add(fileName);
        importCard(fileName);
    }

    public void importCard(String importCard) {
        String cardTerm;
        String cardDefinition;
        int count = 0;
        int numberOfMistakes;

        File file = new File(importCard);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                cardTerm = sc.nextLine();
                cardDefinition = sc.nextLine();
                numberOfMistakes = Integer.parseInt(sc.nextLine());
                logs.add(cardTerm);
                logs.add(cardDefinition);
                flashcards.put(cardTerm, cardDefinition);
                stats.put(cardTerm, numberOfMistakes);
                count++;
            }
        } catch (FileNotFoundException e) {
            addToLogAndPrint("File not found.");
        }
        if (count == 0) {
            addToLogAndPrint("File not found.");
        }
        addToLogAndPrint((count + " cards have been loaded."));
    }
    public void log() {
        addToLogAndPrint("File name:");
        String logFile = scanner.nextLine();
        logs.add(logFile);

        File file = new File(logFile);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (String entry : logs) {
                printWriter.println(entry);
            }
        } catch (IOException e) {
            addToLogAndPrint("ERROR: An exception occurs " + e.getMessage());
        }
        addToLogAndPrint("The log has been saved.");
    }

    public void resetStats() {
        stats.replaceAll((k, v) -> 0);
        addToLogAndPrint("Card statistics have been reset.");
    }

    public void addToLogAndPrint(String input) {
        System.out.println(input);
        logs.add(input);
    }

    public void addToLog(String input) {
        logs.add(input);
    }

    public void getHardestCard() {
        int maxNumberOfErrors = 0;
        int cardsWithWrongAnswers = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The hardest card is ");
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            if (entry.getValue() > 0) {
                maxNumberOfErrors = maxNumberOfErrors < entry.getValue() ? entry.getValue() : maxNumberOfErrors;
            }
        }
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            if (entry.getValue() > 0) {
                if (entry.getValue() == maxNumberOfErrors) {
                    stringBuilder.append('\u0022').append(entry.getKey()).append('\u0022').append(",");
                    cardsWithWrongAnswers++;
                }
            }
        }
        if (cardsWithWrongAnswers > 1) {
            stringBuilder.insert(16,"s").delete(18, 20).insert(18, "are");
        } else {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(".");
        }
        if (cardsWithWrongAnswers == 1 && maxNumberOfErrors > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(". You have ")
                    .append(maxNumberOfErrors).append(" errors answering it.");
        }
        if (maxNumberOfErrors > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(". You have ")
                    .append(maxNumberOfErrors).append(" errors answering them.");
        } else {
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append("There are no cards with errors.");
        }
        addToLogAndPrint(stringBuilder.toString());
        stringBuilder.delete(0,stringBuilder.length());
    }

}
