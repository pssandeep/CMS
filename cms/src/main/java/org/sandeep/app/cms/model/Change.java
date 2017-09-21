package org.sandeep.app.cms.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Change {
	
	private long id;
	private String header;
	private String description;
	private String system;
	private String status;
	private String creationDate;
	private String implementDate;
	
	
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getImplementDate() {
		return implementDate;
	}
	public void setImplementDate(String implementDate) {
		this.implementDate = implementDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}