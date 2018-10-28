# movie_api

<< How to Run >>
1. Check out the develop branch of movie_api repository
2. Open the Maven project using Eclipse
3. Install TestNG into Eclipse if you have not
4. Execute each java class by TestNG



<< Bug Found >>
1. Bug-001 - parameter "count" does not work. The response is always have all movies (total 16),
   no matter the count value (not 0, like 1 or 10)
2. Bug-002 - parameter "q" can be set as other title without any message, but the response ia always as the same as "batman"
3. Bug-003 - poster_path link is not available for some movies



<< Test execution report >>

SPL-001: No two movies should have the same image 

Test result: Same image not found. 

SPL-002: All poster_path links must be valid. poster_path link of null is also acceptable
Test result: Several movies do not have images links. Bug-003 logged.

SPL-003: Sorting requirement. Rule #1 Movies with genre_ids == null should be first in response. Rule #2, if multiple movies have genre_ids == null, then sort by id (ascending). For movies that have non-null genre_ids, results should be sorted by id (ascending)

Test result: sort test case added.

SPL-004: The number of movies whose sum of "genre_ids" > 400 should be no more than 7. Another way of saying this is: there at most 7 movies such that their sum of genre_ids is great than 400
Test result: No more than 7 result confirmed.
SPL-005: There is at least one movie in the database whose title has a palindrome in it. 
Example: "title": "Batman: Return of the Kayak Crusaders". The title contains ‘kayak’ which is a palindrome.

Test result: Found palindrome word in title.

SPL-006: There are at least two movies in the database whose title contain the title of another movie. Example: movie id: 287757 (Scooby-Doo Meets Dante), movie id: 404463 (Dante). This example shows one such set. The business requirement is that there are at least two such occurrences. 

Test result: Confirmed there are at least two movies whose title contain the title of another movie.


Rest API "GET" has been tested
Result – Status code: 200
Return result with 16 movies' information details.

Rest API "POST" has been tested
Result – Status 200
{ "message": "Splunking your submission using monkeys ..... success... movie posted to catalog" }
