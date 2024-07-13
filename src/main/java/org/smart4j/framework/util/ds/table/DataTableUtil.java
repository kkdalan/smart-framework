package org.smart4j.framework.util.ds.table;
import java.util.ArrayList;
import java.util.List;

public class DataTableUtil {

    public static List<List<String>> convertDataTableToList(DataTable dataTable) {
        List<List<String>> resultList = new ArrayList<>();

        // Get column names as the first row
        List<String> headers = dataTable.columnNames();
        resultList.add(headers);

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

    // Example usage
    public static void main(String[] args) {
        DataTable dt = new DataTable("Example Table");
        dt.columns().add("ID");
        dt.columns().add("Name");
        dt.columns().add("Age");

        DataRow row1 = dt.newRow();
        row1.setValue("ID", 1);
        row1.setValue("Name", "John");
        row1.setValue("Age", 30);
        dt.rows().add(row1);

        DataRow row2 = dt.newRow();
        row2.setValue("ID", 2);
        row2.setValue("Name", "Jane");
        row2.setValue("Age", 25);
        dt.rows().add(row2);

        List<List<String>> result = convertDataTableToList(dt);

        // Print the converted result
        for (List<String> row : result) {
            System.out.println(row);
        }
    }
}
