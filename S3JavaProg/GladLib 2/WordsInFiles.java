
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fn = f.getName();
        for(String w : fr.words()){
            if(map.containsKey(w)){
                if(!map.get(w).contains(fn)){
                    ArrayList<String> fns = new ArrayList<String>();
                    fns = map.get(w);
                    fns.add(fn);
                    map.put(w, fns);
                }
            }else{
                ArrayList<String> fns = new ArrayList<String>();
                fns.add(fn);
                map.put(w, fns);
            }
        }
        
    }
    
    public void buildWordFileMap (){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(String w : map.keySet()){
            int cm = map.get(w).size();
            if(cm > max){max = cm;}
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int num){
        ArrayList<String> al = new ArrayList<String>();
        for(String w : map.keySet()){
            if(map.get(w).size() == num){al.add(w);}           
        }
        return al;
        
    }
    public void printFilesIn(String word){
        System.out.println(word + "In ");
        for(String w : map.keySet()){
            if(w.equals(word)){
                for(String fn : map.get(w)){
                    System.out.print(fn + "  ");
                }
            }
        }
        System.out.println("end");
    }
    
    public void tester(){
        buildWordFileMap();
        System.out.println("max " + maxNumber());
        printFilesIn("tree");
        System.out.println("wordsInNumFiles(num)");
        ArrayList<String> al = wordsInNumFiles(4);
        //for(String w : al){System.out.println(w);}
        System.out.println("wordsInNumFiles size" + al.size());
    }
    
    
    
    
}
