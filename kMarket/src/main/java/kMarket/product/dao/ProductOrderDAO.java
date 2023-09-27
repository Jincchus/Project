package kMarket.product.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.cs.db.DBhelper;
import kMarket.product.dto.OrderDTO;

public class ProductOrderDAO extends DBhelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Order 기본 CRUD
	public void insertOrder(OrderDTO dto) {
	};
	public OrderDTO selectOrder(String ordNo) {
		return null;
	};
	public List<OrderDTO> selectOrders() {
		return null;
	};
	public void updateOrder(OrderDTO dto) {};
	public void deleteOrder(String ordNo) {};
}
