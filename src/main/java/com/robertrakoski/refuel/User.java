package com.robertrakoski.refuel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "users_vehicles",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "vehicle_id")})
	@JsonManagedReference
	@JsonIgnoreProperties("refuellings")
	private Set<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference(value = "user")
	private Set<Refuelling> refuellings;
	
	public User() {
	}
	
	public User(Long id, String name, Set<Vehicle> vehicles, Set<Refuelling> refuellings) {
		this.id = id;
		this.name = name;
		this.vehicles = vehicles;
		this.refuellings = refuellings;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public Set<Refuelling> getRefuellings() {
		return refuellings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((refuellings == null) ? 0 : refuellings.hashCode());
		result = prime * result + ((vehicles == null) ? 0 : vehicles.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
