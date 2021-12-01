package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.BoardDTO;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		BoardDTO dto = new BoardDTO();
//		System.out.println(request.getParameter("content"));
		if(request.getParameter("num")!=null) {
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setContent(request.getParameter("content"));
			dto.setSubject(request.getParameter("subject"));
			dto.setWriter(request.getParameter("writer"));
			dto.setEmail(request.getParameter("email"));
			dto.setPasswd(request.getParameter("passwd"));		
			request.setAttribute("dto", dto);
		}
		return "view/updateForm.jsp";
	}

}
