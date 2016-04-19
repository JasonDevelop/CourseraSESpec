
/**
 * Write a description of FindGene here.
 * 
 * @author Jason Li 
 * @version 1.0
 */
import edu.duke.*;
public class StoreAllGenes {
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
    
    public StorageResource storeAll(String dna){
        StorageResource geneSR = new StorageResource();
        int start = -3;
        int stop = 0;
        int prestop =0;
        while(true){
            start = dna.indexOf("ATG", start + 3);            
            if(start == -1){break;}
            if(start > prestop){
            //System.out.println("start" + start);
            stop = findStopIndex(dna, start);
            if(stop != -1){
                //System.out.println("stop" + stop);
                prestop = stop;
                geneSR.add((dna.substring(start, stop +3)));
            }
        }
        }
        return geneSR;

    }
    
    public void testStorageFinder(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        //System.out.println(dna);
        StorageResource geneSR = storeAll(dna);
        System.out.println("geneNum");
        System.out.println(geneSR.size());
        printGenes(geneSR);
        System.out.println("ctg");
        countctg(dna);
        System.out.println("longest");
        System.out.println(longest(geneSR).length());
    }
    
    public double cgRatio(String dna){
        double entireSize = dna.length();
        double cg = 0.0;
        int cpos = -1;
        int gpos = -1;
        while(true){
            cpos = dna.indexOf("C", cpos + 1);
            if (cpos == -1){break;}
            cg += 1.0;
        }
        while(true){
            gpos = dna.indexOf("G", gpos + 1);
            if (gpos == -1){break;}
            cg += 1.0;
        }
        return cg/entireSize;
    }
    
    public void printGenes(StorageResource sr){
        int num60 = 0;
        int numcg = 0;
        for(String gene : sr.data()){
            if(gene.length() > 60){
                //System.out.println(gene);
                num60 += 1;
            }
        }
        System.out.println(num60);
        for(String gene : sr.data()){
            if(cgRatio(gene) > 0.35){
                //System.out.println(gene);
                numcg += 1;
            }
        }
        System.out.println(numcg);
        
    }
    
    public void countctg(String dna){
        int count = 0;
        int cpos = -1;
        while(true){
            cpos = dna.indexOf("CTG", cpos + 1);
            if (cpos == -1){break;}
            count += 1;
        }
        System.out.println(count);
    }
    public String longest(StorageResource sr){
        int temp = 0;
        for(String gene:sr.data()){
            if (gene.length() > temp){temp = gene.length();}            
        }
        for(String gene:sr.data()){
            if(gene.length() == temp){
                return gene;
            }
        }
        return "";
    }
    public void testFinder(){
        String dna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        storeAll(dna);
        dna = "ATGAAATGAAAA";
        //printAll(dna);
    }
}
