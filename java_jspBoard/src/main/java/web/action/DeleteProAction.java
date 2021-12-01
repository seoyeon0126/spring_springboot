package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int r=0;
		if(request.getParameter("num")!=null) {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
			r = dao.deleteArticle(num);
		}
		if(r!=0) {
			request.setAttribute("msg", "삭제되었습니다.");
			request.setAttribute("url", "list.iot");
		} else {
			request.setAttribute("msg", "삭제실패했습니다.");
			request.setAttribute("url", "list.iot");
		}
		
		return "view/MsgPage.jsp";
	}

}
