package kr.co.lotteon.request.product;

import lombok.Data;

@Data
public class ProductViewRequest {
    private String uid;
    private int prodNo;
    private int cartNo;
    private String prodName;
    private String descript;
    private int price;
    private int count;
    private int discount;
    private int point;
    private int delivery;
    private String thumb1;
    private String thumb2;
    private int total;
}
