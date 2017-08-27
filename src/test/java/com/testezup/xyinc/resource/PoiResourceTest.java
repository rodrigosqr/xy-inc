package com.testezup.xyinc.resource;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testezup.xyinc.BaseTest;
import com.testezup.xyinc.builder.PoiBuilder;
import com.testezup.xyinc.model.Poi;
import com.testezup.xyinc.repository.IPoiRepository;



public class PoiResourceTest extends BaseTest{

	final String BASE_PATH = "http://localhost:9091/poi";

	@Autowired
	private IPoiRepository repository;

	@Autowired
	private TestRestTemplate restTemplate;

	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() throws Exception {

		repository.deleteAll();		
		PoiBuilder.buildAll().forEach(poi -> repository.save(poi));
		restTemplate = new TestRestTemplate();
	}

	@Test
	public void testSave() {

		Poi poi = new Poi("Farmacia", 45L, 2L);

		ResponseEntity<Poi> response = restTemplate.postForEntity(BASE_PATH, poi, Poi.class);

		Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		Assert.assertEquals("Farmacia", response.getBody().getname());
	}
	
	@Test(expected = HttpMessageNotReadableException.class)
	public void testSaveBadRequest()  {
		Poi poi = new Poi("Farmácia", 45L, -2L);		
		restTemplate.postForEntity(BASE_PATH, poi, Poi.class);
	}	
	
	@Test
	public void testFindAll() throws IOException{
	 
	    ResponseEntity<String> response = restTemplate
	        .getForEntity(BASE_PATH, String.class);
	 
	    List<Poi> listPoi = MAPPER.readValue(response.getBody(), 
	        MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));
	     
	    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	    Assert.assertFalse(listPoi.isEmpty());
	    Assert.assertEquals(listPoi.size(), 7);
	}
	
	@Test
	public void testFindByProximity() throws IOException{
		List<Poi> listPoi = PoiBuilder.buildAll();
	    
		ResponseEntity<String> response = restTemplate
	        .getForEntity(BASE_PATH + "/proximity/20/10/10", String.class);
	 
	    List<Poi> listPoiResponse = MAPPER.readValue(response.getBody(), 
	        MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));

		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assert.assertTrue(listPoiResponse.size() == 4);
		Assert.assertEquals(listPoiResponse.get(0), listPoi.get(0));
		Assert.assertEquals(listPoiResponse.get(1), listPoi.get(2));
		Assert.assertEquals(listPoiResponse.get(2), listPoi.get(4));
		Assert.assertEquals(listPoiResponse.get(3), listPoi.get(5));
	}	
}
