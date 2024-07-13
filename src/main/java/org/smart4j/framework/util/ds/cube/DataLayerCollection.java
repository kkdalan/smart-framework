package org.smart4j.framework.util.ds.cube;

import java.util.ArrayList;
import java.util.List;

import org.smart4j.framework.util.ds.table.DataTable;

public class DataLayerCollection {

	private List<DataLayer> layers = new ArrayList<>();
	private DataCube cube;

	public DataLayerCollection(DataCube cube) {
		this.cube = cube;
	}

	public DataCube getCube() {
		return this.cube;
	}

	public void add(DataLayer layer) {
		layers.add(layer);
	}

	public DataLayer add(String layerName, DataTable layerData) {
		DataLayer layer = new DataLayer(this.cube, layerName, layerData);
		layers.add(layer);
		return layer;
	}

	public DataLayer get(String layerName) {
		for (DataLayer layer : layers) {
			if (layer.getLayerName().equalsIgnoreCase(layerName)) {
				return layer;
			}
		}
		return null;
	}

	public DataLayer get(int index) {
		return layers.get(index);
	}

	public List<DataLayer> asList() {
		return layers;
	}
}
