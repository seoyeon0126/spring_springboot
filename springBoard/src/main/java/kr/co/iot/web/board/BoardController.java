package kr.co.iot.web.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iot.model.board.dto.BoardVO;
import kr.co.iot.model.board.dto.PageInfo;
import kr.co.iot.model.board.dto.PageVO;
import kr.co.iot.service.board.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//Dependency Injection 으로 service를 넣어줌
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/writeForm")
	public String writeForm(HttpServletRequest request, HttpServletResponse response,
			                Model model, BoardVO vo, PageVO pvo) {
		if(vo !=null && vo.getNum()!=0) //답글...
			model.addAttribute("dto", vo);
		return "board/writeForm";
	}
	
	@RequestMapping("/writePro")
	public String writePro(HttpServletRequest request, HttpServletResponse response,
			Model model, BoardVO vo, PageVO pvo) {
		vo.setIp(request.getRemoteAddr());
		logger.info(vo.toString());
		int r = boardService.writePro(vo);
		 String msg, url;
	     if(r>0) {
	    	 msg = "게시글이 저장되었습니다.";
	     } else {
	    	 msg= "게시글 저장 실패했습니다.";
	     }
	     url="list";
	     model.addAttribute("msg", msg);
	     model.addAttribute("url", url);
		return "board/MsgPage";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,
			Model model, BoardVO vo, PageVO pvo) {
		if(pvo.getCurrentPage()==0) {
		   pvo.setCurrentPage(1);	
		}
		if(pvo.getCurrentBlock()==0) {
			pvo.setCurrentBlock(1);	
		}
        //전체 행의 수를 구하는 service 호출
		pvo.setAllCount(boardService.getAllCount());
		List<BoardVO> list = boardService.getArticles(vo, pvo);
		  if(pvo.getAllCount() % PageInfo.ROW_PER_PAGE==0)
		    	 pvo.setPagAllCnt(pvo.getAllCount() / PageInfo.ROW_PER_PAGE);
		     else
		    	 pvo.setPagAllCnt(pvo.getAllCount() / PageInfo.ROW_PER_PAGE+1);
		model.addAttribute("list", list);
		model.addAttribute("pvo", pvo);

		return "board/list";
	}
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request, HttpServletResponse response,
			Model model, BoardVO vo, PageVO pvo) {
		    if(pvo.getCurrentPage()==0) {
			   pvo.setCurrentPage(1);	
			}
			if(pvo.getCurrentBlock()==0) {
				pvo.setCurrentBlock(1);	
			}
		BoardVO svo  = boardService.getArticle(vo);
		
		 model.addAttribute("dto", svo);
		 model.addAttribute("pvo", pvo);
		return "board/content";
	}
	
	@RequestMapping("/deletePro")
	public String deletePro(HttpServletRequest request, HttpServletResponse response,
			Model model, BoardVO vo, PageVO pvo) {
		if(pvo.getCurrentPage()==0) {
			pvo.setCurrentPage(1);	
		}
		if(pvo.getCurrentBlock()==0) {
			pvo.setCurrentBlock(1);	
		}
		int r = boardService.deletePro(vo);
		 String msg, url;
	     if(r>0) {
	    	 msg = "게시글이 삭제되었습니다.";
	     } else {
	    	 msg= "게시글 삭제 실패했습니다.";
	     }
	     url="list";
	     model.addAttribute("msg", msg);
	     model.addAttribute("url", url);
	     model.addAttribute("pvo", pvo);
		return "board/MsgPage";
	}
	
}
