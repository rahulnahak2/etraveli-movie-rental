# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.

## Reason For Refactor:
The original code base lacked structure, hardcoded data and did not follow SOLID principle. Overall it was not ready for the production.
So, the code was refactored to improve the quality, readability, maintainability and scalability of code by modernizing the architecture and adopting best practices. Below are the changes to meet the expectation.

## Refactored:
1. Updated the java project with apache Maven and spring framework.
2. Updated main class as spring boot stater class.
3. Created "GenerateSlipController" class to receive request from the web browser. Added GET method to receive customer and rental movie details as defined in "CustomerDetails" class and return generated rental slip to the web browser.
4. Re-arranged the java classes with designated packages for better reading and understanding of code.
5. Added lombok to reduce boilerplate code.Add commentMore actions
6. Used Enum to define the type/categories of movies available.
7. Implemented factory and strategy design pattern with the help of solid principles to calculate the rental and bonus points.
8. Added Data JPA repositories and H2(in-memory) database to store movie, customer and rental details instead of hardcoding the data.
9. Handled custom exception and validated the user input
10. Implemented unit testing using junit and mockito to maintain and improve quality of code.
11. Implemented java 21 features to use latest version of modern java.

## To-Do :
1. More test cases can be added to achieve all corner cases.
2. Movie catalog class need to remove once full-featured db configured in the application. For testing purpose I have used (H2)in-memory DB.
3. Configuration of Spring security to secure the accessibility of data.

## To run the test:

```
mvn clean install
mvn test
```
## To run the API:
1. Connect to postman or any other API testing tool.
2. Open a tab to send a request(GET method), use URL : http://localhost:8080/movie-rental/generate-slip Please check the PORT in your local system default port is 8080.
3. In body select application/json and add the customer request such as customer name and movie code and rental days in below given format
   example : {
   "name":"C. U. Stomer",
   "rentals":[
   {
   "movieId":"F001",
   "days":3
   }
   ]
   }

