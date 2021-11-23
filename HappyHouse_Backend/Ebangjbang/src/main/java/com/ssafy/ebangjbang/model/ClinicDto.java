package com.ssafy.ebangjbang.model;

public class ClinicDto {
	private String date;
	private String sidoName;
	private String gugunName;
	private String name;
	private String address;
	private String weekday;
	private String saturday;
	private String sunday;
	private String tele1;
	private String officialName;
	private String tele2;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public String getTele1() {
		return tele1;
	}
	public void setTele1(String tele1) {
		this.tele1 = tele1;
	}
	public String getOfficialName() {
		return officialName;
	}
	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}
	public String getTele2() {
		return tele2;
	}
	public void setTele2(String tele2) {
		this.tele2 = tele2;
	}
	@Override
	public String toString() {
		return "ClinicDto [date=" + date + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", name=" + name
				+ ", address=" + address + ", weekday=" + weekday + ", saturday=" + saturday + ", sunday=" + sunday
				+ ", tele1=" + tele1 + ", officialName=" + officialName + ", tele2=" + tele2 + "]";
	}

	
}
