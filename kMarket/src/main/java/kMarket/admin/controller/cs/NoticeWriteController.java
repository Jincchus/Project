package kMarket.admin.controller.cs;

import kMarket.admin.dto.ArticleRequestDto;
import kMarket.admin.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "noticeWrite",value = "/admin/cs/notice/write.do")
public class NoticeWriteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ArticleService articleService = ArticleService.getInstance();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_notice_create.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String cate = req.getParameter("cate");
        String menu1 = req.getParameter("menu1");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        ArticleRequestDto dto = new ArticleRequestDto();
        dto.setCate(cate);
        dto.setMenu1(menu1);
        dto.setTitle(title);
        dto.setContent(content);

        int cNo = articleService.insertArticle(dto);
        resp.sendRedirect("/admin/cs/notice/detail.do?cNo="+cNo);
    }
}
