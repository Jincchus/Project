package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @NoArgsConstructor @Setter
public class StudyDeleteRequest {
    private Long hostId;
    private Long studyId;
}
