package kr.co.lotteon.request.product;

import kr.co.lotteon.repository.product.presentation.ProductSearchCond2;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// KJ ADMIN PRODUCT 복사 부분 -> PRODUCT SEARCH에 맞춰 변경 필요!!!
@Getter
@Setter
@ToString(of={"searchField","searchValue"})
public class ProductSearchFieldRequest2 {
    private String searchField;
    private String searchValue;
    private Integer pageSize;
    public ProductSearchFieldRequest2() {
        this.searchField = "none";
        this.pageSize = 10;
    }

    public ProductSearchCond2 toSearchCond() {
        if (searchField == null) {
            return new ProductSearchCond2();
        }
        ProductSearchCond2.ProductSearchCond2Builder productBuilder2 = ProductSearchCond2.builder();
        if (searchField.equals("prodName")) {
            productBuilder2.prodName(searchValue);
        } else if (searchField.equals("prodNo")) {
            productBuilder2.prodNo(Integer.parseInt(searchValue));
        } else if (searchField.equals("company")) {
            productBuilder2.company(searchValue);
        } else if (searchField.equals("sellerName")) {
            productBuilder2.sellerName(searchValue);
        }

        productBuilder2.pageSize(pageSize);

        return productBuilder2.build();
    }
}

