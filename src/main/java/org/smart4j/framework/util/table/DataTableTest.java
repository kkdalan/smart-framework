package org.smart4j.framework.util.table;

public class DataTableTest {

    public static void main(String[] args) {
	
	DataTable dt = new DataTable("測試Table");
	// 定義三個欄位
	dt.columns().add("CustomerID");
	dt.columns().add("CustomerName");
	dt.columns().add(new DataColumn("Address"));

	// 產生第一列，用欄名設值
	DataRow dr = dt.newRow();
	dr.setValue("CustomerID", 1);
	dr.setValue("CustomerName", "Shadow");
	dr.setValue("Address", "點部落格");
	dt.rows().add(dr);// 加入至DataRowCollection

	// 產生第二列，用columnIndex設值
	dr = dt.newRow();
	dr.setValue(0, 2);
	dr.setValue(1, "Super Man");
	dr.setValue(2, "U.S.A");
	dt.rows().add(dr);// 加入至DataRowCollection

	// 直接用DataTable+欄名 設定值
	DataRow r = new DataRow(dt);
	dt.rows().add(r);
	dt.setValue(2, "CustomerID", 3);
	dt.setValue(2, "CustomerName", "Java");
	dt.setValue(2, "Address", "unknown");

	// 直接用DataTable+columnIndex 設定值
	DataRow r2 = new DataRow(dt);
	dt.rows().add(r2);
	dt.setValue(3, 0, 4);
	dt.setValue(3, 1, "Microsoft");
	dt.setValue(3, 2, "U.S.A");

	// 印出表格
	dt.print();
    }
}
