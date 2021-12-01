package web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;
import model.dto.PageInfo;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			BoardDAO dao = BoardDAO.getInstance();
			int allCount = dao.getAllCount();
			//총페이지 수, 총 페이지 블럭
			String curPage = request.getParameter("currentPage");
			String curBlock = request.getParameter("currenBlock");
			int currentPage;
			int currentBlock;
			if(curPage==null) {
				currentPage = 1;
			} else {
				currentPage= Integer.parseInt(curPage);
			}
			if(curBlock==null) {
				currentBlock = 1;
			} else {
				currentPage= Integer.parseInt(curBlock);
			}
			
			//리스트를 가져오고
			int beginRow = (currentPage-1)*PageInfo.ROW_PER_PAGE+1;
			int endRow = beginRow+PageInfo.ROW_PER_PAGE-1;
			//페이지 수...
			int pageAllCnt =0;
			if( allCount%PageInfo.ROW_PER_PAGE==0)
				pageAllCnt = allCount/PageInfo.PAGE_PER_NEXT;
			else 
				pageAllCnt = allCount/PageInfo.ROW_PER_PAGE+1;
			
			List<BoardDTO> list = dao.getArticles(beginRow, endRow);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageAllCnt", pageAllCnt);
			request.setAttribute("allCount", allCount);
			
			request.setAttribute("list", list);
			return "view/list.jsp";
	}

}
