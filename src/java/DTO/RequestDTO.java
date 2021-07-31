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
public class RequestDTO {
    private String requestID;
    private String userID;
    private String itemID;
    private float usingTime;
    private Date dateRequest;
    private String statusID;

    public RequestDTO() {
    }

    public RequestDTO(String requestID, String userID, String itemID, float usingTime, Date dateRequest, String statusID) {
	this.requestID = requestID;
	this.userID = userID;
	this.itemID = itemID;
	this.usingTime = usingTime;
	this.dateRequest = dateRequest;
	this.statusID = statusID;
    }

    public String getRequestID() {
	return requestID;
    }

    public void setRequestID(String requestID) {
	this.requestID = requestID;
    }

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }

    public String getItemID() {
	return itemID;
    }

    public void setItemID(String itemID) {
	this.itemID = itemID;
    }

    public float getUsingTime() {
	return usingTime;
    }

    public void setUsingTime(float usingTime) {
	this.usingTime = usingTime;
    }

    public Date getDateRequest() {
	return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
	this.dateRequest = dateRequest;
    }

    public String getStatusID() {
	return statusID;
    }

    public void setStatusID(String statusID) {
	this.statusID = statusID;
    }
    
    
}
