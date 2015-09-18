package com.droidtub.fakecall.model;

public class ContactItem {
	private String name;
	private String number;
	private boolean selected;
	private int hour;
	private int minute;
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	private int second;
	
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
	
	public ContactItem(String name, String number, int hour, int min, int sec){
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