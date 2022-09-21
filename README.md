# robots_apocalypse
This is simple Spring boot REST API  which  stores information about the robot apocalypse survivors as well as the resources they own. It also consumes robot API and is able to keep track of all existing robots.
## Steps to Setup the Spring Boot Back end app (robots_apocalypse)

1. **Clone the application**

	```bash
	git clone https://github.com/cheruiyotcollins/robots_apocalypse.git
	cd robots_apocalypse
	```
  **By default the project uses inbuilt H2 DB but you can change this by going to application.properties file and commenting on H2 configuration settings
  and uncommenting MySQL configuration settings then following the steps below

2. **Create MySQL database**

	```bash
	create database apocalypse_db
	```

3. **Change MySQL username and password as per your MySQL installation**

	+ open `src/main/resources/application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation
	
  

4. **Run the app**

	You can run the spring boot app by typing the following command -

	```bash
	mvn spring-boot:run
	```

	The server will start on port 9090

	You can also package the application in the form of a `jar` file and then run it like so -

	```bash
	mvn package
	java -jar target/apocalypse 0.0.1-SNAPSHOT
  
  4. **Testing the Endpoints**
  5. Use any API endpoint tester for my case I used Postman. The list of available endpoints are:
  7.              **Survivors**
  8.              
  9.  **Add Survivor: http://localhost:9090/survivor/save 
  10.  **List All Survivors: http://localhost:9090/survivor/list
  11. **update survivors location: http://localhost:9090/survivor/location/update
  12. **List Infected Survivors: http://localhost:9090/survivor/infected/list
  13. **List Uninfected Survivors: http://localhost:9090/survivor/uninfected/list
  14. **Survivors Infected Percentage: http://localhost:9090/survivor/infected/percentage
  15. **Uninfected Survivors Percentage: http://localhost:9090/survivor/uninfected/percentage
  16. **Flag Survivor As Infected: http://localhost:9090/survivor/flag/infected/{id}
  17. **Find survivor by id: http://localhost:9090/survivor/find/by/{id}
  18. **Delete survivor by id: http://localhost:9090/survivor/delete/{id}
  19. 
  20.                     **Robots**
  21.                     
  22. **List  All Robots: http://localhost:9090/robot/list
  23. **List All flying robots: http://localhost:9090/robot/flying/list
  24. **List Land Robots: http://localhost:9090/robot/land/list
  25. 
  26.              **A more detailed information is available in the API specification document that is Available in this project root folder**
      
