use `project_planner`;

INSERT INTO companies (company_name) VALUES ('Alpha Solutions');

INSERT INTO users (company_id, user_name, user_password)
VALUES (1, 'test_user', 'test123');

-- Insert projects
INSERT INTO projects (company_id, user_id, project_name, start_date, end_date,
                      total_estimated_cost, total_assigned_employees, is_complete, project_description)
VALUES (1, 1, 'Danske Bank - Payment Processor', '2024-12-12', '2025-12-12',
        1000000.0, 0, false, 'A payment processor for a bank');

INSERT INTO projects (company_id, user_id, project_name, start_date, end_date,
                      total_estimated_cost, total_assigned_employees, is_complete, project_description)
VALUES (1, 1, 'Nordea - Employee Management System', '2025-01-01', '2025-11-01',
        800000.0, 0, false, 'Web-based Employee Management application for a bank');

-- Insert subprojects
INSERT INTO subprojects (project_id, subproject_name, start_date, end_date,
                         total_estimated_cost, total_assigned_employees, total_actual_cost, is_complete,
                         subproject_description, hours_allocated, priority)
VALUES
    (1, 'Frontend Development', '2024-12-12', '2025-06-12',
     0, 0, 0.0, false, 'User interface development', 0, 'HIGH'),
    (1, 'Backend Development', '2024-12-12', '2025-09-12',
     0, 0, 0.0, false, 'Server-side development', 0, 'HIGH');

INSERT INTO subprojects (project_id, subproject_name, start_date, end_date,
                         total_estimated_cost, total_assigned_employees, total_actual_cost, is_complete,
                         subproject_description, hours_allocated, priority)
VALUES
    (2, 'Initial Planning', '2025-01-01', '2025-03-15',
     0, 0, 0.0, false, 'Feasibility, PO meetings', 0, 'HIGH');


-- Insert tasks for project 1
INSERT INTO tasks (subproject_id, task_name, start_date, end_date,
                   estimated_cost, assigned_employees, is_complete, task_description,
                   hours_allocated, priority)
VALUES
    (1, 'Login Page', '2024-12-12', '2025-01-12',
     50000.0, 2, false, 'Implement secure runLogin page', 300, 'HIGH'),
    (1, 'Dashboard', '2025-01-13', '2025-03-12',
     100000.0, 3, false, 'Create main dashboard', 500, 'MEDIUM');

INSERT INTO tasks (subproject_id, task_name, start_date, end_date,
                   estimated_cost, assigned_employees, is_complete, task_description,
                   hours_allocated, priority)
VALUES
    (2, 'Authentication API', '2024-12-12', '2025-02-12',
     80000.0, 3, false, 'Implement authentication endpoints', 400, 'HIGH'),
    (2, 'Transaction Processing', '2025-02-13', '2025-05-12',
     150000.0, 4, false, 'Implement payment processing logic', 800, 'HIGH');

-- Insert tasks for project 2

INSERT INTO tasks (subproject_id, task_name, start_date, end_date,
                   estimated_cost, assigned_employees, is_complete, task_description,
                   hours_allocated, priority)
VALUES
    (3, 'Feasibility', '2025-01-01', '2025-02-01',
     50000.0, 8, false, 'Feasibility study of project', 1500, 'HIGH'),
    (3, 'Initial Mockups', '2025-01-01', '2025-02-01',
     100000.0, 3, false, 'Create presentable mockups for first PO meeting', 1000, 'MEDIUM');