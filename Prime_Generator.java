import java.util.Random;
import java.util.stream.IntStream;

/**
 * Prime number generator class
 * Generates N-digit long prime numbers to create public and private keys
 *
 */
public class Prime_Generator {
    int digits_n;
    private boolean is_prime;
    long prime_num;

    /**
     * Creates a random N-digit long potential prime number
     *
     * @return N digit long potential prime number
     */
    private long number_gen() {
        Random digit = new Random();
        String num_str = "";

        for (int i = 0; i < digits_n; i++) {
            int rand_digit;

            rand_digit = digit.nextInt(9);
            if (i == 0) {
                while (rand_digit == 0) { rand_digit = digit.nextInt(9); }
            }
            num_str += String.valueOf(rand_digit);
        }
        char last_digit = num_str.charAt(digits_n - 1);

        while (Integer.valueOf(last_digit) % 2 == 0 || last_digit == '5') {
            last_digit = (char) (digit.nextInt(7) + '0');
        }
        num_str = num_str.substring(0, digits_n - 1) + last_digit;

        return Long.valueOf(num_str);
    }

    /**
     * Miller rabin primality test
     * Takes any size number n and tests if the number is prime by running the
     * Miller Rabin test for x number of witnesses (known prime numbers) below
     * some threshold (this particular method will use 500).
     *
     * @param potential_prime number that will be run through primality test
     *
     * @return true if n is prime (99.99%) else false
     */
    private boolean miller_rabin(long potential_prime) {
        int witnesses[] = {2, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                           53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
                           109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                           173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
                           233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283,
                           293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359,
                           367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431,
                           433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491,
                           499};
        boolean prime;

        if (potential_prime < 500) {
            prime = IntStream.of(witnesses).anyMatch(x -> x == potential_prime);
            return prime;
        }

        long d = (potential_prime-1) / 2;
        int r = 1;

        while (d % 2 == 0) {
            d /= 2;
            r = r++;
        }

        for (int witness : witnesses) {
            boolean passed_witness;
            long tmp;

            tmp = Main.mod_exp(witness, potential_prime, d);

            if (tmp == 1 || tmp == potential_prime-1) { continue; }

            passed_witness = false;

            for (int j = 0; j < r; j++)
            {
                tmp = tmp * tmp % potential_prime;

                if (tmp == 1) { return false; }

                else if (tmp == potential_prime - 1) {
                    passed_witness = true;
                    break;
                }
            }
            if (!passed_witness) { return false; }
        }

        return true;
    }

    /**
     * Class constructor for the prime generator
     * Generates a random N-digit prime number and then runs it through
     * a miller rabin primality test until a prime number is generated
     *
     * @param digits_n the physical length of the prime number being generated
     */
    public Prime_Generator(int digits_n)
    {
        this.digits_n = digits_n;
        this.is_prime = false;

        long n = 0;

        while (!is_prime) {
            n = number_gen();
            is_prime = miller_rabin(n);
        }

        this.prime_num = n;
    }
}
