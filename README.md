### Refactoring steps

//TODO consider if the variables shoud be bigdecimal or double

// session 1
1. set naming standard to Pascal Case
2. remove unused variables
3. extract CalculatorClass from main to create it and add a conscrutor
4. replace char variable EmplyementType with enum
5. add tax() method to call

//session 2
6. replace if clauses with a switch statement
7. add DTO TaxCalculatorResult for storing and printing
8. add function which transforms input into enum
9. implement input validation for income and employment type
10. override toString() to output DTO values
11. extract emplyemnt and civil processing to functions
