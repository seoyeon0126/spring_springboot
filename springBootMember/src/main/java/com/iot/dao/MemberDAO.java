package com.iot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iot.domain.MemberVO;

@Mapper
public interface MemberDAO {

	int idCheck(MemberVO vo);

	void registerProc(MemberVO vo);

	void memberlist(MemberVO vo);

	List<MemberVO> memberList(MemberVO vo);

	void updateProc(MemberVO vo);

	void deletePro(MemberVO vo);
	
}
