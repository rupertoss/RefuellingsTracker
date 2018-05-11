package com.robertrakoski.refuel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTest.sql")
})
public class RefuellingControllerIntegrationTest extends AbstractTest{
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testGetRefuelling_shouldRespondStatus200() throws Exception {
		
		Refuelling refuelling = getRefuelling1Stub();
		Long refuellingId = 1L;
		
		String json = mapToJson(refuelling);
		String uri = "/api/refuellings/{refuellingId}";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, refuellingId)).andReturn();
		
		int status = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		
		assertEquals("expected HTTP status 200", 200, status);
		assertTrue("expected HTTP content length > 0", content.trim().length() > 0);
		assertTrue("expected HTTP response body to match", content.equals(json));
	}
	
	@Test
	public void testGetRefuelling_whenInvalidRefuellingId_shouldRespondStatus404() throws Exception {
		
		Long refuellingId = Long.MAX_VALUE;
		String uri = "/api/refuellings/{refuellingId}";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, refuellingId)).andReturn();
		
		int status = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		
		assertEquals("expected HTTP status 404", 404, status);
		assertTrue("expected HTTP content length > 0", content.trim().length() > 0);
	}
	
	@Test
	public void testCreateRefuelling_shouldRespondStatus200() throws Exception {
		Refuelling refuelling = getRefuelling1Stub();
		
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
		assertTrue("expected id attribute to match", createdRefuelling.getId().equals(refuelling.getId()));
	}
	
	@Test
	public void testUpdateRefuelling_shouldRespondStatus200() throws Exception {
		Refuelling refuelling = getRefuelling1Stub();
		Long refuellingId = refuelling.getId();
		
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
		assertTrue("expected id attribute to match", updatedRefuelling.getId().equals(refuelling.getId()));
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
