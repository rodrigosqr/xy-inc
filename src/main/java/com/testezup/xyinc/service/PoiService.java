package com.testezup.xyinc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testezup.xyinc.model.Poi;
import com.testezup.xyinc.repository.IPoiRepository;

@Service
public class PoiService {

	@Autowired
	private IPoiRepository poiRepository;

	public Poi save(Poi poi) {
		return poiRepository.save(poi);
	}

	public List<Poi> findAll() {
		return poiRepository.findAll();
	}

	public List<Poi> findByProximity(Long coordinateX, Long coordinateY, Long range) {
		List<Poi> pois = findAll();

		return pois.stream()
				.filter(poi -> Math.sqrt(Math.pow(coordinateX - poi.getCoordinateX(), 2) + Math.pow(coordinateY - poi.getCoordinateY(), 2)) <= range)
				.collect(Collectors.toList());
	}
}
