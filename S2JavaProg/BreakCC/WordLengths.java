
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int [] counts){
        for(String word : resource.words()){
            System.out.println(word);
            int rawLen = word.length();
            if(!Character.isLetter(word.charAt(0))){rawLen --;}
            if(!Character.isLetter(word.charAt(word.length()-1))){rawLen --;}
            System.out.println(rawLen);
            if(rawLen > 0){
            counts[rawLen] ++;}
        }
        for(int i = 1; i < counts.length; i++){
            if(counts[i] != 0){System.out.println(i+"##"+counts[i]);}
        }
    }
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int [] counts = new int [5000];
        countWordLengths(resource, counts);
        System.out.println(indexOfMax(counts));
        
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
