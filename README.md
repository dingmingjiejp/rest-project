### Source code can be found in restapi/src/main/java

## Tech used:
* JDK 1.8
* Spring Boot
* Hibernate
* JPA
* Maven
* H2

## How To Run:
* IDE:<br/>
  Import project to Intellij/STS (The Project is developed under Intellij, should be able to import under STS)<br/>
   * STS: Run As -> Select Spring Boot App<br/>
   * Intellij: Run RestapiApplication 
  
* Command for Batch: <br/>
   * mvnw spring-boot:run
  
I use H2 in memory db for persistence embedded in java process, there's no need to install external db.<br/>
The following advisors data are loaded when the application starts. <br/>
```
  0001
  0002
  0003
  test 
```

## Unit Tests:
Created JUnit Test classes for the service layers.<br/>
Test code could be found in restapi/src/test/

Test Case:
* save a new model with Advisor existed
* save a new model with Advisor not existed
* save a new model with Allocation Percentage Total Invalid
* update a exsiting model
* paged search models in 1st page
* paged search models in 2nd page(last page)
* paged search models with all data


## Integrition Tests:
Used curl/postman to send restful request and verified the reponse.<br/>
Test commands and responses evidences could be found in restapi/it/.<br/>

Test case:
* GET ADVISOR MODEL REQUEST with default page number and size
```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" http://localhost:8080/v1/advisor/test/model
```

* GET ADVISOR MODEL REQUEST with customized page number and size
```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" http://localhost:8080/v1/advisor/test/model?pageNumber=2&amp;pageSize=1
```

* PUT ADVISOR MODEL REQUEST with correct data
```
curl -X PUT -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{
   "name":"Example Model",
   "description":"example model with tech stocks",
   "cashHoldingPercentage":20,
   "driftPercentage":5,
   "createdOn":"2017-03-01",
   "modelType":"TAXABLE",
   "rebalanceFrequency":"QUARTERLY",
   "assetAllocations":[
      {
         "symbol":"AAPL",
         "percentage":30
      },
      {
         "symbol":"GOOG",
         "percentage":20
      },
      {
         "symbol":"IBM",
         "percentage":25
      },
      {
         "symbol":"FB",
         "percentage":25
      }
   ]
}' "http://localhost:8080/v1/advisor/test/model"
```

* PUT ADVISOR MODEL REQUEST with udpating existed model
```
curl -X PUT -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{
   "name":"Example Model",
   "description":"example model with tech stocks UPDATED",
   "cashHoldingPercentage":20,
   "driftPercentage":5,
   "createdOn":"2017-03-01",
   "modelType":"TAXABLE",
   "rebalanceFrequency":"QUARTERLY",
   "assetAllocations":[
      {
         "symbol":"AAPL",
         "percentage":10
      },
      {
         "symbol":"GOOG",
         "percentage":40
      },
      {
         "symbol":"IBM",
         "percentage":15
      },
      {
         "symbol":"FB",
         "percentage":35
      }
   ]
}' "http://localhost:8080/v1/advisor/test/model"
```

* PUT ADVISOR MODEL REQUEST with no advisor existed
```
curl -X PUT -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{
   "name":"Example Model",
   "description":"example model with tech stocks UPDATED",
   "cashHoldingPercentage":20,
   "driftPercentage":5,
   "createdOn":"2017-03-01",
   "modelType":"TAXABLE",
   "rebalanceFrequency":"QUARTERLY",
   "assetAllocations":[
      {
         "symbol":"AAPL",
         "percentage":10
      },
      {
         "symbol":"GOOG",
         "percentage":40
      },
      {
         "symbol":"IBM",
         "percentage":15
      },
      {
         "symbol":"FB",
         "percentage":35
      }
   ]
}' "http://localhost:8080/v1/advisor/badtest/model"
```

* PUT ADVISOR MODEL REQUEST with bad asset allocations
```
curl -X PUT -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{
   "name":"Example Model",
   "description":"example model with tech stocks UPDATED",
   "cashHoldingPercentage":20,
   "driftPercentage":5,
   "createdOn":"2017-03-01",
   "modelType":"TAXABLE",
   "rebalanceFrequency":"QUARTERLY",
   "assetAllocations":[
      {
         "symbol":"AAPL",
         "percentage":50
      },
      {
         "symbol":"GOOG",
         "percentage":40
      },
      {
         "symbol":"IBM",
         "percentage":15
      },
      {
         "symbol":"FB",
         "percentage":35
      }
   ]
}' "http://localhost:8080/v1/advisor/badtest/model"
```
```
