package kMarket.admin.controller.cs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.cs.service.CsArticleService;

@WebServlet("/cs/board/delete.do")
public class QnaDeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	CsArticleService aService = CsArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cNo = req.getParameter("cNo");
		String menu1 = req.getParameter("menu1");
		String menu2 = req.getParameter("menu2");
		
		logger.debug("cNo: "+cNo);
		logger.debug("menu1: "+menu1);
		logger.debug("menu2: "+menu2);
		aService.deleteArticle(cNo);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs/qna/list.do?menu1="+menu1+"&menu2="+menu2);
		dispatcher.forward(req, resp);
	}
	
	
}
