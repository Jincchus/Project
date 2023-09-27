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

import kMarket.member.service.MemberService;


@WebServlet("/member/join.do")
public class JoinController extends HttpServlet {

	private static final long serialVersionUID = -9165570067205844583L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("get join");
		RequestDispatcher dispatcher= req.getRequestDispatcher("/member/join.jsp");
		
		dispatcher.forward(req, resp);
	}
}
