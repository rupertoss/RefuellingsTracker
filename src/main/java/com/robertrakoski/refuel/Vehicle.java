package com.robertrakoski.refuel;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "vehicles")
class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String plateNumber;
	
	@Column(columnDefinition = "INT(10) UNSIGNED NOT NULL DEFAULT '0'")
	private int mileage;
	
	@ManyToMany(mappedBy = "vehicles")
	@JsonBackReference
	private Set<User> users;
	
	@OneToMany(mappedBy = "vehicle")
	@JsonManagedReference(value = "vehicle")
	private Set<Refuelling> refuellings;
	
	public Vehicle() {
	}
	
	public Vehicle(Long id, String plateNumber, int mileage, Set<User> users, Set<Refuelling> refuellings) {
		this.id = id;
		this.plateNumber = plateNumber;
		this.mileage = mileage;
		this.users = users;
		this.refuellings = refuellings;
	}

	void updateMileage(int mileage) {
		this.mileage = mileage;
	}
	
	public Long getId() {
		return id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public int getMileage() {
		return mileage;
	}

	public Set<User> getUsers() {
		return users;
	}

	public Set<Refuelling> getRefuellings() {
		return refuellings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + mileage;
		result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mileage != other.mileage)
			return false;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", plateNumber=" + plateNumber + ", mileage=" + mileage + "]";
	}
}
