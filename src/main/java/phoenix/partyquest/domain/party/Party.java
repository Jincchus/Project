package phoenix.partyquest.domain.party;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.base.BaseLocation;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.location.PartyLocation;

import java.time.LocalDateTime;

/**
 * 모임의 공통 필드를 가지는 엔티티
 */
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="party_type")
@NoArgsConstructor
public class Party extends BaseLocation {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "party_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Member host;

    @OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "thumb")
    private UploadFile thumb;
    @Column(name = "party_title")
    private String title;

    // description 어떻게 파일로 가져 올지 알아보기. LOB 조사 해보자 네이버 smart Editor 2.0
    //TODO: @Lob가 안된다.
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 100000)
    private String description;

    @Enumerated(EnumType.STRING)
    private PartyStatus status;

    @Enumerated(EnumType.STRING)
    private PartyOnOff onOff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_location")
    private PartyLocation location;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    public void changeStatus(PartyStatus status) {
        this.status = status;
    }

    public void changeThumb(UploadFile thumb) {
        this.thumb = thumb;
    }

    public void changeLocation(PartyLocation location) {
        this.location = location == null ? this.location : location;
    }

    public void changeTitle(String title) {
        this.title = title.isEmpty() ? this.title : title;
    }

    public void changeDescription(String description){
        this.description = description.isEmpty() ? this.description : description;
    }

    public void changePartyOnOff(PartyOnOff onOff) {
        this.onOff = onOff == null ? this.onOff : onOff;
    }


    public Party(Member host, UploadFile thumb, String title, String description, PartyStatus status, PartyOnOff onOff, PartyLocation location) {

        this.host = host;
        this.thumb = thumb;
        this.title = title;
        this.description = description;
        this.status = status == null ? PartyStatus.OPENED:status;
        this.onOff = onOff;
        this.location = location;
        this.createdAt = LocalDateTime.now();
    }
}

