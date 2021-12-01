package com.iot.service;

import com.iot.domain.MemberVO;

public interface MemberService {
	public int idCheck(MemberVO vo);

	public void registerProc(MemberVO vo);

	public java.util.List<MemberVO> memberList(MemberVO vo);

	public void updateProc(MemberVO vo);

	public void deletePro(MemberVO vo);

}
