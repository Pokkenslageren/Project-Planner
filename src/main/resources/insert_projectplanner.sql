use `project_planner`;

INSERT INTO `companies` (`company_name`) VALUES ('Alpha Solutions');

INSERT INTO `users` (`user_name`, `user_password`) VALUES ('CEO', 'CEO1234');

INSERT INTO `projects` (company_id, project_name, start_date, end_date,
                        total_estimated_cost, total_estimated_employees, is_complete,
                        project_description)
                        VALUES (1,
                                'payment processor',
                                '2024-12-12',
                                '2025-12-12',
                                1000000.0,
                                25,
                                false,
                                'A payment processor for a bank');

INSERT INTO `subprojects` (project_id, start_date, end_date, total_estimated_cost,
                           total_available_employees, is_complete, subproject_description)
                           VALUES (1,
                                   '2025-1-1',
                                   '2025-2-2',
                                   25000.0,
                                   10,
                                   false,
                                   'Interface for the processor');

INSERT INTO `tasks` (subproject_id, task_name, start_date, end_date, estimated_cost,
                     assigned_employees, is_complete, task_description)
                     VALUES (1, 'Buttons', '2025-1-1', '2025-2-2', 10000.0, 3, false, 'Creating buttons for the interface'),
                            (1, 'Graphics', '2025-1-1', '2025-3-3', 15000.0, 4, false, 'Creating the GUI');
