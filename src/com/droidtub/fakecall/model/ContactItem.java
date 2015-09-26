package com.droidtub.fakecall.model;

public class ContactItem {
	private String name;
	private String number;
	private boolean selected;
	private String hour;
	private String minute;
	private String second;
	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	
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
	
	public ContactItem(String name, String number, String hour, String min, String sec){
		this.name = name;
		this.number = number;
		this.hour = hour;
		this.minute = min;
		this.second = sec;
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