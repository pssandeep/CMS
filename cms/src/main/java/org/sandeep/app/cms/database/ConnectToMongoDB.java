package org.sandeep.app.cms.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  

import org.bson.Document;

public class ConnectToMongoDB { 
   
@SuppressWarnings("resource")
public static void main(String args[]) {  
      
      // Creating a Mongo client 
      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
   
      // Creating Credentials 
      MongoCredential credential; 
      credential = MongoCredential.createCredential("", "test", 
         "".toCharArray()); 
      System.out.println("Connected to the database successfully");  
      
      // Accessing the database 
      MongoDatabase database = mongo.getDatabase("test"); 
      System.out.println("Credentials ::"+ credential);    
      
      //Creating a collection 
      database.createCollection("sampleCollection2"); 
      System.out.println("Collection created successfully"); 
      
      // Retrieving a collection
      MongoCollection<Document> collection = database.getCollection("sampleCollection2"); 
      System.out.println("Collection myCollection selected successfully"); 
      
      Document document = new Document("title", "MongoDB") 
      .append("id", 1)
      .append("description", "database") 
      .append("likes", 100) 
      .append("url", "http://www.tutorialspoint.com/mongodb/") 
      .append("by", "tutorials point");  
      collection.insertOne(document); 
      System.out.println("Document inserted successfully");  
      

   } 
}