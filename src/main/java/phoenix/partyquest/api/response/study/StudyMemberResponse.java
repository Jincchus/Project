package phoenix.partyquest.api.response.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import phoenix.partyquest.api.response.study.category.SmallStudyResponse;
import phoenix.partyquest.domain.party.study.StudyMember;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor
@Slf4j
public class StudyMemberResponse {
    // StudyMember 테이블에 존재하는 값
    private Long memberId;
    private Long studyId;
    private String memberNickName;
    private String memberBio;
    private List<smallMemberResponse> smallMember;

    public StudyMemberResponse(StudyMember studyMember) {
        this.memberId = studyMember.getMember().getId();
        this.studyId = studyMember.getStudy().getId();
        this.memberNickName = studyMember.getMember().getProfile().getNickName();
        this.memberBio = studyMember.getMember().getProfile().getBio();
        this.smallMember = studyMember.getMember().getProfile().getFavoriteSmalls().stream().map(smallMemberResponse::new).collect(Collectors.toList());
    }
}
