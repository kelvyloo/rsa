public class Encoder {
    String msg;
    String ascii_msg;
    long public_key_n;
    long public_key_e;
    long encoded_msg[] = new long[64];

    public Encoder(String msg, long public_key_n, long public_key_e)
    {
        this.msg = msg;
        this.public_key_n = public_key_n;
        this.public_key_e = public_key_e;
    }

    public String letter_to_int()
    {
        return this.ascii_msg;
    }
}
