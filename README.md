### Source code can be found in restapi/src/main/java

## Tech used:
* JDK 1.8
* Spring Boot
* Hibernate
* JPA
* Maven
* H2

## How To Run:
* IDE :<br/>
  Import project to Intellij/STS (The Project is developed under Intellij, should be able to import under STS)<br/>
   * STS: Run As -> Select Spring Boot App<br/>
   * Intellij: Run RestapiApplication 
  
* Command for Batch : <br/>
   * mvnw spring-boot:run
  
I use H2 in memory db for persistence embedded in java process, there's no need to install external db.

## Unit Tests:
Created JUnit Test classes for the service layers.<br/>
Test code could be found in restapi/src/test/

Test Case:<br/>
* save a new model with Advisor existed
* save a new model with Advisor not existed
* save a new model with Allocation Percentage Total Invalid
* update a exsiting model
* paged search models in 1st page
* paged search models in 2nd page(last page)
* paged search models with all data


## Integrition Tests:
Used curl/postman to send restful request and verified the reponse.<br/>
Test commands and responses evidences could be found in restapi/it<br/>

test case
* GET ADVISOR MODEL REQUEST with default page number and size
* GET ADVISOR MODEL REQUEST with customized page number and size
* PUT ADVISOR MODEL REQUEST with correct data
* PUT ADVISOR MODEL REQUEST with udpating existed model
* PUT ADVISOR MODEL REQUEST with no advisor existed
* PUT ADVISOR MODEL REQUEST with bad asset allocations

```
