package com.robertrakoski.refuel;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	Vehicle getById(Long id) {
		checkForExisting(id);
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		return vehicle.get();
	}
	
	Vehicle create(Vehicle vehicle) {
		if(vehicle.getId() != null) 
			throw new EntityExistsException("The id attribute must be null to persist a new entity.");
		return vehicleRepository.save(vehicle);
	}
	
	Vehicle update(Vehicle vehicle) {
		checkForExisting(vehicle.getId());
		return vehicleRepository.save(vehicle);
	}
	
	void delete(Long id) {
		checkForExisting(id);
		vehicleRepository.deleteById(id);
	}
	
	List<Vehicle> getVehiclesByUser(User user) {
		List<Vehicle> vehicles = new LinkedList<>();
		vehicleRepository.findAll().forEach(vehicles::add);
		vehicles.removeIf(v -> !v.getUsers().contains(user));
		return vehicles;
	}
	
	void updateMileageAndSave(Long id, int mileage) {
		Vehicle vehicle = getById(id);
		vehicle.updateMileage(mileage);
		vehicleRepository.save(vehicle);
	}
	
	private void checkForExisting(Long id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		if(!vehicle.isPresent())
			throw new EntityNotFoundException("There is no vehicle with specified id.");
	}
}
