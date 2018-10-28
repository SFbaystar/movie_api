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

public class testTileContainsOtherTitle extends testUtility {
	
	@Test(groups="function")
	public void testTwoTileContainsOtherTitle() {
		//
		// SPL-006: There are at least two movies in the database whose title contain the title of another movie
		//
		HashSet<String> set = new HashSet<String>();
		HashSet<String> checked = new HashSet<String>();

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

        for(int n = 0; n < results.length(); n++){
			if( !set.contains(results.getJSONObject(n).getString("title")) ) {
				set.add( results.getJSONObject(n).getString("title") );
			}
		}
		//System.out.println("HashSet size0 = "+set.size());

		int count = 0;
		for(String t : set) {

			if(checked.contains(t))
				continue;
			else
				checked.add(t);

			count += checkTitle(t, set);
			//System.out.println("Count = " + count);

			if(count>=2) {
				Assert.assertTrue(true);
				return;
			}
		}

		if(count >= 2) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}

}
