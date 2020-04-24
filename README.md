# Developer assessment
In this test, you'll implement a small application to manage call center agents. 

## Overview
* An agent can be assigned to only one team and reports to one manager. 
* A manager can manage multiple teams, and any one team can be managed by at most 2 managers.
* An agent can only be assigned to a team that is managed by the same manager he reports to.

## Basic implementation
Implement the following API endpoints:

* **GET /teams/** - Returns a list of teams in the database in JSON format
* **GET /team/{{id}}/** - Returns a detail view of the specified team in JSON format
* **GET /agents/** - Returns a list of all agents in the database in JSON format
* **GET /agent/{{id}}/** - Returns a detail view of the specified agent in JSON format. This should include team details.
* **POST /team/** - Creates a new team with the specified details - Expects a JSON body
* **POST /agent/** - Creates a new agent with the specified details - Expects a JSON body

## Advanced implementation

* **GET /agents/** - Implement pagination and query parameters on this request. 
    The agents identity number should no longer be returned in this request.
* **POST /manager/** - Creates a new manager with the specified details - Expects a JSON body
* **PUT /team/{{id}}/agent** - Assigns an agent to the specified team
* create an api endpoint that will return a list of all empty teams (i.e teams with no agents or managers)


### Notes and recommendations

* Starter code can be found at https://github.com/xibsolutions/assessment-public.git
* Create your own public repository and commit your work there.
* Send us your repository URL when you've completed the assessment.
* The application uses java and the spring boot framework. You are free to use either Java or Kotlin
* The application has been set up to use an in-memory h2 database.
  A database console is accessible @ localhost:8080/h2-console. The credentials are specified in the application.yml file.
* The project structure should be updated to make the code easier to navigate
* Adhering to standards is preferred
