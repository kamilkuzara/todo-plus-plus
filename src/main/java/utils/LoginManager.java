package utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.DecoderException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
//import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;

public class LoginManager{
    private static final int HASHING_ITERATIONS = 10000;
    private static final int PASSWORD_HASH_LENGTH = 256;
    private static final int SALT_BYTE_LENGTH = 16;

    public static boolean registerUser(String username, String password, String confirmPassword)
    {
        // check if given username exits in the db
        if(DBManager.userExists(username)){
              System.out.println("User already exists");
            return false;
        }

        if(password.isEmpty() || username.isEmpty())
        {
              System.out.println("Neither username nor password can be empty");
            return false;
        }

        if(!password.equals(confirmPassword))
        {
              System.out.println("The passwords do not match");
            return false;
        }

        byte[] saltBytes = generateSalt();
        String saltString = Hex.encodeHexString(saltBytes);

        byte[] hashedPasswordBytes = hashPassword(password.toCharArray(), saltBytes, HASHING_ITERATIONS, PASSWORD_HASH_LENGTH);
        String hashedPasswordString = Hex.encodeHexString(hashedPasswordBytes);

        if(DBManager.registerUser(username, hashedPasswordString, saltString))
        {
              System.out.println(hashedPasswordString);
            return true;
        }
        else
            return false;
    }

    public static boolean logUserIn(String username, String password)
    {
        // check if given username exits in the db
        if(!DBManager.userExists(username)){
              System.out.println("User does not exist");
            return false;
        }

        // get the password hash for that user
        String userPasswordHash = DBManager.getPasswordHash(username);
          System.out.println("Retrieved password hash: " + userPasswordHash);

        // get salt for that user
        String saltString = DBManager.getSalt(username);
          System.out.println("Retrieved salt: " + saltString);
        byte[] saltBytes;
        try
        {
            saltBytes = Hex.decodeHex(saltString.toCharArray());
        }
        catch(DecoderException exception)
        {
              System.out.println("The retrieved salt may be in incorrect format." +
                                  "Check the database records for validity.");
            exception.printStackTrace();
            return false;
        }

        byte[] hashedPasswordBytes = hashPassword(password.toCharArray(), saltBytes, HASHING_ITERATIONS, PASSWORD_HASH_LENGTH);
        String hashedPasswordString = Hex.encodeHexString(hashedPasswordBytes);
          System.out.println("Hashed password: " + hashedPasswordString);

        if(userPasswordHash.equals(hashedPasswordString))
            return true;
        else{
              System.out.println("Password incorrect");
            return false;
        }
    }

    private static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength )
    {
        try
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] passwordHash = key.getEncoded( );
            return passwordHash;

        }
        catch( NoSuchAlgorithmException | InvalidKeySpecException e )
        {
            throw new RuntimeException( e );
        }
   }

   private static byte[] generateSalt()
   {
       SecureRandom random = new SecureRandom();
       byte[] salt = new byte[SALT_BYTE_LENGTH];
       random.nextBytes(salt);

       return salt;
   }
}
