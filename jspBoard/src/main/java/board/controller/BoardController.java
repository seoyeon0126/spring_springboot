package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String command = request.getParameter("command");
		RequestDispatcher rd = null;
		switch (command) {
		case "list": 
			rd= request.getRequestDispatcher("./view/listAction.jsp");
			break;
		
		case "listView": 
			rd= request.getRequestDispatcher("./view/list.jsp");
			break;

		case "writeForm": 
			rd= request.getRequestDispatcher("./view/writeForm.jsp");
			break;
		case "wirteAction": 
			rd= request.getRequestDispatcher("./view/writeAction.jsp");
			break;
		case "content":
			int num = Integer.parseInt(request.getParameter("num"));
			rd= request.getRequestDispatcher("./view/contentAction.jsp?num="+num);
			break;
		case "updateForm":
			rd= request.getRequestDispatcher("./view/updateSelect.jsp");
			break;
		case "updateAction":
			rd= request.getRequestDispatcher("./view/updateAction.jsp");
			break;
		case "deleteAction":
			rd= request.getRequestDispatcher("./view/deleteAction.jsp");
			break;
		}
		
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
