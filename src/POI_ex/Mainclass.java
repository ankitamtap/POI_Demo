package POI_ex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonObject;

public class Mainclass {

	public static void main(String[] args) throws IOException 
	{
	
		Scanner scan = new Scanner(System.in);
		List<Student> stud_list =  new ArrayList<>();
		LinkedHashMap<Integer, JsonObject> map_stud = new LinkedHashMap<Integer, JsonObject>();
		Db_operations op = new Db_operations();
		
		int ch=1;
		while(ch!=0){
		System.out.println("1 Create excel from DB \n2. Data from excel to db ");
		ch = scan.nextInt();
		
		if(ch==1) 
		{
		stud_list= op.Getdata("class5");
		Reports reports = new Reports();
		reports.getreport(stud_list);
		System.out.println("Excel sheet created..");
		}
		else if(ch==2) 
		{
		Read_excel read = new Read_excel();
		map_stud =  read.getData_From_Excel();
		op.AddData(map_stud);
		System.out.println(("Data added to DB"));
		}
		else
			System.out.println("No such operations ");
		}
	}
}
