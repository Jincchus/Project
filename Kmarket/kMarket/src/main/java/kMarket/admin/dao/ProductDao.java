package kMarket.admin.dao;

import kMarket.admin.db.SqlProduct;
import kMarket.admin.request.ProductCreateRequest;
import kMarket.cs.db.DBhelper;

public class ProductDao extends DBhelper {
    private static ProductDao INSTANCE = new ProductDao();
    public static ProductDao getInstance() {
        return INSTANCE;
    }

    private ProductDao() {

    }

    public void insertProduct(ProductCreateRequest dto) {
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(SqlProduct.INSERT_PRODUCT);
            psmt.setString(1, dto.getProdCate1());
            psmt.setString(2, dto.getProdCate2());
            psmt.setString(3, dto.getProdName());
            psmt.setString(4, dto.getDescript());
            psmt.setString(5, dto.getCompany());
            psmt.setInt(6, dto.getPrice());
            psmt.setInt(7, dto.getDiscount());
            psmt.setInt(8, dto.getPoint());
            psmt.setInt(9, dto.getStock());
            psmt.setString(10, dto.getSeller());
            psmt.setInt(11, dto.getDelivery());
            psmt.setString(12, dto.getThumb1());
            psmt.setString(13, dto.getThumb2());
            psmt.setString(14, dto.getThumb3());
            psmt.setString(15, dto.getDetail());
            psmt.setString(16, dto.getStatus());
            psmt.setString(17, dto.getDuty());
            psmt.setString(18, dto.getReceipt());
            psmt.setString(19, dto.getBizType());
            psmt.setString(20, dto.getOrigin());
            psmt.setString(21, dto.getIp());
            psmt.executeUpdate();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
