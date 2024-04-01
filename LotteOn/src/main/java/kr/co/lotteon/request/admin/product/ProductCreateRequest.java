package kr.co.lotteon.request.admin.product;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCate1Entity;
import kr.co.lotteon.entity.product.ProductCate2Entity;
import kr.co.lotteon.entity.product.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
public class ProductCreateRequest {
    private Integer prodCate1_id;  // km_product_cate2 테이블 외래키 annotation 확인
    private Integer prodCate2_id;
    private String prodName;
    private String descript;
    private String company;
    private String seller_id; //uid로 넘어오게
    private int price;
    private int discount;   // default : 0
    private int point;      // default : 0
    private int stock;      // default : 0
    private int sold;       // default : 0
    private int delivery;   // default : 0
    private int hit;        // default : 0
    private int score;      // default : 0
    private int review;     // default : 0
    private MultipartFile thumb1;
    private MultipartFile thumb2;
    private MultipartFile thumb3;
    private MultipartFile detail;
    private String status;  // default : '새상품'
    private String duty;    // default : '과세상품'
    private String receipt; // default : '발행가능 - 신용카드 전표, 온라인 현금영수증'
    private String bizType; // default : '사업자 판매자'
    private String origin;  // default : '상세설명참고'
    private String ip;

    /**
     * ip,multifiles는 컨트롤러에서 따로 바인딩한다.
     * @param cate1
     * @param cate2
     * @param seller
     * @return
     */
    public ProductEntity.ProductEntityBuilder toEntityBuilder(ProductCate1Entity cate1, ProductCate2Entity cate2, Member seller) {
        return ProductEntity.builder()
                .prodCate1(cate1)
                .prodCate2(cate2)
                .prodName(prodName)
                .descript(descript)
                .company(company)
                .seller(seller)
                .price(price)
                .discount(discount)
                .point(point)
                .stock(stock)
                .sold(sold)
                .delivery(delivery)
                .hit(hit)
                .score(score)
                .review(review)
                .status(status)
                .duty(duty)
                .receipt(receipt)
                .bizType(bizType)
                .origin(origin)
                .ip(ip);
    }
}
