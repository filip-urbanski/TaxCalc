# Tax Calculator - Refactoring & Clean Code Assignment

This repository contains an improved version of the **TaxCalculator** application, refactored according to the Clean Code principles and refactoring techniques.

---

## Report detailing the refactoring Steps
### Architectural Decisions and Refactoring Methodology
The refactoring process was conducted in three distinct phases, starting with basic naming adjustments and gradually moving toward structural and architectural changes.

### Phase 1: Foundational Readability (Session 1)
The initial phase focused on ensuring baseline code hygiene and readability. This was achieved by standardizing naming conventions and extracting logical blocks from the main execution thread.
* **Naming Standardization:** Applied the PascalCase naming standard across classes and types to align with Java conventions.
* **Dead Code Elimination:** Removed unused variables that cluttered the execution context.
* **Object Extraction:** Extracted the `TaxCalculator` class from the `main` method and introduced a constructor to establish an object-oriented structure.
* **Primitive Obsession Resolution:** Replaced the primitive `char` variable representing employment types with a strictly typed `EmploymentType` enum.
* **Method Extraction:** Added a dedicated `tax()` method to encapsulate the calculation call.

### Phase 2: Flow Control and Data Encapsulation (Session 2)
This approach, however, still had limitations regarding data handling, input safety, and branching logic. The second phase focused on standardizing control flow and encapsulating output data.
* **Control Flow Optimization:** Replaced sequential `if` clauses with a `switch` statement for evaluating the employment type.
* **Data Encapsulation (DTO):** Introduced the `TaxCalculatorResult` class. Following Data Transfer Object (DTO) principles, this class was implemented to decouple the calculation logic from the stored results and output.
* **Input Parsing and Validation:** Added a dedicated function to transform raw user input into the `EmploymentType` enum, alongside input validation for both the income amount and employment type.
* **Output Standardization:** Overrode the `toString()` method within `TaxCalculatorResult` to store and output the calculated values.
* **Logic Separation:** Extracted the employment and civil tax processing logic into two separate functions (`employmentTaxResult` and `civilTaxResult`).

### Phase 3: Advanced Abstraction and SOLID Implementation (Further Refactoring)
The most prominent architectural issue was the large amount of duplicate code residing within the `employmentTaxResult` and `civilTaxResult` methods. While functional, the code remained difficult to maintain due to high coupling and repeated calculation steps.
* **Behavioral Abstraction (Strategy Pattern):** Replaced the two separate tax calculation methods with a single calculation method that receives a `TaxCalculationStrategy` implementation (`CivilTaxStrategy` or `EmploymentTaxStrategy`) at runtime. This eliminated code duplication and simplified adding new contract types in the future.
* **Financial Precision:** Replaced standard `Double` values with `BigDecimal` across all monetary calculations to avoid floating-point rounding inaccuracies.
* **Separation of Concerns (Presentation):** Moved DTO formatting and printing responsibilities out of the data model and calculation classes into a dedicated `TaxCalculatorResultFormatter`. This ensured that presentation logic is not tied to the core calculations.
* **Configuration Management:** Isolated hardcoded tax rates, thresholds, and magic numbers into a central `TaxConfig` class.

---

## How to Run
1. Ensure you have **Java 21+** and **Maven** installed.
2. Build the project and run tests:
   ```bash
   mvn clean test
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```
