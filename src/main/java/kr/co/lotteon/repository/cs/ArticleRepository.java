package kr.co.lotteon.repository.cs;

import kr.co.lotteon.entity.cs.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity,Integer> {
    public List<ArticleEntity> findArticlesByCate(String cate);
    
    //게시글 저장
    ArticleEntity save(ArticleEntity articleEntity);

    //게시글 조회
    //List<ArticleEntity> findall();

    //게시글 아이디로 조회
    //ArticleEntity findById(int article_id);

    //게시글 삭제
    //void deleteById(int article_id)
}
