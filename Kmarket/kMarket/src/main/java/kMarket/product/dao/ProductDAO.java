package kMarket.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.cs.db.DBhelper;
import kMarket.cs.db.ProductSQL;
import kMarket.product.dto.ProductDTO;
import kMarket.product.dto.ReviewDTO;


public class ProductDAO extends DBhelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 기본 CRUD 
	public void insertProduct(ProductDTO dto) {}
	public ProductDTO selectProduct(String prodNo) {
		ProductDTO dto = new ProductDTO();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_PRODUCT); 
			psmt.setString(1, prodNo);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setProdNo(rs.getInt(1));
				dto.setProdCate1(rs.getString(2));
				dto.setProdCate2(rs.getString(3));
				dto.setProdName(rs.getString(4));
				dto.setDescript(rs.getString(5));
				dto.setCompany(rs.getString(6));
				dto.setSeller(rs.getString(7));
				dto.setPrice(rs.getInt(8));
				dto.setDiscount(rs.getInt(9));
				dto.setPoint(rs.getInt(10));
				dto.setStock(rs.getInt(11));
				dto.setSold(rs.getInt(12));
				dto.setDelivery(rs.getInt(13));
				dto.setHit(rs.getInt(14));
				dto.setScore(rs.getInt(15));
				dto.setReview(rs.getInt(16));
				dto.setThumb1(rs.getString(17));
				dto.setThumb2(rs.getString(18));
				dto.setThumb3(rs.getString(19));
				dto.setDetail(rs.getString(20));
				dto.setStatus(rs.getString(21));
				dto.setDuty(rs.getString(22));
				dto.setReceipt(rs.getString(23));
				dto.setBizType(rs.getString(24));
				dto.setOrigin(rs.getString(25));
				dto.setIp(rs.getString(26));
				dto.setRdate(rs.getString(27));
				
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectProduct() error : " + e.getMessage());
		}
		return dto;
	}
	
	public List<ProductDTO> selectProducts(String cate2, int start) {
		List<ProductDTO> products = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_PRODUCTS);
			psmt.setString(1, cate2);
			psmt.setInt(2, start);
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				ProductDTO dto = new ProductDTO();
				
				dto.setProdNo(rs.getInt(1));
				dto.setProdCate1(rs.getString(2));
				dto.setProdCate2(rs.getString(3));
				dto.setProdName(rs.getString(4));
				dto.setDescript(rs.getString(5));
				dto.setCompany(rs.getString(6));
				dto.setSeller(rs.getString(7));
				dto.setPrice(rs.getInt(8));
				dto.setDiscount(rs.getInt(9));
				dto.setPoint(rs.getInt(10));
				dto.setStock(rs.getInt(11));
				dto.setSold(rs.getInt(12));
				dto.setDelivery(rs.getInt(13));
				dto.setHit(rs.getInt(14));
				dto.setScore(rs.getInt(15));
				dto.setReview(rs.getInt(16));
				dto.setThumb1(rs.getString(17));
				dto.setThumb2(rs.getString(18));
				dto.setThumb3(rs.getString(19));
				dto.setDetail(rs.getString(20));
				dto.setStatus(rs.getString(21));
				dto.setDuty(rs.getString(22));
				dto.setReceipt(rs.getString(23));
				dto.setBizType(rs.getString(24));
				dto.setOrigin(rs.getString(25));
				dto.setIp(rs.getString(26));
				dto.setRdate(rs.getString(27));
				
				products.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectProducts()error : " + e.getMessage());
		}
		
		return products;
	}
	public void updateProduct(ProductDTO dto) {
		
	}
	public void deleteProduct(int prodNo) {
		
	}

	/* 게시물 페이징처리 */
	// 게시물 전체 갯수 조회
	public int selectCountTotal(String cate2) {
		int total = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate2);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountTotal() error : " + e.getMessage());
		}
		return total;
	}
	
	/* 리뷰 */
	// 리뷰 기본 CRUD
	public void insertReview(ReviewDTO dto) {}
	public ReviewDTO selectReview(int revNo) {
		ReviewDTO dto = new ReviewDTO();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_REVIEW);
			psmt.setInt(1, revNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setRevNo(rs.getInt(1));
				dto.setProdNo(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setUid(rs.getString(4));
				dto.setRating(rs.getInt(5));
				dto.setRegip(rs.getString(6));
				dto.setRdate(rs.getString(7));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectReview() error : " + e.getMessage());
		}
		return dto;
	}
	public List<ReviewDTO> selectReviews(String prodNo, int start) {
		List<ReviewDTO> reviews = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_REVIEWS);
			psmt.setString(1, prodNo);
			psmt.setInt(2, start);
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				ReviewDTO dto = new ReviewDTO();
			
				dto.setRevNo(rs.getInt(1));
				dto.setProdNo(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setUid(rs.getString(4));
				dto.setRating(rs.getInt(5));
				dto.setRegip(rs.getString(6));
				dto.setRdate(rs.getString(7));
				dto.setProdName(rs.getString(8));
				
				logger.debug(dto.toString());
				reviews.add(dto);
			}
			close();
		}catch (Exception e) {
			logger.error("selectReviews() error : " + e.getMessage());
		}
		return reviews;
		
	}
	public void updateReview(ReviewDTO dto) { }
	public void deleteReview(int revNo) { }
		
}
