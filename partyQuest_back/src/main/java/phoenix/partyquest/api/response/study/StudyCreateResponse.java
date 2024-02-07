package phoenix.partyquest.api.response.study;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.party.study.Study;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyCreateResponse {

    private Long hostId;
    private Long studyId;

    public StudyCreateResponse(Study study) {
        this.hostId = study.getHost().getId();
        this.studyId = study.getId();
    }
}
