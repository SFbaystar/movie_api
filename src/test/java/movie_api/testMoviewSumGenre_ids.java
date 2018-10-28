package movie_api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testMoviewSumGenre_ids extends testUtility {
	
	@Test(groups="function")
	public void VerifyMovieSumGenre_Ids()
	{
		//
		// SPL-004: The number of movies whose sum of "genre_ids" > 400 should be no more than 7. 
		// Another way of saying this is: there at most 7 movies such that their sum of genre_ids is great than 400
		//
		RestAssured.baseURI = SPLUNK_URI;		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON);
		httpRequest.param("q", "batman");

		Response response = httpRequest.get();
        //System.out.println(response.asString());
		
		// Start parsing
        JSONObject obj = new JSONObject(response.asString());
        //System.out.println(obj.toString());
        
        // get Array type
        JSONArray results = obj.getJSONArray("results");
        
        // SPL-001: No two movies should have the same image 
        int total = 0;
        for(int n = 0; n < results.length(); n++){
        	int count = 0;
        	JSONArray ids = results.getJSONObject(n).getJSONArray("genre_ids");
        	for(int i=0; i<ids.length(); i++) {
        		count += ids.getInt(i);
        	}
        	if( count>400 )
        		total++;
        }
        System.out.println("Total count: "+ total);
        Assert.assertEquals(total<=7, true);

	}

}
