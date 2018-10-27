package movie_api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testParamCountNum extends testUtility{
	
	@Test
	public void testParamCount(){
		//
		// Test param(count, number) - this test failed because count does not work [Bug-001] 
		//
		RestAssured.baseURI = SPLUNK_URI;		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON);
		httpRequest.param("q", "batman");
		httpRequest.param("count", EXPECT_NUM_10);

		Response response = httpRequest.get();
		
		// get and verify the status code
		int code = response.getStatusCode();
		Assert.assertEquals( code, 200 );
		
		// Start parsing
        JSONObject obj = new JSONObject(response.asString());
        //System.out.println(obj.toString());
        
        // get Array type
        JSONArray results = obj.getJSONArray("results");

        Assert.assertEquals(results.length(), EXPECT_NUM_10);
	}
	
}
