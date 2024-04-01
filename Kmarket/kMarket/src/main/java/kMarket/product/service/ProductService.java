package kMarket.product.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.product.dao.ProductDAO;
import kMarket.product.dto.FileDTO;
import kMarket.product.dto.ProductDTO;
import kMarket.product.dto.ReviewDTO;




public enum ProductService {
	
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ProductDAO dao = new ProductDAO();
	
	// Product 기본 CRUD
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	public ProductDTO selectProduct(String prodNo) {
		return dao.selectProduct(prodNo);
	}
	public List<ProductDTO> selectProducts(String cate2, int start) {
		return dao.selectProducts(cate2,start);
	}
	public void updateProduct(ProductDTO dto) {
		dao.updateProduct(dto);
	}
	public void deleteProduct(int prodNo) {
		dao.deleteProduct(prodNo);
	}
	
	/* 파일 */
	// 파일 업로드 경로
	public String getFilePath(HttpServletRequest req, String dir) {
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath(dir);
		
		return path;
	}

	// 파일 다운로드
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO dto) throws IOException {
		// response 파일 다운로드 헤더 수정
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getOfile(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// response 파일 스트림 작업
		String path = getFilePath(req,"/upload");
		logger.debug("path" + path);
		File file = new File(path+"/"+dto.getSfile());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());		
		
		while(true){
			int data = bis.read();
			if(data == -1){
				break;
			}
			bos.write(data);
		}
		
		bos.close();
		bis.close();
	}
	

	/* 페이징 */
	// 전체 게시물 갯수 조회
	public int selectCountTotal(String cate2) {
		return dao.selectCountTotal(cate2);
	}
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		logger.debug("currentPage : "+currentPage);
		return currentPage;
	}
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		
		logger.debug("lastPageNum : " + lastPageNum);
		logger.debug("lastPageNum total : " + total);
		return lastPageNum;
	}
	// 페이지 그룹 번호
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 +1;
		int pageGroupEnd = currentPageGroup * 10;
		
		logger.debug("currentPageGroup : " + currentPageGroup);
		logger.debug("pageGroupStart : " + pageGroupStart);
		logger.debug("pageGroupEnd : " + pageGroupEnd);
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
			logger.debug("pageGroupEnd : " + pageGroupEnd);
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	// 페이지 시작번호
	public int getPageStartNum(int total, int currentPage) {
		logger.debug("getPageStartNum total : " + total);
		int start = (currentPage - 1) * 10;
		logger.debug("start : " + start);
		logger.debug("total - start : " +( total - start));
		return total - start;
	}
	
	// Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
			
	// Product Review(((!!!!점심시간이후 여기서부터 시작!!! review dao -> product dao로 옮기기)))
	
	public void insertReview(ReviewDTO dto) {
		dao.insertReview(dto);
	}
	public ReviewDTO selectReview(int revNo) {
		return dao.selectReview(revNo);
	}
	public List<ReviewDTO> selectReviews(String prodNo, int start) {
		return dao.selectReviews(prodNo, start);
	}
	public void updateReview(ReviewDTO dto) {
		dao.updateReview(dto);
	}
	public void deleteReview(int revNo) {
		dao.deleteReview(revNo);
	}
}
