# clientJournal

1. clone the repo
2. navigate to the local repo folder 
3. run comands:
```
C:\Users\AnastasiiaDepenchuk\IdeaProjects\clientJournal\> cd ResourceServer
```
```
C:\Users\AnastasiiaDepenchuk\IdeaProjects\clientJournal\ResourceServer> mvn install
```
```
C:\Users\AnastasiiaDepenchuk\IdeaProjects\clientJournal\ResourceServer> mvn spring-boot:run
```
```
C:\Users\AnastasiiaDepenchuk\IdeaProjects\clientJournal\ResourceServer> cd ..
C:\Users\AnastasiiaDepenchuk\IdeaProjects\clientJournal\> cd AdminDashboard
```
```
C:\Users\AnastasiiaDepenchuk\IdeaProjects\clientJournal\AdminDashboard> mvn install
```
```
C:\Users\AnastasiiaDepenchuk\IdeaProjects\clientJournal\AdminDashboard> mvn spring-boot:run
```

4. go to http://localhost:8081/login.html and use cred: admin admin to login
* now you here http://localhost:8081/swagger-ui.html

* here is a file with search criteria examples  ...clientJournal\ResourceServer\src\test\resources\search_criteria.json
