package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//������������ �Ѱ��� num�� �޾Ƽ� ó���ϱ�
		int num = Integer.parseInt(request.getParameter("num"));		
		BoardDAO dao= BoardDAO.getInstance();	//dao ��ü ���
		BoardDTO dto = dao.getArticle(num);		//article�������� �Ѱ�
		if(request.getParameter("currentPage")!=null) {
			request.setAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		} else {
			request.setAttribute("currentPage", 1);
		}
		request.setAttribute("dto", dto);		//request�� settAttribute�ϱ�
		return "view/content.jsp";
	}

}
