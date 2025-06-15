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

## Changes:
1. Updated the java project with apache Maven and spring framework.
2. Updated main class as spring boot stater class.
3. Created "GenerateSlipController" class to receive request from the web browser. Added Post method to receive customer and rental movie details as defined in "CustomerDetails" class and return generated rental slip to the web browser.
4. Re-arranged the java classes with designated packages for better reading and understanding of code.

## To-Do :Add commentMore actions
1. Re-construction of business logic with boilerplate code and helper classes.
2. Configuration of database to store and manage the data.
3. Add test cases to optimize the quality of code and helps designing the application for future enhancements.
