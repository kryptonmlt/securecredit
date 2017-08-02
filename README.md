# SecureCredit

## Design decisions
The application is programmed in java/spring and tried to include a variety of features such as the;
* rest controller to expose the data
* JPA repositories to allow for easy interaction with the database
* entities which are then mapped to the connected database and viceversa
* Logging both to file and console
* Unit Tests
* Spring Security to authenticate all the pages except the login page (included user and admin roles) 
* Used HSQL instead of MySQL for easy set up

#### Code Structure:
* org.kryptonmlt.securecredit: Initialisation class
	* model: Maps to database model objects
	* web: Expose the database objects via rest and allow authenticated manipulation
	* validator: Validates the user form
	* service: performs business logic and uses the repositories
	* repository: Interacts with the connected database
	* utils: Utility classes
	
## Prerequisites
- JDK 1.8 or later
- Maven 3 or later

#### Build command:
* mvn clean install
* (war file is generated inside the target folder)

#### Requirements to run:
* java 8
* tomcat 8
* Place the war file under DIST/ (or build your own) inside tomcat/webapps
* Note - user:admin, password:admin is automatically created, rest is empty

## Stack
* Spring Security
* Spring Boot
* Spring Data JPA
* Spring Web Services
* Maven
* JSP
* HSQL
* JUnit

#### Default Web page to use system:
* localhost:8080/securecredit

Login/Create User Page:
![alt text](https://raw.githubusercontent.com/kryptonmlt/securecredit/master/readmeImages/login.png)

Credit Card create/view page:
![alt text](https://raw.githubusercontent.com/kryptonmlt/securecredit/master/readmeImages/cardinsert.png)

Admin page: (can see and edit everyone's credit cards)
![alt text](https://raw.githubusercontent.com/kryptonmlt/securecredit/master/readmeImages/adminview.png)

Credit Card update page:
![alt text](https://raw.githubusercontent.com/kryptonmlt/securecredit/master/readmeImages/adminUpdate.png)

## Closing Remarks:
Given more time there are more things I could have done example;
* Controller mocking, for in-depth tests
* Encrypting credit card number using random secret and not storing it in same database
* Use MySQL instead of HSQL
* Use Spring OAuth instead of the basic Spring Security
