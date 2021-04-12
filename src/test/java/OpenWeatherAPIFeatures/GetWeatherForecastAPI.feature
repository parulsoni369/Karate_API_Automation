@OpenWeatherMapAPIs
Feature:	Get Weather Forecast For Sydney
  Background:

	#Input data to be passed to url
    * def weatherUrl = OpenWeatherData.WeatherUrl + "?q=" + OpenWeatherData.CITY + "&units=" + OpenWeatherData.UNIT + "&appid=" +OpenWeatherData.APP_ID
    * def OneCallUrlPartOne = OpenWeatherData.OneCallUrl + "?lat=" + OpenWeatherData.LAT + "&lon=" + OpenWeatherData.LONG
    * def OneCallUrlPartTwo = "&units=" + OpenWeatherData.UNIT + "&exclude=" + OpenWeatherData.EXCLUDE_DATA + "&appid=" +OpenWeatherData.APP_ID
    * def OneCallUrl = OneCallUrlPartOne + OneCallUrlPartTwo
    * def JavaClass = Java.type('Utils.Utility')

  Scenario: (1) Get Current Weather forecast for Sydney City
	# Define and read the base URL
    * url HostUrl + weatherUrl
	# Send the request using the payload data
    When method get
    Then status 200
    Then print response
    # Match response contains City name as Sydney city
    Then match response.name == ExpectedData.City_Name
    # Match the longitude in reponse
    Then match response.coord.lon == ExpectedData.Longitude
    # Match the latitude in response
    Then match response.coord.lat == ExpectedData.Latitude
    And print "Current Temperature : " +response.main.temp + " degrees Celsius"

  Scenario:	(2) Get Weather forecast for Sydney City for next 7 days and validate temperature for Thursday
    Given url HostUrl + OneCallUrl
    When method get
    Then status 200
    Then print response
    # Match the time zone found is for Sydney City
    Then match response.timezone == ExpectedData.Timezone
    * def jsonResponse = response
    * def date = JavaClass.getDate(jsonResponse)
    # Assert 7 days forecast contains Thursday
    Then assert date.contains("Thu")
    * def minimumTemperature = JavaClass.getMinimumTemperatureForThursday(response)
    # Assert minimum temperature on Thursday is more than 10 degrees Celsius
    Then assert minimumTemperature >= ExpectedData.MinimumTemperature
    Then print "Minimum temperature on : " + date + " for " + response.timezone + " is : " + minimumTemperature + " degree Celsius "


