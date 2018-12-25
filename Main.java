import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Modular exponentiation function
        // Inputs are b, m, n
        // Returns the value of x = b^n mod m where b, m, and n are positive ints

        Scanner scanner = new Scanner(System.in);
        int b, m, n;
        String n_binary = "";
        int x = 1;
        int power;

        System.out.println("This a modular exponentiation calculator!");

        System.out.printf("Please enter a number b: ");
        b = scanner.nextInt();

        System.out.printf("Please enter a number m: ");
        m = scanner.nextInt();

        System.out.printf("Please enter a number n: ");
        n = scanner.nextInt();

        while(n > 0) {
            int tmp = n % 2;
            n_binary = tmp + n_binary;
            n /= 2;
        }

        power = b % m;

        for (int i = 0; i < n_binary.length(); i++) {
            System.out.println(n_binary.length());
            if (n_binary.charAt(i) == 1) {
                x = (x * power) % m;
            }
            power = power * power % m;
        }

        System.out.printf("%d^%d %% %d = %d", b, Integer.parseInt(n_binary, 2), m, x);
    }
}
