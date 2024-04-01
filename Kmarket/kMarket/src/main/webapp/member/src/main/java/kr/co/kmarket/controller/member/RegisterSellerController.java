package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.service.MemberService;

@WebServlet("/member/registerSeller.do")
public class RegisterSellerController extends HttpServlet {

	private static final long serialVersionUID = -2026989742877369639L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			//판매회원 가입페이지로 이동
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/registerSeller.jsp");
			dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid     = req.getParameter("seller_uid");
		String pass1   = req.getParameter("seller_pass1");
		String company = req.getParameter("seller_company");
		String ceo     = req.getParameter("seller_ceo");
		String reg     = req.getParameter("seller_corp_reg");
		String oReg    = req.getParameter("seller_online_reg");
		String tel     = req.getParameter("seller_tel");
		String fax     = req.getParameter("seller_fax");
		String email   = req.getParameter("seller_email");
		String zip     = req.getParameter("seller_zip");
		String addr1   = req.getParameter("seller_addr1");
		String addr2   = req.getParameter("seller_addr2");
		String manager = req.getParameter("seller_manager");
		String managerHp = req.getParameter("seller_managerHp");
		String regip   = req.getRemoteAddr();
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(uid);
		dto.setPass(pass1);
		dto.setCompany(company);
		dto.setCeo(ceo);
		dto.setBizRegNum(reg);
		dto.setComRegNum(oReg);
		dto.setTel(tel);
		dto.setFax(fax);
		dto.setEmail(email);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setManager(manager);
		dto.setManagerHp(managerHp);
		dto.setRegip(regip);
		
		service.insertMemberSeller(dto);
		
		logger.debug("RegisterSeller : " + dto.toString());
		
		resp.sendRedirect("/Kmarket/member/login.do?success=200");
	}
}	
