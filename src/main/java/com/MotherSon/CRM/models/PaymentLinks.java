package com.MotherSon.CRM.models;
 
import java.time.LocalDateTime;
 
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;

import jakarta.persistence.PrePersist;

import jakarta.persistence.PreUpdate;

import jakarta.persistence.Table;
 
@Entity

@Table(name = "payment_links_master")

public class PaymentLinks {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@OneToOne(fetch = FetchType.EAGER)

	@JoinColumn(name = "bookings_id")

	private Bookings bookings;

	@Column(name = "payment_link")

	private String paymentLink;

	private String ipaddress;

	private boolean status= true;

	private boolean isdelete = false;

	@Column(name = "created_by")

	private String createdby;

	@Column(name = "modified_by")

	private String modifiedby;

	@Column(name = "created_date")

	private LocalDateTime createdDate;

	@Column(name = "modified_date")

	private LocalDateTime modifiedDate;


	@PrePersist

    protected void onCreate() {

        createdDate = LocalDateTime.now();

        modifiedDate = LocalDateTime.now();

	}


	 @PreUpdate

	    protected void onUpdate() {

	        modifiedDate = LocalDateTime.now();

	    }
 
 
	public Long getId() {

		return id;

	}
 
 
	public void setId(Long id) {

		this.id = id;

	}
 
 
	public Bookings getBookings() {

		return bookings;

	}
 
 
	public void setBookings(Bookings bookings) {

		this.bookings = bookings;

	}
 
 
	public String getPaymentLink() {

		return paymentLink;

	}
 
 
	public void setPaymentLink(String paymentLink) {

		this.paymentLink = paymentLink;

	}
 
 
	public String getIpaddress() {

		return ipaddress;

	}
 
 
	public void setIpaddress(String ipaddress) {

		this.ipaddress = ipaddress;

	}
 
 
	public boolean isStatus() {

		return status;

	}
 
 
	public void setStatus(boolean status) {

		this.status = status;

	}
 
 
	public boolean isIsdelete() {

		return isdelete;

	}
 
 
	public void setIsdelete(boolean isdelete) {

		this.isdelete = isdelete;

	}
 
 
	public String getCreatedby() {

		return createdby;

	}
 
 
	public void setCreatedby(String createdby) {

		this.createdby = createdby;

	}
 
 
	public String getModifiedby() {

		return modifiedby;

	}
 
 
	public void setModifiedby(String modifiedby) {

		this.modifiedby = modifiedby;

	}
 
 
	public LocalDateTime getCreatedDate() {

		return createdDate;

	}
 
 
	public void setCreatedDate(LocalDateTime createdDate) {

		this.createdDate = createdDate;

	}
 
 
	public LocalDateTime getModifiedDate() {

		return modifiedDate;

	}
 
 
	public void setModifiedDate(LocalDateTime modifiedDate) {

		this.modifiedDate = modifiedDate;

	}


}