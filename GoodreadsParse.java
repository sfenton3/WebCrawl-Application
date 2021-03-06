import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GoodreadsParse implements parseInterface {

  public static String rating;
  public static String bookLink;
  public static String bookDesc;
  public static String[] reviewList = new String[100];

  public static void main(String[] args) throws Exception{
    GoodreadsParse grp = new GoodreadsParse();
     //grp.findLink("name of the wind");
     bookLink = "https://www.goodreads.com/book/show/186074.The_Name_of_the_Wind";
     grp.reviews();
     for(int i=0; i < reviewList.length; i++){
       TimeUnit.SECONDS.sleep(5);
       System.out.println(reviewList[i]);
     }
  }
  
  public boolean findLink(String searchTerm){
    String url = "https://www.goodreads.com/search?utf8=?&q=" + searchTerm + "&search_type=books";
    try{
      Document doc = Jsoup.connect(url).get();
      Element firstLink = doc.body().select("a[class=booktitle]").first();
      bookLink = ("https://goodreads.com" + firstLink.attr("href")).replace("?from_search=true", "");
    }catch(Exception e){
      return false;
    }
    return true;
  }

  public boolean description(){
    try{
      Document doc = Jsoup.connect(bookLink).get();
      Element content = doc.body().select("span[id*=freeText][style]").first();
      bookDesc = content.text();
    }catch(Exception e){
      return false;
    }
    return true;
  }

  public boolean rating(){
    try{
      Document doc = Jsoup.connect(bookLink).get();
      Element content = doc.body().select("span[class=value rating]").first();
      rating = (content.text());
    }catch(Exception e){
      return false;
    }
    return true;
  }

  public boolean reviews(){
    try{
      Document doc = Jsoup.connect(bookLink).get();
      Elements content = doc.body().select("span[id*=freeText][style]");

      int index = 0;
      for(Element cont : content){
          reviewList[index++] = (content.text());
      }
    }catch(IOException e){
      return false;
    }
    return true;
  }

}
