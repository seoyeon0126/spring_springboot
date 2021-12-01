package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.BoardDTO;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//		System.out.println(request.getParameter("num"));
		BoardDTO dto = new BoardDTO();
		if(request.getParameter("num")!=null) {
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setRef(Integer.parseInt(request.getParameter("ref")));
			dto.setStep(Integer.parseInt(request.getParameter("step")));
			dto.setLev(Integer.parseInt(request.getParameter("lev")));
			//���
			request.setAttribute("dto", dto);
		}
		return "view/wirteForm.jsp";//�������� �׻� view�̸��� ��� ���ֱ�
	}
}
