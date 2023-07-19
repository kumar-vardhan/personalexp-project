package com.pet.PersonalExpenditureTrackre.entities;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="expenditures")
public class Expenditure {
	
	@Id
	@Column(name = "exp_id")
	private int expId;
	private double amount;
	private Date spentOn;
	private String descriptions;
	private String remarks;
	private String tags;
	@Column(name="user_name")
	private String userName;
	@Column(name="code")
	private int code;
	@Column(name="cat_code")
	private String catCode;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_name",referencedColumnName = "user_name",insertable = false,updatable = false)
	private Users user;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="cat_code", referencedColumnName = "cat_code",insertable = false,updatable = false)
	private Category category;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="code", referencedColumnName = "code",insertable = false,updatable = false)
	private Payment payment;
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getSpentOn() {
		return spentOn;
	}

	public void setSpentOn(Date spentOn) {
		this.spentOn = spentOn;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

		
	
}
