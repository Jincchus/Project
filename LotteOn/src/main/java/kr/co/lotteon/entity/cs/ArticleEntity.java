package kr.co.lotteon.entity.cs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.lotteon.controller.dto.ArticleDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "km_article")
public class ArticleEntity {
    @Id
    private int article_id;
    private String cate;
    private String menu1;
    private String menu2;
    private String title;
    private String content;
    private int file;
    private int hit;
    private String uid;
    private String regip;
    private LocalDateTime rdate;

    public ArticleDTO toDTO(){
        return ArticleDTO.builder()
                .cate(cate)
                .menu1(menu1)
                .menu2(menu2)
                .title(title)
                .content(content)
                .uid(uid)
                .regip(regip)
                .rdate(rdate)
                .build();
    }
}
