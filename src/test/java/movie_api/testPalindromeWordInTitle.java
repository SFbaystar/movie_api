package movie_api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testPalindromeWordInTitle extends testUtility{
	
	@Test
	public void testPalindromeTitle() {
		//
		// SPL-005: There is at least one movie in the database whose title has a palindrome in it
		//

		RestAssured.baseURI = SPLUNK_URI;		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON);
		httpRequest.param("q", "batman");

		Response response = httpRequest.get();
		
		// Start parsing
        JSONObject obj = new JSONObject(response.asString());
        
        // get Array type
        JSONArray results = obj.getJSONArray("results");
        
        // find palindrome word in title
        for(int n = 0; n < results.length(); n++){
			String[] list = results.getJSONObject(n).getString("title").split(" ");
			for( String w: list){
				if( isPalindrome(w)) {
					// found the palindrome title
					Assert.assertTrue(true);
					return;				
				}
			}
		}
        
		// palindrome word not found
		Assert.assertTrue(false);
	}

}
