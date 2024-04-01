package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.PartyOnOff;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;

import java.sql.Clob;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
public class StudyCreateRequest {
    // TODO: hostId를 request에서 삭제하고 controller 에서 authentication 으로 인증 처리하는 것 진행하기 (11/21)
    private Long hostId; // 모임 방장 pk
    private MultipartFile thumb;
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

    /**
     *  request에서 넘어온 hostId로 host를 불러와서 study 엔티티를 만든다
     *  이후 location, middleCates, smallCates는 StudySerivce에서 할당해준다.
     */
    public Study.StudyBuilder toStudyBuilder(Member host) {
        Study.StudyBuilder studyBuilder = Study.builder();
        studyBuilder = studyBuilder
                .host(host)
                .title(this.title)
                .description(this.description)
                .onOff(PartyOnOff.valueOf(this.partyOnOff))
                .recruitOption(RecruitOption.valueOf(recruitOption))
                .memberUpperLimit(this.memberUpperLimit)
                .studyStartAt(this.studyStartDate)
                .studyEndAt(this.studyEndDate);
        return studyBuilder;
    }
}
