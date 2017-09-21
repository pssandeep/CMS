package org.sandeep.app.cms.database;

import java.util.HashMap;
import java.util.Map;

import org.sandeep.app.cms.model.Change;



public class DatabaseClass {
	
	private static Map<Long, Change> changes = new HashMap<>();
	
	public static Map<Long, Change> getChanges(){
		return changes;
	}

}
