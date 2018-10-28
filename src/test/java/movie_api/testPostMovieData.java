package movie_api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static movie_api.testUtility.*;

public class testPostMovieData {
	
	@Test(groups="basic")
	public void test_Post() {
		
        given().
        	param("name", "superman").
        	param("description", "the best movie ever made").
        	headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
        when().
        	post( SPLUNK_URI ).
        then().
        	assertThat().
        	statusCode(200);
        
        //Assert.assertTrue(true, "POST SUCCESS!");
	}
}
