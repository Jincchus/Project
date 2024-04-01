package phoenix.partyquest.domain.party.study.map;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.Study;

import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor
public class MessageMap {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    private Member receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Enumerated(EnumType.STRING)
    private  MessageStatus messageStatus;

    private LocalDateTime rdate;

    @Builder
    public MessageMap(Member sender, Member receiver, Study study, ApplicationStatus applicationStatus){

        this.sender = sender;
        this.receiver = receiver;
        this.study = study;
        this.applicationStatus = applicationStatus;
        this.messageStatus = MessageStatus.UNREAD;
        this.rdate = LocalDateTime.now();
    }

    public void changeMessageStatus(MessageStatus messageStatus){this.messageStatus = messageStatus;}
}
