package kr.co.lotteon.repository.product.presentation;

import lombok.*;

@Getter @Setter
@ToString(of = {"prodName","prodNo","company","sellerName"})
@NoArgsConstructor
public class ProductSearchCond2 {
    private String prodName;
    private String descript;
    private Integer prodNo;
    private String company;
    private String sellerName;
    private Integer pageSize;

    @Builder
    public ProductSearchCond2(String prodName, Integer prodNo, String company, String sellerName, Integer pageSize, String descript) {
        this.prodName = prodName;
        this.descript = descript;
        this.company = company;
        this.sellerName = sellerName;
        this.pageSize = pageSize;
        this.prodNo = prodNo;
    }
}
