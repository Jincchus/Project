package kr.co.lotteon.request.admin.cs;

import kr.co.lotteon.entity.admin.cs.ArticleStatus;
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
public class CsArticleCreateRequestDTO {

    private int articleId;
    private String cate;
    private String menu1;
    private String menu2;
    private String title;
    private String content;
    private String status;
    private int hit;
    private String uid;
    private String regip;
    @CreationTimestamp
    private LocalDateTime rdate;

    public CsArticleEntity toEntity(){

        return CsArticleEntity.builder()
                .articleId(articleId)
                .cate(cate)
                .menu1(menu1)
                .menu2(menu2)
                .title(title)
                .content(content)
                .status(ArticleStatus.검토중)
                .hit(hit)
                .uid(uid)
                .regip(regip)
                .rdate(rdate)
                .build();
    }
}
