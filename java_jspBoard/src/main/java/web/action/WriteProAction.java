package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			//데이터베이스 객체 DAO를 받고..num을 가져오는 메소드 실행
			BoardDAO dao = BoardDAO.getInstance();
			int newNum=dao.getNewNum();
			BoardDTO dto = new BoardDTO();
			//답글인 경우..
			if(request.getParameter("num")!=null && request.getParameter("num").length()!=0) {
				dto.setRef(Integer.parseInt(request.getParameter("ref")));
				dto.setStep(Integer.parseInt(request.getParameter("step"))+1);
				dto.setLev(Integer.parseInt(request.getParameter("lev"))+1);
			} else {//답글이 아니고 제목글인 경우
				dto.setRef(newNum);
				dto.setStep(0);
				dto.setLev(0);
			}
			//새로 글을 쓰기때문에 무조건 담아줘야 함
			dto.setNum(newNum);
			dto.setSubject(request.getParameter("subject"));
			dto.setIp(request.getRemoteAddr());
			dto.setContent(request.getParameter("content"));
			dto.setWriter(request.getParameter("writer"));
			dto.setEmail(request.getParameter("email"));
			dto.setPasswd(request.getParameter("passwd"));
			//dao이용해서 저장
			int r = dao.writeArticle(dto);
			String msg, url;
			if(r>0) { 
				msg = "게시글이 저장되었습니다.";
			} else {
				msg = "게시글 저장 실패했습니다.";
			}
			url = "list.iot";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		return "view/MsgPage.jsp";
	}

}