package kr.co.lotteon.repository.admin.product.presentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductSearchCondField {
    private String searchField;
    private String searchValue;

    public ProductSearchCond toSearchCond() {
        ProductSearchCond.ProductSearchCondBuilder productBuilder = ProductSearchCond.builder();
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
