package phoenix.partyquest.domain.party.study.map;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.Study;

@Entity
@Getter @NoArgsConstructor
public class ApplicantStudyMap {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;


    @Builder
    public ApplicantStudyMap(Study study, Member member, ApplicationStatus status) {
        this.study = study;
        this.member = member;
        this.status = status;
    }

    public void changeStatus(ApplicationStatus status){
        this.status=status;
    }
}
