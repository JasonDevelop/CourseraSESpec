
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class CaesarCipherTwo {
    private int key1;
    private int key2;
    private int mainKey1;
    private int mainkey2;
    private CaesarCipher cc1;
    private CaesarCipher cc2;
    private CaesarCipher dcc1;
    private CaesarCipher dcc2;
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public CaesarCipherTwo(int v1, int v2){
        key1 = v1;
        key2 = v2;
        mainKey1 = 26 - key1;
        mainkey2 = 26 - key2;
        //shiftedAlphabet = alphabet.substring(key1)+alphabet.substring(0,key1);
        //shiftedAlphabet = alphabet.substring(key2)+alphabet.substring(0,key2);
        cc1 = new CaesarCipher(key1);
        cc2 = new CaesarCipher(key2);
        dcc1 = new CaesarCipher(mainKey1);
        dcc2 = new CaesarCipher(mainkey2);
    }
    public String encrypt(String input){
        String enc = "";
        String currentEnc = "";
        for(int i = 0; i < input.length(); i++){
            if(i % 2 == 0){// key1
                currentEnc = cc1.encrypt(input.substring(i, i+1));
                enc += currentEnc;
            }else{// key2
                currentEnc = cc2.encrypt(input.substring(i, i+1));
                enc += currentEnc;
            }
        }
        
        return enc;
    }
    public String decrypt(String input){
        String dec = "";
        String currentDec = "";
        for(int i = 0; i < input.length(); i++){
            if(i % 2 == 0){// key1
                currentDec = dcc1.encrypt(input.substring(i, i+1));
                dec += currentDec;
            }else{// key2
                currentDec = dcc2.encrypt(input.substring(i, i+1));
                dec += currentDec;
            }
        }
        
        return dec;
        
    }

}
