package StarkManagement.View;

import StarkManagement.Model.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class JTableModelEmployee extends AbstractTableModel {
    private static final int COLUMN_ID      = 0;
    private static final int COLUMN_NAME    = 1;
    private static final int COLUMN_FIRSTNAME     = 2;
    private static final int COLUMN_DEPARTMENT     = 3;

    String[] columns = new String[] {
            "Id", "Name", "First Name","Department"
    };
    private ArrayList<Employee> listEmployees;

    public JTableModelEmployee(ArrayList<Employee> list) {
        this.listEmployees = list;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return listEmployees.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (listEmployees.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Employee getValueAt(int rowIndex, int columnIndex) {
        return listEmployees.get(rowIndex);
    }

}
