package kMarket.admin.controller.cs;

import kMarket.admin.dto.ArticleResponseDto;
import kMarket.admin.service.ArticleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "noticeDetail",value = "/admin/cs/notice/detail.do")
public class NoticeDetailView extends HttpServlet {
    private static final ArticleService articleService = ArticleService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_notice_view.jsp");
        ArticleResponseDto response = new ArticleResponseDto();
        try {
            response= articleService.getOneArticle(Integer.valueOf(req.getParameter("cNo")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("article",response);

        dispatcher.forward(req, resp);
    }
}
