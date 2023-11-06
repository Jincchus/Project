package kr.co.lotteon.entity.admin.cs;


import jakarta.persistence.*;
import kr.co.lotteon.request.admin.cs.CsArticleCommentRequest;
import kr.co.lotteon.response.admin.cs.CsArticleCommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_comment")
public class CsArticleComment {
//NOTI:km_comment table에서 modified_date 컬럼은 삭제하는 것이 좋을 것 같음
//NOTI:왜냐하면 수정을 할 경우 이전에 적은 날짜가 필요없을 것 같기 때문.
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "comment_id")
 private int commentId;

 private String uid;
 private String content;

 //jpa 연관관계
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "articleId")
// private CsArticleEntity articleId;

 @Column(name = "article_id")
 private int articleId;

 @CreationTimestamp
 private LocalDateTime rdate;
//NOTI: 정방향 ,역방향참조, jpa연관관계 매핑 (OneToMany,ManyToOne, OneToONe

 public CsArticleCommentRequest reqToEntity(){
  return CsArticleCommentRequest.builder()
          .commentId(commentId)
          .uid(uid)
          .content(content)
          .articleId(articleId)
          .rdate(rdate)
          .build();
 }
 public CsArticleCommentResponse respToEntity(){
  return CsArticleCommentResponse.builder()
          .commentId(commentId)
          .uid(uid)
          .content(content)
          .articleId(articleId)
          .rdate(rdate)
          .build();
 }




}
