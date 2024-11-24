
------
# Employee Management RESTful Web Service

## Background

This project is designed to demonstrate how to create a simple RESTful web service using **Spring Boot**. The web service provides a basic **GET** API that returns a list of employees, with each employee having attributes like `employee_id`, `first_name`, `last_name`, `email`, and `title`. The service simulates an employee management system where users can fetch employee data via HTTP requests.

### Key Features:
- **GET /employees**: Returns a list of all employees in JSON format.
- The data is **hard-coded** for now (no database connection).
- The service is designed to be scalable and can later be expanded to support other HTTP methods like **POST**, **PUT**, and **DELETE**.

## Prerequisites

Before you begin, ensure that you have the following installed:

- **Java 17 or higher**: Spring Boot requires Java 17 or higher. Install it from [OpenJDK](https://openjdk.java.net/).
- **Gradle**: Gradle is used as the build tool for this project. You can install it from [Gradle's official site](https://gradle.org/install/).
- **IDE (optional)**: An IDE such as **IntelliJ IDEA**, **Visual Studio Code**, or **Eclipse** can be helpful for coding, running, and debugging the project.

## Project Structure

This project is divided into multiple layers for clear organization:

```
/rest-service
    /src
        /main
            /java
                /com
                    /example
                        /rest_service
                            /controller
                                EmployeeController.java
                            /model
                                Employee.java
                                Employees.java
                                EmployeeManager.java
                            RestServiceApplication.java
```

- **/controller**: Contains the `EmployeeController.java` class, which handles HTTP requests.
- **/model**: Contains the `Employee.java`, `Employees.java`, and `EmployeeManager.java` classes that define the employee data and manage the employee list.
- **RestServiceApplication.java**: The entry point for the Spring Boot application.

## Setup Instructions

### 1. Clone the repository

Start by cloning the project repository to your local machine.

```bash
git clone https://github.com/auyjos/employee-management-rest-service.git
cd employee-management-rest-service
```

### 2. Install dependencies

The project uses Gradle to manage dependencies. Open a terminal and navigate to the project directory, then run the following command to install the dependencies:

```bash
gradle build
```

This will download all required dependencies defined in `build.gradle`.

### 3. Run the application

Once the dependencies are installed, you can run the application using Gradle:

```bash
gradle bootRun
```

This will start the Spring Boot application on port `8080`.

## API Endpoints

### **GET /employees**

- **Description**: Retrieves the list of all employees in JSON format.
- **Response**: A JSON array of employee objects, each with the following attributes:
    - `employee_id`: String (e.g., "EMP001")
    - `first_name`: String (e.g., "John")
    - `last_name`: String (e.g., "Doe")
    - `email`: String (e.g., "john.doe@example.com")
    - `title`: String (e.g., "Software Engineer")

#### Example Request:

```bash
curl http://localhost:8080/employees
```

#### Example Response:

```json
{
  "Employees": [
    {
      "employee_id": "EMP001",
      "first_name": "John",
      "last_name": "Doe",
      "email": "john.doe@example.com",
      "title": "Software Engineer"
    },
    {
      "employee_id": "EMP002",
      "first_name": "Jane",
      "last_name": "Smith",
      "email": "jane.smith@example.com",
      "title": "Project Manager"
    },
    {
      "employee_id": "EMP003",
      "first_name": "Mark",
      "last_name": "Taylor",
      "email": "mark.taylor@example.com",
      "title": "HR Specialist"
    }
  ]
}
```

## Project Structure Explanation

### 1. **Model Layer** (`Employee`, `Employees`, `EmployeeManager`)

- **`Employee.java`**: Defines the employee object, including attributes like `employee_id`, `first_name`, `last_name`, `email`, and `title`, along with getters and setters.
  
- **`Employees.java`**: Contains a list of employee objects and provides methods to add and retrieve employees.

- **`EmployeeManager.java`**: Manages the initialization of the employee list and simulates hard-coded employee data. It holds the list of employees and provides the ability to retrieve the list.

### 2. **Controller Layer** (`EmployeeController`)

- **`EmployeeController.java`**: This class exposes a REST endpoint `/employees`, which returns all the employees in JSON format. It relies on the `EmployeeManager` class to fetch the data.

### 3. **Spring Boot Application** (`RestServiceApplication.java`)

- **`RestServiceApplication.java`**: This is the main entry point for the Spring Boot application. It uses the `@SpringBootApplication` annotation to set up and run the Spring Boot application.

## Running the Tests

To run unit tests (if you've added any), use the following command:

```bash
gradle test
```

## Future Enhancements

While this application currently only supports a **GET** request to fetch employee data, the following enhancements can be implemented in the future:

1. **POST /employees**: Add a new employee to the list.
2. **PUT /employees/{id}**: Update an existing employee's data.
3. **DELETE /employees/{id}**: Remove an employee from the list.
4. **Database Integration**: Instead of hard-coding the employee list, you can connect the application to a database (e.g., MySQL, PostgreSQL) using Spring Data JPA.

## Conclusion

This project provides a simple example of how to build a RESTful web service with Spring Boot. It demonstrates best practices for structuring a project and creating a scalable application. You can build upon this foundation to create more complex features and integrate with databases or other services as needed.

---
