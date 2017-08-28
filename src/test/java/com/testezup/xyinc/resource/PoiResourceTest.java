package com.testezup.xyinc.resource;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testezup.xyinc.BaseTest;
import com.testezup.xyinc.builder.PoiBuilder;
import com.testezup.xyinc.model.Poi;
import com.testezup.xyinc.repository.IPoiRepository;
import com.testezup.xyinc.service.PoiService;

public class PoiResourceTest extends BaseTest {

	final String BASE_PATH = "http://localhost:9091/poi";

	private MockMvc mockMvc;

	@Mock
	private IPoiRepository poiRepository;
	
	@Mock
	private PoiService poiServiceMock;
	
	@InjectMocks
	private PoiService poiService;

	@InjectMocks
	PoiResource poiResource;

	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(poiResource).build();
	}

	@Test
	public void testSave() {

		PoiBuilder.buildAll().forEach(poi -> {
			try {
				this.mockMvc
						.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON_UTF8).content(
								MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(PoiBuilder.build())))
						.andExpect(status().is2xxSuccessful());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	@Test
	public void testSaveBadRequest() throws JsonProcessingException, Exception {
		Poi poi = PoiBuilder.build();
		poi.setCoordinateY(-2L);
		
		this.mockMvc
				.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(poi)))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testFindAll() throws Exception {
		Mockito.when(poiServiceMock.findAll()).thenReturn(PoiBuilder.buildAll());

		mockMvc.perform(get(BASE_PATH)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(7)));
	}

	@Test
	public void testFindByProximity() throws Exception {
		Mockito.when(poiRepository.findAll()).thenReturn(PoiBuilder.buildAll());
		List<Poi> listPoi = poiService.findByProximity(20L,10L,10L);
		Mockito.when(poiServiceMock.findByProximity(20L,10L,10L)).thenReturn(listPoi);

		mockMvc.perform(get(BASE_PATH + "/proximity/20/10/10")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(4)));

	}
}