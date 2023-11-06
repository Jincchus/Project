package kr.co.lotteon.response.admin.cs;

import kr.co.lotteon.entity.admin.cs.CsArticleComment;
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
public class CsArticleCommentResponse {


    private int commentId;
    private String uid;
    private String content;
    private int articleId;
    @CreationTimestamp
    private LocalDateTime rdate;


    public CsArticleComment toEntity(){
        return CsArticleComment.builder()
                .commentId(commentId)
                .uid(uid)
                .content(content)
                .articleId(articleId)
                .rdate(rdate)
                .build();
    }

}
