package kr.co.lotteon.entity.admin.cs;

import jakarta.persistence.*;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequestDTO;
import kr.co.lotteon.response.admin.cs.CsArticleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_article")
public class CsArticleEntity {
// NOTI: 스토리보드에 file항목이 없으므로 km_article에서 file 항목 삭제
/*
 TODO: 현재 primary key로 사용되고 있는 article_id를 ano나 articleId로 이름을 변경하는 것이 어떤지 얘기하기
        왜냐하면 스네이크표기법("_")으로 인해서 page처리를 할 때 참조를 못하는 것 같음
        스네이크 표기법이 사용되는 곳 CsArticleService 56번 째 코드
        참고자료: https://nsmchan.tistory.com/30
 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    private String cate;
    private String menu1;
    private String menu2;
    private String title;
    private String content;
    private int hit;
    private String uid;

    private String regip;

    @CreationTimestamp
    private LocalDateTime rdate;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

//      jpa연관관계 매핑
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "articleId")
//    private List<CsArticleComment> comments = new ArrayList<>();

    public CsArticleCreateRequestDTO reqToEntity() {

        return CsArticleCreateRequestDTO.builder()
                .articleId(articleId)
                .cate(cate)
                .menu1(menu1)
                .menu2(menu2)
                .title(title)
                .content(content)
                .hit(hit)
                .uid(uid)
                .regip(regip)
                .rdate(rdate)
                .build();
    }

    public CsArticleResponseDTO respToEntity(){
        return CsArticleResponseDTO.builder()
                .articleId(articleId)
                .cate(cate)
                .menu1(menu1)
                .menu2(menu2)
                .title(title)
                .content(content)
                .hit(hit)
                .uid(uid)
                .regip(regip)
                .rdate(rdate)
                .build();
    }

}
