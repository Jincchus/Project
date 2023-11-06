package kr.co.lotteon.request.product;

import jakarta.validation.constraints.NotEmpty;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCartEntity;
import kr.co.lotteon.entity.product.ProductEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ProductCartRequest {
    @NotEmpty(message = "왜 uid가 비었을까요?")
    String uid;
    int prodNo;
    String prodName;
    String descript;
    int count;
    int price;
    int discount;
    int point;
    int delivery;
    String thumb2;
    int total;

    public ProductCartEntity toEntity(ProductEntity product, Member uid){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProdNo(prodNo);

        return ProductCartEntity.builder()
                .uid(uid) // Member를 참조 중 확인!!
                .product(productEntity) // productEntity 참조중
                .count(count) // DB 컬럼명 : `count` , request data : num
                .price(price)
                .discount(discount)
                .point(point)
                .delivery(delivery)
                .total(total)
                .build();

    }

}
