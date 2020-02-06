package com.revature.models;

public class House {
	private int id;
	private String buildName;
	private String apartmentNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartmentNumber == null) ? 0 : apartmentNumber.hashCode());
		result = prime * result + ((buildName == null) ? 0 : buildName.hashCode());
		result = prime * result + id;
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
		House other = (House) obj;
		if (apartmentNumber == null) {
			if (other.apartmentNumber != null)
				return false;
		} else if (!apartmentNumber.equals(other.apartmentNumber))
			return false;
		if (buildName == null) {
			if (other.buildName != null)
				return false;
		} else if (!buildName.equals(other.buildName))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", buildName=" + buildName + ", apartmentNumber=" + apartmentNumber + "]";
	}

	public House(int id, String buildName, String apartmentNumber) {
		super();
		this.id = id;
		this.buildName = buildName;
		this.apartmentNumber = apartmentNumber;
	}

	public House() {
		super();
	}

}
