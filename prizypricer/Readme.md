1.Spring boot Spring System Requirements and compatibility:

Spring boot 1.5.6 Spring System Requirements and compatibility
-Java compatibility
Spring boot is compatible with Java 1.6, but consider using latest version

-Maven or Gradle
Spring Boot is compatible with Apache Maven 3.2 or above, Gradle 2.9 or above

-Container
Any servlet 3.0+ compatible container
Example : Tomcat 7 or above , Jetty 8 or above

-Spring Framework
Spring Framework 4.3.10.RELEASE or above

-MySql Workbench
For sql db dump

- Postman 
For api's testing

2. Steps to build the application
	-GO to MySql workbench , go to "Data Import/Restore", and import the sql dump that is provided in the folder.
	-Open the project in STS/Eclipse and go to application.properties and set up the SQL configuration.
		spring.datasource.url=jdbc:mysql://localhost:3306/prizy_pricer (change your sql port on which the server is set up)
		spring.datasource.username  (sql username for the server)
		spring.datasource.password 	(sql password for the server)
	- Run the application as Spring boot app and port in configured at 8080.
	
- Open Postman
	Below APIs are designed as per given requirement along with some required assumptions:
	*	- Get request :localhost:8080/products
	*	- Get request :localhost:8080/products/{barcode_id}
		- Get request :localhost:8080/prices
	*	-Post request :localhost:8080/prices
		-Post request :localhost:8080/products
		To implement post requests, set the headers to : "Content-type" in key, "application/json" in value.
		Body is of type i.e.,
		For "localhost:8080/prices"
		{
		"barcodeId" : "1234",
		"price" : "123",
		"notes" : "i think so"
		}

3. To run the tests and entire application,
	Go to source folder and execute following commands in cmd: mvn clean package
	

