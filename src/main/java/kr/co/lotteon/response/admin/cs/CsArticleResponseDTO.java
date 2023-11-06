package kr.co.lotteon.response.admin.cs;


import kr.co.lotteon.entity.admin.cs.CsArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class CsArticleResponseDTO {

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

    private String status;

    public CsArticleEntity  toEntity(){
        return CsArticleEntity.builder()
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

    public CsArticleResponseDTO(CsArticleEntity article){
        this.articleId = article.getArticleId();
        this.cate = article.getCate();
        this.menu1 = article.getMenu1();
        this.menu2 = article.getMenu2();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.hit =article.getHit();
        this.uid = article.getUid();
        this.regip = article.getRegip();
        this.rdate = article.getRdate();
        this.status = article.getStatus().name();
    }
}
