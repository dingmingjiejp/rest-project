### Source code can be found in restapi/src/main/java

## Tech used:
* Spring Boot
* Hibernate
* JPA
* Maven
* H2

## How To Run:
1. Import project to Intellij/STS (The Project is developed under Intellij, should be able to import under STS)
2. STS: Run As -> Select Spring Boot App 
   Intellij: Run RestapiApplication 
   
We use H2 in memory db for persistence embedded in java process, there's no need to install external db.

## Unit Tests:
Created JUnit Test class for the service layers.
Test code can be found in restapi/src/test/

Test Case:
* save a new model with Advisor existed
* save a new model with Advisor not existed
* save a new model with Allocation Percentage Total Invalid
* update a exsiting model
* paged search models in 1st page
* paged search models in 2nd page(last page)
* paged search models with all data



## Integrition Tests:
Used curl/postman to send restful request and verified the reponse.
Test commands and responses could be found in restapi/it

test case
* GET ADVISOR MODEL REQUEST with default page number and size
* GET ADVISOR MODEL REQUEST with customized page number and size
* PUT ADVISOR MODEL REQUEST with correct data
* PUT ADVISOR MODEL REQUEST - udpate
* PUT ADVISOR MODEL REQUEST with no advisor exist
* PUT ADVISOR MODEL REQUEST with bad asset allocations

```
