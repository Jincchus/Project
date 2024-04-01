package phoenix.partyquest.api.response.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import phoenix.partyquest.domain.party.PartyOnOff;
import phoenix.partyquest.domain.party.location.PartyLocation;
import phoenix.partyquest.domain.party.study.Study;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudyWaitingListAPI {
    //WaitingList가 있는 studyId
    private Long studyId;

    //Study를 생성한 host의 Id
    private Long hostId;

    //WaitingList를 가지고 있는 스터디의 제목
    private String title;
    //현재 참여하고 있는 멤버의 수
    private Integer curMembersSize;
    //스터디 개최 방식
    private PartyOnOff onOff;

    //스터디를 개최할 장소
    //PartyLocation의 locationName의 타입이 String이므로 String으로 수정
    private String location;

    //스터디의 최대정원
    private Integer memberUpperLimit;
    //WaitingList속 member의 정보
    private List<StudyWaitingListResponse> waitingList;

    public StudyWaitingListAPI(Study study) {
        this.studyId = study.getId();
        this.hostId = study.getHost().getId();
        this.title = study.getTitle();
        this.curMembersSize = study.getCurMembersSize();
        this.onOff= study.getOnOff();
        this.location = study.getLocation().getLocationName();
        this.memberUpperLimit = study.getMemberUpperLimit();
        this.waitingList = study.getWaitingList().stream().map(StudyWaitingListResponse::new).toList();
    }
}
