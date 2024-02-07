package phoenix.partyquest.api.response.study;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.category.SmallCate;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.map.MiddleStudyMap;
import phoenix.partyquest.domain.party.study.map.SmallStudyMap;

import java.sql.Clob;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ToString @Getter @NoArgsConstructor
public class StudyModifyResponse {
    private Long studyId;
    private Long hostId; // 모임 방장 pk
    private String thumb;
    private String title;
    private String description; // 1차 개발은 String으로 받아온다.
    private String partyOnOff; // 모임의 형태: 온라인 ,오프라인
    private Long locationId; // partyOnOff가 off(오프라인) 모임 일때 모이는 지역을 설정한다.

    private String recruitOption; // recruit option으로 승인제, 선착순 두가지
    private Integer memberUpperLimit; // 스터디 모임 최대 인원수


//    private List<MiddleCateIdNameResponse> middlecates;
//    private List<SmallCateIdNameResponse> smallcates; // 소분류
    private LocalDate studyStartDate; // 스터디 모임 시작 날짜
    private LocalDate studyEndDate;   // 스터디 모임 종료 날짜

    public StudyModifyResponse(Study study) {
        this.studyId = study.getId();
        this.hostId = study.getHost().getId();
        this.thumb = String.valueOf(study.getThumb());
        this.title = study.getTitle();
        this.description = study.getDescription();
        this.partyOnOff = String.valueOf(study.getOnOff());
        this.locationId = study.getLocation().getId();
        this.recruitOption = String.valueOf(study.getRecruitOption());
        this.memberUpperLimit = study.getMemberUpperLimit();
//        this.middlecates = study.getMiddleCates().stream()
//                .map(MiddleCateIdNameResponse::new)
//                .collect(Collectors.toList());
//        this.smallcates = study.getSmallCates().stream()
//                .map(SmallCateIdNameResponse::new)
//                .collect(Collectors.toList());
        this.studyStartDate = study.getStudyStartDate();
        this.studyEndDate = study.getStudyEndDate();
    }

    public class MiddleCateIdNameResponse {
        private Long id;
        private String name;

        public MiddleCateIdNameResponse(MiddleStudyMap middleStudyMap) {
            MiddleCate middleCate = middleStudyMap.getMiddleCate();
            this.id = middleCate.getId();
            this.name = middleCate.getName();
        }
    }

    public class SmallCateIdNameResponse {
        private Long id;
        private String name;

        public SmallCateIdNameResponse(SmallStudyMap smallStudyMap) {
            SmallCate smallCate = smallStudyMap.getSmallCate();
            this.id = smallCate.getId();
            this.name = smallCate.getName();
        }
    }
}
