package phoenix.partyquest.domain.party.study.map;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.Study;

@Entity
@Getter
@NoArgsConstructor
public class LikedStudyMemberMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public LikedStudyMemberMap(Study study, Member member) {
        this.study = study;
        this.member = member;
    }

    public void allocateMember(Member member) {
        this.member = member;
    }

    public void deallocateMember(Member member) {
        this.member = null;
    }
}
