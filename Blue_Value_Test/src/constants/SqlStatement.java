package constants;

public class SqlStatement {
	
	public final static String ADD_ORGANIZATION_QUERY = "INSERT INTO organization(name) VALUES(?)";
	public final static String SELECT_ALL_ORGANIZATIONS_QUERY = "SELECT * FROM organization";
	
	public final static String ADD_MANAGEMENT_QUERY = "INSERT INTO management(org_id, name) VALUES(?, ?)";
	public final static String SELECT_ALL_MANAGEMENTS_QUERY = "SELECT * FROM management";

	public final static String ADD_DEPARTMENT_QUERY_NULL_PARENT = "INSERT INTO department(parent_dep_id, man_id, name) VALUES(null, ?, ?)";
	public final static String ADD_DEPARTMENT_QUERY = "INSERT INTO department(parent_dep_id, man_id, name) VALUES(?, ?, ?)";
	public final static String SELECT_ALL_DEPARTMENTS_QUERY = "SELECT * FROM department";
	
	public final static String ADD_MANAGER_JOB_QUERY = "INSERT INTO manager_job(id, name, description) VALUES(?, ?, ?)";
	public final static String SELECT_ALL_MANAGER_JOBS_QUERY = "SELECT * FROM manager_job";
	
	public final static String ADD_EMPLOYEE_JOB_QUERY = "INSERT INTO employee_job(id, dep_id, name, description) VALUES(?, ?, ?, ?)";
	public final static String SELECT_ALL_EMPLOYEE_JOBS_QUERY = "SELECT * FROM employee_job";
}	
