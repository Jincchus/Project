package kr.co.lotteon.repository.product.presentation;

import kr.co.lotteon.response.product.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepositoryCustom2 {
    Page<ProductListResponse> searchWithPageAndCond(ProductSearchCond2 searchCond, Pageable pageable);
}
