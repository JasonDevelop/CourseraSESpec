
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
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
        for(int i : counts){System.out.println(i);}
        return counts;
    }
    
    public String breakCaesarCipher(String input){
        //mainKey = indexOfMax(countLetters(input)) - 4; // key = ePos
        int key = indexOfMax(countLetters(input)) - 4;
        CaesarCipher bcc = new CaesarCipher(key);
        String enc = bcc.decrypt(input);
        //cc.mainKey = indexOfMax(countLetters(input)) - 4;
        return enc;
    }

    
    
    
    
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String input = fr.asString();
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String enc = cc.encrypt(input);
        System.out.println(enc);
        String dec = cc.decrypt(enc);
        System.out.println(dec);
        System.out.println("by bcc :" + breakCaesarCipher(enc));
        
    }   
    
}
