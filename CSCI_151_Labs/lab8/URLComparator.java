/**
 * This class contains the code to compute the relevance score of a
 * web page, in response to a query.  It also contains a method to
 * compare the scores of two web pages.
 *
 * @author John Donaldson (Spring 2016)
 */

import java.util.*;

class URLComparator implements Comparator<WebPageIndex> {

   List<String> query;
   
   URLComparator(List<String> query){
      this.query = query;
   }
   
   public int score(WebPageIndex idx){
	   int totalScore = 0;
	   int[] subScore = new int[query.size()+1];
	   int i = 0;
	   for(String str : query) { //for every word in query
		   if(idx.contains(str)) { //if word is contained in index
			   int indScore = idx.getCount(str); //get count of str occurrences
			   subScore[i] = indScore; //add str score to subScore list
		   }  
		   i++; //next index in subScore list
	   }
	   for(int j = 0; j < subScore.length-1; j++) { //for every query sub score
		   totalScore = totalScore + subScore[j]; //add them together to generate total score
	   }
	   return totalScore;
   }
	   
	   
      
    public int compare(WebPageIndex idx1, WebPageIndex idx2){ 
       if(score(idx1) < score(idx2)) {
    	   return -1;
       }
       else if(score(idx1) > score(idx2)) {
    	   return 1;
       }
       else{
       return 0;
       }
    }

}
