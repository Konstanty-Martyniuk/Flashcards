import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> map = new TreeMap<>();

        Arrays.asList(strings).forEach(s -> map.merge(s, 1, Integer::sum));

        return map;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((letter, count) -> System.out.println(letter + " : " + count));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}