package kr.co.lotteon.repository.product;

import jakarta.persistence.EntityManager;
import kr.co.lotteon.entity.product.ProductCate1Entity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class Cate1RepositoryTest {
    @Autowired
    Cate1Repository cate1Repository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    //@Rollback(value = false)
    @DisplayName("카테1을 생성한다.")
    void create() {
        List<ProductCate1Entity> firstCateResult = cate1Repository.findAll();
        int firstSize = firstCateResult.size();

        ProductCate1Entity testCate1 = ProductCate1Entity.builder()
                .cate1(99)
                .c1Name("테스트카테")
                .build();
        cate1Repository.save(testCate1);
        em.flush();

        List<ProductCate1Entity> secondResult = cate1Repository.findAll();
        int secondSize = secondResult.size();

        Assertions.assertThat(secondSize).isEqualTo(firstSize + 1);
    }

    @Test
    @DisplayName("테스트코드에서 생성하면 롤백되어야함")
    void iscreate() {

    }
}