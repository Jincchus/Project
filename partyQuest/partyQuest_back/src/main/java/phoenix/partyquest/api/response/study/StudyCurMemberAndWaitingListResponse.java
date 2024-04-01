package phoenix.partyquest.api.response.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import phoenix.partyquest.domain.party.PartyOnOff;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.ApplicantStudyMap;
import phoenix.partyquest.domain.party.study.map.ApplicationStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString
public class StudyCurMemberAndWaitingListResponse {
    //스터디의 제목
    private String title;
    //스터디 만든 사람의 ID
    private String hostId;
    //현재 참여하고 있는 멤버의 수
    private Integer curMembersSize;
    //스터디를 개최할 장소
    private String location;
    //스터디 개최 방식
    private PartyOnOff onOff;

    //스터디의 최대정원
    private Integer memberUpperLimit;
    private RecruitOption recruitOption;
    private List<StudyMemberResponse> curMember;
    private List<StudyWaitingListResponse> waitingList;

    public StudyCurMemberAndWaitingListResponse(Study study,List<StudyMember> members, List<ApplicantStudyMap> waitingList) {
        this.hostId=study.getHost().getEmail();
        this.title=study.getTitle();
        this.curMembersSize=study.getCurMembersSize();
        this.memberUpperLimit=study.getMemberUpperLimit();
        this.location=study.getLocation().getLocationName();
        this.onOff = study.getOnOff();
        this.recruitOption = study.getRecruitOption();
        this.curMember = members.stream().map(StudyMemberResponse::new).collect(Collectors.toList());
        this.waitingList = waitingList.stream().map(StudyWaitingListResponse::new).filter(applicant -> applicant.getApplicationStatus().equals(ApplicationStatus.PENDING)||applicant.getApplicationStatus().equals(ApplicationStatus.REJECTED)).toList();

    }

    public StudyCurMemberAndWaitingListResponse(Study study,List<StudyMember> members) {
        this.hostId=study.getHost().getEmail();
        this.title=study.getTitle();
        this.curMembersSize=study.getCurMembersSize();
        this.memberUpperLimit=study.getMemberUpperLimit();
        this.location=study.getLocation().getLocationName();
        this.onOff = study.getOnOff();
        this.recruitOption = study.getRecruitOption();
        this.curMember = members.stream().map(StudyMemberResponse::new).collect(Collectors.toList());
    }
    }
