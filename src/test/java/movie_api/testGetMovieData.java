package movie_api;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class testGetMovieData extends testUtility {
	
	@Test
	public void test_Get() {
		//
		// check the uri and GET api basic
		//
        given().
        	param("q", "batman").
        	param("count", 0).
        	headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
        when().
        	get(SPLUNK_URI).
        then().
        	assertThat().
        	statusCode(200);
	}
}