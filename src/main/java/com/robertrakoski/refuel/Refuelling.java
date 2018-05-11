package com.robertrakoski.refuel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity	
@Table(name = "refuellings")
class Refuelling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private LocalDateTime timestamp;
	
	@NotNull
	private int mileage;
	
	@NotNull
	@Enumerated
	private FuelType fuelType;
	
	@Column(columnDefinition = "DECIMAL(6,2) NOT NULL")
	private double quantity;
	
	@NotNull
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "vehicle_id", nullable = false)
	@JsonBackReference(value = "vehicle")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user")
	private User user;
	
	@PrePersist
	private void preInsert() {
		if (this.timestamp == null) 
			this.timestamp = LocalDateTime.now();
	}
	
	public Refuelling() {
	}

	public Refuelling(Long id, LocalDateTime timestamp, int mileage, FuelType fuelType, double quantity,
						BigDecimal price, Vehicle vehicle, User user) {
		this.id = id;
		this.timestamp = timestamp;
		this.mileage = mileage;
		this.fuelType = fuelType;
		this.quantity = quantity;
		this.price = price;
		this.vehicle = vehicle;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getMileage() {
		return mileage;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public double getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + mileage;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		Refuelling other = (Refuelling) obj;
		if (fuelType != other.fuelType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mileage != other.mileage)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Refuelling [id=" + id + ", timestamp=" + timestamp + ", mileage=" + mileage + ", fuelType=" + fuelType
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
