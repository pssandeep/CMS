package org.sandeep.app.cms.database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  

import org.bson.Document;
import org.sandeep.app.cms.model.Change;

public class ConnectToMongoDB { 
   
@SuppressWarnings("resource")
public static void main(String args[]) {  
      
      // Creating a Mongo client 
      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
   
      // Creating Credentials 
//      MongoCredential credential; 
//      credential = MongoCredential.createCredential("", "test", 
//         "".toCharArray()); 
//      System.out.println("Connected to the database successfully");  
      
      // Accessing the database 
      MongoDatabase database = mongo.getDatabase("test"); 
//      System.out.println("Credentials ::"+ credential);    
      
      //Creating a collection 
//      database.createCollection("sampleCollection2"); 
//      System.out.println("Collection created successfully"); 
      
      // Retrieving a collection
      MongoCollection<Document> collection = database.getCollection("sampleCollection"); 
      System.out.println("Collection myCollection selected successfully" + collection); 
      
//      Document document = new Document("title", "MongoDB") 
//      .append("id", 1)
//      .append("description", "database") 
//      .append("likes", 100) 
//      .append("url", "http://www.tutorialspoint.com/mongodb/") 
//      .append("by", "tutorials point");  
//      collection.insertOne(document); 
//      System.out.println("Document inserted successfully");  
      
//	    {
//      "creationDate": "2017-09-19",
//      "description": "Change to Payment Stored Procedure to update the status of the payment that is set. This involves updaing the stored procedure at the database level and application code.",
//      "header": "Change to Payment Stored Procedure",
//      "implementDate": "2017-09-22",
//      "status": "New",
//      "system": "DEVON Trade Settlement  System"
//  }	      
      // Getting the iterable object 
      FindIterable<Document> iterDoc = collection.find(); 

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
    	 allChange.add(tempChange);
      }
      

   } 
}