package movie_api;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testVerifyUniquePostImage extends testUtility {
	
	@Test
	public void VerifyUniqueImageInJsonResponse()
	{
		//
		// SPL-001: No two movies should have the same image
		// SPL-002: All poster_path links must be valid. poster_path link of null is also acceptable
		//
		HashSet<String> postSet = new HashSet<String>();
		RestAssured.baseURI = SPLUNK_URI;		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON);
		httpRequest.param("q", "batman");

		Response response = httpRequest.get();
		
		// Start parsing
        JSONObject obj = new JSONObject(response.asString());
        //System.out.println(obj.toString());
        
        // get Array type
        JSONArray results = obj.getJSONArray("results");
        
        // SPL-001: No two movies should have the same image 
        String poster_path;
        for(int n = 0; n < results.length(); n++){
        	
        	if( !results.getJSONObject(n).has("poster_path")) {
        		// SPL-002: All poster_path links must be valid. poster_path link of null is also acceptable
        		// no link - this broke the rule
        		Assert.assertTrue(false, "no image link");
        	}

            // under results, get string type
        	if( !results.getJSONObject(n).getString("poster_path").isEmpty()){
            	if( results.getJSONObject(n).get("poster_path").equals(null) ){
            		// SPL-002: All poster_path links must be valid. poster_path link of null is also acceptable
            		poster_path = "";
            	} else {
            		poster_path = results.getJSONObject(n).getString("poster_path");
            	}
            	
            	// prepare post's HashSet
        		if( !postSet.contains(poster_path))
        			postSet.add(poster_path);
        		else
        			Assert.assertTrue(false, "same post found");
            	System.out.println(poster_path);
        	}
        }
        Assert.assertTrue(true, "No same image");
	}

}
