/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBUtils;
import DTO.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ItemDAO {

    public List<ItemDTO> getListItems(String search) throws SQLException {
	List<ItemDTO> list = null;
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "SELECT I.itemID,I.itemName,I.color,C.cateName,I.usingDate "
			+ " FROM tblItems I,tblCategory C "
			+ "WHERE (I.itemName LIKE '%" + search + "%' OR C.cateName Like '%" + search + "%' OR I.usingDate Like '%" + search + "%') AND C.cateID=I.cateID";
		stm = conn.prepareStatement(sql);

		rs = stm.executeQuery();
		while (rs.next()) {
		    String itemID = rs.getString("itemID");
		    String itemName = rs.getString("itemName");
		    String color = rs.getString("color");
		    String cateName = rs.getString("cateName");
		    float usingDate = rs.getFloat("usingDate");
		    if (list == null) {
			list = new ArrayList<>();
		    }
		    list.add(new ItemDTO(itemID, itemName, color, cateName, usingDate));
		}
	    }
	} catch (Exception e) {
	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (stm != null) {
		stm.close();
	    }
	    if (conn != null) {
		conn.close();
	    }
	}
	return list;
    }
        

    public List<ItemDTO> getListItem(List<ItemDTO> list, int page) {
	List<ItemDTO> listPage = null;
	for (int i = (page*3-3); i < (page*3); i++) {
	    if (list.size() > i) {
		String itemID = list.get(i).getItemID();
		String itemName = list.get(i).getItemName();
		String color = list.get(i).getColor();
		String cateName = list.get(i).getCateName();
		float usingDate = list.get(i).getUsingDate();
		if(listPage==null){
		    listPage=new ArrayList<>();
		}
		listPage.add(new ItemDTO(itemID, itemName, color, cateName, usingDate));
	    }
	}
	return listPage;
    }
}
