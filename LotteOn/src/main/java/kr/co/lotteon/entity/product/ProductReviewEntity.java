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
@Table(name="km_product_review") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int revNo;
    private int prodNo;     // product 테이블 외래키 annotation 확인
    private String content;
    private String uid;     // member 테이블 외래키 annotation 확인
    private int rating;
    private String regip;
    @CreationTimestamp
    private LocalDateTime rdate;
}
