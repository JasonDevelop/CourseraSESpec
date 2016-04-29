
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> unique = new ArrayList<String>();
         for (LogEntry le : records){
             String ip = le.getIpAddress();
             // if ip not recorded add it to unique
             if(! unique.contains(ip)){
                 unique.add(ip);
             }
         }
         return unique.size();
     }
     
     public void printAllHigherThanNum(int num){
        for(LogEntry le : records) {
            int status = le.getStatusCode();
            if(status > num){
                System.out.println(le);
            }
        } 
     }
     
     public ArrayList<LogEntry> uniqueIPVisitsOnDay(String someday){
         ArrayList<LogEntry> rec = new ArrayList<LogEntry>();
         ArrayList<String> uniIp = new ArrayList<String>();
         for(LogEntry le : records){
             String currentDay = le.getAccessTime().toString().substring(4, 10);
             // test if got correct format
             System.out.println(currentDay);
             if(currentDay.equals(someday)){
                 // if le not in uniip
                 if(! uniIp.contains(le.getIpAddress())){
                     uniIp.add(le.getIpAddress());
                     rec.add(le);
                 }
             }
         }
         return rec;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         int count = 0;
         ArrayList<String> unique = new ArrayList<String>();
         ArrayList<LogEntry> rec = new ArrayList<LogEntry>();
         for (LogEntry le : records){
             // if le status in range
            int status = le.getStatusCode();
            String ip = le.getIpAddress();
            if(status >= low && status <= high){
                // not recorded
                if(! unique.contains(ip)){
                    unique.add(ip);
                    rec.add(le);
                }
            }
         }
         return rec.size();
       
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             // if ip not recorded
             String ip = le.getIpAddress();
             if(! counts.containsKey(ip)){
             // put to hashmap with value 1
                counts.put(ip, 1);
             }else{
                 // ip recorded put increased value
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for(String ip : counts.keySet()){
             if(counts.get(ip) > max){
                 max = counts.get(ip);
             }
         }
         return max;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             String day = le.getAccessTime().toString().substring(4, 10);
             if(map.containsKey(day)){
                // ip recorded
                ArrayList<String> ips = map.get(day);
                ips.add(ip);
                map.put(day, ips);
             }else{
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ip);
                 map.put(day, ips);
             }
         }
         return map;
     }
        
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
         String maxDay = "";
         int maxIpNum = 0;
         for(String day : map.keySet()){
             int currentIpNum = map.get(day).size();
             if(currentIpNum > maxIpNum){
                 maxIpNum = currentIpNum;
                 maxDay = day;
             }
         }
         return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
         int maxTimes = 0;
         String maxIp = "";
         ArrayList<String> ips = new ArrayList<String>();
         HashMap<String, Integer> ipOnDayTimes = new HashMap<String, Integer>();
         ArrayList<String> ipsOnDay = new ArrayList<String>();
         for(String currentDay : map.keySet()){
             if(currentDay.equals(day)){// find out the specific day
                 ipsOnDay = map.get(day);
             }
         }
         for(String ip : ipsOnDay){
             if(ipOnDayTimes.containsKey(ip)){// ip recorded
                 ipOnDayTimes.put(ip, ipOnDayTimes.get(ip) + 1);                
             }else{
                 ipOnDayTimes.put(ip, 1);
             }
         }
         // get most visited ip
         for(String ip : ipOnDayTimes.keySet()){
             int currentTimes = ipOnDayTimes.get(ip);
             if(currentTimes > maxTimes){
                 maxTimes = currentTimes;
                 //maxIp = ip;
             }
             
         }
         for(String ip : ipOnDayTimes.keySet()){
             int currentTimes = ipOnDayTimes.get(ip);
             if(currentTimes == maxTimes){
                 ips.add(ip);
             }
         }
         return ips;
     }
     
     
     
     
     
     
}
