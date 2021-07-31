/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ItemDAO;
import DTO.ItemDTO;
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
public class SearchPagingController extends HttpServlet {

    private final static String ERROR = "main.jsp";
    private final static String SUCCESS = "main.jsp";

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
	    int page = Integer.parseInt(request.getParameter("page"));
	    ItemDAO dao = new ItemDAO();
	    HttpSession session = request.getSession();
	    List<ItemDTO> list=(List)session.getAttribute("List_Items");
	    List<ItemDTO> listOut = dao.getListItem(list, page);
	    int count = list.size();
	    int sizePage = 3;
	    int numOfPage = count / sizePage;
	    List<ItemDTO> list2 = (List)session.getAttribute("List_Items");
	    if (count % sizePage != 0) {
		numOfPage++;
	    }
	    request.setAttribute("numOfPage", numOfPage);
	    if (listOut != null) {
		request.setAttribute("List_Items", listOut);
		url = SUCCESS;
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
