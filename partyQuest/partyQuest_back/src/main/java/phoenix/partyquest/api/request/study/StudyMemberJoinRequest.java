package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @NoArgsConstructor@Setter
public class StudyMemberJoinRequest {
    private String applicantId; // 지원하는 유저 아이디
    private Long studyId;     // 참가하려는 스터디 아이디
}
