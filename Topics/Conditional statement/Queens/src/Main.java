import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double x1 = in.nextInt();
        double y1 = in.nextInt();
        double x2 = in.nextInt();
        double y2 = in.nextInt();
        boolean normMovement = Math.abs(x1 - x2) == Math.abs(y1 - y2) || x1 == x2 || y1 == y2;
        if (normMovement) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}