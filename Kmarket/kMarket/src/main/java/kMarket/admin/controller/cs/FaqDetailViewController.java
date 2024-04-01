package kMarket.admin.controller.cs;

import kMarket.admin.dto.ArticleResponseDto;
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
import java.sql.SQLException;

@WebServlet(name = "faqDetail",value = "/admin/cs/faq/detail.do")
public class FaqDetailViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ArticleService articleService = ArticleService.getInstance();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cNo = Integer.parseInt(req.getParameter("cNo"));
        ArticleResponseDto response = new ArticleResponseDto();
        try {
            response = articleService.getOneArticle(cNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("article", response);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_faq_view.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
