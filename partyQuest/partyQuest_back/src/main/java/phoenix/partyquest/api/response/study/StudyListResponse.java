package phoenix.partyquest.api.response.study;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.category.SmallCate;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.map.MiddleStudyMap;
import phoenix.partyquest.domain.party.study.map.SmallStudyMap;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudyListResponse {
    private Long studyId;
    private String thumb;
    private String title;
    private String hostName;
    private Integer likeCnt;
    private List<MiddleCateIdNameResponse> middlecates = new ArrayList<>();
    private List<SmallCateIdNameResponse> smallcates = new ArrayList<>();
    private String onOrOff;
    private String location;
    private String studyStartAt;
    private String studyEndAt;
    private Integer curMemberNum;
    private Integer limitMemberNum;
    private String recruitOption;
    @JsonIgnore
    private int MAX_DESC_LENGTH = 200;

    public StudyListResponse(Study study) {
        this.studyId = study.getId();
        this.thumb = study.getThumb() != null ? study.getThumb().getStoredName() : null;
        this.title = study.getTitle();
        this.hostName = study.getHost().getName();
        this.likeCnt = study.getLikeCount();
        this.middlecates = study.getMiddleCates().stream()
                .map(MiddleCateIdNameResponse::new)
                .collect(Collectors.toList());
        this.smallcates = study.getSmallCates().stream()
                .map(SmallCateIdNameResponse::new)
                .collect(Collectors.toList());
        this.onOrOff = study.getOnOff().name();
        this.location = study.getLocation().getLocationName();
        this.studyStartAt = study.getStudyStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.studyEndAt = study.getStudyStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.curMemberNum = study.getCurMembersSize() == null? 0 : study.getCurMembersSize();
        this.limitMemberNum = study.getMemberUpperLimit();
        this.recruitOption = study.getRecruitOption().name();
    }
    // LEARN: getter, noargsConstructor가 없으면 object mapper 가 동작 하지 않는다.
    @Getter @NoArgsConstructor
    static class MiddleCateIdNameResponse {
        private Long id;
        private String name;

        public MiddleCateIdNameResponse(MiddleStudyMap middleStudyMap) {
            MiddleCate middleCate = middleStudyMap.getMiddleCate();
            this.id = middleCate.getId();
            this.name = middleCate.getName();
        }
    }
    @Getter @NoArgsConstructor
    static class SmallCateIdNameResponse {
        private Long id;
        private String name;

        public SmallCateIdNameResponse(SmallStudyMap smallStudyMap) {
            SmallCate smallCate = smallStudyMap.getSmallCate();
            this.id = smallCate.getId();
            this.name = smallCate.getName();
        }
    }
}
