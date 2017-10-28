package org.sandeep.app.cms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.sandeep.app.cms.database.DatabaseClass;
import org.sandeep.app.cms.model.Change;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

public class ChangeService {

	private MongoDatabase db = DatabaseClass.getMongoDB();

	public List<Change> getAllChanges() {

		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("ChangeRequests");
		// Getting the iterable object
		FindIterable<Document> iterDoc = dbDoc.find();

		// Getting the iterator
		Iterator it = iterDoc.iterator();
		Document doc = new Document();
		List<Change> allChange = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		while (it.hasNext()) {
			doc = (Document) it.next();
			// Change tempChange = new Change();
			// tempChange.setCreationDate(doc.getString("creationDate"));
			// tempChange.setDescription(doc.getString("description"));
			// tempChange.setHeader(doc.getString("header"));
			// tempChange.setImplementDate(doc.getString("implementDate"));
			// tempChange.setStatus(doc.getString("status"));
			// tempChange.setSystem(doc.getString("system"));
			// tempChange.setId(doc.getString("id"));
			// allChange.add(tempChange);

			System.out.println("DOCUMENT: " + doc.toJson());
			Change tempChange = null;
			try {
				// tempChange = mapper.readValue(doc.toString().substring(8),
				// Change.class);
				tempChange = mapper.readValue(doc.toJson(), Change.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			allChange.add(tempChange);

		}
		return new ArrayList<Change>(allChange);
	}

	public List<Change> getChange(String id) {
		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("ChangeRequests");

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);

		// Getting the iterable object
		FindIterable<Document> iterDoc = dbDoc.find(whereQuery);

		// Getting the iterator
		Iterator it = iterDoc.iterator();
		Document doc = new Document();
		List<Change> allChange = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		while (it.hasNext()) {
			doc = (Document) it.next();
			System.out.println("DOCUMENT: " + doc.toJson());
			Change tempChange = null;
			try {
				// tempChange = mapper.readValue(doc.toString().substring(8),
				// Change.class);
				tempChange = mapper.readValue(doc.toJson(), Change.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			allChange.add(tempChange);

		}
		return new ArrayList<Change>(allChange);
	}

	public Change addChange(Change change) {

		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("ChangeRequests");
		long countOfColl = dbDoc.count();

		// {
		// "creationDate": "2017-09-19",
		// "description":
		// "Change to Payment Stored Procedure to update the status of the payment that is set. This involves updaing the stored procedure at the database level and application code.",
		// "header": "Change to Payment Stored Procedure",
		// "implementDate": "2017-09-22",
		// "status": "New",
		// "system": "DEVON Trade Settlement  System"
		// }
		Document document = new Document("creationDate",
				change.getCreationDate())
				.append("description", change.getDescription())
				.append("header", change.getHeader())
				.append("implementDate", change.getImplementDate())
				.append("status", change.getStatus())
				.append("system", change.getSystem())
				.append("id", getNextID());

		DatabaseClass.addDocument(dbDoc, document);
		return change;
	}

	public Change updateChange(String id, Change change) {

		// Retrieve Collections
		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("ChangeRequests");

		Document document = new Document("creationDate",
				change.getCreationDate())
				.append("description", change.getDescription())
				.append("header", change.getHeader())
				.append("implementDate", change.getImplementDate())
				.append("status", change.getStatus())
				.append("system", change.getSystem()).append("id", id);

		// Update Collections
		DatabaseClass.updateDocument(dbDoc, id, document);
		return change;
	}

	public void deleteChange(String id) {

		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("ChangeRequests");

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);

		// Getting the iterable object
		DeleteResult iterDoc = dbDoc.deleteOne(whereQuery);
		System.out.println(iterDoc.getDeletedCount() + " document(s) deleted.");

	}

	public String getNextID() {

		String nextID = "";
		String newID = "";
		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("ChangeRequestIDs");

		// Getting the iterable object
		FindIterable<Document> iterDoc = dbDoc.find();

		// Getting the iterator
		Iterator it = iterDoc.iterator();
		Document doc = new Document();
		doc = (Document) it.next();
		nextID = doc.getString("nextID");
		System.out.println("DOCUMENT SEQ ID " + nextID);
		newID = String.valueOf(Integer.parseInt(nextID) + 1);
		Document documentNew = new Document("nextID", newID);
		System.out.println("DOCUMENT NEW SEQ ID " + documentNew);
		// Update Collections
		dbDoc.replaceOne((Filters.eq("nextID", nextID)), documentNew);  

		return nextID;

	}

}
