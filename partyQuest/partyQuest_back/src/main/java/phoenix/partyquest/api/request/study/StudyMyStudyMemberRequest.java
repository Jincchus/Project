package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudyMyStudyMemberRequest {

    private String memberId;
    private Long studyId;
}
