insert into project (name, start_date, end_date, status, priority)
VALUES ('Project1', '2019-05-10', '2019-08-30', 'Completed', 2);
insert into project (name, start_date, end_date, status, priority)
VALUES ('Project2', '2020-07-18', '2020-07-28', 'Completed', 3);
insert into project (name, start_date, end_date, status, priority)
VALUES ('Project3', '2022-05-10', '2022-08-30', 'Active', 1);
insert into project (name, status, priority)
VALUES ('Project4', 'NotStarted', 1);

insert into task(name, description, status, priority, project_id)
VALUES ('Task1','Add a new customer to a system','ToDo',2,3);
insert into task(name, description, status, priority, project_id)
VALUES ('Task2','Make meeting','Done',3,2);
insert into task(name, description, status, priority, project_id)
VALUES ('Task3','Print the report','Done',3,2);
insert into task(name, description, status, priority, project_id)
VALUES ('Task4','Code review','Done',1,1);