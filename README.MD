# Broadband Tender Demo

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

The broadband tender demo application was developed during a weekend hackathon to provide an insight into code base, architecture approach and project setup for the Deutsche Telekom. 

This application is not run or developed by the Deutsche Telekom !

# Goal

The primary purpose of this demo, is to showcase a well structured and organized test scenario with the following aspects in mind:

- Providing a REST API
- Built in Role Managment
- Process flow for Creating, Reading, Writing and Deleting broadband tenders 
- Log File Managment
- Health Status Endpoint for system monitoring
- Seed data generation to provide a initial demo set of data
- Responsive and reactive UI 
- Easy deployment via a Docker Container on MS Azure 
- Thorough test coverage via Unit- & Integrationtests
# Roles
* Admin (is allowed to access everything and to configure the server)
* WebserviceUser (is allowed to access the api)
* Key Account Manager (is allowed to access the UI)
# Endpoints
* http://tender-demo.azurewebsites.net/ (Key Account Manager Access required)
* Interface Endpoints
    * Creating Dummy Entries via REST Interface
        * [Project](http://tender-demo.azurewebsites.net/api/seed/projects)
        * [Prices](http://tender-demo.azurewebsites.net/api/seed/prices)
    * Access General Data via REST
        * [All Projects](http://tender-demo.azurewebsites.net/api/projects)
        * [HAL Browser](http://tender-demo.azurewebsites.net/api/repositories/browser/index.html#/api/repositories)
    * Access Project Data via REST
        * Create, Read, Update, Delete Operations (http://tender-demo.azurewebsites.net/api/projects)
        * Specific project http://tender-demo.azurewebsites.net/api/projects/$projectId (for example 23)
    * Access Pricing Data via REST 
        * [Get Current Prices](http://localhost:8080/api/masterdata/pricing)
        * Calculate prices for specific project http://localhost:8080/api/pricingMasterData/calculateAndUpdateSum/$projectId (for example 23)
        * Update prices for all project with masterdata http://localhost:8080/api/masterdata/pricing (Post Request)
            *   ```json
                    {
                      "ACCESS_EMPTY_PIPES": 1080,
                      "DEBUNDLED_ACCESS": 2000,
                      "BITSTREAM_ACCESS": 2300,
                      "ACCESSDARKFIBER": 4300,
                      "SHARED_USE": 9600,
                      "BACKHAULNET_WORKACCESS": 2030
                    }
                ```
* Admin Endpoints
    * [Show Logs](http://tender-demo.azurewebsites.net/actuator/logfile)
    * [See if the application is available](https://tender-demo.azurewebsites.net/actuator/health)
    * [See detailed information about past HTTP connections](https://tender-demo.azurewebsites.net/actuator/httptrace)
    * [See server configuration](https://tender-demo.azurewebsites.net/actuator/configprops)
    * [Details about Database Migrations](https://tender-demo.azurewebsites.net/actuator/flyway)
    * ...
### Tech Stack

Broadband Tender Demo uses a number of technologies to work properly:

* Java
* [Flyway](https://flywaydb.org/) - For Database Migration
* [Lombok](https://projectlombok.org/) - Reduces Boilerplate Code 
* [Spring Boot](http://spring.io/projects/spring-boot) - Reduces Boilerplate Code and helps setting up a App Quickly
* Rest
* Azure Cloud - Database and App Hosting with Docker
* Docker - Lightweight Container Solution for the Deployment
* [Gradle](https://gradle.org/) - Build Tool for this Project
* mssql - Azure Cloud Database
* H2 - Embedded Database for local Development
* [JPA/Hibernate](http://spring.io/projects/spring-data-jpa) - Database Access Layer
* Azure Cli - Infrastructure as Code for setting up the Infrastructure automated and to have a history of changes

### IDE Installation

Broadband Tender Demo requires for developing the following Tools:
* Java 1.8 (Because Azure Web App currently just accepts Java 1.8 and nothing higher) 
* Gradle
* Azure Cloud if you dont want to use H2

I would also recommend to use the following Technologies/Tooles:
* Linux or MacOS System
* Intellij
* Docker
* [sdkmann](https://sdkman.io/) for Java and Gradle Installation 

These are the Steps for the Setup

```sh
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk install java 8.0.181-oracle
# Test Java Version
$ java -version
$ sdk install gradle
# Test Gradle Version
$ gradle -version
```
Download and install Intellij and Docker
Last step is checkout the Project and mark the project as a gradle project

### Docker
Broadband Tender Demo is very easy to install and deploy in a Docker container.
First login into the private docker repository
```sh
# Replace privateRepository with your Repository
$ docker login privateRepository
```
Now build the Container with Intellij or console
Intellij:
![N|Solid](https://github.com/VictorGetz/broadband-tender-demo/blob/master/documentation/installation/GradleDockerBuild.png?raw=true)
console
```sh
$ gradle clean dockerPush
```
Now you have build the whole project (Front and Backend) and pushed the docker image to the repository

To start the container with a h2 database execute the following command:

```sh
# Replace privateRepository with your Repository
$ docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=victor privateRepository/broadband-tender-demo
```

Broadband Tender Demo is now available under port 8080

## Architecture Setup
* parent - contains general server things like security, configuration and system tests
* tender-model - contains the model for the application (Entitys)
* tender-service - contains services for the calculation and transformation of masterdata informations
* tender-seed - contains all items regarding seed dummy test data like price masterdata or projects 
## Tests
* Systemtests
    * [ServerSecurity](https://github.com/VictorGetz/broadband-tender-demo/blob/master/src/test/java/com/impresssol/broadband/server/security/ServerSecurityConfigTestIT.java) (Validates the acces permissions for the roles KeyAccountManager, Admin and WebserviceUser)
* Integrationtests
    * [Seed](https://github.com/VictorGetz/broadband-tender-demo/blob/master/tender-seed/src/test/java/com/impresssol/broadband/seed/SeedDataFactoryTestIT.java) (Validates all the informations from the seed service)
* Unittests
    * [PriceCalculation](https://github.com/VictorGetz/broadband-tender-demo/blob/master/tender-service/src/test/java/com/impresssol/broadband/service/calculation/PriceCalculationServiceTest.java) (Validates the results from the calculationservice and checks how it behaves with unexpected values)
### Todos

 - Write More Tests
 - More Clean Code and Clean Archticture