package com.robertrakoski.refuel;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RefuellingService {
	
	@Autowired
	RefuellingRepository refuellingRepository;
	
	@Autowired
	VehicleService vehicleService;
	
	Refuelling getById(Long id) {
		checkForExisting(id);
		Optional<Refuelling> refuelling = refuellingRepository.findById(id);
		return refuelling.get();
	}
	
	Refuelling create(Refuelling refuelling) {
		if(refuelling.getId() != null) 
			throw new EntityExistsException("The id attribute must be null to persist a new entity.");
		updateMileageInVehicle(refuelling);
		return refuellingRepository.save(refuelling);
	}
	
	Refuelling update(Refuelling refuelling) {
		checkForExisting(refuelling.getId());
		updateMileageInVehicle(refuelling);
		return refuellingRepository.save(refuelling);
	}
	
	void delete(Long id) {
		checkForExisting(id);
		refuellingRepository.deleteById(id);
	}
	
	List<Refuelling> getRefuellingsByUser(User user) {
		List<Refuelling> refuellings = getRefuellings();
		return restrictByUser(refuellings, user);
	}
	
	List<Refuelling> getRefuellingsByVehicle(Vehicle vehicle) {
		List<Refuelling> refuellings = getRefuellings();
		return restrictByVehicle(refuellings, vehicle);
	}
	
	List<Refuelling> getRefuellingsByUserAndVehicle(User user, Vehicle vehicle) {
		List<Refuelling> refuellings = getRefuellings();
		return restrictByUser(restrictByVehicle(refuellings, vehicle), user);
	}
	
	private List<Refuelling> getRefuellings() {
		List<Refuelling> refuellings = new LinkedList<>();
		refuellingRepository.findAll().forEach(refuellings::add);
		return refuellings;
	}
	
	private List<Refuelling> restrictByVehicle(List<Refuelling> refuellings, Vehicle vehicle) {
		refuellings.removeIf(v -> !v.getVehicle().equals(vehicle));
		return refuellings;
	}
	
	private List<Refuelling> restrictByUser(List<Refuelling> refuellings, User user) {
		refuellings.removeIf(u -> !u.getUser().equals(user));
		return refuellings;
	}
	
	private void checkForExisting(Long id) {
		Optional<Refuelling> refuelling = refuellingRepository.findById(id);
		if(!refuelling.isPresent())
			throw new EntityNotFoundException("There is no vehicle with specified id.");
	}
	
	private void updateMileageInVehicle(Refuelling refuelling) {
		Long vehicleId = refuelling.getVehicle().getId();
		int vehicleMileage = refuelling.getMileage();
		vehicleService.updateMileageAndSave(vehicleId, vehicleMileage);
	}
}
