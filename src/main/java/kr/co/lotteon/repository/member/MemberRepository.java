package kr.co.lotteon.repository.member;

import kr.co.lotteon.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {
    @Query("select m from Member m left join fetch m.carts c where m.uid = :uid")
    public Optional<Member> findMemberByIdWithCarts(@Param("uid") String uid);
}
