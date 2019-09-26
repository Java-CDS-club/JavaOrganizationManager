package test;

class ManagerJob {
	int id;
	String name;
	String description;

	public ManagerJob() {
	}

	public ManagerJob(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
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
		return "ID: " + this.getID() + " Name: " + this.getName() + " Description: " + this.getDescription();
	}
}