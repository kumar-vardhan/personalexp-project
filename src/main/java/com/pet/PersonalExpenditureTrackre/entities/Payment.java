package com.pet.PersonalExpenditureTrackre.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {
	
	@Id
	private int code;
	private String names;
	private String remarks;
	@Column(name="user_name")
	private String userName;
	

	@ManyToOne
	@JoinColumn(name="user_name",referencedColumnName = "user_name",insertable = false,updatable = false)
	@JsonIgnore
	private Users user;

	

	@OneToMany(mappedBy = "payment")
	@JsonIgnore
	private List<Expenditure> e;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Expenditure> getE() {
		return e;
	}

	public void setE(List<Expenditure> e) {
		this.e = e;
	}
	

	
}
