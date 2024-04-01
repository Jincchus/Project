package phoenix.partyquest.domain.party.study;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.Member;

@Entity@Getter
@NoArgsConstructor
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Builder
    public StudyMember(Member member, Study study) {
        this.member = member;
        this.study = study;
    }
}
