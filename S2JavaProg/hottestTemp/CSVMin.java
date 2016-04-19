/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with smallestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If smallestSoFar is nothing
            smallestSoFar = smallestOfTwoRecords(smallestSoFar, currentRow);
        //The smallestSoFar is the answer
        }
        return smallestSoFar;
    }
    

    public void testHottest () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEST"));
    }
    
    public CSVRecord coldestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            smallestSoFar = smallestOfTwoRecords(smallestSoFar, currentRow);
        }
        return smallestSoFar;
    }
    
    public void testColdestInManyDays(){
        CSVRecord smallest = coldestInManyDays();
        System.out.println("coldest temperature in many days was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public CSVRecord smallestOfTwoRecords(CSVRecord smallestSoFar, CSVRecord currentRow){
    
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currentTemp < smallestTemp){
                if(currentTemp != -9999){
                    smallestSoFar = currentRow;
                }
            }
        }
        
        return smallestSoFar;
    }
    
    public void fileWithColdestTemperature(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            String info = record.get("DateUTC") + ": " + record.get("TemperatureF");
            System.out.println(info);
        }
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            String rawHumidity = currentRow.get("Humidity");
            if( !rawHumidity.equals("N/A")){
                smallestSoFar = smallestOfTwoRecordsOfSpecificItem("Humidity" , smallestSoFar, currentRow);               
            }
        }
        return smallestSoFar;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
        System.out.println("lowest Humidity was " + lowestHumidity.get("Humidity") +
                   " at " + lowestHumidity.get("DateUTC"));
        
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = lowestHumidityInFile(parser);
            smallestSoFar = smallestOfTwoRecordsOfSpecificItem("Humidity" , smallestSoFar, currentRow);
        }
        return smallestSoFar;
        
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println("lowest Humidity In Many Files was " + smallest.get("Humidity") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public CSVRecord smallestOfTwoRecordsOfSpecificItem(String item ,CSVRecord smallestSoFar, CSVRecord currentRow){
    
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else{
            double current = Double.parseDouble(currentRow.get(item));
            double smallest = Double.parseDouble(smallestSoFar.get(item));
            if(current < smallest){
                
                    smallestSoFar = currentRow;
            }
        }        
        return smallestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sumTemp = 0.0;
        int num = 0;
        for(CSVRecord record : parser){
            sumTemp += Double.parseDouble(record.get("TemperatureF"));
            num ++;
        }
        
        return sumTemp/num;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumTemp = 0.0;
        int num = 0;
        for(CSVRecord record : parser){
            String rawHumi = record.get("Humidity");
            if(!rawHumi.equals("N/A")){
                double humidity = Double.parseDouble(rawHumi);
                if(humidity >= value){
                    double temp = Double.parseDouble(record.get("TemperatureF"));
                    num ++;
                    sumTemp += temp;
                }
            }
        }
        
        return sumTemp/num;
        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(averageTemperatureWithHighHumidityInFile(parser, 80));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
