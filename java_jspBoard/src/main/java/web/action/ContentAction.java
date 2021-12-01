package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//이전페이지에 넘겨준 num을 받아서 처리하기
		int num = Integer.parseInt(request.getParameter("num"));		
		BoardDAO dao= BoardDAO.getInstance();	//dao 객체 얻기
		BoardDTO dto = dao.getArticle(num);		//article가져오기 한개
		if(request.getParameter("currentPage")!=null) {
			request.setAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		} else {
			request.setAttribute("currentPage", 1);
		}
		request.setAttribute("dto", dto);		//request에 settAttribute하기
		return "view/content.jsp";
	}

}
