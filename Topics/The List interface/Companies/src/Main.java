import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] yourList = scanner.nextLine().split(" ");
        System.out.println(Arrays.asList(yourList));
    }
}