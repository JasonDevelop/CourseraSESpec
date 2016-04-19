
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CodonCount {
    private HashMap<String, Integer> map;
    public CodonCount(){
        map = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start, String dna){
        map.clear();
        while(start + 3 <= dna.length()){
            String codon = dna.substring(start, start + 3);
            if(map.containsKey(codon)){map.put(codon, map.get(codon)+1);}
            else{map.put(codon, 1);}
            start += 3;
        }
    }
    public String getMostCommonCodon(){
        int maxCount = 0;
        String maxCodon = "";
        for(String currentCodon : map.keySet()){
            int currentCount = map.get(currentCodon);
            if(currentCount > maxCount){
                maxCount = currentCount;
                maxCodon = currentCodon;
            }
        }
        System.out.println("maxC: "+maxCount);
        return maxCodon;
    }

    public void printCodonCounts(int start, int end){
        for(String currentCodon : map.keySet()){
            int currentCount = map.get(currentCodon);
            if(currentCount >= start && currentCount <= end){
                System.out.println(currentCodon+": "+currentCount);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        for(int i = 0; i < 3; i++){
            System.out.println("Start :" + i);
            buildCodonMap(i, dna);
            System.out.println("getMostCommonCodon(): " + getMostCommonCodon());
            System.out.println("unique" + map.size());
            printCodonCounts(7, 7);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
