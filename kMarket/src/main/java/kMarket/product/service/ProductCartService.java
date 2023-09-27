package kMarket.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.product.dao.ProductCartDAO;
import kMarket.product.dto.CartDTO;

public enum ProductCartService {
	INSTANCE;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ProductCartDAO dao = new ProductCartDAO();
	
	// Cart 기본 CRUD
	public void insertCart(CartDTO dto) {
		dao.insertCart(dto);
	}
	public CartDTO selectCart(String cartNo) {
		return dao.selectCart(cartNo);
	}
	public List<CartDTO> selectCarts(String uid) {
		return dao.selectCarts(uid);
	}
	public void updateCart(CartDTO dto) {
		dao.updateCart(dto);
	}
	public void deleteCart(String cartNo) {
		dao.deleteCart(cartNo);
	}
}
