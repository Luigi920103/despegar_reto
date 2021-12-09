# TAE Challenge - despegar test
Base framework to be used in the exercise delivered for despegar challenge

#Prerequisites
To be able to run this project locally you will need to have the following apps installed.
* Java / SDK 8 or higher
* maven (optional)
* intelliJ with cucumber plugins (optional)

# Run the Test cases
The framework includes the test case for the despegar page for the challenge
In order to see the script in execution you need to run the file `src/test/resources/Cucumber.feature` or the runner `src/test/java/RunTest.java`

Also, you can execute it through maven for do this you have to execute the following code in the terminal located on the root folder of the project
* mvn clean test or 
* mvn verify

execute * mvn verify in order to generate the reports of serenity which you will find after execute the verify command in the path \target\site\serenity


# Also, keep in mind the content of the variables in this Cucumber.feature
(**_`It applies to all`_**) 

Navigator with which you will execute the test you can use "Firefox" or if you put any other word it will execute on Chrome as default navigator
* name="navigator" value="Firefox"

Number of adults travelers
* name="adults" value="1"

Class of the flight it supports the following values Economica, Premium economy, Ejecutiva/Business, Primera Clase
*  name="class" value="Economica"

Place of departure
* name="departure_city" value="MEDE"

Note: during the test was necessary to use another value different to the provided value because the search doesn't work properly during the creation of the script provided value (MDE)

Destiny
* name="destination_city" value="bBOG"

Note: currently the app has a bug in which doesn't take the first character for this reason instead of send BOG I send bBog

Time in the future in which you will depart
gap with months since the day of the execution of the test
* name="months_gap" value="2"

gap with days since the day of the execution of the test plus the gap of months configured in the previous parameter
* name="days_gap" value="7"

Note: if you want you can manage this just with one variable for example you can mark monthGap 0 and dayGap 1, and you will indicate that your departure time is tomorrow, just keep in mind that at least one of these variable has to have a value bigger to cero a the other can be set as 0 if you want

Period of days of your travel, This will count since the day of departure
* name="comeback_days_gap" value="7"


With this, you will be able to select one of the packages that the search can give you for the round-trip flight
* name="package_option" value="1"

With this, you will be able to select one of the results that the search can give you for the round-trip flight into the package selected for the departure flight
* name="departure_option" value="1"

With this, you will be able to select one of the results that the search can give you for the round-trip flight into the package selected for the comeback flight
* name="comeback_option" value="1"

Finally, With this parameter, you will be able to indicate the message that will be shown in the page of confirmation of data before to buy the tickets
* name="message" value="¡Falta poco! Completa tus datos y finaliza tu compra"

## Using the IDE plugin
Right-click the feature file and select the option "Run"


