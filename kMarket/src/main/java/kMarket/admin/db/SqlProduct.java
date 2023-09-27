package kMarket.admin.db;

public class SqlProduct {
    public final static String INSERT_PRODUCT = """
			INSERT INTO `km_product` SET  
			`prodCate1`=?,
			`prodCate2`=?,
			`prodName`=?,
			`descript`=?,
			`company`=?,
			`price`=?,
			`discount`=?,
			`point`=?,
			`stock`=?,
			`seller`=?,
			`delivery`=?,
			`thumb1`=?,
			`thumb2`=?,
			`thumb3`=?,
			`detail`=?,
			`status`=?,
			`duty`=?,
			`receipt`=?,
			`bizType`=?,
			`origin`=?,
			`ip` =?,
			`rdate` = NOW()""";
}
