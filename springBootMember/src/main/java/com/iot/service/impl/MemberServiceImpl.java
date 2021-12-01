package com.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.dao.MemberDAO;
import com.iot.domain.MemberVO;
import com.iot.service.MemberService;

@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public int idCheck(MemberVO vo) {
		return memberDAO.idCheck(vo);
	}

	@Override
	public void registerProc(MemberVO vo) {
		memberDAO.registerProc(vo);
	}

	@Override
	public List<MemberVO> memberList(MemberVO vo) {
		return memberDAO.memberList(vo);
	}

	@Override
	public void updateProc(MemberVO vo) {
		memberDAO.updateProc(vo);
	}

	@Override
	public void deletePro(MemberVO vo) {
		memberDAO.deletePro(vo);
		
	}


}
