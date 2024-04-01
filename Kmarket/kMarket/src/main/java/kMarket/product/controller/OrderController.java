package kMarket.product.controller;

import java.io.IOException;
import java.util.Iterator;
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

import com.google.gson.Gson;

import kMarket.member.dto.MemberDTO;
import kMarket.product.dto.CartDTO;

@WebServlet("/product/order.do")
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		logger.debug("uid : " +uid);
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/order.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String[] prodNos = req.getParameterValues("prodNo");
//		String[] prodName = req.getParameterValues("prodName");
//		String[] descripts = req.getParameterValues("descript");
//		String[] thumb2s = req.getParameterValues("thumb2");
		
//		logger.debug(""+CartToOrders);
//		logger.debug(prodNos.toString());
//		logger.debug(descripts.toString());
//		logger.debug(thumb2s.toString());
		
//		req.setAttribute("cartToOrders", CartToOrders);
//		req.setAttribute("prodNo", prodNo);
//		req.setAttribute("prodName", prodName);
//		req.setAttribute("descripts", descripts);
//		req.setAttribute("thumb2s", thumb2s);
		String uid = "aaa1"; //req.getParameter("uid");
		
		String cartToOrderJSON = req.getParameter("cartToOrder");
		
		Gson gson = new Gson();
		
		// JSON 문자열을 Java 객체로 변환
	    CartDTO[] cartToOrder = gson.fromJson(cartToOrderJSON, CartDTO[].class);

	    logger.debug("CartDTO[] : " + cartToOrderJSON.toString());
        logger.debug("carts :"+cartToOrder[0]);
	    req.setAttribute("carts", cartToOrder);

        // 파싱된 데이터 출력
        for (CartDTO cto : cartToOrder) {
            System.out.println("cartDto" + cartToOrder.toString());
        }
		// resp.sendRedirect("/kMarket/product/order.do?uid="+uid);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/order.jsp");
		dispatcher.forward(req, resp);
	}
}
