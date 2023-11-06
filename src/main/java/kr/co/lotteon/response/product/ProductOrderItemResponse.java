package kr.co.lotteon.response.product;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.entity.product.ProductOrderEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductOrderItemResponse {

//    list<ProductCartResponse>

    private String prodName;
    private String descript;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;
    private String thumb1;


    public ProductOrderItemResponse(ProductCartResponse cartResponse){
        this.prodName = cartResponse.getProdName();
        this.descript = cartResponse.getDescript();
        this.count = cartResponse.getCount();
        this.price = cartResponse.getPrice();
        this.discount = cartResponse.getDiscount();
        this.point = cartResponse.getPoint();
        this.delivery = cartResponse.getDelivery();
        this.total = cartResponse.getTotal();
        this.thumb1 = cartResponse.getThumb1();
    }

}
