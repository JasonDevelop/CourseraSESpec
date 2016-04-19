
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
    public boolean isVowel(Character ch){
        String vowelTable = "aeiouAEIOU";
        int idx = vowelTable.indexOf(ch);
        return idx != -1;
    }
    public String replaceVowels(String phrase, Character ch){
        StringBuilder SBPhrase = new StringBuilder(phrase);
        for(int i = 0; i < SBPhrase.length(); i++){
            Character currentCh = SBPhrase.charAt(i);
            if(isVowel(currentCh)){
                SBPhrase.setCharAt(i, ch);
            }
        }
        return SBPhrase.toString();
    }
    
    public void testReplaceVowels(){
        System.out.println(replaceVowels("fuck", '*'));
    }
    public String emphasize(String phrase, Character ch){
        StringBuilder SBP = new StringBuilder(phrase);
        for(int i = 0; i < SBP.length(); i ++){
            if(SBP.charAt(i) == ch){
                if(i % 2 != 0){
                    SBP.setCharAt(i, '*');
                }else{
                    SBP.setCharAt(i, '+');
                }
            }
        }
        return SBP.toString();
    }
    
    public void tsetEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
    }

}
