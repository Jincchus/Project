package kr.co.lotteon.response.product;

import kr.co.lotteon.dto.product.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ProductOrderResponse {
    private List<ProductDTO> products;
}

