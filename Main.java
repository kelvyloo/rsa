import java.util.Scanner;

public class Main {
    public static int mod_exp(int b, int m, int n) {
        // Modular exponentiation method
        // Inputs are b, m, n
        // Returns the value of x = b^n mod m where b, m, and n are positive ints
        String n_binary = "";
        int x = 1;
        int power;

        while(n > 0) {
            int tmp = n % 2;
            n_binary = tmp + n_binary;
            n /= 2;
        }

        power = b % m;

        for (int i = 0; i < n_binary.length(); i++) {
            if (n_binary.charAt(i) == 1) {
                x = (x * power) % m;
            }
            power = power * power % m;
        }

        return x;
    }
    public static void main(String[] args) {
        int b, m, n, x;

        Scanner scanner = new Scanner(System.in);
        System.out.println("This a modular exponentiation calculator!");

        System.out.printf("Please enter a number b: ");
        b = scanner.nextInt();

        System.out.printf("Please enter a number m: ");
        m = scanner.nextInt();

        System.out.printf("Please enter a number n: ");
        n = scanner.nextInt();

        x = Main.mod_exp(b, m ,n);

        Encoder encoder = new Encoder("Hello", 362783, 19);
        encoder.letter_to_int();

        System.out.printf("%d^%d %% %d = %d", b, n, m, x);
    }
}
