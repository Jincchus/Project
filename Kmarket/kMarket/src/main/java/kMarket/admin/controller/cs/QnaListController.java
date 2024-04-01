package kMarket.admin.controller.cs;

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

import java.io.IOException;
import java.util.List;

@WebServlet(name = "qnaList",value = "/admin/cs/qna/list.do")
public class QnaListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    CsArticleService aService = CsArticleService.INSTANCE;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String cate = "qna";
    	String menu1 = req.getParameter("menu1");
    	String menu2 = req.getParameter("menu2");
    	String pg = req.getParameter("pg");
    	
    	
		
    	//현재 페이지 번호
    	int currentPage = aService.getCurrentPage(pg);
    	int start = aService.getStartNum(currentPage);
    	int total = 0;
    	
    	List<CsArticleDTO> lists = null;
    	if(menu2.equals("0")) {
    		logger.debug("menu2 empty");
    		total = aService.selectCountTotal(cate, menu1);
    		lists = aService.selectArticles(cate, menu1, start);
    	}else {
    		logger.debug("menu2 is ");
    		total = aService.selectMenu2CountTotal(cate,menu1,menu2);
    		lists = aService.selectAdminArticles(cate, menu1, menu2, start);
    	}
    	int lastPageNum = aService.getLastPageNum(total);
    	int[] result = aService.getPageGroupNum(currentPage, lastPageNum);
    	int pageStartNum = aService.getPageStartNum(total, currentPage);
    	
    	logger.debug("menu1 :"+menu1);
    	logger.debug("menu2 :"+menu2);
    	logger.debug("currentPage :"+currentPage);
    	logger.debug("total :"+total);
    	logger.debug("lastPageNum :"+lastPageNum);
    	logger.debug("result[0] :"+result[0]);
    	logger.debug("result[1] :"+result[1]);
    	logger.debug("pageStartNum :"+pageStartNum);
    	logger.debug("start :"+start);
    	logger.debug("lists :"+lists.toString());
    	
    	req.setAttribute("lists", lists);
		req.setAttribute("cate", cate);
		req.setAttribute("menu1", menu1);
		req.setAttribute("menu2", menu2);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
 
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_qna_list.jsp");
		dispatcher.forward(req, resp);
    }
}
