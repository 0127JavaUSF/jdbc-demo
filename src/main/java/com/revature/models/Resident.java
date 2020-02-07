package com.revature.models;

public class Resident {

	private int id;
	private String name;
	private int rent;
	private int houseId;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getHouseId() {
		return houseId;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + houseId;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rent;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resident other = (Resident) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (houseId != other.houseId)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rent != other.rent)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resident [id=" + id + ", name=" + name + ", rent=" + rent + ", houseId=" + houseId + ", email=" + email
				+ "]";
	}

	public Resident(int id, String name, int rent, int houseId, String email) {
		super();
		this.id = id;
		this.name = name;
		this.rent = rent;
		this.houseId = houseId;
		this.email = email;
	}

	public Resident() {
		super();
	}

}
