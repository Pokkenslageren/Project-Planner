package ProjectPortal.Repository;

import java.time.LocalDate;
import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.Iterator;
import java.util.List;

@Repository
public class ProjectRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterator<Double> iterator() {
        return null;
    }

    /**
     * Inserts a new project record into the `projects` database table.
     * @param project an object containing the project details to be stored, including
     *                company ID, user ID, project name, start date, end date, total estimated cost,
     *                total assigned employees, completion status, and project description.
     */
    public void createProject(Project project) {
        String query = "INSERT INTO projects (company_id, user_id, project_name, start_date, " +
                "end_date, total_estimated_cost, total_assigned_employees, is_complete, " +
                "project_description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query,
                project.getCompanyId(),
                project.getUserId(),
                project.getProjectName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getTotalEstimatedCost(),
                project.getTotalAssignedEmployees(),
                project.isComplete(),
                project.getProjectDescription()
        );
    }

    /**
     * Reads and retrieves a project from the database using the given project ID.
     * @param projectId the unique identifier of the project to be retrieved
     * @return the {@link Project} object corresponding to the given project ID,
     *         or throws an exception if no project is found
     */
    public Project readProject(int projectId) {
        String query = "SELECT * FROM projects WHERE project_id = ?;";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return jdbcTemplate.queryForObject(query, rowMapper, projectId);
    }

    /**
     * Retrieves a list of all projects from the database.
     * @return a list of {@link Project} objects representing all projects stored in the database.
     */
    public List<Project> readAllProjects() {
        String query = "SELECT * FROM projects;";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    /**
     * Updates the details of an existing project in the database.
     * @param project    The Project object containing the updated details of the project.
     * @param projectId  The unique identifier of the project to be updated.
     */
    public void updateProject(Project project, int projectId) {
        String query = "UPDATE projects SET company_id = ?, project_name = ?, start_date = ?, " +
                "end_date = ?, total_estimated_cost = ?, total_assigned_employees = ?, " +
                "is_complete = ?, project_description = ? WHERE project_id = ?;";
        jdbcTemplate.update(query,
                project.getCompanyId(),
                project.getProjectName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getTotalEstimatedCost(),
                project.getTotalAssignedEmployees(),
                project.isComplete(),
                project.getProjectDescription(),
                projectId
        );
    }

    /**
     * Deletes a project from the database with the given project ID.
     * @param projectId the unique identifier of the project to be deleted
     */
    public void deleteProject(int projectId) {
        String query = "DELETE FROM projects WHERE project_id = ?;";
        jdbcTemplate.update(query, projectId);
    }

    /**
     * Calculates the total actual cost of all subprojects within the provided list.
     * The method retrieves the sum of estimated costs for tasks belonging to each subproject,
     * processes the sum for all subprojects, and returns the total cost.
     * @param subprojects a list of Subproject objects for which the total actual cost needs to be calculated
     * @return the total actual cost as a double value for all provided subprojects
     */
    public double calculateTotalActualCost(List<Subproject> subprojects) {
        double totalActualCost = 0.0;

        for (Subproject subproject : subprojects) {
            String query = "SELECT SUM(estimated_cost) FROM tasks WHERE subproject_id = ?";
            Double subprojectCost = jdbcTemplate.queryForObject(query, Double.class, subproject.getSubprojectId());

            if (subprojectCost != null) {
                totalActualCost += subprojectCost;
            }
        }

        return totalActualCost;
    }

    /**
     * Calculates the total number of employees assigned to all tasks
     * within all subprojects of a given project.
     * @param projectId The unique identifier of the project for which
     *                  the total number of employees is to be calculated.
     * @return The total number of employees assigned to all tasks
     *         across all subprojects of the specified project.
     */
    public int calculateTotalProjectEmployees(int projectId) {
        String subprojectQuery = "SELECT subproject_id FROM subprojects WHERE project_id = ?";
        List<Integer> subprojectIds = jdbcTemplate.queryForList(subprojectQuery, Integer.class, projectId);

        int totalEmployees = 0;

        for (Integer subprojectId : subprojectIds) {
            String taskQuery = "SELECT SUM(assigned_employees) FROM tasks WHERE subproject_id = ?";
            Integer subprojectEmployees = jdbcTemplate.queryForObject(taskQuery, Integer.class, subprojectId);

            if (subprojectEmployees != null) {
                totalEmployees += subprojectEmployees;
            }
        }

        return totalEmployees;
    }

    /**
     * Formats a given LocalDate into a JavaScript-compatible date string representation.
     * The method constructs the string in the format: "new Date(year, month, day)".
     * Note that the month is adjusted by subtracting 1 to match JavaScript's zero-based month indexing.
     * @param date the LocalDate instance to be formatted.
     * @return a formatted string representing the date in JavaScript's Date format.
     */
    public String formatForJavaScript(LocalDate date) {
        return String.format("new Date(%d, %d, %d)",
                date.getYear(),
                date.getMonthValue() - 1,
                date.getDayOfMonth());
    }
}