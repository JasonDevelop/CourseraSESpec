
/**
 * Write a description of CountsTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CountsTester {
    private LogAnalyzer la = new LogAnalyzer();
    public CountsTester(){
        la.readFile("weblog2_log");
    }
    public void testCountVisitsPerIP(){

        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("countVisitsPerIP() "+ counts);
    }
    
    public void testMostNumberVisitsByIP(){

        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println("MostNumberVisitsByIP() "+ max);
    }
    
    public void testIPsForDays(){
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(map);
    }
    
    public void testDayWithMostIPVisits(){
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println("dayWithMostIPVisits() " + la.dayWithMostIPVisits(map));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(map, "Sep 29"));
        
    }
}
