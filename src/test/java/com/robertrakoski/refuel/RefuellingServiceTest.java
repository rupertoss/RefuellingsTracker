package com.robertrakoski.refuel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTest.sql")
})
public class RefuellingServiceTest extends AbstractTest {

	@Autowired
	RefuellingService refuellingService;
	
	@Test
	public void testGetById() {
		Long id = 3L;
		
		Refuelling refuelling = refuellingService.getById(id);
		
		assertNotNull("expected not null", refuelling);
		assertEquals("expected id attribute match", id, refuelling.getId());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetById_whenIdNotFound_shouldthrowEntityNotFoundException() {
		Long id = Long.MAX_VALUE;
		
		refuellingService.getById(id);
	}
	
	@Test
	public void testCreate() {
		LocalDateTime timestamp = LocalDateTime.of(2018, 05, 05, 22, 15, 0);
		Refuelling refuelling = new Refuelling(null, timestamp, 78994, FuelType.Pb95, 37.61, new BigDecimal(185.94), getVehicle2Stub(), getUser2Stub());
		
		Refuelling createdRefuelling = refuellingService.create(refuelling);
		
		assertEquals("expected timestamp attribute match", timestamp, createdRefuelling.getTimestamp());
	}
	
	@Test(expected = EntityExistsException.class)
	public void testCreate_whenEntityWithGivenAlreadyExist_shouldThrowEntityExistsException() {
		LocalDateTime timestamp = LocalDateTime.of(2018, 05, 05, 22, 15, 0);
		Refuelling refuelling = new Refuelling(1L, timestamp, 78994, FuelType.Pb95, 37.61, new BigDecimal(185.94), getVehicle2Stub(), getUser2Stub());
		
		refuellingService.create(refuelling);
	}
	
	@Test
	public void testUpdate() {
		Long refuellingId = 3L;
		
		Refuelling refuelling = refuellingService.getById(refuellingId);
		
		assertNotNull("expected not null", refuelling);
		
		Refuelling updatedRefuelling = refuellingService.update(refuelling);
		
		assertNotNull("expected not null", updatedRefuelling);
		assertEquals("expected id attribute match", refuellingId, updatedRefuelling.getId());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDelete() {
		Long refuellingId = 4L;
		
		Refuelling refuelling = refuellingService.getById(refuellingId);
		
		assertNotNull("expected not null", refuelling);
		
		refuellingService.delete(refuellingId);
		
		refuellingService.getById(refuellingId);
			}
	
}
