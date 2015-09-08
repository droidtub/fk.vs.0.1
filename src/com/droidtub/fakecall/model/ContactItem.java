package com.droidtub.fakecall.model;

public class ContactItem {
	private String name;
	private String number;
	private boolean selected;
	
	public ContactItem(){
		
	}
	
	public ContactItem(String name, String number){
		this.name = name;
		this.number = number;
	}
	
	public ContactItem(String name, String number, boolean selected){
		this.name = name;
		this.number = number;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
