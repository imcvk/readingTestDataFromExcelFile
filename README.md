# Reading Test Data from Excel File using Apache POI

This project demonstrates how to read test data from an Excel file in a Java-based Selenium automation framework using **Apache POI**. This is useful for data-driven testing, where test data is managed externally in Excel and fed into test scripts dynamically.

## Features
- **Apache POI Integration**: Reads data from `.xls` or `.xlsx` files.
- **Data-Driven Testing**: Allows tests to execute with multiple sets of input data from Excel files.
- **Selenium WebDriver**: Browser automation.

## Prerequisites
- Java 8+
- Maven
- Apache POI

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/imcvk/readingTestDataFromExcelFile.git
   
**Navigate to the project folder:**

`cd readingTestDataFromExcelFile`

**Install dependencies:**

`mvn clean install`

**Run the tests:**

`mvn test`

**How it Works**

* Excel File: Test data is stored in an Excel file in the src/main/resources directory.
* Apache POI: The ExcelReader class reads the data from Excel sheets and supplies it to the test scripts.
* Test Execution: Selenium WebDriver uses the data from Excel to perform automated browser actions.

**Folder Structure**
* src/main/java: Contains the ExcelReader class for reading Excel files.
* src/test/java: Test cases that use data from Excel for execution.
* src/main/resources: Location of the Excel test data files.

**License**

This project is licensed under the MIT License.
``This `README.md` provides an overview of the project’s purpose, how it reads Excel test data, and instructions for setting it up and running the tests.
``




