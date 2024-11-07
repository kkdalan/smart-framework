package org.smart4j.framework.util.ds.cube;

import org.smart4j.framework.util.ds.table.DataRow;
import org.smart4j.framework.util.ds.table.DataTable;

public class DataCubeTest {

	public static void main(String[] args) {
		
		// Create a DataCube
		DataCube dataCube = new DataCube();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Define the first layer with a DataTable and columns
		DataLayer layer1 = dataCube.newLayer("Layer1");
		DataTable dt1 = layer1.getLayerData();
		dt1.columns().add("CustomerID");
		dt1.columns().add("CustomerName");
		dt1.columns().add("Address");

		// Add data to the first layer
		DataRow dr1 = dt1.newRow();
		dr1.setValue("CustomerID", 1);
		dr1.setValue("CustomerName", "Shadow");
		dr1.setValue("Address", "點部落格");
		dt1.rows().add(dr1);

		dr1 = dt1.newRow();
		dr1.setValue(0, 2);
		dr1.setValue(1, "Super Man");
		dr1.setValue(2, "U.S.A");
		dt1.rows().add(dr1);

		DataRow r1 = new DataRow(dt1);
		dt1.rows().add(r1);
		dt1.setValue(2, "CustomerID", 3);
		dt1.setValue(2, "CustomerName", "Java");
		dt1.setValue(2, "Address", "unknown");

		DataRow r2 = new DataRow(dt1);
		dt1.rows().add(r2);
		dt1.setValue(3, 0, 4);
		dt1.setValue(3, 1, "Microsoft");
		dt1.setValue(3, 2, "U.S.A");

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Define the second layer with a DataTable and columns
		DataLayer layer2 = dataCube.newLayer("Layer2");
		DataTable dt2 = layer2.getLayerData();
		dt2.columns().add("ProductID");
		dt2.columns().add("ProductName");
		dt2.columns().add("Price");
		
		// Add data to the second layer
		DataRow dr2 = dt2.newRow();
		dr2.setValue("ProductID", 101);
		dr2.setValue("ProductName", "Laptop");
		dr2.setValue("Price", 1000);
		dt2.rows().add(dr2);

		dr2 = dt2.newRow();
		dr2.setValue(0, 102);
		dr2.setValue(1, "Smartphone");
		dr2.setValue(2, 700);
		dt2.rows().add(dr2);

		DataRow r3 = new DataRow(dt2);
		dt2.rows().add(r3);
		dt2.setValue(2, "ProductID", 103);
		dt2.setValue(2, "ProductName", "Tablet");
		dt2.setValue(2, "Price", 500);

		DataRow r4 = new DataRow(dt2);
		dt2.rows().add(r4);
		dt2.setValue(3, 0, 104);
		dt2.setValue(3, 1, "Monitor");
		dt2.setValue(3, 2, 300);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Print the DataCube
		dataCube.print();
	}
}
