package kMarket.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.member.dto.MemberDTO;
import kMarket.member.service.MemberService;


@WebServlet("/member/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = -2365079189659779648L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.debug("doget...1");
		
		String success = req.getParameter("success");
		
		req.setAttribute("success", success);
		
		
		
		logger.debug("doget...2");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/login.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid  = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		logger.debug("uid :"+uid);
		logger.debug("pass :"+pass);
		
		MemberDTO member = service.selectMember(uid, pass);
		logger.debug("member :"+member.toString());
		if(member != null) {
			//현재 세션 구하기
			HttpSession session = req.getSession(); //세션 객체를 받음
			//사용자 세션 설정
			session.setAttribute("sessUser", member); //세션 처리
			//리다이렉트
			resp.sendRedirect("/kMarket/index.do");
		
		}else { //로그인 실패
			
			//리다이렉트
			resp.sendRedirect("${ctxPath}/member/login.do?success=100");
		}
		
	}
}
