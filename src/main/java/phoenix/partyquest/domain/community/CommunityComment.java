package phoenix.partyquest.domain.community;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import phoenix.partyquest.domain.member.Member;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class CommunityComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_email")
    private Member writer;

    private String content;


    // OneToMany 관계같은데 설정이 안됨 확이하기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community communityId;

    @CreationTimestamp
    private LocalDateTime rdate;

}