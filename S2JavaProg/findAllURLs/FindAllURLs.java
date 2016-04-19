
/**
 * Write a description of FindAllURLs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class FindAllURLs {
    public  StorageResource find(String url){
        URLResource page = new URLResource(url);
        StorageResource sr = new StorageResource();
        for (String word: page.words()){
            //System.out.println(word);
            if(word.indexOf("href=") != -1){
                //System.out.println(word);
                int start = word.indexOf("http");
                //int boundary = word.indexOf(">");
                int stop = word.indexOf("\"", start + 5);
                if(start != -1 && stop != -1){
                    //System.out.println(word.substring(start, stop));
                    //System.out.println(word);
                    //System.out.println(start+"******"+stop);
                    sr.add(word.substring(start, stop));
                }
            }
        }
        
        return sr;
    }
    
    public void testURLWithStorage(){
    
        StorageResource sr = find("http://qq.com");
        StorageResource sec = new StorageResource();
        StorageResource com = new StorageResource();
        StorageResource comend = new StorageResource();
        int dotcount = 0;
        System.out.println();
        System.out.println("all the URLs found on this page");
        for(String url:sr.data()){
            //System.out.println(url);
            if(url.indexOf("https") != -1){sec.add(url);}
            if(url.indexOf(".com") != -1){com.add(url);}
            if(url.indexOf(".com") >= url.length() - 5){comend.add(url);}
            dotcount += count(url);
        }
        System.out.println("the number of URLs found on this page");
        System.out.println(sr.size());
        System.out.println("the number of secure links");
        System.out.println(sec.size());
        System.out.println("number of links that have .com");
        System.out.println(com.size());
        System.out.println("number of links that end with .com or .com/");
        System.out.println(comend.size());
        System.out.println("total number of dots");
        System.out.println(dotcount);
        
        

        
    
    }
    
    public int count(String url){
    
        int count = 0;
        int pos = -1;
        while(true){
            pos = url.indexOf(".", pos+ 1);
            if (pos == -1){break;}
            count ++;
        }
        return count;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
