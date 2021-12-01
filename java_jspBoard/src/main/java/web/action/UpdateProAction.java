package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int r =0;
		BoardDTO dto = new BoardDTO();
		if(request.getParameter("num")!=null) {
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setContent(request.getParameter("content"));
			dto.setSubject(request.getParameter("subject"));
			dto.setWriter(request.getParameter("writer"));
			dto.setEmail(request.getParameter("email"));
			dto.setPasswd(request.getParameter("passwd"));
			dto.setIp(request.getRemoteAddr());
			//dao 가져오기
			//수정하고 오기
			BoardDAO dao = BoardDAO.getInstance();
			r = dao.updateAtricle(dto);
			if(r!=0) {
				request.setAttribute("msg", "수정되었습니다.");
				request.setAttribute("url", "list.iot");
			} else {
				request.setAttribute("msg", "수정실패했습니다.");
				request.setAttribute("url", "list.iot");
			}
		}
		//MsgPage활용...
		return "view/MsgPage.jsp";
	}

}
