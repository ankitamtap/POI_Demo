package POI_ex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reports {

	String[] columns = { "Rollno", "Name", "Physics", "Chemistry", "English", "Hindi", "Math", "Biology", "Average"};

	public void getreport(List<Student> stud_list) throws IOException {
		FileOutputStream fileOut = new FileOutputStream("test1.xlsx");
		Workbook workbook = new XSSFWorkbook();

		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Students");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

		CellStyle cellstyle = workbook.createCellStyle();
		cellstyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);

			int rowNum = 1;
			for (Student s : stud_list) {
				Row row = sheet.createRow(rowNum++);
				String sumFormula = String.format("AVERAGE(C%s:H%s)", rowNum, rowNum);
				
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(s.getRollno());
				cell0.setCellStyle(cellstyle);

				Cell cell1 = row.createCell(1);
				cell1.setCellValue(s.getName());
				cell1.setCellStyle(cellstyle);

				Cell cell2 = row.createCell(2);
				cell2.setCellValue(s.getPhysics());
				cell2.setCellStyle(cellstyle);

				Cell cell3 = row.createCell(3);
				cell3.setCellValue(s.getChemistry());
				cell3.setCellStyle(cellstyle);

				Cell cell4 = row.createCell(4);
				cell4.setCellValue(s.getEnglish());
				cell4.setCellStyle(cellstyle);

				Cell cell5 = row.createCell(5);
				cell5.setCellValue(s.getHindi());
				cell5.setCellStyle(cellstyle);

				Cell cell6 = row.createCell(6);
				cell6.setCellValue(s.getMaths());
				cell6.setCellStyle(cellstyle);
				
				Cell cell7 = row.createCell(7);
				cell7.setCellValue(s.getBiology());
				cell7.setCellStyle(cellstyle);
				
				Cell cell8 = row.createCell(8);
				cell8.setCellValue("");
				cell8.setCellFormula(sumFormula);
				
			}
		}

//		Row arow = sheet.createRow(15);
//		Cell acell = arow.createCell(4);
//		acell.setCellValue("abg");
//		acell.setCellFormula();
		
		for (int i1 = 0; i1 < columns.length; i1++) 
		{
			sheet.autoSizeColumn(i1);
		}

		
		workbook.write(fileOut);
		
		fileOut.close();
		 workbook.close();
	}

}
