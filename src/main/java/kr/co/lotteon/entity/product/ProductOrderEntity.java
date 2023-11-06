package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
import kr.co.lotteon.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_product_order") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductOrderEntity {
//noti:
//    orderComplete테이블삭제
//    why? -> 어디에 쓰이는지 모르겠어서

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ordUid")
    private Member ordUid;      // member 테이블 외래키 annotation 확인

    private int ordCount;       // default : 0
    private int ordPrice;       // default : 0
    private int ordDiscount;    // default : 0
    private int ordDelivery;    // default : 0
    private int savePoint;      // default : 0
    private int usedPoint;      // default : 0
    private int ordTotPrice;    // default : 0
    private String recipName;
    private String recipHp;
    private String recipZip;
    private String recipAddr1;
    private String recipAddr2;
    private int ordPayment;
    @CreationTimestamp
    private LocalDateTime ordDate;
}
