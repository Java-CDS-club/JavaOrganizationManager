package test;

public class EmployeeJob {

	int id;
	int depID;
	String name;
	String description;

	public EmployeeJob() {
	}

	public EmployeeJob(int id, int depID, String name, String description) {
		this.id = id;
		this.depID = depID;
		this.name = name;
		this.description = description;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getDepID() {
		return this.depID;
	}

	public void setDepID(int depID) {
		this.depID = depID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String d) {
		this.description = d;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return "ID: " + this.getID() + " Department ID: " + this.getDepID() + " Name: " + this.getName()
				+ " Description: " + this.getDescription();
	}
}
