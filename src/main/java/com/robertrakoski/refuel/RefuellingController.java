package com.robertrakoski.refuel;

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
@RequestMapping(value = "api/refuellings")
public class RefuellingController {

	@Autowired
	RefuellingService refuellingService;

	@GetMapping(value = "/{refuellingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Refuelling> getRefuelling(@PathVariable(value = "refuellingId") Long refuellingId) {
		Refuelling refuelling = refuellingService.getById(refuellingId);
		return new ResponseEntity<Refuelling>(refuelling, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Refuelling> createRefuelling(@Valid @RequestBody Refuelling refuelling) {
		Refuelling savedRefuelling = refuellingService.create(refuelling);
		return new ResponseEntity<Refuelling>(savedRefuelling, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{refuellingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Refuelling> updateRefuelling(@Valid @RequestBody Refuelling refuelling) {
		Refuelling updatedRefuelling = refuellingService.update(refuelling);
		return new ResponseEntity<Refuelling>(updatedRefuelling, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{refuellingId}")
	public ResponseEntity<Refuelling> deleteRefuelling(@PathVariable(value = "refuellingId") Long refuellingId) {
		refuellingService.delete(refuellingId);
		return new ResponseEntity<Refuelling>(HttpStatus.NO_CONTENT);
	}
}
