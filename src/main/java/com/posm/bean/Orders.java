package com.posm.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Orders {
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date orderTime;
	private double total;
	private Integer state;
	private Integer uid;
	private Integer aid;
	private String coding;
	private Address address;
	public String getCoding() {
		return coding;
	}
	public void setCoding(String coding) {
		this.coding = coding;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderTime=" + orderTime + ", total=" + total + ", state=" + state + ", uid="
				+ uid + ", aid=" + aid + ", coding=" + coding + ", address=" + address + "]";
	}
	
}
