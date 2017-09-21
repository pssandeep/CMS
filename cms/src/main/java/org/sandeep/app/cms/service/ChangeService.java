package org.sandeep.app.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sandeep.app.cms.database.DatabaseClass;
import org.sandeep.app.cms.model.Change;

public class ChangeService {
	
	private Map<Long, Change> changes = DatabaseClass.getChanges();
/*	public ChangeService(){
		changes.put(1L, new Change(1L,"header1","desc1","comment1","system1","status1"));
		changes.put(2L, new Change(2L,"header2","desc2","comment2","system2","status2"));
	}*/
	
	public List<Change> getAllChanges(){
		return new ArrayList<Change>(changes.values());
	}
	
	public Change getChange(long id){
		return changes.get(id);
	}
	
	public Change addChange(Change change){
		change.setId(changes.size()+1);
		changes.put(change.getId(),change);
		return change;
	}
	
	public Change updateChange(long id,Change change){
		
		changes.put(id,change);
		return change;
	}	
	public void deleteChange(long id){

		changes.remove(id);

	}
	
}

