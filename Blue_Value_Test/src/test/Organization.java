package test;

class Organization {

	int id;
	String name;

	public Organization() {
	}

	public Organization(String name) {
		this.name = name;
	}

	public Organization(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ID: " + this.getId() + " Name: " + this.getName();
	}

}