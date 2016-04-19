
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    public void update(String person){
        int index = names.indexOf(person);
        if(index == -1){
            names.add(person);
            counts.add(1);
        }else{   
            int count = counts.get(index);
            counts.set(index, count + 1);
        }        
    }
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int index = line.indexOf(".");
            if(index != -1){
                String person = line.substring(0, index);
                update(person);
            }
        }
    }
    public int indexOfMax(ArrayList<Integer> counts){
        int max = counts.get(0);
        int maxIndex = 0;
        for(int k=0; k < counts.size(); k++){
            if (counts.get(k) > max){
                max = counts.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int k = 0; k < names.size(); k++){
            int num = counts.get(k);
            if(num >= num1 && num <= num2){
            System.out.println(names.get(k)+"  "+ counts.get(k));
            }
        }
    }
    public void tester(){
        findAllCharacters();
        int mainIndex = indexOfMax(counts);
        for(int k = 0; k < names.size(); k++){
            //System.out.println(names.get(k) +"  "+ counts.get(k));
        }
        System.out.println("main character: "+names.get(mainIndex) +"  "+ counts.get(mainIndex));
        charactersWithNumParts(50,102);
    }
}
