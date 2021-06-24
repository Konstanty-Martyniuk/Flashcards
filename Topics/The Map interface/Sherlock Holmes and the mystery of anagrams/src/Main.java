import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] word1 = scanner.nextLine().toLowerCase().split("");
        String[] word2 = scanner.nextLine().toLowerCase().split("");

        SortedMap<String, Integer> map1 = new TreeMap<>();
        SortedMap<String, Integer> map2 = new TreeMap<>();
        Arrays.asList(word1).forEach(s -> map1.merge(s, 1, Integer::sum));
        Arrays.asList(word2).forEach(s -> map2.merge(s, 1, Integer::sum));
        System.out.println(Objects.equals(map1, map2) ? "yes" : "no");
    }
}