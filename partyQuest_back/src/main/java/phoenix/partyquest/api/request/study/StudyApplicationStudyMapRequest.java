package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudyApplicationStudyMapRequest {
    //스터디에 참여하려는 유저의 id
    private long applicantId;
    //유저가 참여하려는 스터디의 번호
    private long studyId;
}
