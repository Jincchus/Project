package kr.co.lotteon.request.admin.product;

import kr.co.lotteon.repository.admin.product.presentation.ProductSearchCond;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString(of={"searchField","searchValue"})
public class ProductSearchFieldRequest  {
    private String searchField;
    private String searchValue;
    private Integer pageSize;
    public ProductSearchFieldRequest() {
        this.searchField = "none";
        if (this.pageSize == null) {
            this.pageSize = 10;
        }
    }

    public ProductSearchCond toSearchCond() {
        if (searchField == null) {
            return new ProductSearchCond();
        }
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

        productBuilder.pageSize(pageSize);

        return productBuilder.build();
    }
}

