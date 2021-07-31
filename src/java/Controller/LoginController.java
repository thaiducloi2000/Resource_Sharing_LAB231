/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ItemDAO;
import DAO.RequestDAO;
import DAO.UserDAO;
import DTO.RequestDTO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class LoginController extends HttpServlet {

    private final String ERROR = "login.jsp";
    private static final String EMPLOYEE_PAGE = "main.jsp";
    private static final String MANAGER_PAGE = "manager.jsp";

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
	    UserDAO dao = new UserDAO();
	    UserDTO user = dao.checkLogin(userID, password);
	    HttpSession session = request.getSession();
	    if (user != null && user.isStatus()) {
		session.setAttribute("LIST_USER", user);
		String recaptchar = request.getParameter("g-recaptcha-response");
		if (!(recaptchar == null || recaptchar.length() == 0)) {
		    String role = user.getRole();
		    if (role.equalsIgnoreCase("EMP")) {
			url = EMPLOYEE_PAGE;
		    }
		    if (role.equalsIgnoreCase("MAN")) {
			RequestDAO sDao = new RequestDAO();
			List<String> status = sDao.getListStatus();
			session.setAttribute("List_Status", status);
			url = MANAGER_PAGE;
		    }
		}
	    } else {
		request.setAttribute("ERROR", "UserID or Password are not correct!!! Please Check Again !!");
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
