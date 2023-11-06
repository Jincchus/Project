package kr.co.lotteon.dto.product;

import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.entity.product.ProductOrderItemEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    // OrderItem insert
    private int prodNo;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;

    private String uid;
    private String prodName;
    private String descript;
    private String thumb1;

    //23.10.29동일
    //entity와의 관계연결 중 prodNo는 productEntity prodNo로 정의되어야 하는데
    //여기서는 int로 정의 되어 있어서 주석처리하였다.
    public ProductOrderItemEntity toOrderItem(ProductEntity prodNo) {
        return ProductOrderItemEntity.builder()
                .prodNo(prodNo)
                .count(count)
                .price(price)
                .discount(discount)
                .point(point)
                .delivery(delivery)
                .total(total)
                .build();
    }
}
