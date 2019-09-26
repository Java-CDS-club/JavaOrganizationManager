package test;

class Management {
	int id;
	int organizationID;
	String name;

	Management() {
	}

	public Management(int organizationID, String name) {
		this.organizationID = organizationID;
		this.name = name;
	}

	public Management(int id, int organizationID, String name) {
		this.id = id;
		this.organizationID = organizationID;
		this.name = name;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	public void setOrgID(int id) {
		this.organizationID = id;
	}

	public int getOrgID() {
		return this.organizationID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "ID: " + this.getID() + " Organization ID: " + this.getOrgID() + " Name: " + this.getName();
	}
}