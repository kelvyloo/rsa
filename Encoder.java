public class Encoder {
    /**
     * INSTANCE VARIABLES:
     * Public variables
     * @msg           Message to be encoded
     * @public_key_n  A prime number n such that n = pq
     * @public_key_e  Another prime number such that the private key d*e = 1 mod (p-1)(q-1)
     *
     * Private variables
     * @string_coded_msg Original message but with each letter replaced by its alphanumeric value
     * @digits_n         Length of the public key n
     * @encoded_msg      Final encoded message after encryption
     */
    String msg;
    long public_key_n;
    long public_key_e;

    private String string_coded_msg;
    private int digits_n = String.valueOf(public_key_n).length();
    private long encoded_msg[] = new long[64];

    /**
     * Class Constructor
     * */
    public Encoder(String msg, long public_key_n, long public_key_e)
    {
        this.msg = msg;
        this.public_key_n = public_key_n;
        this.public_key_e = public_key_e;
    }

    public String letter_to_int()
    {
        string_coded_msg = msg.toUpperCase();

        for (int i = 0; i < msg.length(); i++) {
            char letter = msg.charAt(i);
            String alphabet_num;

            if (letter >= 'A' && letter <= 'J') {
                alphabet_num = "0" + (letter - 'A');
            }
            else {
                alphabet_num = String.valueOf(letter - 'A');
            }
            string_coded_msg.replace(String.valueOf(letter), alphabet_num);
        }
        return string_coded_msg;
    }
}
