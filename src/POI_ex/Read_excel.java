package POI_ex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;

public class Read_excel {

	static ArrayList<String> column_names = new ArrayList<String>();

	public LinkedHashMap<Integer, JsonObject> getData_From_Excel() throws IOException {

		JsonObject stud_json = new JsonObject();

		LinkedHashMap<Integer, JsonObject> map = new LinkedHashMap<Integer, JsonObject>();
		Workbook workbook = new XSSFWorkbook("test1.xlsx");
		Sheet sheet = workbook.getSheet("Students");

		Iterator<Row> row_itr = sheet.iterator();
		int key = 0;
		while (row_itr.hasNext()) {
			JsonObject json = new JsonObject();
			Row row = row_itr.next();

			if (row.getRowNum() == 0) 
			{
				for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) 
				{
					column_names.add(row.getCell(k).getStringCellValue());
				}
				System.out.print("cols " + column_names);
			} else if (row.getRowNum() != 0) {

				// key=(int) row.getCell(0).getNumericCellValue();
				for (int i = 0; i < column_names.size(); i++) {
					key = (int) row.getCell(0).getNumericCellValue();
					if (row.getCell(i) != null) 
					{
						if (row.getCell(i).getCellTypeEnum() == CellType.NUMERIC) 
						{
							json.addProperty(column_names.get(i), row.getCell(i).getNumericCellValue());
						} else if (row.getCell(i).getCellTypeEnum() == CellType.STRING) 
						{
							json.addProperty(column_names.get(i), row.getCell(i).getStringCellValue());
						} else if (row.getCell(i).getCellTypeEnum() == CellType.FORMULA) 
						{
							json.addProperty(column_names.get(i), row.getCell(i).getCellFormula());
						}
					}
				}
				map.put(key, json);
			}
		}
		// System.out.println(map);
		return map;
	}
}
