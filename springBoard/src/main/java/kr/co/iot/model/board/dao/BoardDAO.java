package kr.co.iot.model.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iot.model.board.dto.BoardVO;


@Mapper
public interface BoardDAO {

	int getNewNum();
	
	int writePro(BoardVO vo);
	
	int getAllCount();

	List<BoardVO> getArticles(BoardVO vo);

	BoardVO getArticle(BoardVO vo);

	int updateReadCount(BoardVO vo);

	int deletePro(BoardVO vo);

	int updatePro(BoardVO vo);
	
}
