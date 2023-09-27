package kMarket.admin.controller.cs;

import kMarket.admin.dto.ArticleResponseDto;
import kMarket.admin.request.ArticleUpdateRequest;
import kMarket.admin.service.ArticleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "noticeModify",value = "/admin/cs/notice/modify.do")
public class NoticeModifyController extends HttpServlet {
    private ArticleService articleService = ArticleService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_notice_modify.jsp");
        Integer cNo = Integer.valueOf(req.getParameter("cNo"));

        ArticleResponseDto findArticle = null;

        try {
            findArticle = articleService.getOneArticle(cNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("article", findArticle);
        dispatcher.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 이거 안해주면 한글 박살 나서 들어간다.
        ArticleUpdateRequest dto = new ArticleUpdateRequest();
        dto.setcNo(Integer.valueOf(req.getParameter("cNo")));
        dto.setCate(req.getParameter("cate"));
        dto.setMenu1(req.getParameter("menu1"));
        dto.setMenu2(req.getParameter("menu2"));
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));

        try {
            boolean isUpdated = articleService.updateArticle(dto);
            if (isUpdated) {
                resp.sendRedirect("/admin/cs/notice/detail.do?cNo=" + req.getParameter("cNo"));
            }else{
                resp.sendRedirect("/admin/cs/notice/modify.do?cNo="+req.getParameter("cNo"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
