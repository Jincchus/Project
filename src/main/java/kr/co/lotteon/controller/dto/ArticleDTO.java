package kr.co.lotteon.controller.dto;

import jakarta.persistence.Id;
import kr.co.lotteon.entity.cs.ArticleEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class ArticleDTO {

    private String cate;
    private String menu1;
    private String menu2;
    private String title;
    private String content;
    private String uid;
    private String regip;
    private LocalDateTime rdate;

    public ArticleEntity toEntity(){
        return ArticleEntity.builder()
                .cate(cate)
                .menu1(menu1)
                .menu2(menu2)
                .title(title)
                .content(content)
                .file(0)
                .hit(0)
                .uid(uid)
                .regip(regip)
                .rdate(LocalDateTime.now())

                .build();
    }
}
