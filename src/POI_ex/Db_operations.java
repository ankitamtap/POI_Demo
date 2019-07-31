package POI_ex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;

import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class Db_operations 
{
	MongoClient mongoclient = new MongoClient("localhost", 27017);
	MongoDatabase database = mongoclient.getDatabase("school");
	MongoCollection<Document> collection = null;
	int rollno = 1;
	public List<Student> stud_list = new ArrayList<>();
	
	public List<Student> Getdata(String str) 
	{
		BasicDBObject query = new BasicDBObject();
		query.put("Name", -1);
		String fields = "{_id:0}";
		Bson bson = BasicDBObject.parse(fields);

		collection = database.getCollection("class5");
		MongoCursor<Document> curcor = collection.find().projection(bson).sort(query).iterator();
		Document doc = new Document();

		while (curcor.hasNext()) {
			doc = curcor.next();
			int biology = 0, maths = 0;

			if (doc.getInteger("biology") != null) {
				biology = doc.getInteger("biology");
			} else if (doc.getInteger("maths") != null) {
				maths = doc.getInteger("maths");
			}
			
			stud_list.add(new Student(rollno++, doc.getString("Name"), doc.getInteger("physics"),
					doc.getInteger("chemistry"), doc.getInteger("english"), doc.getInteger("hindi"), maths, biology));
		}
		return stud_list;
	}

	public void AddData(LinkedHashMap<Integer, JsonObject> map_stud) 
	{
		ArrayList<String> cols = new ArrayList<String>();
		cols = Read_excel.column_names;
		System.out.print("in add "+cols);
		collection = database.getCollection("DataFromExcel");
		List<Document> list = new ArrayList<Document>();

		for (Map.Entry m : map_stud.entrySet()) 
		{
			int key = (int) m.getKey();
			JsonObject j = new JsonObject();
			j = map_stud.get(key);
			Document doc = Document.parse(j.toString());
			collection.insertOne(doc);
		}

	}
}