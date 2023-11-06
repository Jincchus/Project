package kr.co.lotteon.response.product;

import kr.co.lotteon.entity.member.MemberLevel;
import kr.co.lotteon.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@Log4j2
public class ProductListResponse {

    private int prodNo;
    private String prodName;
    private String descript;
    private String seller;
    private String level;
    private int price;
    private int discount;   // default : 0
    private int delivery;   // default : 0
    private int score;
    private String thumb1;

    public ProductListResponse(ProductEntity product) {
        this.prodNo = product.getProdNo();
        this.prodName = product.getProdName();
        this.descript = product.getDescript();
        this.seller = product.getSeller().getUid();
        this.level = product.getSeller().getLevel().name();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.delivery = product.getDelivery();
        this.score = product.getScore();
        this.thumb1 = product.getThumb1().getStoredFileName();
    }

    public ProductListResponse(int prodNo, String prodName, String descript, String seller, MemberLevel level, int price, int discount, int delivery, int score, String thumb1) {
        this.prodNo = prodNo;
        this.prodName = prodName;
        this.descript = descript;
        this.seller = seller;
        this.level = level.name();
        this.price = price;
        this.discount = discount;
        this.delivery = delivery;
        this.score = score;
        this.thumb1 = thumb1;
    }
}
