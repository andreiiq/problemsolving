INSERT INTO abstract_user_model (id, firstname, lastname, email, password, profile_image_path) VALUES (1, 'Razvan', 'Popescu', 'razvan@gmail.com', 'user',  "C:\\pbs-files\\profile-images\\razvan@gmail.com");
INSERT INTO abstract_user_model (id, firstname, lastname, email, password, profile_image_path) VALUES (2, 'Razvan', 'Georgescu', 'andrei@gmail.com', 'user',  "C:\\pbs-files\\profile-images\\andrei@gmail.com");

INSERT INTO student_model (id) VALUES (1);
INSERT INTO teacher_model (id) VALUES (2);



INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (1, 'Java', 'Develop a project with maximum efficiency by completing the steps in time',1, 50);
INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (2, 'PHP', 'Develop a project with maximum efficiency by completing the steps in time',1, 100);
INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (3, 'C#', 'Develop a project with maximum efficiency by completing the steps in time',1, 30);
INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (4, 'Databases', 'Develop a project with maximum efficiency by completing the steps in time',1, 450);
INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (5, 'Java1', 'Develop a project with maximum efficiency by completing the steps in time',1, 50);
INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (6, 'Java2', 'Develop a project with maximum efficiency by completing the steps in time',1, 50);
INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (7, 'Java3', 'Develop a project with maximum efficiency by completing the steps in time',1, 50);
INSERT INTO abstract_task_model (id, title, description, tasks,points_rewarded) VALUES (8, 'Java4', 'Develop a project with maximum efficiency by completing the steps in time',1, 50);


INSERT INTO course_model (id, title) VALUES (1, "Java");
INSERT INTO course_model (id, title) VALUES (2, "PHP");
INSERT INTO course_model (id, title) VALUES (3, "Java learning");

INSERT INTO solution_model (id, solutions, sent_solutions, solution_path, upload_time) VALUES (1, 1, 2, "C:\\pbs-files\\solutions\\1.rar", "2016-06-05 10:32:53");
INSERT INTO solution_model(id, solutions, sent_solutions, solution_path, upload_time) VALUES (2, 1, 2, "C:\\pbs-files\\solutions\\2.rar", "2016-06-05 10:32:53");
INSERT INTO solution_model (id, solutions, sent_solutions, solution_path, upload_time) VALUES (3, 1, 2, "C:\pbs-files\\solutions\\3.rar", "2016-06-12 10:32:53");


INSERT INTO task_model (id, course_id, solution_id, owned_tasks) VALUES (1, 1, 1,2);
INSERT INTO task_model (id, course_id, solution_id, owned_tasks) VALUES (2, 1, 2, 2);
INSERT INTO task_model (id, course_id, solution_id, owned_tasks) VALUES (3, 1,3, 2);
INSERT INTO task_model (id, course_id) VALUES (4, 1);

INSERT INTO subtask_model (id, subtasks) VALUES (5, 1);
INSERT INTO subtask_model (id, subtasks) VALUES (6, 1);
INSERT INTO subtask_model (id, subtasks) VALUES (7, 1);


INSERT INTO level_model (id, value, capped) VALUES (1, 1, false);
INSERT INTO level_model (id, value, capped) VALUES (2, 2, false);
INSERT INTO level_model (id, value, capped) VALUES (3, 3, false);
INSERT INTO level_model (id, value, capped) VALUES (4, 4, false);
INSERT INTO level_model (id, value, capped) VALUES (5, 5, false);
INSERT INTO level_model (id, value, capped) VALUES (6, 6, false);
INSERT INTO level_model (id, value, capped) VALUES (7, 7, false);
INSERT INTO level_model (id, value, capped) VALUES (8, 8, false);
INSERT INTO level_model (id, value, capped) VALUES (9, 9, false);
INSERT INTO level_model (id, value, capped) VALUES (10, 10, true);


INSERT INTO skill_model (id, name, level_Model, experience) VALUES (1, "Java", 1, 500);
INSERT INTO skill_model (id, name, level_Model, experience) VALUES (2, "Php", 1, 500);
INSERT INTO skill_model (id, name, level_Model, experience) VALUES (3, "Python", 3, 500);

INSERT INTO subtask_model_skills_gained (subtask_model, skills_gained) VALUES(5, 1);
INSERT INTO subtask_model_skills_gained (subtask_model, skills_gained) VALUES(6, 2);
INSERT INTO subtask_model_skills_gained (subtask_model, skills_gained) VALUES(7, 2);
)
INSERT INTO abstract_notification_model (id, notifications, unseen) VALUES (1, 1, false);
INSERT INTO abstract_notification_model (id, notifications, unseen) VALUES (2, 1, false);
INSERT INTO abstract_notification_model (id, notifications, unseen) VALUES (3, 1, false);


INSERT INTO abstract_query_notification_model (id, status)  VALUES (1, "PENDING");
INSERT INTO abstract_query_notification_model (id, status)  VALUES (2, "PENDING");
INSERT INTO abstract_query_notification_model (id, status)  VALUES (3, "PENDING");

INSERT INTO invite_mentor_notification_model (id, subtasks)  VALUES (1,  5);
INSERT INTO invite_task_notification_model (id, subtasks)  VALUES (2,  5);
INSERT INTO invite_task_notification_model (id, subtasks)  VALUES (3,  5);




