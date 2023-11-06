package kr.co.lotteon.response.admin.product;

import kr.co.lotteon.entity.product.ProductCate1Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class Cate1DictAPIResponse {
    private String cate1Name; // cate1 이름 + pk 값 맨 뒤에 붙여서 pk를 같이 실어서 보낸다.
    private List<Cate2DictAPIResponse> cate2ListDto;

    public Cate1DictAPIResponse(ProductCate1Entity cate1) {
        this.cate1Name = cate1.getC1Name()+ " "+cate1.getCate1();
        this.cate2ListDto = cate1.getCate2s().stream()
                .map(Cate2DictAPIResponse::new)
                .collect(Collectors.toList());
    }
}
