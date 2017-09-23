package org.sandeep.app.cms.database;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.sandeep.app.cms.model.Change;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseClass {

	private static Map<Long, Change> changes = new HashMap<>();
	private static MongoDatabase database;

	public static Map<Long, Change> getChanges() {
		return changes;
	}

	public static MongoDatabase getMongoDB() {

		// Creating a Mongo client
		@SuppressWarnings("resource")
		MongoClient mongo = new MongoClient("localhost", 27017);

		// Creating Credentials
		MongoCredential credential;
		credential = MongoCredential.createCredential("", "test",
				"".toCharArray());
		System.out.println("Connected to the database successfully");

		// Accessing the database
		database = mongo.getDatabase("test");
		System.out.println("Credentials ::" + credential);

		return database;
	}

	public static void createColletion(String collName) {

		// Creating a collection
		database.createCollection(collName);
		System.out.println("Collection created successfully");
	}

	public static MongoCollection<Document> retrieveCollection(String collName) {
		
		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection(collName);
		System.out.println("Collection myCollection selected successfully");

		return collection;
	}

	public static void addDocument(String collName,Document newDocument) {
		
		//add Document
		MongoCollection<Document> collection = database.getCollection(collName);
		collection.insertOne(newDocument);
		System.out.println("Document inserted successfully");
	}

}
