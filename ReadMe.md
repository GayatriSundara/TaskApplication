# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:


Run the TaskApplication.java as Java Application 

#Filter by Label
http://localhost:8080/getLabel?label=Tech


#AddTask
http://localhost:8080/Add

#DeleteTask
http://localhost:8080/delete/2

#modifyTask
http://localhost:8080/modify/3

Payload:
{
	"priority":"HIGH",
	"description":"do  skip",
   	"label":"non-development",
    "startdate":"2019-01-01",
   	"endDate":"2019-09-09"
}


#FilterByPriority
http://localhost:8080/get/HIGH


#Filter usingDates
http://localhost:8080/get?fromDate=2019-02-10&toDate=2019-03-01




