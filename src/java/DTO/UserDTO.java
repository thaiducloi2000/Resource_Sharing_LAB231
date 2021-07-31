/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class UserDTO {
    private String userID;
    private String userName;
    private String password;
    private String gmail;
    private String phoneNumber;
    private String address;
    private Date createDate;
    private String role;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(String userID, String userName, String password,String gmail, String phoneNumber, String address, Date createDate,String role,boolean status) {
	this.userID = userID;
	this.userName = userName;
	this.password = password;
	this.phoneNumber = phoneNumber;
	this.address = address;
	this.createDate = createDate;
	this.role=role;
	this.status=status;
	this.gmail=gmail;
    }

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public Date getCreateDate() {
	return createDate;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public boolean isStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }

    public String getGmail() {
	return gmail;
    }

    public void setGmail(String gmail) {
	this.gmail = gmail;
    }
    
    
}
