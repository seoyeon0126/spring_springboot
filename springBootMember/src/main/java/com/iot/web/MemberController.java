package com.iot.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.domain.MemberVO;
import com.iot.service.MemberService;

	@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService memberService;

	@RequestMapping("registerForm")
	public String writePro(HttpServletRequest request, HttpServletResponse response, Model model, MemberVO vo) {
		logger.info("1234");
		return "Register";
	}

	@RequestMapping("idCheck")
	@ResponseBody
	public int idCheck(HttpServletRequest request, HttpServletResponse response, 
			Model model, MemberVO vo) {
		logger.info("id 체크하러 옴");
		int cnt = memberService.idCheck(vo);
		return cnt;
	}
	
	@RequestMapping(value="/registerProc")
	public String registerProc(HttpServletRequest request, HttpServletResponse response, 
			Model model, MemberVO vo) {
		memberService.registerProc(vo);
		return "redirect:/memberList";//controller로 가라..
	}
	
	@RequestMapping(value="/memberList")
	public String memberList(HttpServletRequest request, HttpServletResponse response, 
			Model model, MemberVO vo) {
		List<MemberVO> mList = memberService.memberList(vo);
		
		model.addAttribute("mList",mList);
		return "memberList";
	}
	
	@RequestMapping(value="/memberDetail")
	public String memberDetail(HttpServletRequest request, HttpServletResponse response, 
			Model model, MemberVO vo) {
		List<MemberVO> mList = memberService.memberList(vo);
		
		model.addAttribute("mvo",mList.get(0));
		return "memberDetail";
	}
	
	@RequestMapping(value="/updateForm")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, 
			Model model, MemberVO vo) {
//		logger.info(vo.toString());
		model.addAttribute("mvo",vo);
		return "updateForm";
	}
	
	@RequestMapping(value="/updateProc")
	public String updateProc(HttpServletRequest request, HttpServletResponse response, 
			Model model, MemberVO vo) {
		memberService.updateProc(vo);
		return "redirect:/memberList";//controller로 가라..
	}
	
	@RequestMapping(value="/deletePro")
	public String deletePro(HttpServletRequest request, HttpServletResponse response, 
			Model model, MemberVO vo) {
		memberService.deletePro(vo);
		return "redirect:/memberList";//controller로 가라..
	}
	
}
