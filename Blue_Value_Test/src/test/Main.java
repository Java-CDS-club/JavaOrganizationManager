package test;

import java.sql.SQLException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SQLException {
		DBManager dbman = new DBManager();

		Organization google = new Organization("Google");
		Organization microsoft = new Organization("Microsoft");

//    	// MANAGEMENT FOR GOOGLE & MICROSOFT
		Management googleSalesMan = new Management(1, "Sales");
		Management googleDevelopmentMan = new Management(1, "Development");
		Management microsoftSalesMan = new Management(2, "Sales");
		Management microsoftDevelopmentMan = new Management(2, "Development");

		// DEVELOPMENT (Level 1) DEPARTMENTS FOR GOOGLE & MICROSOFT
		Department googleDevelopmentDepartmentGoogleSearch = new Department(2, "Google Search");

		Department microsoftDevelopmentDepartmentBingSearch = new Department(2, "Bing Search");

		// Top level GOOGLE sales department for USA
		Department googleSalesDepartmentUSA = new Department(1, "USA");
		// Top level GOOGLE sales department for EU
		Department googleSalesDepartmentEU = new Department(1, "EU");
		// Top level MICROSOFT sales department for USA
		Department microsoftSalesDepartmentUSA = new Department(2, "USA");
		// Top level MICROSOFT sales department for EU
		Department microsoftSalesDepartmentEU = new Department(2, "EU");

		// SALES (Level 2) DEPARTMENTS FOR GOOGLE

		// Second level GOOGLE sales department for USA, California ( 3 is the id of
		// USA)
		Department googleSalesDepartmentUSACa = new Department(3, 1, "California");
		// Second level GOOGLE sales department for USA, New York
		Department googleSalesDepartmentUSANY = new Department(3, 1, "New York");

		// SALES (Level 3) DEPARTMENTS FOR GOOGLE New York
		// Third level GOOGLE sales department for USA, New York, Queens (parent_dep_id
		// = 8 for New York, USA)
		Department googleSalesDepartmentUSANYQueens = new Department(8, 1, "Queems");

		// JOBS
		// MANAGERS
		// id refers to management table id, only one per management table row
		// Senior Sales Manager at Google
		ManagerJob aliceSeniorSalesGoogle = new ManagerJob(1, "Alice", "Senior Sales Manager");

		// EMPLOYEES
		EmployeeJob John0 = new EmployeeJob(0, 1, "John0", "Junior Sales");
		EmployeeJob John1 = new EmployeeJob(1, 1, "John1", "Junior Sales");
		EmployeeJob John2 = new EmployeeJob(2, 1, "John2", "Junior Sales");
		EmployeeJob John3 = new EmployeeJob(3, 1, "John3", "Junior Sales");
		EmployeeJob John4 = new EmployeeJob(4, 1, "John4", "Junior Sales");
		EmployeeJob John5 = new EmployeeJob(5, 1, "John5", "Junior Sales");
		EmployeeJob John6 = new EmployeeJob(6, 1, "John6", "Junior Sales");
		EmployeeJob John7 = new EmployeeJob(7, 1, "John7", "Junior Sales");

		// Insert organization
		dbman.addOrganization(google);
		dbman.addOrganization(microsoft);

		// Insert management
		dbman.addManagement(googleSalesMan);
		dbman.addManagement(googleDevelopmentMan);
		dbman.addManagement(microsoftSalesMan);
		dbman.addManagement(microsoftDevelopmentMan);

		// Insert top level departments
		dbman.addTopLevelDepartment(googleDevelopmentDepartmentGoogleSearch);
		dbman.addTopLevelDepartment(microsoftDevelopmentDepartmentBingSearch);
		dbman.addTopLevelDepartment(googleSalesDepartmentUSA);
		dbman.addTopLevelDepartment(googleSalesDepartmentEU);
		dbman.addTopLevelDepartment(microsoftSalesDepartmentUSA);
		dbman.addTopLevelDepartment(microsoftSalesDepartmentEU);

		// Insert second level departments
		dbman.addDepartment(googleSalesDepartmentUSACa);
		dbman.addDepartment(googleSalesDepartmentUSANY);

		// Insert third level departments
		dbman.addDepartment(googleSalesDepartmentUSANYQueens);

		// Insert manager job
		dbman.addManagerJob(aliceSeniorSalesGoogle);

		dbman.addEmployeeJob(John0);
		dbman.addEmployeeJob(John1);
		dbman.addEmployeeJob(John2);
		dbman.addEmployeeJob(John3);
		dbman.addEmployeeJob(John4);
		dbman.addEmployeeJob(John5);
		dbman.addEmployeeJob(John6);
		dbman.addEmployeeJob(John7);

//      // Illegal inserts
//      // Must fail (no duplicate entries)
//  	Organization google2 = new Organization("Google");    	

		// Must fail no duplicate management department for one organization
//    	Management microsoftDevelopmentMan2 = new Management(2, "Development");
//    	dbman.addManagement(microsoftDevelopmentMan2);

		// Must fail no more than one manager to management table
//    	ManagerJob bobSeniorSalesGoogle = new ManagerJob(2, "Bob", "Senior Sales Manager");

		// No more than 8 employees on the same department
		EmployeeJob John8 = new EmployeeJob(8, 1, "John8", "Junior Sales");
		dbman.addEmployeeJob(John8);

		List<Organization> orgs = dbman.getAllOrganizations();
		List<Management> mans = dbman.getAllManagements();
		List<Department> deps = dbman.getAllDepartments();
		List<ManagerJob> mjs = dbman.getAllManagerJobs();
		List<EmployeeJob> ejs = dbman.getAllEmployeeJobs();

		System.out.println("\n---------------------------------------------------");
		System.out.println("Organizations");
		dbman.printList(orgs);
		System.out.println("Managements");
		dbman.printList(mans);
		System.out.println("Departments");
		dbman.printList(deps);
		System.out.println("Manager Jobs");
		dbman.printList(mjs);
		System.out.println("Employee Jobs");
		dbman.printList(ejs);
		System.out.println("---------------------------------------------------");
	}

}