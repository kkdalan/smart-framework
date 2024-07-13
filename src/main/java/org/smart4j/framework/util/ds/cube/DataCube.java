package org.smart4j.framework.util.ds.cube;

import java.util.List;
import java.util.stream.Collectors;

import org.smart4j.framework.util.PrintUtil;
import org.smart4j.framework.util.ds.table.DataTable;
import org.smart4j.framework.util.ds.table.DataTableUtil;

public class DataCube {

	private DataLayerCollection layers;

	public DataCube() {
		this.layers = new DataLayerCollection(this);
	}

	public DataLayerCollection layers() {
		return layers;
	}

	public DataLayer getLayer(String layerName) {
		return layers.get(layerName);
	}

	public DataLayer getLayer(int layerIndex) {
		return layers.get(layerIndex);
	}

	public void addLayer(String layerName, DataTable layerData) {
		layers.add(layerName, layerData);
	}

	public void removeLayer(String layerName) {
		DataLayer layer = getLayer(layerName);
		if (layer != null) {
			layers.asList().remove(layer);
		}
	}

	public DataLayer newLayer(String layerName) {
		DataLayer layer = new DataLayer(this, layerName, new DataTable());
		layers.add(layer);
		return layer;
	}

	public void setData(int layerIndex, int rowIndex, int columnIndex, Object value) {
		DataLayer layer = getLayer(layerIndex);
		if (layer != null) {
			layer.getLayerData().setValue(rowIndex, columnIndex, value);
		}
	}

	public void setData(String layerName, int rowIndex, int columnIndex, Object value) {
		DataLayer layer = getLayer(layerName);
		if (layer != null) {
			layer.getLayerData().setValue(rowIndex, columnIndex, value);
		}
	}

	public void setData(String layerName, int rowIndex, String columnName, Object value) {
		DataLayer layer = getLayer(layerName);
		if (layer != null) {
			layer.getLayerData().setValue(rowIndex, columnName, value);
		}
	}

	public Object getData(String layerName, int rowIndex, int columnIndex) {
		DataLayer layer = getLayer(layerName);
		if (layer != null) {
			return layer.getLayerData().getValue(rowIndex, columnIndex);
		}
		return null;
	}

	public Object getData(String layerName, int rowIndex, String columnName) {
		DataLayer layer = getLayer(layerName);
		if (layer != null) {
			return layer.getLayerData().getValue(rowIndex, columnName);
		}
		return null;
	}

	public DataTable getLayerData(String layerName) {
		DataLayer layer = getLayer(layerName);
		return (layer != null) ? layer.getLayerData() : null;
	}

	public DataTable getLayerData(int layerIndex) {
		DataLayer layer = getLayer(layerIndex);
		return (layer != null) ? layer.getLayerData() : null;
	}

	public List<String> getLayerNames() {
		return layers.asList().stream().map(DataLayer::getLayerName).collect(Collectors.toList());
	}

	public void print() {
		for (DataLayer layer : layers().asList()) {
			System.out.println("Layer: " + layer.getLayerName());
			PrintUtil.printAsTable(layer.getLayerData().columnNames(),
					DataTableUtil.convertDataTableToList(layer.getLayerData()));
		}
	}
}
