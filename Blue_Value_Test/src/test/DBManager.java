package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import constants.SqlStatement;

class DBManager {

	private static Connection conn;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	private static List<Organization> organization = new ArrayList<Organization>();
	private static List<Management> management = new ArrayList<Management>();
	private static List<Department> department = new ArrayList<Department>();
	private static List<ManagerJob> manager_job = new ArrayList<ManagerJob>();
	private static List<EmployeeJob> employee_job = new ArrayList<EmployeeJob>();

	public void addOrganization(Organization org) throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.ADD_ORGANIZATION_QUERY);

		setPreparedStatementProperties(org.getName());
		try {
			preparedStatement.execute();
		} catch (java.sql.SQLIntegrityConstraintViolationException ignore) {

		}
		closeConnections();
	}

	public void addManagement(Management man) throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.ADD_MANAGEMENT_QUERY);

		// Convert variable args to string
		setPreparedStatementProperties(String.valueOf(man.getOrgID()), man.getName());

		preparedStatement.execute();

		closeConnections();
	}

	public void addTopLevelDepartment(Department dep) throws SQLException {
		conn = ConnectionObj.getConnection();

		preparedStatement = conn.prepareStatement(constants.SqlStatement.ADD_DEPARTMENT_QUERY_NULL_PARENT);

		setPreparedStatementProperties(String.valueOf(dep.managementID), dep.getName());

		preparedStatement.execute();

		closeConnections();
	}

	public void addDepartment(Department dep) throws SQLException {
		conn = ConnectionObj.getConnection();

		preparedStatement = conn.prepareStatement(constants.SqlStatement.ADD_DEPARTMENT_QUERY);

		setPreparedStatementProperties(String.valueOf(dep.parentDepartmentID), String.valueOf(dep.managementID),
				dep.getName());

		preparedStatement.execute();

		closeConnections();
	}

	public void addManagerJob(ManagerJob mj) throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.ADD_MANAGER_JOB_QUERY);

		setPreparedStatementProperties(String.valueOf(mj.getID()), mj.getName(), mj.getDescription());

		preparedStatement.execute();

		closeConnections();
	}

	public void addEmployeeJob(EmployeeJob ej) throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.ADD_EMPLOYEE_JOB_QUERY);

		if (this.checkIfDepartmentIsFull(ej.depID)) {
			System.out.println("A department cannot have more than 8 employees");
			System.out
					.println("Employee with name " + ej.name + " cannot be inserted to department with id " + ej.depID);
			return;
		}

		setPreparedStatementProperties(String.valueOf(ej.getID()), String.valueOf(ej.getDepID()), ej.getName(),
				ej.getDescription());

		preparedStatement.execute();

		System.out.println("Employee: " + ej.name + " inserted");

		closeConnections();
	}

	// Check if a department has no more than 8 employees
	public boolean checkIfDepartmentIsFull(int departmentID) throws SQLException {
		int employeeCount = 0;
		conn = ConnectionObj.getConnection();

		String sql = "SELECT COUNT(*) AS employeeCount\n" + "FROM test.department d\n"
				+ "INNER JOIN test.employee_job e on e.dep_id = d.id\n" + "WHERE d.id=?;";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setInt(1, departmentID);

		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			employeeCount = rs.getInt("employeeCount");
		}

		System.out.println("EMPLOYEE COUNT: = " + employeeCount);

		if (employeeCount >= 8) {
			return true;
		} else {
			return false;
		}

	}

	public static List<Organization> getAllOrganizations() throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.SELECT_ALL_ORGANIZATIONS_QUERY);
		resultSet = preparedStatement.executeQuery();

		List<Organization> organizationList = new ArrayList<Organization>();

		while (resultSet.next()) {

			Organization org = new Organization();

			org.setId(resultSet.getInt("id"));
			org.setName(resultSet.getString("name"));
			organizationList.add(org);
		}

		return organizationList;
	}

	public static List<Management> getAllManagements() throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.SELECT_ALL_MANAGEMENTS_QUERY);
		resultSet = preparedStatement.executeQuery();

		List<Management> managementList = new ArrayList<Management>();

		while (resultSet.next()) {
			Management man = new Management();
			man.setID(resultSet.getInt("id"));
			man.setOrgID(resultSet.getInt("org_id"));
			man.setName(resultSet.getString("name"));
			managementList.add(man);
		}

		return managementList;
	}

	public static List<Department> getAllDepartments() throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.SELECT_ALL_DEPARTMENTS_QUERY);

		resultSet = preparedStatement.executeQuery();

		List<Department> departmentList = new ArrayList<Department>();

		while (resultSet.next()) {
			Department dep = new Department();
			dep.setID(resultSet.getInt("id"));
			dep.setParentDepID(resultSet.getInt("parent_dep_id"));
			dep.setName(resultSet.getString("name"));
			departmentList.add(dep);
		}

		return departmentList;
	}

	public static List<ManagerJob> getAllManagerJobs() throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.SELECT_ALL_MANAGER_JOBS_QUERY);

		resultSet = preparedStatement.executeQuery();

		List<ManagerJob> managerJobsList = new ArrayList<ManagerJob>();

		while (resultSet.next()) {
			ManagerJob mj = new ManagerJob();
			mj.setID(resultSet.getInt("id"));
			mj.setName(resultSet.getString("name"));
			mj.setDescription(resultSet.getString("description"));
			managerJobsList.add(mj);
		}

		return managerJobsList;
	}

	public static List<EmployeeJob> getAllEmployeeJobs() throws SQLException {
		conn = ConnectionObj.getConnection();
		preparedStatement = conn.prepareStatement(constants.SqlStatement.SELECT_ALL_EMPLOYEE_JOBS_QUERY);

		resultSet = preparedStatement.executeQuery();

		List<EmployeeJob> employeeJobsList = new ArrayList<EmployeeJob>();

		while (resultSet.next()) {
			EmployeeJob ej = new EmployeeJob();
			ej.setID(resultSet.getInt("id"));
			ej.setDepID(resultSet.getInt("dep_id"));
			ej.setName(resultSet.getString("name"));
			ej.setDescription(resultSet.getString("description"));
			employeeJobsList.add(ej);
		}

		return employeeJobsList;
	}

	public void printList(List list) {
		list.forEach(System.out::println);
	}

	private static void closeConnections() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	// @param variable length array of strings as student properties
	private static void setPreparedStatementProperties(String... strArgs) throws SQLException {
		for (int i = 0; i < strArgs.length; i++) {
			preparedStatement.setString(i + 1, strArgs[i]);
		}
	}
}