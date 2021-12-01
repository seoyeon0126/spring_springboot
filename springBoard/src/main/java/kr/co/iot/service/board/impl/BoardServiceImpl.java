package kr.co.iot.service.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iot.model.board.dao.BoardDAO;
import kr.co.iot.model.board.dto.BoardVO;
import kr.co.iot.model.board.dto.PageInfo;
import kr.co.iot.model.board.dto.PageVO;
import kr.co.iot.service.board.BoardService;

@Service(value="boardService")	//ctrl+shift+O..
public class BoardServiceImpl implements BoardService {
	 //의존성 주입 dao
   @Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int getNewNum() {
		return boardDAO.getNewNum();
	}

	@Override
	public List<BoardVO> getArticles (BoardVO vo, PageVO pvo) {
		int startRow = (pvo.getCurrentPage()-1)*PageInfo.ROW_PER_PAGE+1;
		int endRow = startRow+PageInfo.ROW_PER_PAGE-1;
		vo.setStartRow(startRow);
		vo.setEndRow(endRow);
		return boardDAO.getArticles(vo);
	}

	@Override
	public int writePro(BoardVO vo) {
		return boardDAO.writePro(vo);
	}

	@Override
	public int getAllCount() {
		return boardDAO.getAllCount();
	}

	@Override
	public BoardVO getArticle(BoardVO vo) {
		BoardVO svo = new BoardVO();
		//조회수
		int r = boardDAO.updateReadCount(vo);
		if(r>0) {
			svo = boardDAO.getArticle(vo);
		}
		return svo;
	}

	@Override
	public int deletePro(BoardVO vo) {
		return boardDAO.deletePro(vo);
	}

	@Override
	public int updatePro(BoardVO vo) {
		return boardDAO.updatePro(vo);
	}
}
