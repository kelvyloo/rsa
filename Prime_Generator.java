import java.util.Random;

/**
 * Prime number generator class
 * Generates N-digit long prime numbers to create public and private keys
 *
 */
public class Prime_Generator {
    int digits_n;
    long prime_num;

    public Prime_Generator(int digits_n)
    {
        this.digits_n = digits_n;

        Random digit = new Random();
        String num_str = "";

        for (int i = 0; i < digits_n; i++) {
            num_str += digit.nextInt(9);
        }
    }
}
