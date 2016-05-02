INSERT INTO abstract_user_model (id, firstname, lastname, email, password) VALUES (1, 'Razvan', 'Popescu', 'razvan@gmail.com', 'user');
INSERT INTO abstract_user_model (id, firstname, lastname, email, password) VALUES (2, 'Razvan', 'Georgescu', 'andrei@gmail.com', 'user');

INSERT INTO student_model (id) VALUES (1);
INSERT INTO student_model (id) VALUES (2);



INSERT INTO abstract_task_model (id, title, description, student,points_rewarded) VALUES (1, 'Java', 'Develop a project with maximum efficiency by completing the steps in time',1, 50);
INSERT INTO abstract_task_model (id, title, description, student,points_rewarded) VALUES (2, 'PHP', 'Develop a project with maximum efficiency by completing the steps in time',1, 100);
INSERT INTO abstract_task_model (id, title, description) VALUES (3, 'C#', 'Develop a project with maximum efficiency by completing the steps in time');
INSERT INTO abstract_task_model (id, title, description) VALUES (4, 'Databases', 'Develop a project with maximum efficiency by completing the steps in time');
INSERT INTO abstract_task_model (id, title, description) VALUES (5, 'Java1', 'Develop a project with maximum efficiency by completing the steps in time');
INSERT INTO abstract_task_model (id, title, description) VALUES (6, 'Java2', 'Develop a project with maximum efficiency by completing the steps in time');
INSERT INTO abstract_task_model (id, title, description) VALUES (7, 'Java3', 'Develop a project with maximum efficiency by completing the steps in time');
INSERT INTO abstract_task_model (id, title, description) VALUES (8, 'Java4', 'Develop a project with maximum efficiency by completing the steps in time');


INSERT INTO course_model (id, title) VALUES (1, "Java");
INSERT INTO course_model (id, title) VALUES (2, "Java");



INSERT INTO task_model (id, course_id) VALUES (1, 1);
INSERT INTO task_model (id, course_id) VALUES (2, 1);

INSERT INTO subtask_model (id, subtasks) VALUES (1, 1);

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


INSERT INTO skill_model (id, name, level_Model) VALUES (1, "Java", 2);
INSERT INTO skill_model (id, name, level_Model) VALUES (2, "Php", 1);
INSERT INTO skill_model (id, name, level_Model) VALUES (3, "Python", 3);

INSERT INTO subtask_model_skills_gained (subtask_model, skills_gained) VALUES(1, 1);


