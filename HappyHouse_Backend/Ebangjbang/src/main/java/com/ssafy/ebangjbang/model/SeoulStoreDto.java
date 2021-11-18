package com.ssafy.ebangjbang.model;

public class SeoulStoreDto {

	private String storeName; // 가게명
	private String classes; // 분류
	private String classDetail1;
	private String classDetail2;
	private String dongName; // 동이름
	private String address;
	private String addressStreet;
	private String lat; // lat
	private String lng; // lng
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getClassDetail1() {
		return classDetail1;
	}
	public void setClassDetail1(String classDetail1) {
		this.classDetail1 = classDetail1;
	}
	public String getClassDetail2() {
		return classDetail2;
	}
	public void setClassDetail2(String classDetail2) {
		this.classDetail2 = classDetail2;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "SeoulStoreDto [storeName=" + storeName + ", classes=" + classes + ", classDetail1=" + classDetail1
				+ ", classDetail2=" + classDetail2 + ", dongName=" + dongName + ", address=" + address
				+ ", addressStreet=" + addressStreet + ", lat=" + lat + ", lng=" + lng + "]";
	}

}
