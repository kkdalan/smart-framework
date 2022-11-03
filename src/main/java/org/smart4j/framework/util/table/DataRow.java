package org.smart4j.framework.util.table;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DataRow {

    private Map<String, Object> dataMap = new LinkedHashMap<String, Object>();

    /**
     * 在getValue()和setValue()時候，程式碼須透過此成員的欄位名稱來找出Map字典裡的物件
     */
    private DataColumnCollection columns;
    /**
     * 此資料列所屬的DataTable，唯讀
     */
    private DataTable table;

    /**
     * DataRow被建立時，必須指定所屬的DataTable
     * 
     * @param DataRow所屬的DataTable
     */
    public DataRow(DataTable table) {
	this.table = table;
	this.columns = table.columns();
    }

    /**
     * 取得DataRow所屬的DataTable
     * 
     * @return DataTable
     */
    public DataTable getTable() {
	return this.table;
    }

    /**
     * 設定該列該行的值
     * 
     * @param columnindex 行索引(從0算起)
     * @param value       要設定的值
     */
    public void setValue(int columnindex, Object value) {
	setValue(this.columns.get(columnindex), value);
    }

    /**
     * 設定該列該行的值
     * 
     * @param columnName 行名稱
     * @param value      要設定的值
     */
    public void setValue(String columnName, Object value) {
	dataMap.put(columnName.toLowerCase(), value);
    }

    /**
     * 設定該列該行的值
     * 
     * @param column DataColumn物件
     * @param value  要設定的值
     */
    private void setValue(DataColumn column, Object value) {
	if (column != null) {
	    String lowerColumnName = column.getColumnName().toLowerCase();
	    if (dataMap.containsKey(lowerColumnName)) {
		dataMap.remove(lowerColumnName);
	    }
	    dataMap.put(lowerColumnName, value);
	}
    }

    /**
     * 取得該列該行的值
     * 
     * @param columnIndex 行索引(從0算起)
     * @return Object
     */
    public Object getValue(int columnIndex) {
	String columnName = this.columns.get(columnIndex).getColumnName().toLowerCase();// 取得Key
	return dataMap.get(columnName);
    }

    /**
     * 取得該列該行的值
     * 
     * @param columnName 行名稱
     * @return Object
     */
    public Object getValue(String columnName) {
	return dataMap.get(columnName.toLowerCase());// 利用欄名(Key)來取值
    }

    /**
     * 取得該列該行的值
     * 
     * @param column DataColumn物件
     * @return Object
     */
    public Object getValue(DataColumn column) {
	return dataMap.get(column.getColumnName().toLowerCase());// 利用欄名(Key)來取值
    }

    public Set<String> columnNames() {
	return dataMap.keySet();
    }

}