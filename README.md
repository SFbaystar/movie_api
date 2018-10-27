# movie_api

CompanyS has decided to diversify its product line and go into the movie data business. We are collecting movie information from a variety of sources and exposing REST API to enable anyone to be able fetch these details. In addition, users can also submit movie information. CompanyS is using some fancy ML algorithms to process the submission and add to our movie database. 

Movie API Details:
Endpoint: https://splunk.mocklab.io/movies


GET /movie: Returns CompanyS’s entire collection of movies

Parameters
Name	Required	Description	Internal CompanyS Notes
  q	  Required	Movie name	Currently, the API only supports q=’batman’. 
count	optional	Limits number of records in the response. Count = 0 mean return all records	

Headers:
Must pass headers: Accept = application/json


POST /movie: Allows anyone to submit a movie and it’s description for processing. Once posted, the movie will appear in the GET /movie API 

Payload:
name: <string> (required)
description: <string>	(required)

Headers:
Must pass headers: Content-Type = application/json
