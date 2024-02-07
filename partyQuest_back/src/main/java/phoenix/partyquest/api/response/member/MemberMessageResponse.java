package phoenix.partyquest.api.response.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import phoenix.partyquest.domain.party.study.map.ApplicationStatus;
import phoenix.partyquest.domain.party.study.map.MessageMap;
import phoenix.partyquest.domain.party.study.map.MessageStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberMessageResponse {

    //참가 상태
    private ApplicationStatus status;

    //스터디 Id
    private Long studyId;

    //스터디 명
    private String studyTitle;

    //날짜
    private LocalDateTime rdate;


    public MemberMessageResponse(MessageMap messageMap) {
        this.status = messageMap.getApplicationStatus();
        this.studyId = messageMap.getStudy().getId();
        this.studyTitle=messageMap.getStudy().getTitle();
        this.rdate = messageMap.getRdate();
    }
}
