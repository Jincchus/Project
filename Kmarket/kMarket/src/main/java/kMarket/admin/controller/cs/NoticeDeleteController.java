package kMarket.admin.controller.cs;
/*
//import com.fasterxml.jackson.databind.ObjectMapper;
import kMarket.admin.request.ArticleMultipleDeleteRequest;
import kMarket.admin.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name = "noticeDeleteController",value = "/admin/cs/notice/delete.do")
public class NoticeDeleteController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final ArticleService articleService = ArticleService.getInstance();
    //private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cNo = Integer.parseInt(req.getParameter("cNo"));
        logger.info("requested cNo = {}",cNo);
        try {
            articleService.deleteArticle(cNo);
            resp.sendRedirect("/admin/cs/notice/list.do");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String cur;
        while ((cur = br.readLine()) != null) {
            sb.append(cur);
        }

        //ObjectMapper om = new ObjectMapper();
        // json 요청 읽어와서 자바 객체로 포팅
        //ArticleMultipleDeleteRequest dto = om.readValue(sb.toString(), ArticleMultipleDeleteRequest.class);
        for (Integer id: dto.getArticleIds()) {
            try {
                articleService.deleteArticle(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}*/
