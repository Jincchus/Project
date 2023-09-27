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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "faqList",value = "/admin/cs/faq/list.do")
public class FaqListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ArticleService articleService = ArticleService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_faq_list.jsp");
        String menu1 = req.getParameter("menu1");
        String menu2 = req.getParameter("menu2");

        if (menu1 == null) {
            //제일 처음 list로 들어오는 경우 default값('회원'-'가입')으로 리턴 해준다.
            menu1 = "회원";
            menu2 = "가입";
        }
        List<ArticleResponseDto> articles = new ArrayList<>();
        try {
            articles = articleService.getTenFaq(menu1, menu2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("articles",articles);
        dispatcher.forward(req,resp);
    }
}
