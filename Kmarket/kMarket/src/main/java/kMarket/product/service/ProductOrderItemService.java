package kMarket.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.product.dao.ProductOrderItemDAO;
import kMarket.product.dto.OrderItemDTO;

public enum ProductOrderItemService {
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductOrderItemDAO dao = new ProductOrderItemDAO();
	
	public void insertOrderItem(OrderItemDTO dto) {
		dao.insertOrderItem(dto);;
	};
	public OrderItemDTO selectOrderItem(String ordNo) {
		return dao.selectOrderItem(ordNo);
	};
	public List<OrderItemDTO> selectOrderItems() {
		return dao.selectOrderItems();
	};
	public void updateOrderItem(OrderItemDTO dto) {
		dao.updateOrderItem(dto);
	};
	public void deleteOrderItem(String ordNo) {
		dao.deleteOrderItem(ordNo);
	};
}
