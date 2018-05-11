package com.robertrakoski.refuel;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractTest {

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
	
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }
    
    protected Refuelling getRefuelling1Stub() {
    	LocalDateTime timestamp = LocalDateTime.of(2018, 05, 01, 12, 0, 0);
       	return new Refuelling(1L, timestamp, 124578, FuelType.ON, 42.12, new BigDecimal(174.53), getVehicle1Stub(), getUser1Stub());
    }
    
    protected Refuelling getRefuelling2Stub() {
    	LocalDateTime timestamp = LocalDateTime.of(2018, 05, 02, 13, 5, 0);
       	return new Refuelling(2L, timestamp, 78456, FuelType.Pb95, 30.00, new BigDecimal(150.00), getVehicle2Stub(), getUser2Stub());
    }
    
    protected Refuelling getRefuelling3Stub() {
    	LocalDateTime timestamp = LocalDateTime.of(2018, 05, 04, 14, 50, 0);
       	return new Refuelling(3L, timestamp, 125108, FuelType.ON, 27.34, new BigDecimal(121.78), getVehicle1Stub(), getUser1Stub());
    }
    
    protected Refuelling getRefuelling4Stub() {
    	LocalDateTime timestamp = LocalDateTime.of(2018, 05, 05, 22, 15, 0);
       	return new Refuelling(4L, timestamp, 78994, FuelType.Pb95, 37.61, new BigDecimal(185.94), getVehicle2Stub(), getUser2Stub());
    }
    
    protected Refuelling getRefuelling5Stub() {
    	LocalDateTime timestamp = LocalDateTime.of(2018, 05, 8, 8, 20, 30);
       	return new Refuelling(5L, timestamp, 79621, FuelType.Pb95, 12.02, new BigDecimal(61.02), getVehicle2Stub(), getUser1Stub());
    }
    
    protected Vehicle getVehicle1Stub() {
    	Set<Refuelling> refuellings = new LinkedHashSet<>();
//    	refuellings.add(getRefuelling1Stub());
//    	refuellings.add(getRefuelling3Stub());
    	Set<User> users = new LinkedHashSet<>();
//    	users.add(getUser1Stub());
    	return new Vehicle(1L, "RZ01234", 123456, users, refuellings);
    }
    
    protected Vehicle getVehicle2Stub() {
    	Set<Refuelling> refuellings = new LinkedHashSet<>();
//    	refuellings.add(getRefuelling2Stub());
//    	refuellings.add(getRefuelling4Stub());
//    	refuellings.add(getRefuelling5Stub());
    	Set<User> users = new LinkedHashSet<>();
//    	users.add(getUser1Stub());
//    	users.add(getUser2Stub());
    	return new Vehicle(2L, "RZE45678", 78001, users, refuellings);
    }
    
    protected User getUser1Stub() {
    	Set<Refuelling> refuellings = new LinkedHashSet<>();
//    	refuellings.add(getRefuelling1Stub());
//    	refuellings.add(getRefuelling3Stub());
//    	refuellings.add(getRefuelling5Stub());
    	Set<Vehicle> vehicles = new LinkedHashSet<>();
//    	vehicles.add(getVehicle1Stub());
//    	vehicles.add(getVehicle2Stub());
    	return new User(1L, "user1", vehicles, refuellings);
   	}
    
    protected User getUser2Stub() {
    	Set<Refuelling> refuellings = new LinkedHashSet<>();
//    	refuellings.add(getRefuelling2Stub());
//    	refuellings.add(getRefuelling4Stub());
    	Set<Vehicle> vehicles = new LinkedHashSet<>();
//    	vehicles.add(getVehicle2Stub());
    	return new User(2L, "user2", vehicles, refuellings);
   	}
}
