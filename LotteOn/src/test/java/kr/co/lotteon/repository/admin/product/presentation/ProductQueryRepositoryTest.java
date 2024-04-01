package kr.co.lotteon.repository.admin.product.presentation;

import kr.co.lotteon.repository.admin.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductQueryRepositoryTest {
    @Autowired
    ProductQueryRepository productQueryRepository;

    @Test
    @DisplayName("불러오자")
    void fetchWithPaging() {

    }
}