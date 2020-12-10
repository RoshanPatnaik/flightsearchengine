# FlightSearchEngine

## Overview:
The search engine allows the users to search flights between places with their own search preferences (number of stops,duration of journey, price range) on a particular date.

## Steps to run and use the search engine in your system: 
* Open any Code editor which has Java and SpringBoot support
* Create a new Spring Starter Project and add all the files in this repo - "https://github.com/loser968/flightsearchengine" 
* Create a database with the name "flight_details" in your local MySql server
* Open application.properties file in the resources and enter the username and password of your MySql Server  
* Run the project as SpringBootApp and make sure your internal server is running
* Open any web browser and type in the link - "localhost:8080/flights/save". This would save all the details of flights available in flight_schedule.txt file into your database 	
* Then if you change the link to "localhost:8080/flights/all" you will get a html page with available flights
* You can click on 'Search for Your Flights' button and then search for the flights according to your own preferences
 
## Technologies Used:
* SpringBoot
* MySQL
* Html
