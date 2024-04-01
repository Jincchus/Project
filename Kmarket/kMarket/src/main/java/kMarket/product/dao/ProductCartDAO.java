package kMarket.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.cs.db.DBhelper;
import kMarket.cs.db.ProductSQL;
import kMarket.product.db.ProdDBHelper;
import kMarket.product.dto.CartDTO;


public class ProductCartDAO extends DBhelper{ // dbhelper 경로 정리
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Cart 기본 CRUD
	public void insertCart(CartDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.INSERT_CART);
			psmt.setString(1, dto.getUid());
			psmt.setInt(2, dto.getProdNo());
			psmt.setInt(3, dto.getCount());
			psmt.setInt(4, dto.getPrice());
			psmt.setInt(5, dto.getDiscount());
			psmt.setInt(6, dto.getPoint());
			psmt.setInt(7, dto.getDelivery());
			psmt.setInt(8, dto.getTotal());
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			logger.error("insertCart() error : " + e.getMessage());
		}
	}
	public CartDTO selectCart(String cartNo) {
		return null;
	}
	public List<CartDTO> selectCarts(String uid) {
		
		List<CartDTO> carts = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_CARTS);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				
				
				dto.setCartNo(rs.getInt(1));
				dto.setUid(rs.getString(2));
				dto.setProdNo(rs.getInt(3));
				dto.setCount(rs.getInt(4));
				dto.setPrice(rs.getInt(5));
				dto.setDiscount(rs.getInt(6));
				dto.setPoint(rs.getInt(7));
				dto.setDelivery(rs.getInt(8));
				dto.setTotal(rs.getInt(9));
				dto.setRdate(rs.getString(10));
				dto.setThumb2(rs.getString(11));
				dto.setProdName(rs.getString(12));
				dto.setDescript(rs.getString(13));
				
				logger.debug(dto.toString());
				
				carts.add(dto);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCarts() : " + e.getMessage());
		}
		
		return carts;
	}
	public void updateCart(CartDTO dto) {
	}
	public void deleteCart(String cartNo) {
	}

}
