package kr.co.lotteon.request.product;

import kr.co.lotteon.dto.product.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductOrderItemRequest {

    List<ProductDTO> products;
}


