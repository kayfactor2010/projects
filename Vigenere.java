import java.util.HashMap;
import java.util.LinkedList;
/**
 * A class that implements Vigenere cipher and inherits that of Substitution
 */
public class Vigenere extends Substitution {
    char[] upper = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    /**
     * A linear search on an array of characters
     * @param data
     * @param n
     * @param key
     * @return The location of a character in a character array
     */
    static int seqSearch(char[] data, int n, int key) {
		int i;
		boolean found;

		// start sequential search on the array called data[]
		found = false;	// to indicate if the number is found
		i = 0;		// an index variable to iterate through the array
		while (i<n && found==false) {
			if (data[i] == key) {
				found = true;
                return i;
			} else {
				i = i+1;
			}
		}
        return 0;
    }
    /**
     * A linear search on an array of integers
     * @param data
     * @param n
     * @param key
     * @return The second location of an integer in an array of integers
     */
    static int seqSearch(int[] data, int n, int key) {
		int i;
		boolean found;

		// start sequential search on the array called data[]
		found = false;	// to indicate if the number is found
		boolean found1 = false;
        i = 0;		// an index variable to iterate through the array
		while (i<n && found==false) {
			if (data[i] == key) {
				found = true;
                i = i + 1;
                while (i<n && found1 == false) {
                    if (data[i] == key ) {
                        found1 = true;
                        return i;
                    }
                    else {
                        i = i+1;
                    } 
                }
			} else {
				i = i+1;
			}
		}
        return 0;
    }

    /**
     * Overriden encrypt method
     */
    public char encrypt(char plainCh) {
        if (keyWord.equals("")) {
            return plainCh;
        }
        else {
            theShift = LL.getFirst();
        }
        
        String theInt = Integer.toString(theShift);
        String theChar = Character.toString(plainCh);
        
        String[] totalString = {"encrypt", theInt, theChar}; 
        String encryptedChar = Caesar.main1(totalString);
        
        LL.add(theShift);
        LL.remove();
        return encryptedChar.charAt(0);
    }

    int theShift = 0;

    /**
     * Overridden decrypt method
     */
    public char decrypt(char plainCh) {
        if (keyWord.equals("")) {
            return plainCh;
        }
        else {
            theShift = LL.getFirst();
        }
        
        String theInt = Integer.toString(theShift);
        String theChar = Character.toString(plainCh);
        
        String[] totalString = {"decrypt", theInt, theChar}; 
        String encryptedChar = Caesar.main1(totalString);
        
        LL.add(theShift);
        LL.remove();
        return encryptedChar.charAt(0);
    }
    
    /**
     * Empty Vigenere constructor
     */
    public Vigenere() {

    }
    
    public static int[] numbers = new int[1000];
    static int[] finalArray = new int[1000];

    LinkedList<Integer> LL = new LinkedList<Integer>();
    String keyWord;
    /**
     * Vigenere constructor for a keyword and changes these into their respective numbers for the Caesar cipher
     * @param key
     */
    public Vigenere(String key) {
        char[] chArray = key.toCharArray();
        for (int i=0; i<key.length(); i++) {
            int pos = seqSearch(upper, upper.length, chArray[i]);
            
            int theNum = pos;
            if (key == "") {
                theNum = 0;
            }
            numbers[i] = theNum;
            //System.out.println(theNum);
        }
        int position = seqSearch(numbers, numbers.length, 0);
        int[] newNum = new int[position];
        //System.out.println(position);
        for (int k=0; k<position; k++) {
            newNum[k] = numbers[k];
        }
        
        int size = newNum.length;
        for (int j=0; j<1000; j++) {
            finalArray[j] = newNum[(j%size)];
            //System.out.println(finalArray[j]);
        }
        for (int p=0; p<key.length(); p++) {
            //System.out.println(p);
            //System.out.println(finalArray.length);
            //System.out.println(key.charAt(p));
            //System.out.println(finalArray[p]);
            LL.add(finalArray[p]);
        }
        keyWord = key;
        
    }
    /**
     * The main method that combines all the methods
     * @param args
     */
    public static void main(String args[]) {
        if (((args.length == 3) && (args[0].equals("encrypt") || args[0].equals("decrypt"))) ) {
            if (args[0].equals("encrypt")) {
                Vigenere v = new Vigenere(args[1]);
                System.out.println(v.encrypt(args[2]));
            } 
            else {
                Vigenere v = new Vigenere(args[1]);
                System.out.println(v.decrypt(args[2]));
            }   
            
   
        }
        
        else if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
        }
        else if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
        }
        else {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
        }
    }
}

