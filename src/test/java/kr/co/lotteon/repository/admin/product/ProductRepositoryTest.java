package kr.co.lotteon.repository.admin.product;

import kr.co.lotteon.entity.product.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("프로덕트 첫 테스트 findAll()")
    void listTest() {
        List<ProductEntity> result = productRepository.findAll();
        Assertions.assertThat(result.size())
                .isGreaterThan(0);
    }
}