import java.util.HashMap;
/**
 * A class that inherits from Substitution and successfully implements a character by character cipher
 * It uses a fixed translation table that encodes letters
 * It does this by encoding the first character (position 0) as the second (position 1), then the third to the fourth and so on
 */
public class MonoAlphaSubstitution extends Substitution {

    HashMap<Character, Character> translation1 = new HashMap<Character, Character>();
    /**
     * The empty constructor for encryptions/decryptions without a key string
     */
    public MonoAlphaSubstitution() {

    }
    HashMap<Character, Character> translation2 = new HashMap<Character, Character>();
    HashMap<Character, Character> translation3 = new HashMap<Character, Character>();
    /**
     * The constructor for key strings
     * @param str
     * @returns the translation table for the key string
     */
    public MonoAlphaSubstitution(String str) {
        char[] chArray = str.toCharArray();
        for (int i=0; i<chArray.length-1; i=i+2) {
            char ch1 = chArray[i];
            char ch2 = chArray[i+1];
            translation2.put(ch1, ch2);
            translation3.put(ch2, ch1);
            
        }

    }
    /**
     * A new overriden encrypt method that utilises the translation table made in the constructor to encrypt characters
     */
    public char encrypt(char plainCh) {
        if (translation2.get(plainCh) == null) {
            return plainCh;
        }
        else {
            char newCh = translation2.get(plainCh);
            return newCh;
        }
    }
    /**
     * A new overriden decrypt method that utilises the translation table made in the constructor to decrypt characters
     */
    public char decrypt(char plainCh) {
        if (translation3.get(plainCh) == null) {
            return plainCh;
        }
        else {
            char newCh = translation3.get(plainCh);
            return newCh;
        }
    }
    /**
     * The main method that loops in turn by using the encrypt/decrypt method for each character in a string
     * @param args
     * @return the fully encrypted or decrypted string
     */
    public static void main(String args[]) {
 
        if (((args.length == 3) && (args[0].equals("encrypt") || args[0].equals("decrypt"))) ) {
            
            if (args[0].equals("encrypt")) {
                MonoAlphaSubstitution m = new MonoAlphaSubstitution(args[1]);
                System.out.println(m.encrypt(args[2]));
            }
            else {
                MonoAlphaSubstitution m = new MonoAlphaSubstitution(args[1]);
                System.out.println(m.decrypt(args[2]));
            }
            
        }
        else if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        }
        else if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        }
        else {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        }
    }
}
