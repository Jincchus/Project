package kr.co.lotteon.entity.admin.cs;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_article_cate")
public class CsArticleCateEntity {

    @Id
    @Column(name = "article_cate_id")
    private int articleId;
    private String cate;
    private String menu1;
    private String menu2;
}
