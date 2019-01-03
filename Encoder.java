/**
 * Encoder Class
 * Takes the message that the user desires be encoded and can encode the message using RSA
 * modular encryption.
 *
 * @msg            Message to be encoded
 * @public_key_n   A prime number n such that n = pq
 * @private_key_e  Private key prime number such that the private key d*e = 1 mod (p-1)(q-1)
 *
 * @digits_n      Length of the public key n
 * @encoded_msg   Final encoded message after encryption
 */
public class Encoder {
    String msg;
    long public_key_n;
    long private_key_e;

    private int digits_n;
    private long encoded_msg[];

    /**
     * Class Constructor
     *
     * @param msg           Message to be encoded
     * @param public_key_n  A prime number n such that n = pq
     * @param private_key_e  Another prime number such that the private key d*e = 1 mod (p-1)(q-1)
     *
     * @return        Nothing
     * */
    public Encoder(String msg, long public_key_n, long private_key_e)
    {
        this.msg = msg;
        this.public_key_n = public_key_n;
        this.private_key_e = private_key_e;
        this.digits_n = String.valueOf(public_key_n).length();
    }

    /**
     * Simple scheme to transform letters into their alphanumeric value (i.e. X = 23).
     * Letters before the 10th letter K are padded with a zero in front (i.e. B = 01).
     * This encoded message needs to be a length such msg.length % digits_n == 0
     * so the string is padded with Xs
     *
     * @return the alphanumeric string message
     * */
    private String letter_to_int()
    {
        String tmp_coded_msg = msg.toUpperCase();
        String string_coded_msg = tmp_coded_msg;
        String X_str = "23";

        for (int i = 0; i < tmp_coded_msg.length(); i++) {
            char letter = tmp_coded_msg.charAt(i);
            String alpha_num;

            if (letter >= 'A' && letter <= 'J') {
                alpha_num = "0" + (letter - 'A');
            }
            else {
                alpha_num = String.valueOf(letter - 'A');
            }
            string_coded_msg = string_coded_msg.replace(String.valueOf(letter), alpha_num);
        }

        while (string_coded_msg.length() % digits_n != 0) string_coded_msg += X_str;

        return string_coded_msg;
    }

    /**
     * Takes the alphanumeric code form of the message and performs modular exponentiation to it
     * in chunks of N digits (the digit size of the public key n).
     *
     * @return an array of integers which represent the original msg after encoding
     * */
    public long[] encode()
    {
        String string_coded_msg = letter_to_int();
        encoded_msg = new long[string_coded_msg.length()/digits_n];
        int index = 0;
        String tmp;

        for (int i = 0; i <= string_coded_msg.length(); i++) {
            if (i % (digits_n) == 0 && i != 0) {
                tmp = string_coded_msg.substring((i-(digits_n)), i);
                encoded_msg[index] = Main.mod_exp(Long.parseLong(tmp), public_key_n, private_key_e);
                index += 1;
            }
        }
        return encoded_msg;
    }
}
