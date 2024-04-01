package kr.co.lotteon.repository.admin.cs;

import kr.co.lotteon.entity.admin.cs.CsArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsArticleRepository extends JpaRepository<CsArticleEntity, Integer> {

    public Page<CsArticleEntity> findByCate(String cate, Pageable pageable);

    public Page<CsArticleEntity> findByCateAndMenu1(String cate, String menu1,Pageable pageable);

    public Page<CsArticleEntity> findByCateAndMenu1AndMenu2(String cate, String menu1, String menu2, Pageable pageable);

}
