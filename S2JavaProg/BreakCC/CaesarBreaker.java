
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class CaesarBreaker extends CaesarCipher {
    public int [] countLetters(String mes){
        
        int [] counts = new int [26];
        //for(int i = 0; i < counts.length; i++){counts[i] = 0;}
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
    
    public void testCountLetters(){
        String mes = "aee";
        System.out.println(countLetters(mes)[3]);
    
    }
       
    public String decrypt(String input){
        //StorageResource sr = new StorageResource();
        String deMes = "";
        int eCont = 0;
        for(int key = 0; key < 26; key ++){
            CaesarCipher cc = new CaesarCipher();
            String currentMes = cc.encrypt(input, 26 - key);
            int currentECont = countLetters(currentMes)[4];
            System.out.println(currentMes +":"+currentECont);
            if(eCont == 0){
                eCont = currentECont;
                deMes = currentMes;
            }else{
                if(currentECont > eCont){
                    eCont = currentECont;
                    deMes = currentMes;
                }
            }
        }
        return deMes;
    }
    
    public void testDecrypt(){ //
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!";
        System.out.println(decrypt(message));
        
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
    
    public void testHalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
    
    public int indexOfMax(int [] values)
    {
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
    
    public int getKey(String s){
        int ePos = indexOfMax(countLetters(s));
        return ePos;
    }
    
    public void testGetKey(){
        System.out.println(getKey("aaaedccrrar"));
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        String de = "";
        int key1 = getKey(s1) - 4;
        int key2 = getKey(s2) - 4;
        //int key1 = 14;
        //int key2 = 24;
        System.out.println(key1 +"-------"+key2);
        StringBuilder sb = new StringBuilder(encrypted);
        for(int i = 0; i < encrypted.length(); i++){
            if(i % 2 == 0){
                // use key1
                //System.out.print(sb.charAt(i) + "~~~~~~~");
                sb.setCharAt(i, cc.encrypt(sb.charAt(i) + "", (26 - key1)%26).charAt(0));
                //System.out.println(sb.charAt(i));
            }
            if(i % 2 == 1){
                // use key2
                //System.out.print(sb.charAt(i) + "-------");
                sb.setCharAt(i, cc.encrypt(sb.charAt(i) + "", (26 - key2)%26).charAt(0));
                //System.out.println(sb.charAt(i));
            }
        }
        
        return sb.toString();

    }
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String s = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        s = fr.asString();
        System.out.println(decryptTwoKeys(s));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} 
