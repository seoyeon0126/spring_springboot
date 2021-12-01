package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			//�����ͺ��̽� ��ü DAO�� �ް�..num�� �������� �޼ҵ� ����
			BoardDAO dao = BoardDAO.getInstance();
			int newNum=dao.getNewNum();
			BoardDTO dto = new BoardDTO();
			//����� ���..
			if(request.getParameter("num")!=null && request.getParameter("num").length()!=0) {
				dto.setRef(Integer.parseInt(request.getParameter("ref")));
				dto.setStep(Integer.parseInt(request.getParameter("step"))+1);
				dto.setLev(Integer.parseInt(request.getParameter("lev"))+1);
			} else {//����� �ƴϰ� ������� ���
				dto.setRef(newNum);
				dto.setStep(0);
				dto.setLev(0);
			}
			//���� ���� ���⶧���� ������ ������ ��
			dto.setNum(newNum);
			dto.setSubject(request.getParameter("subject"));
			dto.setIp(request.getRemoteAddr());
			dto.setContent(request.getParameter("content"));
			dto.setWriter(request.getParameter("writer"));
			dto.setEmail(request.getParameter("email"));
			dto.setPasswd(request.getParameter("passwd"));
			//dao�̿��ؼ� ����
			int r = dao.writeArticle(dto);
			String msg, url;
			if(r>0) { 
				msg = "�Խñ��� ����Ǿ����ϴ�.";
			} else {
				msg = "�Խñ� ���� �����߽��ϴ�.";
			}
			url = "list.iot";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		return "view/MsgPage.jsp";
	}

}