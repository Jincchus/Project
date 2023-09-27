package kMarket.product.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.cs.db.DBhelper;
import kMarket.cs.db.ProductSQL;
import kMarket.product.dto.OrderItemDTO;

public class ProductOrderItemDAO extends DBhelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Order 기본 CRUD
	public void insertOrderItem(OrderItemDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.INSERT_ORDER_ITEM);
			psmt.setInt(1, dto.getOrdNo());
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
			logger.error("insertOrderItem() : " + e.getMessage());
		}
	};
	public OrderItemDTO selectOrderItem(String ordNo) {
		return null;
	};
	public List<OrderItemDTO> selectOrderItems() {
		return null;
	};
	public void updateOrderItem(OrderItemDTO dto) {};
	public void deleteOrderItem(String ordNo) {};
}
