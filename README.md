# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.


## To run the test:

```
javac src/*.java
java -cp src java.com.etraveli.movierental.com.etraveli.movierental.MovieRentalApplication
```

## Refactored:
1. Updated the java project with apache Maven and spring framework.
2. Updated main class as spring boot stater class.
3. Created "GenerateSlipController" class to receive request from the web browser. Added Post method to receive customer and rental movie details as defined in "CustomerDetails" class and return generated rental slip to the web browser.
4. Re-arranged the java classes with designated packages for better reading and understanding of code.
5. Added lombok to reduce boilerplate code.Add commentMore actions
6. Used Enum to define the type/categories of movies available.
7. Implemented factory and strategy design pattern with the help of solid principles to calculate the rental and bonus points.

## To-Do :
1. Configuration of database to store and manage the data.
2. Add test cases to optimize the quality of code and helps designing the application for future enhancements.
