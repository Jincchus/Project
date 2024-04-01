package kr.co.lotteon.repository.admin.cs;

import kr.co.lotteon.entity.admin.cs.CsArticleComment;
import kr.co.lotteon.response.admin.cs.CsArticleCommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface CsArticleCommentRepository extends JpaRepository<CsArticleComment, Integer> {

    @Query("select c from CsArticleComment c where c.articleId = :articleId")
    CsArticleComment findCommentByArticleId(@Param("articleId") int articleId);

}
