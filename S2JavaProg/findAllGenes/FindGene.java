
/**
 * Write a description of FindGene here.
 * 
 * @author Jason Li 
 * @version 1.0
 */
import edu.duke.*;
public class FindGene {
    public int findStartIndex(String dna){
        //dna = dna.toUpperCase();
        int start = dna.indexOf("ATG");
        //StorageResource startSR = new StorageResource();
        if(start == -1){return -1;}       
        return start;
    }
    
    public int findStopIndex(String dna, int start){
        dna = dna.toUpperCase();
        //StorageResource stopSR = new StorageResource();
        int stop1 = dna.indexOf("TAG", start + 3);
        if (stop1 == -1 || (stop1 - start) % 3 != 0){
            stop1 =  dna.length();
        }
        int stop2 = dna.indexOf("TGA", start + 3);
        if (stop2 == -1 || (stop2 - start) % 3 != 0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("TAA", start + 3);
        if (stop3 == -1 || (stop3 - start) % 3 != 0){
            stop3 =  dna.length();
        }
        
        if( Math.min(stop1, Math.min(stop2, stop3)) == dna.length()){return -1;}
        else{return Math.min(stop1, Math.min(stop2, stop3));}
        
    }
    
    public void printAll(String dna){
        int start = -3;
        int stop = 0;
        int prestop =0;
        while(true){
            start = dna.indexOf("ATG", start + 3);            
            if(start == -1){break;}
            if(start > prestop){
            System.out.println("start" + start);
            stop = findStopIndex(dna, start);
            if(stop != -1){
                System.out.println("stop" + stop);
                prestop = stop;
                System.out.println(dna.substring(start, stop +3));
            }
        }
        }

    }
    
    public void testFinder(){
        String dna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        printAll(dna);
        dna = "ATGAAATGAAAA";
        //printAll(dna);
    }
}
