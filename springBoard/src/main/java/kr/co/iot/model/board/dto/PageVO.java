package kr.co.iot.model.board.dto;

import lombok.Data;

@Data
public class PageVO {
	private int allCount;
	private int pagAllCnt;
	private int currentPage;
	private int currentBlock;
}
