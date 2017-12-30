import java.util.concurrent.TimeUnit;

public class WebcrawlSpeed{
  
  public static int delay;
  
  public static void main(String[] args){
    delay();   
  }
   
  WebcrawlSpeed(){
    this.delay = 5;
    
  }
  WebcrawlSpeed(int delay){
    this.delay = delay;    
  }
  
  public static void delay(int delay){
    try{
      TimeUnit.SECONDS.sleep(delay);}
    catch(Exception e){
      System.out.println("Error in delay(int delay) method in WebcrawlSpeed");
    }   
  }
  
  public static void delay(){
    try{
      TimeUnit.SECONDS.sleep(5);
    }
    catch(Exception e){
      System.out.println("Error in delay() method in WebcrawlSpeed"); 
    }   
  }
    
  
}
    
    