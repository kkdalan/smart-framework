package org.smart4j.framework.util.ds.cube;

import org.smart4j.framework.util.ds.table.DataTable;

public class DataLayer {

	private String layerName;
	private DataTable layerData;
	private DataCube cube;

	public DataLayer(DataCube cube, String layerName) {
		this.cube = cube;
		this.layerName = layerName;
	}
	
	public DataLayer(DataCube cube, String layerName, DataTable layerData) {
		this.cube = cube;
		this.layerName = layerName;
		this.layerData = layerData;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public DataTable getLayerData() {
		return layerData;
	}

	public void setLayerData(DataTable layerData) {
		this.layerData = layerData;
	}

	public DataCube getCube() {
		return cube;
	}

	@Override
	public String toString() {
		return "DataLayer{" + "layerName='" + layerName + '\'' + ", layerData=" + layerData + ", cube=" + cube + '}';
	}
}
