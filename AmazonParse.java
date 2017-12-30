import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AmazonParse implements parseInterface{
  
  public static String rating;
  public static String bookLink;
  public static String bookDesc;
  public static String[] reviewList = new String[20];

  
  //Each seperate stat method returns t/f depending if it can fetch the data
  public static void main(String[] args){
    AmazonParse ap = new AmazonParse();
    ap.findLink("Name of the wind"); 
    BrowserTab bt = new BrowserTab();
    bt.openTab(bookLink);
    System.out.println(bookLink);
  }
  
  public boolean findLink(String searchTerm){
    String url = "https://www.amazon.com/s/ref=nb_sb_ss_rsis_1_0?url=search-alias%3Daps&field-keywords=" + searchTerm;
    try{
      Document doc = Jsoup.connect(url).get();
      Element firstLink = doc.body().select("a[class*=s-access-detail-page]").first();
      bookLink = firstLink.attr("href");
    }catch(Exception e){
      return false;
    }
    return true;    
  }
  
  public boolean description(){
    try{
      Document doc = Jsoup.connect(bookLink).get();
      Element content = doc.body().select("div[id=bookDescription_feature_div]").first();
      bookDesc = content.text();
    }catch(Exception e){
      return false;
    }
    return true;    
  }
  
  public boolean rating(){
    try{
      Document doc = Jsoup.connect(bookLink).get();
      Element content = doc.body().select("span.arp-rating-out-of-text").first();
      rating = (content.text());
    }catch(Exception e){
      return false;
    }
    return true;   
  }
  
  public boolean reviews(){
    try{
      Document doc = Jsoup.connect(bookLink).get();
      Elements content = doc.body().select("div[class=a-row review-data]");
      
      int index = 0;
      for(Element cont : content){
        reviewList[index++] = (content.text().replace("Read more", ""));
      }
    }catch(Exception e){
      return false;
    }
    return true;    
  }
}
    