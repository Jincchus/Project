package kMarket.cs.db;

public class ProductSQL {
	
	// Product
	public static final String SELECT_PRODUCT = "SELECT * FROM `km_product` WHERE `prodNo` = ?";
	public static final String SELECT_PRODUCTS = "SELECT * FROM `km_product` WHERE `prodCate2` =? Order By `prodNo` DESC LIMIT ?, 10 " ;
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `km_product` WHERE `prodCate2` = ?";
	
	// Review
	public static final String SELECT_REVIEW = "SELECT * FROM `km_product_review` WHERE `revNo` = ?";
	public static final String SELECT_REVIEWS = "SELECT "
												+ "a.*, "
												+ "b.`prodName` "
												+ "FROM `km_product_review` AS a "
												+ "JOIN `km_product` AS b "
												+ "ON a.`prodNo` = b.`prodNo` "
												+ "WHERE a.`prodNo` =? Order By `revNo` DESC LIMIT ?, 10 " ;
	// Cart
	public static final String INSERT_CART = "INSERT INTO `km_product_cart` SET "
											+ "`uid` = ?, "
											+ "`prodNo` = ?, "
											+ "`count` = ?, "
											+ "`price` = ?, "
											+ "`discount` = ?, "
											+ "`point` = ?, "
											+ "`delivery` = ?, "
											+ "`total` = ?, "
											+ "`rdate` = NOW() ";
	public static final String SELECT_CARTS = "SELECT "
											+ "a.*, "
											+ "b.`thumb2`, b.`prodName`, b.`descript` "
											+ "FROM `km_product_cart` AS a "
											+ "JOIN `km_product` AS b "
											+ "ON a.`prodNo` = b.`prodNo` "
											+ "WHERE a.`uid` = ? ";

	// Order Item
	public static final String INSERT_ORDER_ITEM = "INSERT INTO `km_product_cart_item` "
												 + "`ordNo` = ?, "
												 + "`prodNo` = ?, "
												 + "`count` = ?, "
												 + "`price` = ?, "
												 + "`discount` = ?, "
												 + "`point` = ?, "
												 + "`delivery` =?, "
												 + "`total` =? ";
}
