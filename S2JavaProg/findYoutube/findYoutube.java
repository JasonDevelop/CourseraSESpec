
/**
 * Write a description of findYoutube here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class findYoutube {
    public void find(){
        URLResource page = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word: page.words()){
            //System.out.println(word);
            if(word.indexOf("youtube.com") != -1){
                //System.out.println(word);
                int start = word.indexOf("\"");
                int boundary = word.indexOf(">");
                int stop = word.lastIndexOf("\"", boundary);
                System.out.println(word.substring(start + 1, stop+1));
                
            }
        }
    }
}
