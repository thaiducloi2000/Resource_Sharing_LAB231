/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBUtils;
import DTO.ItemDTO;
import DTO.UserDTO;
import DTO.UserGoogole;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author PC
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
	UserDTO user = null;
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "SELECT userID,userName,gmail,dateCreate,roleID,status from tblUser "
			+ " WHERE userID='" + userID + "'AND password='" + password + "'";
		stm = conn.prepareStatement(sql);

		rs = stm.executeQuery();
		while (rs.next()) {
		    String userName = rs.getString("userName");
		    String gmail=rs.getString("gmail");
		    Date dateCreate = rs.getDate("dateCreate");
		    String role = rs.getString("roleID");
		    boolean status = rs.getBoolean("status");
		    user = new UserDTO(userID, userName, "", gmail,"", "", dateCreate, role, status);
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
	return user;
    }

    public UserDTO CheckGGLogin(String gmail) throws SQLException {
	UserDTO user = null;
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "SELECT userID,userName,gmail,dateCreate,roleID,status From tblUser "
			+ " Where gmail='" + gmail + "'";
		stm = conn.prepareStatement(sql);

		rs = stm.executeQuery();
		while (rs.next()) {
		    String userID = rs.getString("userID");
		    String userName = rs.getString("userName");
		    Date dateCreate = rs.getDate("dateCreate");
		    String role = rs.getString("roleID");
		    boolean status = rs.getBoolean("status");
		    user = new UserDTO(userID, userName, "", gmail,"", "", dateCreate, role, status);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
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
	return user;
    }

    public boolean addNewUser(UserDTO user) throws SQLException {
	boolean check=false;
	Connection conn = null;
	PreparedStatement stm = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "Insert INTO tblUser (userID,userName,password,gmail,phoneNumber,address,dateCreate,status,roleID) "
			+ "Values(?,?,?,?,?,?,?,?,?)";
		stm = conn.prepareStatement(sql);
		stm.setString(1, user.getUserID());
		stm.setString(2, user.getUserName());
		stm.setString(3, user.getPassword());
		stm.setString(4, user.getGmail());
		stm.setString(5, user.getPhoneNumber());
		stm.setString(6, user.getAddress());
		stm.setDate(7, user.getCreateDate());
		stm.setBoolean(8, user.isStatus());
		stm.setString(9, user.getRole());
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

    public boolean activeUser(String userID) throws SQLException{
	boolean check=false;
	Connection conn = null;
	PreparedStatement stm = null;
	try {
	    conn = DBUtils.openConection();
	    if (conn != null) {
		String sql = "UPDATE tblUser SET "
			+ "status='True' "
			+ " where userID='"+userID+"'";
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
    
    //get token from Google API(code duoc tham khao)
    public static String getToken(final String code) throws ClientProtocolException, IOException {
	String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
		.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
			.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
			.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
			.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
		.execute().returnContent().asString();
	JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
	String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
	return accessToken;
    }

    //get Information of user from Google's token(code duoc tham khao)
    public static UserGoogole getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
	String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
	String response = Request.Get(link).execute().returnContent().asString();
	UserGoogole googlePojo = new Gson().fromJson(response, UserGoogole.class);
	System.out.println(googlePojo);
	return googlePojo;
    }
}
