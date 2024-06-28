# Java SprintBoot
Our project uses Spring Boot with Gradle to build backend infrastructure for generating API routes for
frontend interaction. The main goal is to provide endpoints for efficient data retrieval and manipulation,
including fetching data, adding entities, and handling errors with comprehensive logging and exception
management.

## Set up database
Set up datbase according to [this tutorial](https://tuni.sharepoint.com/:w:/r/sites/TG-TTHarjoittelut2024-x-team-16/Shared%20Documents/x-team-16/database%20setup.docx?d=w4c52dd477f404e93b0e843018aaac08b&csf=1&web=1&e=aEizci).

## Download repository
Copy repository to your local machine.

### Tips
If port is occupied: 
1. netstat -ano | findstr :8080
2. taskkill /F /PID [taskid]
3. ./gradlew bootRun --stacktrace
