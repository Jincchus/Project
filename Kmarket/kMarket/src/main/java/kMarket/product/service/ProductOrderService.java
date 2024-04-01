package kMarket.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.product.dao.ProductOrderDAO;
import kMarket.product.dto.OrderDTO;

public enum ProductOrderService {
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductOrderDAO dao = new ProductOrderDAO();
	
	public void insertOrder(OrderDTO dto) {
		dao.insertOrder(dto);;
	};
	public OrderDTO selectOrder(String ordNo) {
		return dao.selectOrder(ordNo);
	};
	public List<OrderDTO> selectOrders() {
		return dao.selectOrders();
	};
	public void updateOrder(OrderDTO dto) {
		dao.updateOrder(dto);
	};
	public void deleteOrder(String ordNo) {
		dao.deleteOrder(ordNo);
	};
}
