package kr.co.lotteon.response.product;

import kr.co.lotteon.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class ProductViewResponse {

    private int prodNo;
    private String prodName;
    private String descript;
    private String company;
    private String seller;
    private int price;
    private int discount;   // default : 0
    private int point;      // default : 0
    private int stock;      // default : 0
    private int sold;       // default : 0
    private int delivery;   // default : 0
    private int hit;        // default : 0
    private int score;      // default : 0
    private int review;     // default : 0
    private String thumb1;
    private String thumb2;
    private String thumb3;
    private String detail;
    private String status;  // default : '새상품'
    private String duty;    // default : '과세상품'
    private String receipt; // default : '발행가능 - 신용카드 전표, 온라인 현금영수증'
    private String bizType; // default : '사업자 판매자'
    private String origin;  // default : '상세설명참고'
    private String ip;
    private String rdate;

    public ProductViewResponse(ProductEntity product) {
        this.prodNo = product.getProdNo();
        this.prodName = product.getProdName();
        this.descript = product.getDescript();
        this.company = product.getCompany();
        this.seller = product.getSeller().getUid();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.point = product.getPoint();
        this.stock = product.getStock();
        this.sold = product.getSold();
        this.delivery = product.getDelivery();
        this.hit = product.getHit();
        this.score = product.getScore();
        this.review = product.getReview();
        this.thumb1 = product.getThumb1().getStoredFileName();
        this.thumb2 = product.getThumb2().getStoredFileName();
        this.thumb3 = product.getThumb3().getStoredFileName();
        this.detail = product.getDetail().getStoredFileName();
        this.status = product.getStatus();
        this.duty = product.getDuty();
        this.receipt = product.getReceipt();
        this.bizType = product.getBizType();
        this.origin = product.getOrigin();
        this.ip = product.getIp();
        this.rdate = String.valueOf(product.getRdate());
    }
}
