package kMarket.cs.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

@WebServlet("/cs/board/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	CsArticleService aService = CsArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cate = req.getParameter("cate");
		String type = "write";
		
		logger.debug("cate : "+cate);
		
		req.setAttribute("cate", cate);
		req.setAttribute("type", type);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/board/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String cate =req.getParameter("cate");
		String writer =req.getParameter("writer");
		String menu1 =req.getParameter("menu1");
		String menu2 =req.getParameter("menu2");
		String title =req.getParameter("title");
		String content =req.getParameter("content");
		String regip =req.getRemoteAddr();
		
		logger.debug("post cate : "+cate);
		logger.debug("post menu1 : "+menu1);
		logger.debug("post menu2 : "+menu2);
		logger.debug("post title : "+title);
		logger.debug("post content : "+content);
		
		CsArticleDTO dto = new CsArticleDTO();
		dto.setCate(cate);
		dto.setWriter(writer);
		dto.setMenu1(menu1);
		dto.setMenu2(menu2);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setRegip(regip);
		
		int cNo = aService.insertArticle(dto);
		logger.debug("cNo : "+cNo);
		
		resp.sendRedirect("/kMarket/cs/board/view.do?cNo="+cNo);
		
		//resp.sendRedirect("/kMarket/cs/board/list.do?cate="+cate+"&menu1="+Base64.getUrlEncoder().encodeToString(menu1.getBytes(StandardCharsets.UTF_8)));
		//resp.sendRedirect("/kMarket/cs/board/list.do?cate="+cate+"&menu1="+Base64.getEncoder().encodeToString(menu1.getBytes()));
		
		
		/*
		강사님의 스토리 보드에는 파일업로드 부분이 없어서 기능구현할 때 없에려고 합니다.
		기능 구현이 완료된 이후 시간이 남으면 추가로 넣을 수 있도록 하겠습니다.
		
				//파일 업로드
				
				 
				///////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////
				파일업로드 기능을 추가시 write.jsp의 form에 enctype="multipart/form-data"설정해야함
				///////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////
				String path = aService.getPath(req, "/upload");
				logger.debug("path : "+path);
				
				
				MultipartRequest mr = aService.uploadfile(req, path);
				
				 
				String writer = mr.getParameter("writer");
				String title = mr.getParameter("title");
				String content = mr.getParameter("content");
				String oName = mr.getOriginalFileName("file");
				String cate = mr.getParameter("cate");
				String group = mr.getParameter("group");
				String type = mr.getParameter("type");
				String regip = req.getRemoteAddr();
				
				logger.debug("writer : "+writer);
				logger.debug("title : "+title);
				logger.debug("content : "+content);
				logger.debug("cate : "+cate);
				logger.debug("group : "+group);
				logger.debug("type : "+type);
				
				CsArticleDTO dto = new CsArticleDTO();
				dto.setWriter(writer);
				dto.setTitle(title);
				dto.setContent(content);
				dto.setFile(oName);
				dto.setCate(cate);
				dto.setRegip(regip);
				int no  = aService.insertArticle(dto);
				logger.debug("no : "+no);
				
				
				//파일명 수정 및 파일 테이블
					if(oName!=null) {
					
					String sName = aService.renameToFile(req, oName, path);
					FileDTO filedto = new FileDTO();
					filedto.setAno(no);
					filedto.setOfile(oName);
					filedto.setSfile(sName);
					fService.insertFile(filedto);
					
					}
			*/	
	
	}
	
}
