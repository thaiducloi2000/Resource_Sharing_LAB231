/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author PC
 */
public class ItemDTO {
    private String itemID;
    private String itemName;
    private String color;
    private float usingDate;
    private String cateName;

    public ItemDTO() {
    }

    public ItemDTO(String itemID, String itemName, String color, String cateName,float usingDate) {
	this.itemID = itemID;
	this.itemName = itemName;
	this.color = color;
	this.cateName = cateName;
	this.usingDate=usingDate;
    }

    public String getItemID() {
	return itemID;
    }

    public void setItemID(String itemID) {
	this.itemID = itemID;
    }

    public String getItemName() {
	return itemName;
    }

    public void setItemName(String itemName) {
	this.itemName = itemName;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public String getCateID() {
	return cateName;
    }

    public void setCateID(String cateID) {
	this.cateName = cateID;
    }

    public float getUsingDate() {
	return usingDate;
    }

    public void setUsingDate(float usingDate) {
	this.usingDate = usingDate;
    }

    public String getCateName() {
	return cateName;
    }

    public void setCateName(String cateName) {
	this.cateName = cateName;
    }
    
    
}
