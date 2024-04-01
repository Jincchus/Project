package phoenix.partyquest.api.response.study;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import phoenix.partyquest.api.response.study.category.SmallStudyResponse;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;
import phoenix.partyquest.domain.party.study.map.SmallStudyMap;

import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
@NoArgsConstructor
@Slf4j
public class StudyViewResponse {
    private String thumb; // 모임 사진
    private String avatar; // 호스트 프로필 사진
    private String hostNickName; // 호스트 닉네임
    private String hostBio;
    private String title; // study 모임 이름
    private List<SmallStudyResponse> smallCate; // 요구 기술스택에 들어갈 samllCate이름 response 만들어주기 entity 참조시 stackoverflow 됨
    private String description; //상세 내역
    private Integer curMembersSize; // 현재인원
    private Integer memberUpperLimit; // 총원
    private RecruitOption recruitOption; // ENUM 'FCFS' / 'PNP'
    private LocalDate studyStartDate; // study 시작일
    private LocalDate studyEndDate; // study 종료일
    private String partyLocation; // 모임장소
    private List<StudyMemberResponse> studyMemberInfo;
    private List<LikedStudyMemberResponse> likedStudyMembers; // 좋아요한 멤버들 리스트

    @Builder
    public StudyViewResponse(Study study,List<LikedStudyMemberResponse> likedStudyMembers) {
        this.thumb = study.getThumb()==null? null:study.getThumb().getStoredName();
        log.info(thumb);
        this.avatar = study.getHost().getProfile().getAvatar() != null ? study.getHost().getProfile().getAvatar().getStoredName() : null;
        this.hostNickName = study.getHost().getProfile().getNickName();
        this.hostBio = study.getHost().getProfile().getBio();
        this.title = study.getTitle();
        this.smallCate = study.getSmallCates().stream().map(SmallStudyResponse::new).toList(); //kj 설정 때문에 집에서 확인불가
        this.description = study.getDescription();
        this.curMembersSize = study.getCurMembersSize();
        this.memberUpperLimit = study.getMemberUpperLimit();
        this.recruitOption = study.getRecruitOption();
        this.studyStartDate = study.getStudyStartDate();
        this.studyEndDate = study.getStudyEndDate();
        // kj location이용 가능 확인
        this.partyLocation = study.getLocation().getLocationName();
        // 매핑할 response 필요
        this.studyMemberInfo = study.getStudyMemberList().stream().map(StudyMemberResponse::new).toList();
        this.likedStudyMembers = likedStudyMembers;
    }

}
