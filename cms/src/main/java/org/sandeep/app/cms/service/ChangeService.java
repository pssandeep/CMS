package org.sandeep.app.cms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.sandeep.app.cms.database.DatabaseClass;
import org.sandeep.app.cms.model.Change;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ChangeService {

	private MongoDatabase db = DatabaseClass.getMongoDB();
	
	public List<Change> getAllChanges() {
		
		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("sampleCollection");
	      // Getting the iterable object 
	      FindIterable<Document> iterDoc = dbDoc.find(); 
	      int i = 1; 

	      // Getting the iterator 
	      Iterator it = iterDoc.iterator(); 
	      Document doc = new Document();
	      List<Change> allChange = new ArrayList<>();
	      while (it.hasNext()) {  
	    	 doc = (Document) it.next();
	    	 Change tempChange = new Change();
	    	 tempChange.setCreationDate(doc.getString("creationDate"));
	    	 tempChange.setDescription(doc.getString("description"));
	    	 tempChange.setHeader(doc.getString("header"));
	    	 tempChange.setImplementDate(doc.getString("implementDate"));
	    	 tempChange.setStatus(doc.getString("status"));
	    	 tempChange.setSystem(doc.getString("system"));
	    	 tempChange.setId(doc.getString("id"));
	    	 allChange.add(tempChange);
	    	 
	         System.out.println("DOCUMENT: " +doc.getString("creationDate"));  
	      i++; 
	      }	
		return new ArrayList<Change>(allChange);
	}

//	public Change getChange(long id) {
//		return changes.get(id);
//	}

	public Change addChange(Change change) {

		MongoCollection<Document> dbDoc = DatabaseClass
				.retrieveCollection("sampleCollection");
	      long countOfColl = dbDoc.count(); 	
	  
//		change.setId(changes.size() + 1);
//		changes.put(change.getId(), change);
		
//	    {
//	        "creationDate": "2017-09-19",
//	        "description": "Change to Payment Stored Procedure to update the status of the payment that is set. This involves updaing the stored procedure at the database level and application code.",
//	        "header": "Change to Payment Stored Procedure",
//	        "implementDate": "2017-09-22",
//	        "status": "New",
//	        "system": "DEVON Trade Settlement  System"
//	    }		
		Document document = new Document("creationDate", change.getCreationDate())
				.append("description", change.getDescription())
				.append("header", change.getHeader())
				.append("implementDate", change.getImplementDate())
				.append("status", change.getStatus())
				.append("system", change.getSystem())
				.append("id", Long.toString(countOfColl+1));
		
		DatabaseClass.addDocument(dbDoc, document);
		return change;
	}
//
//	public Change updateChange(long id, Change change) {
//
//		changes.put(id, change);
//		return change;
//	}
//
//	public void deleteChange(long id) {
//
//		changes.remove(id);
//
//	}

}
