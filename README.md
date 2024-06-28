# Java SprintBoot
Our project uses Spring Boot with Gradle to build backend infrastructure for generating API routes for
frontend interaction. The main goal is to provide endpoints for efficient data retrieval and manipulation,
including fetching data, adding entities, and handling errors with comprehensive logging and exception
management.

## Set up database
Set up datbase according to [this tutorial]([https://www.themealdb.com/api.php](https://tuni.sharepoint.com/:w:/r/sites/TG-TTHarjoittelut2024-x-team-16/_layouts/15/Doc2.aspx?action=edit&sourcedoc=%7B4c52dd47-7f40-4e93-b0e8-43018aaac08b%7D&wdOrigin=TEAMS-MAGLEV.teamsSdk_ns.rwc&wdExp=TEAMS-TREATMENT&wdhostclicktime=1719597164515&web=1)).

## Download repository
Copy repository to your local machine.

### Tips
If port is occupied: 
1. netstat -ano | findstr :8080
2. taskkill /F /PID [taskid]
3. ./gradlew bootRun --stacktrace
