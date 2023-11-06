package kr.co.lotteon.repository.admin.product.presentation;

import kr.co.lotteon.repository.product.presentation.ProductSearchCond2;
import kr.co.lotteon.response.admin.product.ProductAdminListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepositoryCustom {
    Page<ProductAdminListResponse> searchWithPageAndCond(ProductSearchCond searchCond, Pageable pageable);
}
