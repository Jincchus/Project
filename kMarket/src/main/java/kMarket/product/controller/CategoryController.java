package kMarket.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

/*
 * 날짜 : 2023/09/17
 * 이름 : 박경진
 * 내용 : category json data 수신
 */

@WebServlet("/product/_aside.do")
public class CategoryController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// GET 요청으로 데이터를 받기 위해 URL에서 파라미터를 추출하지 않습니다.
	        
	        // JSON 생성 (임의의 응답 데이터)
	        JsonObject json = new JsonObject();
	        json.addProperty("message", "GET 요청을 받았으며, 데이터를 처리했습니다.");
	
	        // JSON 출력
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        PrintWriter writer = resp.getWriter();
	        writer.print(json.toString());
		}
}
