//package com.demo.online_quiz_system.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Entity
//public class Address {
//	@Id
//	private int addressId;
//	@Column(length = 50)
//	private String street;
//	@Column(length = 10)
//	private String city;
//	@Column(length = 10)
//	private String state;
//	@Column(length = 10)
//	private String zip;
//	public Address(int addressId, String street, String city, String state, String zip) {
//		super();
//		this.addressId = addressId;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.zip = zip;
//	}
//	@Override
//	public String toString() {
//		return "Address [street=" + street + ", city=" + city + ", state=" + state +", zip=" + zip + "]";
//	}
//	public Address() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//}