# Tunts Challenge
Create an application in a programming language of your choice. The application must be able to read a google sheets spreadsheet, fetch the necessary information, calculate and write the result in the spreadsheet.

## Solution
The solution was built with Java 17 + Spring Boot 2.7.3 + Gradle + Swagger and Docker using the MVC pattern.

### Requirements
- Install Java 17.
- Install Git.
- Install Gradle.
- Install Docker.

### How to execute

- Build the application by typing
>./gradlew build
- Build the docker image by typing 
>docker build --tag=tunts:latest .
- Execute the docker-compose by typing 
>docker-compose up

### Link to spreadsheet
  - [Google Spreadsheets](https://docs.google.com/spreadsheets/d/1PMfEfSqKlvZ79pCJmCfBp7uWr5l87Ha99kovNF2alYM/edit#gid=0)
  
###  Api Documentation
Swagger The evolution of your API’s functionality is inevitable, but the headache of maintaining API docs doesn’t have to be. Swagger tools takes the hard work out of generating and maintaining your API docs, ensuring your documentation stays up-to-date as your API evolves.

To explore the api documentation, is necessary run the project and access:
>http://localhost:8080/swagger-ui/index.html
