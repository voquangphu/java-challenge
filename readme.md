# Employee Management API

This API provides CRUD (Create, Read, Update, Delete) operations to manage employee data.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Endpoints](#endpoints)
  - [Request and Response Examples](#request-and-response-examples)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- Your preferred IDE (e.g., IntelliJ, Eclipse)

### Installation

1. Clone this repository.
2. Open the project in your preferred IDE.
3. Build the project using Maven.

## Usage

### Endpoints

#### Get Employee Data

- **Endpoint:** `GET /api/v1/employees`
- **Description:** Retrieves a list of all employees.

#### Get Employee by ID

- **Endpoint:** `GET /api/v1/employees/{id}`
- **Description:** Retrieves employee details by ID.

#### Add Employee

- **Endpoint:** `POST /api/v1/employees/add`
- **Description:** Adds a new employee.

#### Update Employee

- **Endpoint:** `PUT /api/v1/employees/update/{id}`
- **Description:** Updates employee details by ID.

#### Delete Employee

- **Endpoint:** `DELETE /api/v1/employees/delete/{id}`
- **Description:** Deletes an employee by ID.

### Request and Response Examples
- Swagger: http://localhost:8080/swagger-ui.html
- Configuration: https://github.com/voquangphu/java-challenge/blob/main/src/main/java/jp/co/axa/apidemo/configurations/SwaggerConfiguration.java

### Logging
- Logback path: mylog.log
- Configuration: https://github.com/voquangphu/java-challenge/blob/main/src/main/resources/logback.xml

#### My experience in Java
- I have 10 years experience using Java, knowledge with Struts, Spring MVC, SpringBoot
- I started learning Spring MVC and Spring Boot when I got my current job about 2 years ago, before that I self-learnt a bit
- I basically learn as my job required by looking at other people's code, but Spring has been my choice of framework since I knew it due to great support, documentation, popularity
