package kr.co.lotteon.repository.product.presentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductSearchCondField2 {
    private String searchField;
    private String searchValue;

    public ProductSearchCond2 toSearchCond() {
        ProductSearchCond2.ProductSearchCond2Builder productBuilder = ProductSearchCond2.builder();
        if (searchField.equals("prodName")) {
            productBuilder.prodName(searchValue);
        } else if (searchField.equals("prodNo")) {
            productBuilder.prodNo(Integer.parseInt(searchValue));
        } else if (searchField.equals("company")) {
            productBuilder.company(searchValue);
        } else if (searchField.equals("sellerName")) {
            productBuilder.sellerName(searchValue);
        }
        return productBuilder.build();
    }
}
