/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            largestSoFar = largestOfTwoRecords(largestSoFar, currentRow);
        //The largestSoFar is the answer
        }
        return largestSoFar;
    }
    

    public void testHottest () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
    
    public CSVRecord hottestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = hottestHourInFile(parser);
            largestSoFar = largestOfTwoRecords(largestSoFar, currentRow);
        }
        return largestSoFar;
    }
    
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature in many days was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord largestOfTwoRecords(CSVRecord largestSoFar, CSVRecord currentRow){
    
        if(largestSoFar == null){
            largestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp > largestTemp){
                largestSoFar = currentRow;
            }
        }
        
        return largestSoFar;
    }   
}
