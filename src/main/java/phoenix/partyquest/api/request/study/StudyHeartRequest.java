package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = {"memberId","studyId"})
@NoArgsConstructor
public class StudyHeartRequest {

    private Long memberId;
    private Long studyId;

    public StudyHeartRequest(Long memberId, Long studyId) {
        this.memberId = memberId;
        this.studyId = studyId;
    }
}
