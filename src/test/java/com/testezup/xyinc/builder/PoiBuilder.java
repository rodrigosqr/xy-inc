package com.testezup.xyinc.builder;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.testezup.xyinc.model.Poi;

public final class PoiBuilder {

	public static Poi build() {
		List<Poi> listPoi = buildAll();
		return listPoi.get(new Random().nextInt(listPoi.size()));
	}

	public static List<Poi> buildAll() {
		List<Poi> listPoi = new ArrayList<>();
		listPoi.add(new Poi("Lanchonete", 27L, 12L));
		listPoi.add(new Poi("Posto", 31L, 18L));
		listPoi.add(new Poi("Joalheria", 15L, 12L));
		listPoi.add(new Poi("Floricultura", 19L, 21L));
		listPoi.add(new Poi("Pub", 12L, 8L));
		listPoi.add(new Poi("Supermercado", 23L, 6L));
		listPoi.add(new Poi("Churrascaria", 28L, 2L));
		return listPoi;
	}
}
