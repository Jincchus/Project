package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
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
@Table(name="km_product_order_item") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductOrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ordItem_id")
    private int ordItemId;

    @ManyToOne
    @JoinColumn(name="ordNo")
    private ProductOrderEntity ordNo;  // order 테이블 외래키 annotation 확인

    @ManyToOne
    @JoinColumn(name="prodNo")
    private ProductEntity prodNo; // product 테이블 외래키 annotation 확인


    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;
}
