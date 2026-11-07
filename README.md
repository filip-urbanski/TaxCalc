# Tax Calculator - Refactoring & Clean Code Assignment

This repository contains an improved version of the **TaxCalculator** application, refactored according to the Clean Code principles and refactoring techniques.

## Refactoring Steps

// session 1
1. set naming standard to Pascal Case
2. remove unused variables
3. extract CalculatorClass from main to create it and add a conscrutor
4. replace char variable EmplyementType with enum
5. add tax() method to call

// session 2  
6. replace if clauses with a switch statement  
7. add DTO TaxCalculatorResult for storing and printing  
8. add function which transforms input into enum  
9. implement input validation for income and employment type  
10. override toString() to output DTO values  
11. extract employemnt and civil processing to functions  

// further refactoring
12. replace the two tax calculation methods with one method that receives a tax strategy
13. use BigDecimal for all monetary calculations
14. move DTO formatting to a dedicated formatter

---

## How to Run
1. Ensure you have **Java 21+** and **Maven** installed.
2. Build the project and run tests:
   ```bash
   mvn clean test
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```