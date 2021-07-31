/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DAO.sendEmail;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class RegisterController extends HttpServlet {

    private final String ERROR = "login.jsp";
    private final String SUCCESS = "verify.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	String url = ERROR;
	try {
	    String userID = request.getParameter("userID");
	    String password = request.getParameter("password");
	    String phone = request.getParameter("phone");
	    String userName = request.getParameter("userName");
	    String address = request.getParameter("address");
	    String emai = request.getParameter("email");
	    long millis = System.currentTimeMillis();
	    java.sql.Date createDate = new java.sql.Date(millis);

	    sendEmail sm = new sendEmail();
	    String code = sm.getRandom();

	    UserDTO user = new UserDTO(userID, userName, password, emai, phone, address, createDate, "EMP", false);

	    boolean test = sm.sendEmail(user, code);
	    if (test) {
		HttpSession session = request.getSession();
		UserDAO dao = new UserDAO();
		boolean check = dao.addNewUser(user);
		if (check) {
		    session.setAttribute("authcode", code);
		    session.setAttribute("userID", user.getUserID());
		    url = SUCCESS;
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    request.getRequestDispatcher(url).forward(request, response);
	}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
