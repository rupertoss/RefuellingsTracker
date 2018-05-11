package com.robertrakoski.refuel;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserService {

	@Autowired
	UserRepository userRepository;
	
	User getById(Long id) {
		checkForExisting(id);
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	User create(User user) {
		if(user.getId() != null) 
			throw new EntityExistsException("The id attribute must be null to persist a new entity.");
		return userRepository.save(user);
	}
	
	User update(User user) {
		checkForExisting(user.getId());
		return userRepository.save(user);
	}
	
	void delete(Long id) {
		checkForExisting(id);
		userRepository.deleteById(id);
	}
	
	private void checkForExisting(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new EntityNotFoundException("There is no user with specified id.");
	}
}
