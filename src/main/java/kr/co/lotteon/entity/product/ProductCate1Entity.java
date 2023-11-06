package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_product_cate1") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductCate1Entity {

    @Id
    private int cate1;
    private String c1Name;

    @OneToMany(mappedBy = "prodCate1",cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    @OneToMany(mappedBy = "cate1",fetch = FetchType.LAZY)
    private List<ProductCate2Entity> cate2s = new ArrayList<>();

    public void addProduct(ProductEntity productEntity) {
        this.products.add(productEntity);
        productEntity.setProdCate1(this);
    }
}
