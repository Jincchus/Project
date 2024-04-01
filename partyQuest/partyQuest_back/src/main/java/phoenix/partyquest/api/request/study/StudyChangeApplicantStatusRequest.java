package phoenix.partyquest.api.request.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import phoenix.partyquest.domain.party.study.map.ApplicationStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudyChangeApplicantStatusRequest {
    //스터디의 생성자의 Id
    private String hostId;
    //스터디에 참여하려는 유저의 id
    private long applicantId;
    //유저가 참여하려는 스터디의 번호
    private long studyId;
    //유저의 참여 상태
    private ApplicationStatus status;

}
