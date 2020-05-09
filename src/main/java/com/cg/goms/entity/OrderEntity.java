package com.cg.goms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="great_outdoor_order_table")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="orderId")
	private String orderId;
	
	@Column(name="userId")
	private String userId;
	
    @Column(name = "productId")
	private String productId;
    
    @Column(name="price")
    private double price;
    
    @Column(name="paymentType")
    private String paymentType;
    
    @Column(name="quantity")
    private long itemQuantity;
    

	@Column(name="addressId")
	private String addressId;
	
	@Column(name="dispatchStatus")
	private int dispatchStatus;
	
	@Column(name="orderInitiateTime", updatable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate orderInitiateTime; 
	
	@Column(name="orderDispatchTime")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate orderDispatchTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public int getDispatchStatus() {
		return dispatchStatus;
	}
	public void setDispatchStatus(int dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}
	public LocalDate getOrderInitiateTime() {
		return orderInitiateTime;
	}
	public void setOrderInitiateTime(LocalDate orderInitiateTime) {
		this.orderInitiateTime = orderInitiateTime;
	}
	public LocalDate getOrderDispatchTime() {
		return orderDispatchTime;
	}
	public void setOrderDispatchTime(LocalDate orderDispatchTime) {
		this.orderDispatchTime = orderDispatchTime;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public long getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	

}
