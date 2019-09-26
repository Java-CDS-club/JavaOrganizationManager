package test;

class Department {
	int id;
	int parentDepartmentID;
	int managementID;
	String name;

	public Department() {
	}

	public Department(int managementID, String name) {
		this.managementID = managementID;
		this.name = name;
	}

	public Department(int parentDepartmentID, int managementID, String name) {
		this.parentDepartmentID = parentDepartmentID;
		this.managementID = managementID;
		this.name = name;
	}

	public Department(int id, int parentDepartmentID, int managementID, String name) {
		this.id = id;
		this.parentDepartmentID = parentDepartmentID;
		this.managementID = managementID;
		this.name = name;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	public int getManagementID() {
		return this.managementID;
	}

	public void setManagementID(int id) {
		this.managementID = id;
	}

	public void setParentDepID(int id) {
		this.parentDepartmentID = id;
	}

	public int getParentDepID() {
		return this.parentDepartmentID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "ID: " + this.getID() + " Parent Dep ID: " + this.getParentDepID() + " Name: " + this.getName();
	}
}