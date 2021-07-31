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
import DTO.UserGoogole;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet("/login-google")
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String EMPLOYEE_PAGE = "main.jsp";
    private static final String MANAGER_PAGE = "manager.jsp";

    private static final long serialVersionUID = 1L;

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
//	processRequest(request, response);
	String code = request.getParameter("code");
	String url = ERROR;
	try {
	    if (code == null || code.isEmpty()) {
		url = ERROR;
	    } else {

		String accessToken = UserDAO.getToken(code);
		UserGoogole guest = UserDAO.getUserInfo(accessToken);
		UserDAO dao = new UserDAO();
		UserDTO user = dao.CheckGGLogin(guest.getEmail());
		HttpSession session = request.getSession();
		if (user != null && user.isStatus()) {
		    session.setAttribute("LIST_USER", user);
		    String role = user.getRole();
		    if (role.equalsIgnoreCase("EMP")) {
			url = EMPLOYEE_PAGE;
		    }if ( role.equalsIgnoreCase("MAN")) {
			RequestDAO sDao = new RequestDAO();
			List<String> status = sDao.getListStatus();
			session.setAttribute("List_Status", status);
			url = MANAGER_PAGE;
		    }
		}
		else{
		    request.setAttribute("ERROR", "Your Account a not allow for System.");
		}
	    }
	} catch (Exception ex) {
	    Logger.getLogger(LoginGoogleController.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    request.getRequestDispatcher(url).forward(request, response);
	}

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
