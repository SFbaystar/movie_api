package movie_api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testGetResponseCode extends testUtility {
	
	@Test
	public void testResponseStatusCode(){
		
		RestAssured.baseURI = SPLUNK_URI;		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON);
		httpRequest.param("q", "batman");

		Response response = httpRequest.get();
		
		// get and verify the status code
		int code = response.getStatusCode();
		Assert.assertEquals( code, 200 );
	}
}
