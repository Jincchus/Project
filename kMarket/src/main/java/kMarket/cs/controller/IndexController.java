package kMarket.cs.controller;

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

import kMarket.cs.dto.CsArticleDTO;
import kMarket.cs.service.CsArticleService;

@WebServlet("/cs/index.do")
public class IndexController extends HttpServlet {

	CsArticleService aService = CsArticleService.INSTANCE;
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<CsArticleDTO>notices= aService.indexSelectNotices();
		List<CsArticleDTO>qnas= aService.indexSelectQnas();
		logger.debug("notices :"+notices.toString());
		logger.debug("qnas :"+qnas.toString());
		
		req.setAttribute("notices", notices);
		req.setAttribute("qnas", qnas);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/index.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
}
