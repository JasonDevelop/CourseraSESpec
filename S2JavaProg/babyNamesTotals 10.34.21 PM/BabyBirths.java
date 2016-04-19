/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int uniBoys = 0;
        int uniGirls = 0;
        int uniTotal = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            int numName = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                if(numName <= 5){uniBoys += 1;}
            }
            else {
                totalGirls += numBorn;
                if(numName <= 5){uniGirls += 1;}
            }
        }
        uniTotal = uniBoys + uniGirls;
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("unique boy names= " + uniBoys);
        System.out.println( "unique girl names= " + uniGirls);
        System.out.println("total unique names = " + uniTotal);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public CSVParser getParserOfYear(int year){
        String f = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        return parser;
    }
    
    public int getRank(int year, String name, String gender){
        String f = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for(CSVRecord rec : parser){
            String currentGender = rec.get(1);
            String currentName = rec.get(0);
            //System.out.println(currentName);
            if(currentGender.equals(gender)){
                rank ++;
                //System.out.println(rank);
                if(currentName.equals(name)){return rank;}
            }
        }
        return -1;      
    }
    
    public void testGetRank(){ // checked
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    
    public String getName(int year, int rank, String gender){
        CSVParser parser = getParserOfYear(year);
        int currentRank = 0;
        for(CSVRecord rec : parser){
            String currentGender = rec.get(1);
            String currentName = rec.get(0);
            if(currentGender.equals(gender)){
                currentRank ++;
                if(currentRank ==rank){
                    return currentName;
                }
            }
        }
        return "NO NAME";              
    }
    
    public void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name +" born in "+ year + " would be " + newName + " in " + newYear);
        return newName;
    }
    
    public void testwhatIsNameInYear(){ // checked
        String newName = whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println(newName);
    }
    
    public CSVParser getParserOfFile(File f){
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        return parser;
    }
    
    private int getYearOfFileName(String fname){
        int year = Integer.parseInt(fname.substring(3, 7));
        return year;
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int year = 0;
        int rank = 0;
        for(File f : dr.selectedFiles()){
            int currentYear = getYearOfFileName(f.getName());
            CSVParser parser = getParserOfYear(currentYear);
            int currentRank = getRank(currentYear, name, gender);
            System.out.println(currentRank);
            if(currentRank != -1){
                System.out.println("ENTER" + currentRank);
                System.out.println(currentYear +": "+ currentRank);
                if(rank == 0){ // initialize
                    rank = currentRank;
                    year = currentYear;
                }
                else{
                    if(currentRank < rank){
                        year = currentYear;
                        rank = currentRank;
                        System.out.println(year +": "+ currentRank);
                    }
                }
            }
        }
        return year;
    }
    
    public void testYearOfHighestRank(){
        int year = yearOfHighestRank("Genevieve", "F");
        System.out.println(year);
    }
    
    public double getAverageRank(String name, String gender){
        double rankSum = 0.0;
        int fileNum = 0;
        //System.out.println("*******");
        DirectoryResource dr = new DirectoryResource();
        //System.out.println("*******");
        for(File f : dr.selectedFiles()){
            CSVParser parser = getParserOfFile(f);
            int currentYear = getYearOfFileName(f.getName());
            int currentRank = getRank(currentYear, name, gender);
            rankSum += currentRank;
            fileNum ++;            
        }
        return rankSum/fileNum;
    }
    
    public void testGetAverageRank(){
        double aveRank = getAverageRank("Robert", "M");
        System.out.println(aveRank);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int thisRank = getRank(year, name, gender);
        CSVParser parser = getParserOfYear(year);
        //int thisRank = 3;
        //FileResource fr = new FileResource();
        //CSVParser parser = fr.getCSVParser();
        int totalBirths = 0;
        int currentRank = 0;
        for(CSVRecord rec : parser){
            if(rec.get(1).equals(gender)){
                currentRank ++;
                if(currentRank < thisRank){
                    System.out.println(rec.get(0));
                    int currentBirths = Integer.parseInt(rec.get(2));
                    totalBirths += currentBirths;
                }
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int totalBirths = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(totalBirths);
    }
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
