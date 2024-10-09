//package com.demo.online_quiz_system.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//@Entity
//public class Student {
//	@Id
//	private String empId;
//	@Column(length = 20)
//	private String empName;
//	private double salary;
//	@OneToOne
//	@JoinColumn(name = "address_id")
//	private Address address;
//
//
//	public Student() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public Student(String empId, String empName, double salary, Address address) {
//		super();
//		this.empId = empId;
//		this.empName = empName;
//		this.salary = salary;
//		this.address = address;
//	}
//	@Override
//	public String toString() {
//		return "Student [empId=" + empId + ", empName=" + empName + ", salary=" +
//				salary + ", address=" + address + "]";
//	}
//
//}
