import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Brutus {
    /**
     * Main method that access all methods. Finds the lowest chi-squared value
     * for all 26 shifts
     * @param args
    */
    public static void main(String[] args) {

        if (args.length == 1) {
            String str1 = args[0];
            Double lowest = 5.0;
            String theString = "";
            for (int i = 0; i<26; i++) {
                String theNewString = rotate(i, str1); 
                double[] db1 = frequency(theNewString);
                if (chiSquared(db1, english) < lowest) {
                    lowest = chiSquared(db1, english);
                    theString = theNewString;
                }
            
            }
            System.out.println(theString);
        }
        else if (args.length > 1) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
        }
        else if (args.length < 1) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
        }
    }

    public static final double[] english = {
        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };

    public static final char[] charArray = {
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    /**
     * Counts how many letters are in the string
     * @param str1
     * @return an array of said letters
     */
    public static int[] count (String str1) {
        
        int[] lettersCount = new int[26];
        for (int i=0; i<26; i++) {
            char ch1 = charArray[i];
            for (int j=0; j<(str1.length()); j++) {
                char[] character1 = new char[1];
                str1.getChars(j,j+1,character1,0);
                char aCharacter = character1[0];
                char bCharacter = Character.toLowerCase(aCharacter);
                if (ch1 == (bCharacter)) {
                    lettersCount[i] = lettersCount[i] + 1;
                }
           }     
        }

        return (lettersCount);
    }

    /**
     * Works out how often letters occur in a word
     * @param str1
     * @return an array of doubles of said frequencies of letters
     */
    public static double[] frequency (String str1) {
        int theCount = 0;
        int[] theAmount = count(str1);
        for (int i=0;i<26;i++) {
            if (theAmount[i] > 0) {
                theCount = theCount + theAmount[i];
            }

        }
        double[] letterFreq = new double[26];
        for (int j=0;j<26;j++) {
            
            if (theAmount[j] == 0) {
                letterFreq[j] = 0;
            }
            
            else {
                int num1 = theAmount[j];
                int num2 = theCount;
                double theInt = ((num1) / ((double) (num2)));
                letterFreq[j] = (theInt);
            }   
        ;   
        }
        
        return letterFreq;
    }

    /**
     * uses the chiSquared algorithm to work out how close a certain shift
     * is to the english language frequencies
     * @param db1
     * @param db2
     * @return a single double of how likely it is to be correct (lower is better)
     */
    public static double chiSquared (double[] db1, double[] db2) {
        double chi = 0.0;
        for (int i=0; i<26; i++) {

        double dbl1 = db1[i];
        double dbl2 = db2[i];
        double theSum = Math.pow(dbl1 - dbl2, 2);
        chi = chi + ((theSum) / (double) dbl2);
        }
        return chi;
    }

    /**
     * shifts a certain character forward by a certain shift
     * @param shiftNum
     * @param character
     * @return a shifted character
     */
    public static char rotateValue (Integer shiftNum, char character) {
        int ascii = (int) character;
        int value = ascii + (shiftNum % 26);
        char newValue = (char) value;
        if ((ascii > 96 && ascii < 123) || (ascii < 91 && ascii > 64)) {
            
            if ((value > 122 && ascii > 96 && ascii < 123) || (value > 90 && ascii > 64 && ascii < 91)) {
                value = value - 26;
                newValue = (char) value;
                return newValue;
            }
            else if ((value < 97 && ascii > 96 && ascii < 123) || (value < 65 && ascii > 64 && ascii < 91)){
                value = value + 26;
                newValue = (char) value;
                return newValue;
            }
            else {
                return newValue;
            }
        }
        else {
            return character;
        }
    }

    /**
     * shifts an entire string by a specific shift number
     * @param theShiftNum
     * @param theString
     * @return the shifted string
     */
   public static String rotate (Integer theShiftNum, String theString) {
        int length = theString.length();
        String theNewString = "";
        for (int i=0; i < length; i++) {
            char theCharacter = (theString.substring(i, i+1)).charAt(0);
            theNewString = theNewString + rotateValue(theShiftNum, theCharacter);
        }   
        return (theNewString);
   }

}
