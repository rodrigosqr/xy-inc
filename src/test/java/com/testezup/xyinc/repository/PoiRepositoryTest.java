package com.testezup.xyinc.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.testezup.xyinc.BaseTest;
import com.testezup.xyinc.builder.PoiBuilder;
import com.testezup.xyinc.model.Poi;

@Transactional
@Rollback
public class PoiRepositoryTest extends BaseTest{
	
	@Autowired
	private IPoiRepository poiRepository;

	private Poi poi;

	@Before
	public void init() {
		this.poiRepository.deleteAll();
		this.poi = PoiBuilder.build();
	}

	@Test
	public void saveTest() {
		this.poiRepository.save(this.poi);
		Assert.assertNotNull(this.poi.getId());
	}

	@Test
	public void findAllTest() {
		PoiBuilder.buildAll().forEach(poi -> this.poiRepository.save(poi));
		List<Poi> listPoi = this.poiRepository.findAll();
		Assert.assertFalse(listPoi.isEmpty());
		Assert.assertEquals(listPoi.size(), 7);
	}
}
