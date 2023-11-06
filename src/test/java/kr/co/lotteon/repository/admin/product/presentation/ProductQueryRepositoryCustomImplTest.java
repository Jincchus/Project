package kr.co.lotteon.repository.admin.product.presentation;

import jakarta.persistence.EntityManager;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.member.MemberRole;
import kr.co.lotteon.entity.product.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductQueryRepositoryCustomImplTest {
    @Autowired
    ProductQueryRepository productQueryRepository;
    @Autowired
    EntityManager em;


    @BeforeEach
    void beforeEach() {
        Member findMember = em.createQuery("select m from Member m where m.role = :role", Member.class)
                .setParameter("role", MemberRole.ROLE_SELLER)
                .getSingleResult();

        for (int i = 0; i < 100; i++) {
            ProductEntity.builder()
                    .prodName(String.format("testProd-%d",i))
                    .seller(findMember)
                    .
        }
    }
}