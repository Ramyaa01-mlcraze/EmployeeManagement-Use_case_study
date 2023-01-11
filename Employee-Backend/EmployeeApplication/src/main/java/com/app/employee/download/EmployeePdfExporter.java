package com.app.employee.download;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import com.app.employee.model.Employee;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 
/*PDF exporter class*/ 
public class EmployeePdfExporter {
    private List<Employee> listEmployees;
     
    public EmployeePdfExporter(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }
    
	/* Write the Employee PDF Table Header */
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.YELLOW);
        cell.setPadding(6);
        
		/* Set the font style helvetica */
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
        
		/* Setting the cell with the specific header value */
        cell.setPhrase(new Phrase("Employee ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email ID", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Salary", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Allocated Job", font));
        table.addCell(cell);
    }
    
	/* Write the data by iterating the list of employees through For loop */
    private void writeTableData(PdfPTable table) {
        for (Employee employee : listEmployees) {
            table.addCell(String.valueOf(employee.getId()));
            table.addCell(employee.getempFirstName());
            table.addCell(employee.getempLastName());
            table.addCell(employee.getempEmailID());
            table.addCell(employee.getempSalary());
            table.addCell(employee.getempAllocatedJobs());
        }
    }
    
	/* Export the File using HTTP response */ 
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        
		/* Open the PDF file to write the data */
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
         
        Paragraph p = new Paragraph("List of Employees", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        
		/* Add the heading of the file */
        document.add(p);
        
		/* Create a table with 6 columns */
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.0f, 2.5f, 2.5f, 3.2f, 1.5f, 2.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}