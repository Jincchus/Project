package kMarket.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import kMarket.member.dto.SignupDTO;
import kMarket.member.service.SignupService;


@WebServlet("/member/signup.do")
public class SignupController extends HttpServlet {

	private static final long serialVersionUID = -2074751761210579667L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	SignupService service = new SignupService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("type");
		logger.debug("signup : " + type);
		List<SignupDTO> terms = service.selectTerms(type);
		logger.debug("terms : " + terms);
		req.setAttribute("terms", terms);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/signup.jsp");
		dispatcher.forward(req, resp);
	}
}
