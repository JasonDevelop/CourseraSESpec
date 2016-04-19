
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
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
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < message.length(); i++){
            if(i % 2 == start){
                sb.append(message.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public String breakCaesarCipher(String input){
        int key1 = indexOfMax(countLetters(halfOfString(input, 0))) - 4;
        int key2 = indexOfMax(countLetters(halfOfString(input, 1))) - 4;
        CaesarCipherTwo bcct = new CaesarCipherTwo(key1, key2);
        String dec = bcct.decrypt(input);
        return dec;
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String input = fr.asString();
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwo cct = new CaesarCipherTwo(21, 8);
        String enc = cct.encrypt(input);
        String dec = cct.decrypt(enc);
        System.out.println(enc);
        System.out.println(dec);
        //String decbybcc = breakCaesarCipher(enc);
        //System.out.println("by bcc: " + decbybcc);
    }


}
