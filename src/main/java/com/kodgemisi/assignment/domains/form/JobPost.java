package com.kodgemisi.assignment.domains.form;

public class JobPost {
	
	private String title;
	private String description;
	private int numOfPerson;
	private String lastDate;
	private int id;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumOfPerson() {
		return numOfPerson;
	}
	public void setNumOfPerson(int numOfPerson) {
		this.numOfPerson = numOfPerson;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
