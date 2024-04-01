package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
import kr.co.lotteon.entity.file.UploadFile;
import kr.co.lotteon.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_product") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodCate1")
    private ProductCate1Entity prodCate1;  // km_product_cate2 테이블 외래키 annotation 확인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodCate2")
    private ProductCate2Entity prodCate2;
    private String prodName;
    private String descript;
    private String company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller")
    private Member seller;

    private int price;
    private int discount;   // default : 0
    private int point;      // default : 0
    private int stock;      // default : 0
    private int sold;       // default : 0
    private int delivery;   // default : 0
    private int hit;        // default : 0
    private int score;      // default : 0
    private int review;     // default : 0

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "thumb1")
    private UploadFile thumb1;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "thumb2")
    private UploadFile thumb2;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "thumb3")
    private UploadFile thumb3;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detail")
    private UploadFile detail;

    private String status;  // default : '새상품'
    private String duty;    // default : '과세상품'
    private String receipt; // default : '발행가능 - 신용카드 전표, 온라인 현금영수증'
    private String bizType; // default : '사업자 판매자'
    private String origin;  // default : '상세설명참고'
    private String ip;
    @CreationTimestamp
    private LocalDateTime rdate;


}
