
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
    public String countryInfo(CSVParser parser, String country){
        String info = "";
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                info = country + ":" + record.get("Exports") + ":" + record.get("Value (dollars)");
                return info;
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));            
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int num = 0;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                num ++;
            }
        }
        return num;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            if(record.get("Value (dollars)").length() > amount.length()){
                System.out.println(record.get("Country") + " " +record.get("Value (dollars)"));
            }
        }
    }
}
