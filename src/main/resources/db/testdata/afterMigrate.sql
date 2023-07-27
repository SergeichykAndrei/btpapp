INSERT INTO employee (name) VALUES ('Andrei');
INSERT INTO employee (name) VALUES ('Stas');
INSERT INTO employee (name) VALUES ('Marharita');
INSERT INTO employee (name) VALUES ('Kristina');

INSERT INTO project (name) VALUES ('Java');
INSERT INTO project (name) VALUES ('C#');
INSERT INTO project (name) VALUES ('GO');
INSERT INTO project (name) VALUES ('C++');

INSERT INTO project_employee (project_id, employee_id, role) VALUES (
                                                                        (SELECT id FROM project WHERE name = 'Java'),
                                                                        (SELECT id FROM employee WHERE name = 'Andrei'),
                                                                        'Developer'
                                                                    );
INSERT INTO project_employee (project_id, employee_id, role) VALUES (
                                                                        (SELECT id FROM project WHERE name = 'C#'),
                                                                        (SELECT id FROM employee WHERE name = 'Stas'),
                                                                        'Developer'
                                                                    );
INSERT INTO project_employee (project_id, employee_id, role) VALUES (
                                                                        (SELECT id FROM project WHERE name = 'GO'),
                                                                        (SELECT id FROM employee WHERE name = 'Marharita'),
                                                                        'Developer'
                                                                    );
INSERT INTO project_employee (project_id, employee_id, role) VALUES (
                                                                        (SELECT id FROM project WHERE name = 'C++'),
                                                                        (SELECT id FROM employee WHERE name = 'Kristina'),
                                                                        'Developer'
                                                                    );

INSERT INTO report (project_id, employee_id, description, created_at) VALUES (
                                                                                 (SELECT id FROM project WHERE name = 'Java'),
                                                                                 (SELECT id FROM employee WHERE name = 'Andrei'),
                                                                                 'Andrei Description', current_timestamp
                                                                             );
INSERT INTO report (project_id, employee_id, description, created_at) VALUES (
                                                                                 (SELECT id FROM project WHERE name = 'C#'),
                                                                                 (SELECT id FROM employee WHERE name = 'Stas'),
                                                                                 'Stas Description', current_timestamp
                                                                             );
INSERT INTO report (project_id, employee_id, description, created_at) VALUES (
                                                                                 (SELECT id FROM project WHERE name = 'GO'),
                                                                                 (SELECT id FROM employee WHERE name = 'Marharita'),
                                                                                 'Marharita Description', current_timestamp
                                                                             );
INSERT INTO report (project_id, employee_id, description, created_at) VALUES (
                                                                                 (SELECT id FROM project WHERE name = 'C++'),
                                                                                 (SELECT id FROM employee WHERE name = 'Kristina'),
                                                                                 'Kristina Description', current_timestamp
                                                                             );