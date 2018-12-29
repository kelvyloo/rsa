/**
 * Decoder Class
 * Stores the encrypted message and can decrypt that message back to its original state
 *
 */
public class Decoder {
     long encoded_msg[];
     long public_key_n;
     long private_key_d;

     private long digits_n;
     private String decoded_msg;

     /**
      * Class constructor
      *
      * @param encoded_msg   array of long ints
      * @param public_key_n  public key prime number known by both users
      * @param private_key_d private key that only recipient should know
      *
      * @return None
      */
     public Decoder(long[] encoded_msg, long public_key_n, long private_key_d)
     {
         this.encoded_msg = encoded_msg;
         this.public_key_n = public_key_n;
         this.private_key_d = private_key_d;
         this.digits_n = String.valueOf(public_key_n).length();
     }

    /**
     * Decodes the encoded blocks by calculating the mod exp of each number,
     * iterating through the new blocks of numbers and finding the alphanumeric
     * representation of each letter from the original message
     *
     * @return the decoded string
     */
     public String decode()
     {
         long block;
         String block_str;
         char letter;
         String alphanumeric;
         String tmp_msg = "";

         for (int i = 0; i < encoded_msg.length; i++) {
             block = Main.mod_exp(encoded_msg[i], public_key_n, private_key_d);

             block_str = String.valueOf(block);

             if (String.valueOf(block).length() != digits_n) {
                 block_str = "0" + block_str;
             }
             tmp_msg += block_str;
         }

         for (int i = 0; i < tmp_msg.length(); i++) {
             if (i % 2 == 1) {
                 alphanumeric = tmp_msg.substring(i-1, i);
                 letter = (char) (Integer.valueOf(alphanumeric) + 'A');
                 decoded_msg += letter;
             }
         }

         return decoded_msg;
     }
}
