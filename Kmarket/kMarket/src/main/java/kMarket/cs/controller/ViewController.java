package kMarket.cs.controller;

import java.io.IOException;

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

@WebServlet("/cs/board/view.do")
public class ViewController extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	CsArticleService aService = CsArticleService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cNo = req.getParameter("cNo");
		String cate = req.getParameter("cate");
		String menu1 = req.getParameter("menu1");
		String type = "view";
		CsArticleDTO dto = aService.selectArticle(cNo);
		CsArticleDTO reply =  aService.selectReply(cNo);
		logger.debug("dto : "+dto.toString());
		logger.debug("reply : "+reply);
		logger.debug("date : "+dto.getRdate());
		logger.debug("date : "+dto.getMaskingWriter());
		req.setAttribute("dto", dto);
		req.setAttribute("reply", reply);
		req.setAttribute("type", type);

		if(dto!=null) {
			req.setAttribute("cate", dto.getCate());
			req.setAttribute("menu1", dto.getMenu1());
		}else {
		req.setAttribute("cate", cate);
		req.setAttribute("menu1", menu1);
		}
			
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/board/view.jsp");
		dispatcher.forward(req, resp);
	}
}

/*
String group = req.getParameter("group");
String cate = req.getParameter("cate");
String type = "view";
logger.debug("group : "+group);
logger.debug("cate : "+cate);
//cNo를 받아와서 셀렉트로 조회
req.setAttribute("group", group);
req.setAttribute("cate", cate);
req.setAttribute("type", type);
*/

