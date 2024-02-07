package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyLeaveRequest {
    private String memberId;
    private Long studyId;
}
