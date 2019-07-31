package POI_ex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class readnew 
{
	public JsonObject sheetsJsonObject = new JsonObject();
	Workbook workbook=null;
	
	public JsonObject read() throws IOException 
	{	
		workbook = new XSSFWorkbook("test1.xlsx");
	for(
	int i = 0;i<workbook.getNumberOfSheets();i++)
	{
		JsonArray sheetArray = new JsonArray();
		ArrayList<String> columnNames = new ArrayList<String>();
		org.apache.poi.ss.usermodel.Sheet sheet =  workbook.getSheet("Students");
		Iterator<Row> sheetIterator = sheet.iterator();

		while (sheetIterator.hasNext()) {

			Row currentRow = sheetIterator.next();
			JsonObject jsonObject = new JsonObject();

			if (currentRow.getRowNum() != 0) {

				for (int j = 0; j < columnNames.size(); j++) {

					if (currentRow.getCell(j) != null) {
						if (currentRow.getCell(j).getCellTypeEnum() == CellType.STRING) {
							jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getStringCellValue());
						} else if (currentRow.getCell(j).getCellTypeEnum() == CellType.NUMERIC) {
							jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getNumericCellValue());
						}
					} else {
						jsonObject.addProperty(columnNames.get(j), "");
					}
				}

				sheetArray.add(jsonObject);

			} else {
				// store column names
				for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
					columnNames.add(currentRow.getCell(k).getStringCellValue());
				}
			}
		}

		sheetsJsonObject.add(workbook.getSheetName(i), sheetArray);
	}

	return sheetsJsonObject;
	}
}


