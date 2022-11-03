package org.smart4j.framework.util.table;

import java.util.ArrayList;
import java.util.List;

public class DataColumnCollection {

    private List<DataColumn> columns = new ArrayList<DataColumn>();

    /**
     * DataColumnCollection所屬的DataTable，唯讀
     */
    private DataTable table;

    /**
     * DataColumnCollection被建立時，一定要指定所屬的DataTable
     * 
     * @param table
     */
    public DataColumnCollection(DataTable table) {
	this.table = table;
    }

    /**
     * 取得DataColumnCollection所屬的DataTable
     * 
     * @return DataTable
     */
    public DataTable getTable() {
	return this.table;
    }

    /**
     * 加入一個DataColumn物件，程式碼會設定該DataColumn的DataTable和呼叫add()方法的DataColumnCollection同一個DataTable
     * 
     * @param column
     */
    public void add(DataColumn column) {
	column.setTable(this.table);
	columns.add(column);
    }

    /**
     * 給欄位名稱 <br/>
     * 加入一個DataColumn物件，程式碼會設定該DataColumn的DataTable和呼叫add()方法的DataColumnCollection同一個DataTable
     * 
     * @param columnName
     * @return
     */
    public DataColumn add(String columnName) {
	DataColumn column = new DataColumn(columnName.toLowerCase());
	column.setTable(this.table);
	columns.add(column);
	return column;
    }

    /**
     * 依據欄名，取得DataColumn
     * 
     * @param columnName 欄名
     * @return DataColumn
     */
    public DataColumn get(String columnName) {
	DataColumn column = null;
	for (DataColumn dataColumn : columns) {
	    if (dataColumn.getColumnName().toLowerCase().equals(columnName.toLowerCase())) {
		return dataColumn;
	    }
	}
	return column;
    }

    public DataColumn get(int columnIndex) {
	return columns.get(columnIndex);
    }

    public List<DataColumn> asList() {
	return columns;
    }

}
