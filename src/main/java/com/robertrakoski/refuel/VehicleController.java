package com.robertrakoski.refuel;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/vehicles")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired 
	RefuellingService refuellingService;

	@GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> getVehicle(@PathVariable(value = "vehicleId") Long vehicleId) {
		Vehicle vehicle = vehicleService.getById(vehicleId);
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
		Vehicle savedVehicle = vehicleService.create(vehicle);
		return new ResponseEntity<Vehicle>(savedVehicle, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{vehicleId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle) {
		Vehicle updatedVehicle = vehicleService.update(vehicle);
		return new ResponseEntity<Vehicle>(updatedVehicle, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{vehicleId}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable(value = "vehicleId") Long vehicleId) {
		vehicleService.delete(vehicleId);
		return new ResponseEntity<Vehicle>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/{vehicleId}/refuellings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Refuelling>> getVehicleRefuellings(@PathVariable(value = "vehicleId") Long vehicleId) {
		Vehicle vehicle = vehicleService.getById(vehicleId);
		List<Refuelling> refuellingList = refuellingService.getRefuellingsByVehicle(vehicle);
		return new ResponseEntity<List<Refuelling>>(refuellingList, HttpStatus.OK);
	}
}
