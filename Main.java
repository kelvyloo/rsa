import java.util.Scanner;

public class Main {
    /**
     * Fast modular exponentiation calculator
     * Calculates the b^e mod m by converting the exponent into its binary seq
     * (i.e. 117 = 1110101).
     * Starting from the LSB if the bit is 1 then calculate base^bit_power mod m
     * (i.e 5^1 mod 19, 5^2 mod 19, 5^4 mod 19, etc.)
     * The product of those successive modular operations is the answer;
     *
     *
     * @base  Base
     * @mod   Modulo
     * @exp   Exponent
     *
     * @return b^e mod m
     * */
    public static long mod_exp(long base, long mod, long exp) {
        String n_binary = "";
        long x = 1;
        long power;

        while(exp > 0) {
            long tmp = exp % 2;
            n_binary = tmp + n_binary;
            exp /= 2;
        }

        power = base % mod;

        for (int i = 0; i < n_binary.length(); i++) {
            if (n_binary.charAt(i) == 1) {
                x = (x * power) % mod;
            }
            power = power * power % mod;
        }

        return x;
    }

    public static void main(String[] args) {
        long b, m, n, x;
        long encoded_msg[];

        Scanner scanner = new Scanner(System.in);
        System.out.println("This is a simple encoder!");

        System.out.printf("Please enter a number b: ");
        b = scanner.nextLong();

        System.out.printf("Please enter a number m: ");
        m = scanner.nextLong();

        System.out.printf("Please enter a number n: ");
        n = scanner.nextLong();

        x = Main.mod_exp(b, m ,n);

        Encoder encoder = new Encoder("Hello", 362783, 19);
        encoded_msg = encoder.encode();

        System.out.printf("%d^%d %% %d = %d", b, n, m, x);
    }
}
