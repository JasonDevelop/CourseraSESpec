import edu.duke.*;

public class CaesarCipher {
    private int key;
    public int mainKey;
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String lalphabet = alphabet.toLowerCase();
    private String shiftedAlphabet = "";
    private String lshiftedAlphabet;
 
    public CaesarCipher(int value){
        key = value;
        mainKey = 26 - key;
        System.out.println(key);
        shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        lshiftedAlphabet = lalphabet.substring(key)+
        lalphabet.substring(0,key);
    }
    
    public int indexOfMax(int [] values){
        int tmp = 0;
        int pos = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > tmp){
                tmp = values[i];
                pos = i;
            }
        }
        return pos;
    }
    public int [] countLetters(String mes){        
        int [] counts = new int [26];
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < mes.length(); i++){
            char ch = Character.toUpperCase(mes.charAt(i));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
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
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(mainKey);
        String deMes = cc.encrypt(input);
        return deMes;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

