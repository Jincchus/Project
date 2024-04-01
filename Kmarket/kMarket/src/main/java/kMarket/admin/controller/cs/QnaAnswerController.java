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

/**
 * TODO: query param으로 qna detail처리하기
 */
@WebServlet(name = "qnaWrite",value = "/admin/cs/qna/answer.do")
public class QnaAnswerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    CsArticleService aService = CsArticleService.INSTANCE;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String cNo = req.getParameter("cNo");
    	
    	logger.debug("cNo : "+cNo);
    	CsArticleDTO list = aService.selectArticle(cNo);
    	logger.debug("list : "+list.toString());
    	
    	CsArticleDTO reply =  aService.selectReply(cNo);
    	logger.debug("Get reply :"+reply);
    	req.setAttribute("reply", reply);
    	req.setAttribute("list", list);
    	RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_qna_answer.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String menu1= req.getParameter("menu1");
    	String menu2= req.getParameter("menu2");
    	String parent= req.getParameter("cNo");
    	String reply = req.getParameter("reply");
    	//댓글(reply)를 content로 넣고, parent에 원글의 번호를 넣어서 구분해야겠음
    	
    	logger.debug("doPost menu1 : "+menu1);
    	logger.debug("doPost menu2 : "+menu2);
    	logger.debug("doPost parent : "+parent);
    	logger.debug("doPost reply : "+reply);
    	
    	CsArticleDTO dto = new CsArticleDTO();
    	dto.setMenu1(menu1);
    	dto.setMenu2(menu2);
    	dto.setParent(parent);
    	dto.setContent(reply);//reply를 content로 넣을 예정
    	logger.debug(dto.toString());
    	aService.updateArticle(dto);
    	
    	resp.sendRedirect("/kMarket/admin/cs/qna/answer.do?cNo="+parent);
    	
    }

}
