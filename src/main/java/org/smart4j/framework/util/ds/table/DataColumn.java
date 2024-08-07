package org.smart4j.framework.util.ds.table;

public class DataColumn {

    /**
     * DataColumn所屬的DataTable
     */
    private DataTable table;
    /**
     * DataColumn的欄位名稱
     */
    private String columnName; // 欄名，當做DataRow的key

    /**
     * DataColumn被建立時，一定要指定欄名
     * 
     * @param columnName 欄名
     */
    public DataColumn(String columnName) {
	this.columnName = columnName.toLowerCase();
    }

    /**
     * 給DataColumnCollection加入DataColumn時設定所屬的DataTable的方法，同一個package才用到
     * 
     * @param table
     */
    void setTable(DataTable table) {
	this.table = table;
    }

    /**
     * 取得DataColumn所屬的DataTable，唯讀
     * 
     * @return DataTable
     */
    public DataTable getTable() {
	return this.table;
    }

    /**
     * DataColumn物件的toString()，會回傳自己的欄名
     * 
     * @return
     */
    @Override
    public String toString() {
	return this.columnName;
    }

    public String getColumnName() {
	return columnName;
    }

    public void setColumnName(String columnName) {
	this.columnName = columnName;
    }

}