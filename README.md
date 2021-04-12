# Karate_API_Automation
This is a project on Open Weather Map APIs test automation using Karate API BDD Automation Framework.

##Feature:
Get Weather Forecast For Sydney
###Scenario 1: 
Get Current Weather forecast for Sydney City.
###Scenario 2: 
Get Weather forecast for Sydney City for next 7 days and validate temperature for Thursday

## Setup
1. Clone this repository in your local directory.
2. Open the project using intelliJ or Eclipse.
3. Open Weather Map website 'https://openweathermap.org/' and 'Sign Up' OR 'Sign In' to your account.
4. Go to 'My API Keys' link in your account created and create your own APIKey or copy the default one provided.
5. Go to your project, and set your 'APIKey' value (the one you copied), in the JSON key_name "APP_ID" inside 'Data/OpenWeatherMapData/OpenWeatherData.json'

## How To Run
1. Run the cucumber test Runner from 'src/test/java/Execution/WeatherAPICucumberRunner.java'
2. This will generate Cucumber report inside directory structure like 'target/WeatherAPI_Reports_12_04_2021_23_00_08/cucumber-html-reports' 
3. Open 'overview-features.html' in your browser. This is the cucumber report generated.
4. For generating Karate surefire-reports run 'src/test/java/Execution/KarateTestRunner.java'
   (By default Cucumber reports will also generate the same Karate surefire reports)
   