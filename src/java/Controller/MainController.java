/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class MainController extends HttpServlet {

    private static final String LOGIN = "LoginController";
    private static final String HOME = "login.jsp";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH_Items = "SearchItemsController";
    private static final String Register_Page = "RegisterController";
    private static final String Verify = "VerifyController";
    private static final String Booking = "BookingController";
    private static final String REQUEST = "RequestController";
    private static final String SEARCH_REQUEST = "SearchrequestController";
    private static final String SEARCH_HISTORY = "SearchRequestHistoryController";
    private static final String DELETE = "DeleteController";

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
	String url = HOME;
	try {
	    String action = request.getParameter("action");
	    if (action == null) {
		url = HOME;
	    } else if ("login".equals(action)) {
		url = LOGIN;
	    } else if ("logout".equals(action)) {
		url = LOGOUT;
	    } else if ("Search".equals(action)) {
		url = SEARCH_Items;
	    } else if ("Page".equals(action)) {
		url = SEARCH_Items;
	    } else if ("Register".equals(action)) {
		url = Register_Page;
	    } else if ("Verify".equals(action)) {
		url = Verify;
	    } else if ("Book".equals(action)) {
		url = Booking;
	    } else if ("Accept".equals(action) || "Reject".equals(action)) {
		url = REQUEST;
	    } else if ("Search Request".equals(action)) {
		url = SEARCH_REQUEST;
	    } else if ("SearchH".equals(action)) {
		url = SEARCH_HISTORY;
	    }else if ("Delete".equals(action)) {
		url = DELETE;
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
