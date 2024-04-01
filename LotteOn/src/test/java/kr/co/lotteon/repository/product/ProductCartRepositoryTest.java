package kr.co.lotteon.repository.product;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCartEntity;
import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.repository.member.MemberRepository;
import org.apache.ibatis.annotations.Param;
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
class ProductCartRepositoryTest {

    @Autowired
    ProductCartRepository productCartRepository;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("productCartTest 1 ")
    void findCartByIdTest(){
        List<Member> members = memberRepository.findAll();
        Member findMember = members.get(0);
        String requestUid = findMember.getUid();

        List<ProductCartEntity> result = productCartRepository.findCartById(requestUid);
        Assertions.assertThat(result.size()).isGreaterThan(0);
    }

}