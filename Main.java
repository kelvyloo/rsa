import java.util.Arrays;
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
     * @param base  Base
     * @param mod   Modulo
     * @param exp   Exponent
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
        String secret_msg;
        long public_key, private_key;
        long encoded_msg[];

        Scanner scanner = new Scanner(System.in);
        System.out.println("This is a simple encoder!");

        System.out.printf("Please enter your secret message: ");
        secret_msg = scanner.nextLine();

        System.out.printf("Please enter the public key n: ");
        public_key = scanner.nextLong();

        System.out.printf("Please enter your private key e: ");
        private_key = scanner.nextLong();

        Encoder encoder = new Encoder(secret_msg, public_key, private_key);
        encoded_msg = encoder.encode();

        System.out.println(Arrays.toString(encoded_msg));
    }
}
