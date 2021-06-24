import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SortedMap<Integer, String> map = new TreeMap<>();
        String[] range = scanner.nextLine().split(" ");
        int rangeMin = Integer.parseInt(range[0]);
        int rangeMax = Integer.parseInt(range[1]);
        int size = Integer.parseInt(scanner.nextLine());

        while (scanner.hasNext()) {
            String[] temp = scanner.nextLine().split(" ");
            int key = Integer.parseInt(temp[0]);
            String value = temp[1];
            if (key >= rangeMin && key <= rangeMax) {
                map.put(key, value);
            }
        }

        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}