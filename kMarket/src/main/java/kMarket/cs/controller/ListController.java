package kMarket.cs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/cs/board/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	CsArticleService aService = CsArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 수신
		String cate = req.getParameter("cate");
		String menu1 = req.getParameter("menu1");
		if(menu1.equals("전체보기")) {
			menu1 = "0";
		}
		String pg = req.getParameter("pg");
		String type ="list";
		logger.debug("menu1 :"+menu1);
		logger.debug("cate :"+cate);
		
		//현재 페이지 번호
		int currentPage = aService.getCurrentPage(pg);
		int total = aService.selectCountTotal(cate, menu1);
		int lastPageNum = aService.getLastPageNum(total);
		int[] result = aService.getPageGroupNum(currentPage, lastPageNum);
		int pageStartNum = aService.getPageStartNum(total, currentPage);
		int start = aService.getStartNum(currentPage);
		
		logger.debug("currentPage :"+currentPage);
		logger.debug("total :"+total);
		logger.debug("lastPageNum :"+lastPageNum);
		logger.debug("result[0] :"+result[0]);
		logger.debug("result[1] :"+result[1]);
		logger.debug("pageStartNum :"+pageStartNum);
		logger.debug("start :"+start);
		List<CsArticleDTO> lists =CsArticleService.INSTANCE.selectArticles(cate, menu1,start);;
		
		logger.debug("lists :"+lists.toString());
		
		
		req.setAttribute("lists", lists);
		req.setAttribute("cate", cate);
		if(menu1.equals("0")) {
			menu1 ="전체보기";
			logger.debug("menu1 전체보기로 변경 "+menu1);
		}
		req.setAttribute("menu1", menu1);
		req.setAttribute("type", type);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		RequestDispatcher dispatcher = null;
		if(cate.equals("faq")) {
			Map<String, List<CsArticleDTO>> menu2Group = aService.getMenu2Group(lists);
			logger.debug("menu2Group : "+menu2Group.toString());
			req.setAttribute("menu2Group", menu2Group);
			dispatcher = req.getRequestDispatcher("/cs/board/faqList.jsp");
		}else {
			dispatcher = req.getRequestDispatcher("/cs/board/list.jsp");
		}
	
		dispatcher.forward(req, resp);
		
	}
	
}
/*
int i= menu1.indexOf("/");
int j = menu1.indexOf("_");
logger.debug("i : "+i);
logger.debug("j: "+j);


if(i>0) {
logger.debug("before encoding menu1 : "+menu1);
logger.debug("start i "); 
byte[] decode= Base64.getUrlDecoder().decode(menu1);
 logger.debug("decode :"+decode);
menu1= new String(decode,StandardCharsets.UTF_8);
logger.debug("after encoding menu1 :" +menu1); 
}


if(j>0) {
	logger.debug("start j "); 
	logger.debug("before encoding menu1 : "+menu1);
	 byte[] decode= Base64.getUrlDecoder().decode(menu1);
	 logger.debug("decode :"+decode);
	menu1= new String(decode,StandardCharsets.UTF_8);
	logger.debug("after encoding menu1 :" +menu1); 
	}
*/