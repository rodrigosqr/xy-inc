package com.testezup.xyinc.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testezup.xyinc.model.Poi;
import com.testezup.xyinc.service.PoiService;

@RestController
@RequestMapping("/poi")
public class PoiResource {

	@Autowired
	private PoiService poiService;
	
	
	@GetMapping
	public List<Poi> listAll() {
		return poiService.findAll();
	}
	
	@GetMapping("/proximity/{coordinateX}/{coordinateY}/{range}")
	public List<Poi> listByProximity(@PathVariable("coordinateX") Long coordinateX, @PathVariable("coordinateY") Long coordinateY, @PathVariable("range") Long range){		
		return poiService.findByProximity(coordinateX, coordinateY, range);
	}
	
	@PostMapping
	public ResponseEntity<Poi> save(@Valid @RequestBody Poi poi){
		Poi poiSave = poiService.save(poi);		
		return ResponseEntity.status(HttpStatus.CREATED).body(poiSave);
		
	}
}
