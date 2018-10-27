package movie_api;

import java.util.HashSet;

public class testUtility {
	
	public static final String SPLUNK_URI = "https://splunk.mocklab.io/movies";
	static final int EXPECT_ALL = 0;
	static final int EXPECT_NUM_10 = 10;
	
	// find if a title contains another title
	public static int checkTitle(String s, HashSet<String> set){
		int count = 0;

		for(String t : set) {
			if(s.equals(t))
				continue;
			
			if( s.contains(t) || t.contains(s) )
				count++;	
		}
		return count;
	}
	
	// find if there is a palindrome word
	public static boolean isPalindrome(String w) {
		int len = w.length();
		if(len<2)
			return true;
		for(int i=0; i<len/2; i++) {
			if( w.charAt(i) != w.charAt(len-1-i)) {
				return false;
			}
		}
		return true;
	}	

}
