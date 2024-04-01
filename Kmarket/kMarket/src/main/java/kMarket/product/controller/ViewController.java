package kMarket.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.member.dto.MemberDTO;
import kMarket.product.dto.CartDTO;
import kMarket.product.dto.ProductDTO;
import kMarket.product.dto.ReviewDTO;
import kMarket.product.service.ProductCartService;
import kMarket.product.service.ProductService;


@WebServlet("/product/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService service = ProductService.INSTANCE;
	private ProductCartService cartService = ProductCartService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodNo = req.getParameter("prodNo");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		logger.debug("prodNo : " + prodNo);
		logger.debug("cate1 : " + cate2);
		logger.debug("cate2 : " + cate2);
		
		// 현재 세션
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		
		// 상품 조회
		ProductDTO product = service.selectProduct(prodNo);
		logger.debug(product.toString());
		
		req.setAttribute("prodNo", prodNo);
		req.setAttribute("cate1", cate1);
		req.setAttribute("cate2", cate2);
		req.setAttribute("product", product);
		
		
		/* 리뷰 조회 및 페이징 */
		// 페이지 데이터 수신
		String pg = req.getParameter("pg");
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수
		int total = service.selectCountTotal(prodNo);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		// 리뷰 조회
		List<ReviewDTO> reviews = service.selectReviews(prodNo,start);
		logger.debug(prodNo, start);

		req.setAttribute("total", total);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("reviews", reviews);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CartDTO dto = new CartDTO(); 
		
		String uid = "aaa1"; //req.getParameter("uid"); 
		String thumb2 = req.getParameter("thumb2");
		String prodNo = req.getParameter("prodNo"); 
		String prodName = req.getParameter("prodName"); 
		String descript = req.getParameter("descript"); 
		String count = req.getParameter("num"); 
		String price = req.getParameter("price"); // view form의 total -> oriPrice , discount계산한 것 db에 어떻게 넣을지 의논
		String discount = req.getParameter("discount"); 
		String point = req.getParameter("point"); 
		String delivery = req.getParameter("delivery"); 
		String total = req.getParameter("total"); 
		
		dto.setUid(uid);
		dto.setThumb2(thumb2);
		dto.setProdNo(prodNo);
		dto.setProdName(prodName);
		dto.setDescript(descript);
		dto.setCount(count);
		dto.setPrice(price);
		dto.setDiscount(discount);
		dto.setPoint(point);
		dto.setDelivery(delivery);
		dto.setTotal(total);

		logger.debug("dto : " + dto.toString());
		
		// if prodNo != null updateCart
		
		// else insertCart
		cartService.insertCart(dto);
		
		resp.sendRedirect("/kMarket/product/cart.do?uid="+uid);
	}
}
