import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] word1 = scanner.nextLine().toLowerCase().split("");
        String[] word2 = scanner.nextLine().toLowerCase().split("");

        Map<String, Integer> count = new HashMap<>();
        for (String value : word1) {
            int current = count.getOrDefault(value, 0);
            count.put(value, current + 1);
        }
        for (String value : word2) {
            int current = count.getOrDefault(value, 0);
            count.put(value, current - 1);
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            int diff = entry.getValue();
            int times = Math.abs(diff);
            for (int i = 0; i < times; i++) {
                result.add(entry.getKey());
            }
        }
        System.out.println(result.size());

    }
}