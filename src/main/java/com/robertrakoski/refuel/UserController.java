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
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private RefuellingService refuellingService;
	
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable(value = "userId") Long userId) {
		User user = userService.getById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.create(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
		User updatedUser = userService.update(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "userId") Long userId) {
		userService.delete(userId);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/{userId}/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vehicle>> getUserVehicles(@PathVariable(value = "userId") Long userId) {
		User user = userService.getById(userId);
		List<Vehicle> vehicleList = vehicleService.getVehiclesByUser(user);
		return new ResponseEntity<List<Vehicle>>(vehicleList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}/refuellings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Refuelling>> getUserRefuellings(@PathVariable(value = "userId") Long userId) {
		User user = userService.getById(userId);
		List<Refuelling> refuellingList = refuellingService.getRefuellingsByUser(user);
		return new ResponseEntity<List<Refuelling>>(refuellingList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}/vehicles/{vehicleId}/refuellings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Refuelling>> getUserVehicleRefuellings(@PathVariable(value = "userId") Long userId, @PathVariable(value = "vehicleId") Long vehicleId) {
		Vehicle vehicle = vehicleService.getById(vehicleId);
		User user = userService.getById(userId);
		if(!vehicle.getUsers().contains(user))
			throw new IllegalArgumentException("Specified user does not own vehicle with given id");
		List<Refuelling> refuellingList = refuellingService.getRefuellingsByUserAndVehicle(user, vehicle);
		return new ResponseEntity<List<Refuelling>>(refuellingList, HttpStatus.OK);
	}
}
