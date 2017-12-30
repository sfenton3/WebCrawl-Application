/* A parse website, recieves a book title.
 *    1. Locate url that contains search. (findLink)
 *    2. Use search to find raw links.    (findLink)
 *    3. click-though link to find description/rating/reviews
 * 
 */

public interface parseInterface {
  //searchTerm is a book title.
  public boolean findLink(String searchTerm);
  public boolean description();
  public boolean rating();
  public boolean reviews();
  
  
}