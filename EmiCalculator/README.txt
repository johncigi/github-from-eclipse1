**************************************************************Details about this project************************************************************

Website Used : EMICalculator.net
Browser : Firefox/Chrome
Framework Used : TestNG Maven
Tecnology Used : Jenkins Selenium_Grid

**************************************************************Run this project**********************************************************************

1. Open cmd and go to folder where selenium-server-standlone-jar is present
2. Make sure all webdrivers and jar are at same location
3. On cmd pass this command
	java -jar selenium-server-standalone-3.141.59.jar -role hub -port 5555

4. Make sure your chromedriver and geekodriver are saved in your C: drive.

5. Open another cmd  and go to folder where selenium-server-standlone-jar is present
	java -Dwebdriver.gecko.driver="C:\geckodriver.exe" -Dwebdriver.chrome.driver="C:\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://192.168.1.17:5555/grid/register -port 5566

6. Open cmd and go to project folder loaction
	mvn clean install

7. Open project and run EMIgrid.xml file as Testng.

7. All test cases will run on Chrome and FireFox browsers parallelly using Selenuim grid.


This will run your project 
***************************************************************************************************************************************************

1. On successful build HTMl report will be created at folder test-output

**************************************************************About the java files*****************************************************************

1. BaseTestClass.java and PageBaseClass.java
	Files where all global methods are declared
	Location : \src\main\java\baseClasses

2. Pom Pages
	Location : \src\main\java\pageClasses

3. Test Cases
	Location : \src\main\java\testCases

4. Utilities
	Location : \src\main\java\utilities

5. WebDrivers
	Location : \drivers

6. Excel files and config.property file
	Location : \Resources\Repositiries



