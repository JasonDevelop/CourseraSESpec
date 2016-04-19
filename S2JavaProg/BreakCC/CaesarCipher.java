import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lalphabet = alphabet.toLowerCase();
        //Compute the shifted alphabet
       
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String lshiftedAlphabet = lalphabet.substring(key)+
        lalphabet.substring(0,key);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            int lidx = lalphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: lowercase
            else{
                if(lidx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = Character.toLowerCase(shiftedAlphabet.charAt(lidx));
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", key);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder SBP = new StringBuilder(input);
        
        for(int i = 0; i < SBP.length(); i ++){
            if( i % 2 == 0){
                System.out.print(SBP.charAt(i) + "~~~~~~~");
                SBP.setCharAt(i, encrypt(SBP.charAt(i) + "", key1).charAt(0));
                System.out.println(SBP.charAt(i));
            }else{
            // use key2
                System.out.print(SBP.charAt(i) + "-------");
                SBP.setCharAt(i, encrypt(SBP.charAt(i) + "", key2).charAt(0));
                System.out.println(SBP.charAt(i));
            }
        }
        return SBP.toString();
    }
    
    public void testEncryptTwoKeys(){
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
}

