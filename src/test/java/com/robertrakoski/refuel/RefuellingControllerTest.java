package com.robertrakoski.refuel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(RefuellingController.class)
@AutoConfigureWebMvc
public class RefuellingControllerTest extends AbstractTest {
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	RefuellingService refuellingService;
	
	@InjectMocks
	RefuellingController refuellingController;
	
	@Test
	public void testGetRefuelling_shouldRespondStatus200() throws Exception {
		
		Refuelling refuelling = getRefuelling1Stub();
		Long refuellingId = 1L;
		String uri = "/api/refuellings/{refuellingId}";
		
		when(refuellingService.getById(refuellingId)).thenReturn(refuelling);
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, refuellingId)).andReturn();
		
		int status = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		
		assertEquals("expected HTTP status 200", 200, status);
		assertTrue("expected HTTP content length > 0", content.trim().length() > 0);
	}
	
	@Test
	public void testGetRefuelling_whenInValidRefuellingId_shouldRespondStatus404() throws Exception {
		
		Long refuellingId = Long.MAX_VALUE;
		String uri = "/api/refuellings/{refuellingId}";
		
		when(refuellingService.getById(refuellingId)).thenThrow(new EntityNotFoundException());
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, refuellingId)).andReturn();
		
		int status = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		
		assertEquals("expected HTTP status 404", 404, status);
		assertTrue("expected HTTP content length > 0", content.trim().length() > 0);
	}
	
	@Test
	public void testCreateRefuelling_shouldRespondStatus200() throws Exception {
		Refuelling refuelling = getRefuelling1Stub();
		
		when(refuellingService.create(any(Refuelling.class))).thenReturn(refuelling);
		
		String uri = "/api/refuellings";
		String inputJson = mapToJson(refuelling);
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders
								.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
								.accept(MediaType.APPLICATION_JSON_VALUE)
								.content(inputJson))
								.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		assertEquals("expected HTTP status 201", 201, status);
		assertTrue("expected HTTP content length > 0", content.trim().length() > 0);
		
		Refuelling createdRefuelling = mapFromJson(content, Refuelling.class);
		
		assertNotNull("expected not null entity", createdRefuelling);
		assertNotNull("expected not null id attribute", createdRefuelling.getId());
	}
	
	@Test
	public void testUpdateRefuelling_shouldRespondStatus200() throws Exception {
		Refuelling refuelling = getRefuelling1Stub();
		Long refuellingId = refuelling.getId();
		
		when(refuellingService.update(any(Refuelling.class))).thenReturn(refuelling);
		
		String uri = "/api/refuellings/{refuellingId}";
		String inputJson = mapToJson(refuelling);
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders
								.put(uri, refuellingId).contentType(MediaType.APPLICATION_JSON_VALUE)
								.accept(MediaType.APPLICATION_JSON_VALUE)
								.content(inputJson))
								.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		assertEquals("expected HTTP status 200", 200, status);
		assertTrue("expected HTTP content length > 0", content.trim().length() > 0);
		
		Refuelling updatedRefuelling = mapFromJson(content, Refuelling.class);
		
		assertNotNull("expected not null entity", updatedRefuelling);
		assertNotNull("expected not null id attribute", updatedRefuelling.getId());
	}
	
	@Test
	public void testDeleteRefuelling_shouldRespondStatus200() throws Exception {
		Long refuellingId = 1L;
		
		String uri = "/api/refuellings/{refuellingId}";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, refuellingId)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		assertEquals("expected HTTP status 204", 204, status);
	}
}
