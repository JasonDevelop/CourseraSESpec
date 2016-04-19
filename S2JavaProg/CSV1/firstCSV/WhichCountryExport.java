
/**
 * Write a description of WhichCountryExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountryExport {
    public void find(CSVParser parser, String interest){
        for (CSVRecord record : parser){
            if(record.get("Exports").contains(interest)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public void findCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        find(parser, "coffee");
    }

}
