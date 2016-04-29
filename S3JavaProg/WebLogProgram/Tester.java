
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    private LogAnalyzer la;
    public Tester(){
        la = new LogAnalyzer();
        la.readFile("weblog2_log");
    }
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        //System.out.println("#######");
        la.printAll();
    }
    
    public void testCountUniqueIPs(){

        System.out.println("CountUniqueIPs() "+la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        ArrayList<LogEntry> rec = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("size "+rec.size());
        for(LogEntry le : rec){
            System.out.println(le);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        System.out.println("CountUniqueIPsInRange() "+ la.countUniqueIPsInRange(400, 499));
    
    
    }
    
    public void testMostNumberVisitsByIP(){
        HashMap<String, Integer> map = la.countVisitsPerIP();
        System.out.println("mostNumberVisitsByIP()" + la.mostNumberVisitsByIP(map));
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
