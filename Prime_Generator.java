import java.util.Random;

/**
 * Prime number generator class
 * Generates N-digit long prime numbers to create public and private keys
 *
 */
public class Prime_Generator {
    int digits_n;
    boolean is_prime;
    long prime_num;

    private long number_gen() {
        Random digit = new Random();
        String num_str = "";

        for (int i = 0; i < digits_n; i++) {
            num_str += String.valueOf(digit.nextInt(9));
        }
        char last_digit = num_str.charAt(digits_n - 1);

        while (last_digit != '1' || last_digit != '3' || last_digit != '7') {
            last_digit = (char) (digit.nextInt(7) + '0');
        }
        num_str = num_str.substring(0, digits_n - 1) + last_digit;

        return Long.valueOf(num_str);
    }

    public Prime_Generator(int digits_n)
    {
        this.digits_n = digits_n;
        this.is_prime = false;

        while (!is_prime) {
            prime_num = number_gen();
        }
    }
}
