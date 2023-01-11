package com.app.employee.download;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.app.employee.model.Employee;
 
public class EmployeeExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Employee> listEmployees;
     
    public EmployeeExcelExporter(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
        workbook = new XSSFWorkbook();
    }
 
	/* Creating the excel sheet consisting of rows and columns */
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Employees");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Employee ID", style);      
        createCell(row, 1, "First Name", style);       
        createCell(row, 2, "Last Name", style);    
        createCell(row, 3, "Email ID", style);
        createCell(row, 4, "Salary", style);
        createCell(row, 5, "Allocated Job", style);
         
    }
    
	/* Creating the cells to write the values */
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } 
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    
	/*
	 * Writing the data from the Employee Database which contains list of employees
	 */
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        
		/* For loop to iterate the number of employees */        
        for (Employee employee : listEmployees) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, employee.getId(), style);
            createCell(row, columnCount++, employee.getempFirstName(), style);
            createCell(row, columnCount++, employee.getempLastName(), style);
            createCell(row, columnCount++, employee.getempEmailID(), style);
            createCell(row, columnCount++, employee.getempSalary(), style);
            createCell(row, columnCount++, employee.getempAllocatedJobs(), style);
             
        }
    }
    
	/* Export the excel file as a HTTP response */ 
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
