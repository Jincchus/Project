package phoenix.partyquest.api.response.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import phoenix.partyquest.domain.party.PartyOnOff;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.ApplicantStudyMap;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor
public class StudyMyPageMadeByMeResponse {
    //스터디 번호
    private Long studyId;
    // 모임 사진
    private String thumb;
    //스터디 제목
    private String title;
    // 모임 장소 -온,오프라인
    private PartyOnOff onOff;
    //모임장소
    private String location;
    // 모집 타입 -선착순 or 승인제
    private RecruitOption recruitOption;
    //현재인원
    private Integer curMembersSize;
    //최대정원
    private Integer memberUpperLimit;
    //모임 시작일
    private LocalDate studyStartDate;
    //모임 종료일
    private LocalDate studyEndDate;

    public StudyMyPageMadeByMeResponse(Study study) {
        this.studyId = study.getId();
        this.title = study.getTitle();
        this.curMembersSize = study.getCurMembersSize();
        this.memberUpperLimit = study.getMemberUpperLimit();
        this.location = study.getLocation().getLocationName();
        this.thumb = study.getThumb().getStoredName();
        this.recruitOption = study.getRecruitOption();
        this.onOff = study.getOnOff();
        this.studyStartDate = study.getStudyStartDate();
        this.studyEndDate = study.getStudyEndDate();
    }

    public StudyMyPageMadeByMeResponse(StudyMember studyMember) {
        this.studyId = studyMember.getStudy().getId();
        this.title = studyMember.getStudy().getTitle();
        this.curMembersSize = studyMember.getStudy().getCurMembersSize();
        this.memberUpperLimit = studyMember.getStudy().getMemberUpperLimit();
        this.location = studyMember.getStudy().getLocation().getLocationName();
        this.thumb = studyMember.getStudy().getThumb().getStoredName();
        this.recruitOption = studyMember.getStudy().getRecruitOption();
        this.onOff = studyMember.getStudy().getOnOff();
        this.studyStartDate = studyMember.getStudy().getStudyStartDate();
        this.studyEndDate = studyMember.getStudy().getStudyEndDate();
    }

    public StudyMyPageMadeByMeResponse(LikedStudyMemberMap likeStudy) {
        this.studyId = likeStudy.getStudy().getId();
        this.title = likeStudy.getStudy().getTitle();
        this.curMembersSize = likeStudy.getStudy().getCurMembersSize();
        this.memberUpperLimit = likeStudy.getStudy().getMemberUpperLimit();
        this.location = likeStudy.getStudy().getLocation().getLocationName();
        this.thumb = likeStudy.getStudy().getThumb().getStoredName();
        this.recruitOption = likeStudy.getStudy().getRecruitOption();
        this.onOff = likeStudy.getStudy().getOnOff();
        this.studyStartDate = likeStudy.getStudy().getStudyStartDate();
        this.studyEndDate = likeStudy.getStudy().getStudyEndDate();
    }

    public StudyMyPageMadeByMeResponse(ApplicantStudyMap waitingList) {
        this.studyId = waitingList.getStudy().getId();
        this.title = waitingList.getStudy().getTitle();
        this.curMembersSize = waitingList.getStudy().getCurMembersSize();
        this.memberUpperLimit = waitingList.getStudy().getMemberUpperLimit();
        this.location = waitingList.getStudy().getLocation().getLocationName();
        this.thumb = waitingList.getStudy().getThumb().getStoredName();
        this.recruitOption = waitingList.getStudy().getRecruitOption();
        this.onOff = waitingList.getStudy().getOnOff();
        this.studyStartDate = waitingList.getStudy().getStudyStartDate();
        this.studyEndDate = waitingList.getStudy().getStudyEndDate();
    }
}
