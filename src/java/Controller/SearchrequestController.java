/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RequestDAO;
import DTO.RequestDTO;
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
public class SearchrequestController extends HttpServlet {

    private static final String SUCCESS = "manager.jsp";
    private static final String ERROR = "error.jsp";

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
	    int page=Integer.parseInt(request.getParameter("page"));
	    String itemName = request.getParameter("itemName");
	    String status=request.getParameter("status");
	    if("All".equals(status)){
		status="";
	    }
	    RequestDAO dao = new RequestDAO();
	    List<RequestDTO> list = dao.getListBySearch(itemName, status);
	    HttpSession session = request.getSession();
	    session.setAttribute("LIST_REQUEST", list);
	    int count = list.size();
	    int sizePage = 3;
	    List<RequestDTO> listOut = dao.getListRequest(list, page);
	    int numOfPage = count / sizePage;
	    if (count % sizePage != 0) {
		numOfPage++;
	    }
	    request.setAttribute("numOfPage", numOfPage);
	    if (list != null) {
		request.setAttribute("LIST_REQUEST", listOut);
		url=SUCCESS;
	    }

	} catch (Exception e) {
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
