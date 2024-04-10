package com.epul.demo.utilitaires;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;


public  class MonMotPassHash
{
    private static final int SaltSize = 32;
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 128;

    /// <summary>
    /// Génère le sel sous forme d'une clé
    /// </summary>
    /// <returns></returns>

    public static byte[] GenerateSalt()
    {
        byte[] salt = new byte[SaltSize];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * On retourne un mot de passe haché.<br>
     * erreur : le mot de passe est détruit (le  char[] est rempli de zéros)
     *
     * @param password le mot de passe en clair
     * @param salt     le sel généré
     *
     * @retourne un mot de passe avec un sel
     */
    public static byte[] generatePasswordHash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        //Arrays.fill(password, Character.MIN_VALUE);
        try {
            // génère une clé  keyFactory en utilisant  l'algorithme PDBKDF2WithHmacSHA1
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * retourne  true si les passwors sont identiques .<br>
     * erreur : le mot de passe est détruit (le  char[] est rempli de zéros)
     *
     * @param pwdCO     le mot de passe à contrôler
     * @param pwdh          le mot de passe stocké
     *
     * @return true si tout concorde
     */
    public static boolean verifyPassword( byte[] pwdCO, byte[] pwdh) {
       // Arrays.fill(password, Character.MIN_VALUE);
        if (pwdCO.length != pwdh.length) return false;
        for (int i = 0; i < pwdCO.length; i++) {
            if (pwdCO[i] != pwdh[i]) return false;
        }
        return true;
    }

    /// <summary>
    ///  Cette méthode transforme une chaîne de caractère en bytes
    /// </summary>

    public static byte[] transformeEnBytes(String maChaine)
    {

        Charset charset = StandardCharsets.US_ASCII;

        byte[] bytes = Base64.getDecoder().decode(maChaine);

        return bytes;

    }

    /// <summary>
    ///  Cette méthode transforme une tableau bytes  en chaîne
    /// </summary>

    public static String bytesToString(byte[] monByte)
    {
        String str =  Base64.getEncoder().encodeToString(monByte);

        return str;
    }

    public  static  char[] converttoCharArray( String maChaine)
{

    char[] mesChar = maChaine.toCharArray();
    /*char[] mesChar = new char[maChaine.length()];
    for (int i =0; i< maChaine.length(); i++)
    {
        mesChar[i]= maChaine.charAt(i);

    }*/
    return mesChar;
}
}

