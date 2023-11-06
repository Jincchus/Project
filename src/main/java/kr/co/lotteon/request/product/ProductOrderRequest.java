package kr.co.lotteon.request.product;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductOrderEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Slf4j
public class ProductOrderRequest {

    private int ordNo ;
    private String ordUid ;
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

    public ProductOrderEntity toEntity(Member uid){

        return ProductOrderEntity.builder()
                .ordNo(ordNo)
                .ordUid(uid)//uid는 멤버에서 join되므로 Member를 선언해준다.
                .ordCount(ordCount)
                .ordPrice(ordPrice)
                .ordDiscount(ordDiscount)
                .ordDelivery(ordDelivery)
                .savePoint(savePoint)
                .usedPoint(usedPoint)
                .ordTotPrice(ordTotPrice)
                .recipName(recipName)
                .recipHp(recipHp)
                .recipZip(recipZip)
                .recipAddr1(recipAddr1)
                .recipAddr2(recipAddr2)
                .ordPayment(ordPayment)
                .ordDate(ordDate)
                .build();
    }

}
