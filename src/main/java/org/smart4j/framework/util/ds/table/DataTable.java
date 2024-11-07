package org.smart4j.framework.util.ds.table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.smart4j.framework.util.PrintUtil;

public class DataTable {

	/**
	 * 保存DataRow的集合，在DataTable初始化時，便會建立
	 */
	private DataRowCollection rows;
	/**
	 * 保存DataColumn的集合，在DataTable初始化時，便會建立
	 */
	private DataColumnCollection columns;
	/**
	 * DataTable的名稱，沒什麼用到
	 */
	private String tableName;

	/**
	 * 初始化DataTable，並建立DataColumnCollection，DataRowCollection
	 */
	public DataTable() {
		this.columns = new DataColumnCollection(this);
		this.rows = new DataRowCollection(this);

	}

	/**
	 * 除了初始化DataTable， 可以指定DataTable的名字(沒什麼意義)
	 * 
	 * @param dataTableName DataTable的名字
	 */
	public DataTable(String tableName) {
		this();
		this.tableName = tableName;
	}

	/**
	 * 由此DataTable物件來建立一個DataRow物件
	 * 
	 * @return DataRow
	 */
	public DataRow newRow() {
		DataRow row = new DataRow(this);// DataRow為呼叫此方法DataTable的成員
		return row;
	}

	/**
	 * 把DataTable當做二維陣列，給列索引和行索引，設定值的方法 <br/>
	 * (發佈者自行寫的方法)
	 * 
	 * @param rowIndex    列索引(從0算起)
	 * @param columnIndex 行索引(從0算起)
	 * @param value       要給的值
	 */
	public void setValue(int rowIndex, int columnIndex, Object value) {
		this.rows.get(rowIndex).setValue(columnIndex, value);
	}

	/**
	 * 把DataTable當做二維陣列，給列索引和行名稱，設定值的方法 <br/>
	 * (發佈者自行寫的方法)
	 * 
	 * @param rowIndex    列索引(從0算起)
	 * @param columnIndex 行名稱
	 * @param value       要給的值
	 */
	public void setValue(int rowIndex, String columnName, Object value) {
		this.rows.get(rowIndex).setValue(columnName.toLowerCase(), value);
	}

	/**
	 * 把DataTable當做二維陣列，給列索引和行索引，取得值的方法 <br/>
	 * (發佈者自行寫的方法)
	 * 
	 * @param rowIndex    列索引(從0算起)
	 * @param columnIndex 行索引(從0算起)
	 * @return 回傳該位置的值
	 */
	public Object getValue(int rowIndex, int columnIndex) {
		return this.rows.get(rowIndex).getValue(columnIndex);
	}

	/**
	 * 把DataTable當做二維陣列，給列索引和行名稱，取得值的方法 <br/>
	 * (發佈者自行寫的方法)
	 * 
	 * @param rowIndex   列索引(從0算起)
	 * @param columnName 行名稱
	 * @return 回傳該位置的值
	 */
	public Object getValue(int rowindex, String columnName) {
		return this.rows.get(rowindex).getValue(columnName.toLowerCase());
	}

	public DataRowCollection rows() {
		return rows;
	}

	public DataColumnCollection columns() {
		return columns;
	}

	public List<String> columnNames() {
		return columns.asList().stream().map(e -> e.getColumnName()).collect(Collectors.toList());
	}

	public String getTableName() {
		return tableName;
	}

	public void print() {
		System.out.println(tableName + ":");
		PrintUtil.printAsTable(columnNames(), asList());
	}
	
	public List<List<String>> asList() {
		return convertToList(this);
	}
	
	private static List<List<String>> convertToList(DataTable dataTable) {
		List<List<String>> resultList = new ArrayList<>();

		// Get column names as the first row
		List<String> headers = dataTable.columnNames();

		// Iterate through each row in the DataTable
		for (int rowIndex = 0; rowIndex < dataTable.rows().size(); rowIndex++) {
			List<String> rowData = new ArrayList<>();
			// Get data for each column in the current row
			for (int colIndex = 0; colIndex < headers.size(); colIndex++) {
				Object value = dataTable.getValue(rowIndex, colIndex);
				rowData.add(value != null ? value.toString() : "");
			}
			resultList.add(rowData);
		}

		return resultList;
	}

}