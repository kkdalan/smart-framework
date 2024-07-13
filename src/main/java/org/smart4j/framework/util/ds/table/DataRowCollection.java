package org.smart4j.framework.util.ds.table;

import java.util.ArrayList;
import java.util.List;

public class DataRowCollection {

	private List<DataRow> rows = new ArrayList<DataRow>();

	/**
	 * DataRowCollection所屬的DataTable，唯讀
	 */
	private DataTable table;

	/**
	 * DataRowCollection被建立時，一定要指定所屬的DataTable
	 * 
	 * @param table
	 */
	public DataRowCollection(DataTable table) {
		this.table = table;
	}

	/**
	 * 取得所屬的DataTable
	 * 
	 * @return DataTable
	 */
	public DataTable getTable() {
		return this.table;
	}

	public List<DataRow> asList() {
		return rows;
	}

	public DataRow get(int rowIndex) {
		return rows.get(rowIndex);
	}

	public void add(DataRow row) {
		rows.add(row);
	}
	
	public int size() {
		return rows.size();
	}

}
