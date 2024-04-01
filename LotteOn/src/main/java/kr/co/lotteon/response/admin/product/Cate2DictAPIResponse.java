package kr.co.lotteon.response.admin.product;

import kr.co.lotteon.entity.product.ProductCate2Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Cate2DictAPIResponse {
    private Integer cate2Id;
    private String cate2Name;

    public Cate2DictAPIResponse(ProductCate2Entity cate2) {
        this.cate2Id = cate2.getCate2();
        this.cate2Name = cate2.getC2Name();
    }
}
