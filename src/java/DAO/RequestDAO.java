/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBUtils;
import DTO.RequestDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author PC
 */
public class RequestDAO {

    public String CreateID() {
	Random rnd = new Random();
	int number = rnd.nextInt(999999);
	return String.format("%06d", number);
    }

    public boolean addNewRequest(RequestDTO request) throws SQLException {
	boolean check = false;
	Connection conn = null;
	PreparedStatement stm = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "INSERT INTO tblRequests "
			+ " Values (?,?,?,?,?,?)";
		stm = conn.prepareStatement(sql);
		stm.setString(1, request.getRequestID());
		stm.setString(2, request.getUserID());
		stm.setString(3, request.getItemID());
		stm.setFloat(4, request.getUsingTime());
		stm.setDate(5, request.getDateRequest());
		stm.setString(6, "N");
		check = stm.executeUpdate() > 0 ? true : false;
	    }
	} catch (Exception e) {
	} finally {
	    if (stm != null) {
		stm.close();
	    }
	    if (conn != null) {
		conn.close();
	    }
	}
	return check;
    }

    public List<RequestDTO> getAllRequest() throws SQLException {
	List<RequestDTO> list = null;
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "SELECT R.requestID,U.userName,I.itemName,R.usingTime,R.dateRequest,S.status "
			+ " FROM tblItems I, tblRequests R,tblUser U,tblStatus S "
			+ " WHERE U.userID=R.userID AND I.itemID=R.itemID AND R.statusID = S.statusID AND R.statusID='N'";
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while (rs.next()) {
		    String requestID = rs.getString("requestID");
		    String userName = rs.getString("userName");
		    String itemName = rs.getString("itemName");
		    float usingTime = Float.parseFloat(rs.getString("usingTime"));
		    Date dateRequest = rs.getDate("dateRequest");
		    String status = rs.getString("status");
		    if (list == null) {
			list = new ArrayList<>();
		    }
		    list.add(new RequestDTO(requestID, userName, itemName, usingTime, dateRequest, status));
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

    public List<RequestDTO> getListRequest(String userID,Date dateRequest) throws SQLException {
	List<RequestDTO> list = null;
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "SELECT R.requestID,U.userName,I.itemName,R.usingTime,R.dateRequest,S.status "
			+ " FROM tblItems I, tblRequests R,tblUser U,tblStatus S "
			+ " WHERE U.userID=R.userID AND I.itemID=R.itemID AND R.statusID = S.statusID AND "
			+ " R.dateRequest='"+dateRequest+"' AND R.userID='" + userID + "'"
			+ " AND (R.statusID='N' OR R.statusID='A' OR R.statusID='R')";
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while (rs.next()) {
		    String requestID = rs.getString("requestID");
		    String userName = rs.getString("userName");
		    String itemName = rs.getString("itemName");
		    float usingTime = Float.parseFloat(rs.getString("usingTime"));
//		    Date dateRequest = rs.getDate("dateRequest");
		    String status = rs.getString("status");
		    if(status=="New"){
			status="Waiting";
		    }
		    if (list == null) {
			list = new ArrayList<>();
		    }
		    list.add(new RequestDTO(requestID, userName, itemName, usingTime, dateRequest, status));
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

    public List<RequestDTO> getListBySearch(String itemID,String status) throws SQLException {
	List<RequestDTO> list = null;
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "SELECT R.requestID,U.userName,I.itemName,R.usingTime,R.dateRequest,S.status "
			+ " FROM tblItems I, tblRequests R,tblUser U,tblStatus S "
			+ " WHERE U.userID=R.userID AND I.itemID=R.itemID AND R.statusID = S.statusID AND I.itemName like '%" + itemID + "%'"
			+ " AND S.status like'%"+status+"%'";
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while (rs.next()) {
		    String requestID = rs.getString("requestID");
		    String userName = rs.getString("userName");
		    String itemName = rs.getString("itemName");
		    float usingTime = Float.parseFloat(rs.getString("usingTime"));
		    Date dateRequest = rs.getDate("dateRequest");
		    String status1 = rs.getString("status");
		    if (list == null) {
			list = new ArrayList<>();
		    }
		    list.add(new RequestDTO(requestID, userName, itemName, usingTime, dateRequest, status1));
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

    public boolean updateStatus(String requestID, String status) throws SQLException {
	boolean check = false;
	Connection conn = null;
	PreparedStatement stm = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "UPDATE tblRequests SET statusID='" + status + "'  where requestID='" + requestID + "'";
		stm = conn.prepareStatement(sql);

		check = stm.executeUpdate() > 0 ? true : false;
	    }
	} catch (Exception e) {
	} finally {
	    if (stm != null) {
		stm.close();
	    }
	    if (conn != null) {
		conn.close();
	    }
	}
	return check;
    }
    
    public List<RequestDTO> getListRequest(List<RequestDTO> list, int page) {
	List<RequestDTO> listPage = null;
	for (int i = (page*3-3); i < (page*3); i++) {
	    if (list.size() > i) {
		String requestID = list.get(i).getRequestID();
		String userName = list.get(i).getUserID();
		String itemName = list.get(i).getItemID();
		Date dateRequest = list.get(i).getDateRequest();
		float usingTime = list.get(i).getUsingTime();
		String status=list.get(i).getStatusID();
		if(listPage==null){
		    listPage=new ArrayList<>();
		}
		listPage.add(new RequestDTO(requestID, userName, itemName, usingTime, dateRequest, status));
	    }
	}
	return listPage;
    }
    
    public List<String> getListStatus() throws SQLException{
	List<String> list=null;
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "SELECT * FROM tblStatus ";
		stm = conn.prepareStatement(sql);
		
		rs = stm.executeQuery();
		while (rs.next()) {
		    String status=rs.getString("status");
		    if(list==null){
			list=new ArrayList<>();
		    }
		    list.add(status);
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
    
    public boolean deleteRequest(String requestID) throws SQLException{
	boolean check = false;
	Connection conn = null;
	PreparedStatement stm = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "UPDATE tblRequests SET statusID='I'  where requestID='" + requestID + "'";
		stm = conn.prepareStatement(sql);

		check = stm.executeUpdate() > 0 ? true : false;
	    }
	} catch (Exception e) {
	} finally {
	    if (stm != null) {
		stm.close();
	    }
	    if (conn != null) {
		conn.close();
	    }
	}
	return check;
    }
}
