public class Caesar {
    /**
     * Takes a single string and a shift number and shifts this string by the number
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            int num = Integer.parseInt(args[0]);
            String str1 = (args[1]); 
            System.out.println(rotate(num, str1));
            
        }
        else if (args.length > 2) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
        }
        else if (args.length < 2) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
        }
    }



    /**
     * shifts a certain character by a specific shift number
     */
    public static char rotate (int shiftNum, char character) {
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
     * Shifts a string by a certain shift number
     */
   public static String rotate (int theShiftNum, String theString) {
        int length = theString.length();
        String theNewString = "";
        for (int i=0; i < length; i++) {
            char theCharacter = (theString.substring(i, i+1)).charAt(0);
            theNewString = theNewString + rotate(theShiftNum, theCharacter);
        }   
        return (theNewString);
   }
}