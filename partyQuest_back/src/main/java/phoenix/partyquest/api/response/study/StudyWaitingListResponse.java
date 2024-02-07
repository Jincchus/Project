package phoenix.partyquest.api.response.study;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import phoenix.partyquest.domain.party.location.PartyLocation;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.map.ApplicantStudyMap;
import phoenix.partyquest.domain.party.study.map.ApplicationStatus;

@Getter@Setter
@ToString
@NoArgsConstructor
public class StudyWaitingListResponse {
    //ApplicantStudyMap 테이블에 존재하는 값들을 저장

    private Long memberId;
    private String memberNickName;
    private ApplicationStatus applicationStatus;

    public StudyWaitingListResponse(ApplicantStudyMap applicantStudyMap) {
        this.memberId = applicantStudyMap.getMember().getId();
        this.memberNickName = applicantStudyMap.getMember().getProfile().getNickName();
        this.applicationStatus = applicantStudyMap.getStatus();
    }



//    public StudyWaitingListResponse(Study study) {
//        this.memberId = study.getWaitingList().;
//        this.StudyId = studyId;
//        this.location = location;
//        this.curMembersSize = curMembersSize;
//        this.memberUpperLimit = memberUpperLimit;
//        this.applicationStatus = applicationStatus;
//    }
}
