insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
VALUES ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Admin'),
       ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Manager'),
       ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Employee');

insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, enabled,
                  first_name, gender, last_name, user_name, role_id)
values ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, true, 'admin', 'MALE', 'admin', 'admin@gmail.com', 1),
       ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, true, 'John', 'MALE', 'Smith', 'john@gamil.com', 2),
       ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, true, 'Tom', 'MALE', 'Hanks', 'tom@gamil.com', 3);

insert into projects(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, is_complete, project_name,
                  project_code, start_date, end_date, project_detail, project_status, manager_id)
values ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, false, 'Selenium', 'Sel001', '2021-01-05', '2021-01-05', 'Project Details', 'OPEN', 3);