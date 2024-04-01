package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "cate1")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_product_cate2") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductCate2Entity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate1")
    private ProductCate1Entity cate1;  // km_product_cate1 테이블 외래키 annotation 확인
    @Id
    private int cate2;
    private String c2Name;

    @OneToMany(mappedBy = "prodCate2")
    private List<ProductEntity> products = new ArrayList<>();

    public void addProduct(ProductEntity product) {
        this.products.add(product);
        product.setProdCate2(this);
    }
}
