import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder(message);
        String mes = "";
        for(int i  = whichSlice; i < message.length(); i+=totalSlices){
            mes += sb.charAt(i);
        }
        return mes;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int i = 0; i < klength; i++){
            CaesarCracker cc = new CaesarCracker(mostCommon);
            String currentMes = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(currentMes);
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        HashMap<String, HashSet<String>> language = new HashMap<String, HashSet<String>>();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            System.out.println("loading");
            FileResource dfr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dfr);
            language.put(f.getName(), dictionary);
            System.out.println("load finished");// alert load fin
        }
        breakForAllLanguages(encrypted, language);
        //String decrypted = breakForLanguage(encrypted, dictionary, 'e');
        //System.out.println(decrypted);
        
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for(String line : fr.lines()){
            hs.add(line.toLowerCase());
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String word : message.split("\\W+")){
            if(dictionary.contains(word.toLowerCase())){
                count += 1;
            }
        }
        return count;
    }
    // modi int
    public int breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonCh){
        int maxCount = 0;
        String mes = "";
        int finkl = 0;
        for(int kl = 1; kl <= 100; kl ++){
            int [] key  = tryKeyLength(encrypted, kl, mostCommonCh);
            VigenereCipher vc = new VigenereCipher(key);
            String currentMessage = vc.decrypt(encrypted);
            int currentCount = countWords(currentMessage, dictionary);
            if(currentCount > maxCount){
                maxCount = currentCount;
                mes = currentMessage;
                finkl = kl;
            }
        }
        System.out.println("by kl" + finkl+"counts"+maxCount);
        return maxCount;
    }
    // addi string
    public String sbreakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonCh){
        int maxCount = 0;
        String mes = "";
        int finkl = 0;
        for(int kl = 1; kl <= 100; kl ++){
            int [] key  = tryKeyLength(encrypted, kl, mostCommonCh);
            VigenereCipher vc = new VigenereCipher(key);
            String currentMessage = vc.decrypt(encrypted);
            int currentCount = countWords(currentMessage, dictionary);
            if(currentCount > maxCount){
                maxCount = currentCount;
                mes = currentMessage;
                finkl = kl;
            }
        }
        System.out.println("by kl" + finkl+"counts"+maxCount);
        return mes;
    }

    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for(String word : dictionary){
            StringBuilder sb = new StringBuilder(word);
            for(int i = 0; i < sb.length(); i++){
                char currentCh = Character.toLowerCase(sb.charAt(i));                
                if(hm.containsKey(currentCh)){
                    hm.put(currentCh, hm.get(currentCh) + 1);
                }else{
                    hm.put(currentCh, 1);
                }
            }
        }
        // find most common char in hm
        char finCh = 'a';
        int maxCount = 0;
        for(char ch : hm.keySet()){
            int currentCount = hm.get(ch);
            if(currentCount > maxCount){
                maxCount = currentCount;
                finCh = ch;
            }
        }
        System.out.println("ch   "+finCh+"    counts "+maxCount);
        return finCh;
    }
    
    public void testMost(){
        FileResource fr = new FileResource();
        mostCommonCharIn(readDictionary(fr));
        
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> language){
            int maxCount = 0;
            String finLang = "";
            char finMostComCh = 'a';
            for(String currentLang : language.keySet()){
                HashSet<String> dictionary = language.get(currentLang);
                char mostCommonCh = mostCommonCharIn(dictionary);
                System.out.println(currentLang);
                int currentCount = breakForLanguage(encrypted, dictionary, mostCommonCh);// count
                if(currentCount > maxCount){
                    maxCount = currentCount;
                    finLang = currentLang;
                    finMostComCh = mostCommonCh;
                }
            }
            System.out.println("maxCount---"+maxCount);
            System.out.println("finLang----"+finLang);
            String decrypted = sbreakForLanguage(encrypted, language.get(finLang), finMostComCh);
            System.out.println(decrypted);
    }    
    public void tester(){
        //System.out.println(sliceString("abcdefghijklm", 0, 3));
        FileResource fr = new FileResource();
        int [] key = tryKeyLength(fr.asString(), 4, 'e');
        for(int k : key){System.out.println(k);}        
    }
    
}
