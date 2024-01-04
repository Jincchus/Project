package phoenix.partyquest.api.request.study;

import lombok.*;
import phoenix.partyquest.domain.party.PartyOnOff;
import phoenix.partyquest.domain.party.location.PartyLocation;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.map.MiddleStudyMap;
import phoenix.partyquest.domain.party.study.map.SmallStudyMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class StudyModifyRequest {
    private Long studyId;
    private Long hostId; // 모임 방장 pk
    private String title;
    private String description; // 1차 개발은 String으로 받아온다.
    private String partyOnOff; // 모임의 형태: 온라인 ,오프라인
    private Long locationId; // partyOnOff가 off(오프라인) 모임 일때 모이는 지역을 설정한다.
    private String recruitOption; // recruit option으로 승인제, 선착순 두가지
    private Integer memberUpperLimit; // 스터디 모임 최대 인원수


    private List<Long> middleCateIds; //study 중분류
    private List<Long> smallCateIds;  //study 소분류
    private LocalDate studyStartDate; // 스터디 모임 시작 날짜
    private LocalDate studyEndDate;   // 스터디 모임 종료 날짜

    public Study updateStudy(Study targetStudy) {
        targetStudy.changeTitle(this.title);
        targetStudy.changeDescription(this.description);
        targetStudy.changePartyOnOff(PartyOnOff.valueOf(this.partyOnOff));
        targetStudy.changeRecruitOption(RecruitOption.valueOf(this.recruitOption));
        targetStudy.changeMemberUpperLimit(this.memberUpperLimit);

        targetStudy.changeStudyEndDate(LocalDate.from(this.studyEndDate));

        return targetStudy;
    }

}
