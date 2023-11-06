package kr.co.lotteon.repository.admin.product.presentation;

import lombok.*;

@Getter @Setter
@ToString(of = {"prodName","prodNo","company","sellerName"})
@NoArgsConstructor
public class ProductSearchCond {
    private String prodName;
    private String descript;
    private Integer prodNo;
    private String company;
    private String sellerName;
    private Integer pageSize;
    private Integer priceMin;
    private Integer priceMax;

    @Builder
    public ProductSearchCond(String prodName, String descript, Integer prodNo, String company, String sellerName, Integer pageSize, Integer priceMin, Integer priceMax) {
        this.prodName = prodName;
        this.descript = descript;
        this.prodNo = prodNo;
        this.company = company;
        this.sellerName = sellerName;
        this.pageSize = pageSize;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }
}
