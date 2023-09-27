package kMarket.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.member.dto.MemberDTO;
import kMarket.member.service.MemberService;


@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = -8565694525391581478L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/register.jsp");
			dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid    = req.getParameter("km_uid");
		String pass1  = req.getParameter("km_pass1");
		String name   = req.getParameter("km_name");
		String gender = req.getParameter("km_gender");
		String hp     = req.getParameter("km_hp");
		String email  = req.getParameter("km_email");
		String zip    = req.getParameter("km_zip");
		String addr1  = req.getParameter("km_addr1");
		String addr2  = req.getParameter("km_addr2");
		String regip  = req.getRemoteAddr();
		
		/*
		 * logger.debug("uid : " + uid); logger.debug("pass1 : " + pass1);
		 * logger.debug("name : " + name); logger.debug("gender : " + gender);
		 * logger.debug("hp : " + hp); logger.debug("email : " + email);
		 * logger.debug("zip : " + zip); logger.debug("addr1 : " + addr1);
		 * logger.debug("addr2 : " + addr2); logger.debug("regip : " + regip);
		 */
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(uid);
		dto.setPass(pass1);
		dto.setName(name);
		dto.setGender(gender);
		dto.setHp(hp);
		dto.setEmail(email);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setRegip(regip);
		
		service.insertMember(dto);
		logger.info("register_Post dto : "+dto);
		
		resp.sendRedirect("/member/login.do");
	}	
}