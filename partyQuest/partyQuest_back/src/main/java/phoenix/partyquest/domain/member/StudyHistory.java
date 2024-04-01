package phoenix.partyquest.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.profile.MemberProfile;
import phoenix.partyquest.domain.party.study.Study;

@Entity
@Getter
@NoArgsConstructor
public class StudyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_profile_id")
    private MemberProfile memberProfile;

    @Enumerated(EnumType.STRING)
    private StudyHistoryStatus historyStatus;

    //TODO: party 구현하기
    @Builder
    public StudyHistory(Study study, StudyHistoryStatus historyStatus) {
        this.study = study;
        this.historyStatus = historyStatus == null? StudyHistoryStatus.ONGOING : historyStatus;
    }

    public void allocateMember(MemberProfile memberProfile) {
        this.memberProfile = memberProfile;
    }
    public void changeHistoryStatus(StudyHistoryStatus status) {
        this.historyStatus = status;
    }


}
