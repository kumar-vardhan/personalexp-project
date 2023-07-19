package com.pet.PersonalExpenditureTrackre.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Users {

	@Id
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_pwd")
	private String userPassword;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Expenditure> exp;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Payment> pay;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Expenditure> getExp() {
		return exp;
	}

	public void setExp(List<Expenditure> exp) {
		this.exp = exp;
	}

	public List<Payment> getPay() {
		return pay;
	}

	public void setPay(List<Payment> pay) {
		this.pay = pay;
	}
	
	
	
}
