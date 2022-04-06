##Topic: Task tracker

This application will help you to organize your projects and tasks in it.

##System configuration and startup process

Tomcat in my case started on port(s): 8080 (http) with context path ''.

For starting the application you will need to restore the DB
(in my case it's PostgreSQL, the restore "TaskTracker.tar" file is included here).

In package "Config" in AppConfig.class you need to change URL, username and password for connecting with DataBase

Also in application.properties file do the same actions

If the cUrl doesn't work try to change '' to ""

For opening swagger you need to write url in browser in my case 'localhost:8080/swagger-ui.html'
(maybe you will have another port)
You may see the picture of the swagger here too 'TaskTracker swagger pic.JPEG'.

If you will have problems with AppConfig.class exactly java: package com.sun.org.slf4j.internal does not exist, 

Firstly, try to add maven dependency

'<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.28</version>
</dependency>'

Secondly, if the first will not work
delete 2 imports 
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

And replace them with @Log (lombok) annotation

in 44 line change to log.info("Embedded DataSource bean cannot be created!")
_ _ _ _
You can use it daily
- - - -
###Functionality for project model

1) Get all projects (GET http://localhost:8080/projects
or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/projects')
---
2) Get all projects sorted by your parameter like startDate
(GET http://localhost:8080/projects/sorted?field=startDate
or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/projects/sorted?field=startDate'
---
3) Get project by id (ex. id=1)
(GET http://localhost:8080/projects/1
or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/projects/1')
---
4) Add new project 
(POST http://localhost:8080/projects/addProject 
or curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \
   "endDate": "2021-04-06", \
   "name": "string", \
   "priority": 2, \
   "startDate": "2020-04-06", \
   "status": "NotStarted" \
   }' 'http://localhost:8080/projects/addProject')
---
5) Delete project by id (ex. id=13)
(DELETE http://localhost:8080/projects/deleteProject/13
or curl -X DELETE --header 'Accept: */*' 'http://localhost:8080/projects/deleteProject/13')
---
6) Update all fields (ex. in project id=13)
(PUT http://localhost:8080/projects/updateProject
or curl -X PUT --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \
   "endDate": "2021-04-06", \
   "id": 13, \
   "name": "string", \
   "priority": 2, \
   "startDate": "2020-04-06", \
   "status": "Active" \
   }' 'http://localhost:8080/projects/updateProject')
---
8) Update status (in project id=13)
(PUT http://localhost:8080/projects/updateProject/13/status/Completed
or curl -X PUT --header 'Content-Type: application/json' --header 'Accept: */*' 'http://localhost:8080/projects/updateProject/13/status/Completed')


----
###Functionality for task model

1)Get all tasks (GET http://localhost:8080/tasks
or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/tasks')
---
2)Get task by id (ex id=6)
(GET http://localhost:8080/tasks/6 
or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/tasks/6')
---
3)Get task by status (ex. status="ToDo")
(GET http://localhost:8080/tasks/taskByStatus?status=ToDo 
or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/tasks/taskByStatus?status=ToDo')
---
4)Add new task, 
all our tasks are in projects, they can't exist by themselves, it is obvious to set project_id
(POST http://localhost:8080/tasks/addTask
or curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \
    "description": "string", \
    "name": "string", \
    "priority": 2, \
    "projectId": 2, \
    "status": "ToDo" \
    }' 'http://localhost:8080/tasks/addTask')
---
5)Delete task (ex. in curl id=10)
(DELETE http://localhost:8080/tasks/deleteTask/{id}
or curl -X DELETE --header 'Accept: */*' 'http://localhost:8080/tasks/deleteTask/10')
---
6) Update all task fields
(PUT http://localhost:8080/tasks/updateTask
or curl -X PUT --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \
   "description": "string", \
   "id": 8, \
   "name": "string", \
   "priority": 2, \
   "projectId": 2, \
   "status": "ToDo" \
   }' 'http://localhost:8080/tasks/updateTask')

---
7)Update project the task belongs to (ex. in task with id=8 change project_id=3)
(PUT http://localhost:8080/tasks/updateTask/8/projectId/3
or curl -X PUT --header 'Content-Type: application/json' --header 'Accept: */*' 'http://localhost:8080/tasks/updateTask/8/projectId/3')
---
8)Change task status (ex task with id=8 change status to Done)
( PUT http://localhost:8080/tasks/updateTaskId/8/status/Done
or curl -X PUT --header 'Content-Type: application/json' --header 'Accept: */*' 'http://localhost:8080/tasks/updateTaskId/8/status/Done')
