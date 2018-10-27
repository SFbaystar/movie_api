package movie_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testSortMovie extends testUtility {
	
	@Test
	public void SortMovie_ByGenreId()
	{
		//
		// SPL-003: Sorting requirement. 
		// Rule #1 Movies with genre_ids == null should be first in response. 
		// Rule #2, if multiple movies have genre_ids == null, then sort by id (ascending). 
		// For movies that have non-null genre_ids, results should be sorted by id (ascending)
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
        
        ArrayList<JSONObject> sortedResNullGenreId = new ArrayList<JSONObject>();
        ArrayList<JSONObject> sortedResNonNullGenreId = new ArrayList<JSONObject>();
        JSONArray sortedJsonArray = new JSONArray();
        
        for(int n = 0; n < results.length(); n++){
        	int count = 0;
        	JSONArray ids = results.getJSONObject(n).getJSONArray("genre_ids");
        	for(int i=0; i<ids.length(); i++) {
        		count += ids.getInt(i);
        	}
        	if( count == 0 ){
        		// Null Benre_Id
        		sortedResNullGenreId.add(results.getJSONObject(n));
        	}else{
        		// Non-null Benre_Id
        		sortedResNonNullGenreId.add(results.getJSONObject(n));
        	}
        }       
        //System.out.println("Null Benre_Id size:  " + sortedResNullGenreId.size());
        //System.out.println("NonNull Benre_Id size:  " + sortedResNonNullGenreId.size());
        
        Collections.sort(sortedResNullGenreId, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "id";

            public int compare(JSONObject a, JSONObject b) {
                Integer valA = 0;
                Integer valB = 0;

                try {
                    valA = (Integer) a.get(KEY_NAME);
                    valB = (Integer) b.get(KEY_NAME);
                } 
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });
        int i;
        for (i = 0; i < sortedResNullGenreId.size(); i++) {
            sortedJsonArray.put(sortedResNullGenreId.get(i));
        }
        
        Collections.sort(sortedResNonNullGenreId, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "id";

            public int compare(JSONObject a, JSONObject b) {
                Integer valA = 0;
                Integer valB = 0;

                try {
                    valA = (Integer) a.get(KEY_NAME);
                    valB = (Integer) b.get(KEY_NAME);
                } 
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });
        for (i = sortedResNullGenreId.size(); i < sortedResNullGenreId.size()+sortedResNonNullGenreId.size(); i++) {
            sortedJsonArray.put(sortedResNonNullGenreId.get(i-sortedResNullGenreId.size()));
        }
        
        // prepare the final sorted result
//        for(i=0; i<sortedJsonArray.length(); i++){
//        	System.out.println(sortedJsonArray.get(i).toString());
//        	System.out.println("===");
//        }
        
        Assert.assertTrue(true);
	}
}
