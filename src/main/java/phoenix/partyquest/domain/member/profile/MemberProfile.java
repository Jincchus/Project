package phoenix.partyquest.domain.member.profile;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.base.BaseLocation;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.StudyHistory;
import phoenix.partyquest.domain.member.map.MiddleMemberMap;
import phoenix.partyquest.domain.member.map.SmallMemberMap;
import phoenix.partyquest.domain.party.location.PartyLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * 필드설명:
 * favoriteMiddles: 중간 카테중 유저 관심사로 저장된것들: ex:) front-end,back-end ,infra
 * favoriteSmalls:  말단 카테중 유저 관심사로 저장된것들: ex:) react, spring-boot
 */
@Entity
@Getter
@NoArgsConstructor
public class MemberProfile extends BaseLocation {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_profile_id")
    private Long id;

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(unique = true)
    private String nickName;
    @Enumerated(EnumType.STRING)
    private MemberMBTI mbti;
    private String bio;
    @OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "avatar")
    private UploadFile avatar;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<MiddleMemberMap> favoriteMiddles = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<SmallMemberMap> favoriteSmalls = new ArrayList<>();

    @OneToMany(mappedBy = "memberProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyHistory> studyHistories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferred_location")
    private PartyLocation preferredLocation;

    private String link1;
    private String link2;
    public void allocateMember(Member member) {
        this.member = member;
    }

    public void addStudyHistory(StudyHistory studyHistory) {
        studyHistories.add(studyHistory);
        studyHistory.allocateMember(this);
    }
    public void removeStudyHistory(StudyHistory studyHistory){
        this.studyHistories.remove(studyHistory);
    }

    public void addMiddle(MiddleMemberMap middleMap) {
        this.favoriteMiddles.add(middleMap);
        middleMap.allocateProfile(this);
    }
    public void addSmall(SmallMemberMap smallMap) {
        this.favoriteSmalls.add(smallMap);
        smallMap.allocateProfile(this);
    }

    public void update(MemberProfile memberProfile) {
        this.nickName = memberProfile.getNickName();
        this.bio= memberProfile.getBio();
        this.mbti = memberProfile.getMbti();
        this.preferredLocation = memberProfile.getPreferredLocation();
        this.link1 = memberProfile.getLink1();
        this.link2 = memberProfile.getLink2();
    }

    public void clearFavoriteMiddles() {
        this.favoriteMiddles.clear();
    }
    public void clearFavoriteSmalls() {
        this.favoriteSmalls.clear();
    }

    public void changeAvatar(UploadFile newAvatar) {
        this.avatar = newAvatar;
    }
    /**
     * 회원가입시 기본으로 할당하는 프로필입니다.
     * @param member
     */
    public MemberProfile(Member member) {
        this.member = member;
        this.nickName = member.getEmail().substring(0,member.getEmail().lastIndexOf("@"));
    }

    @Builder
    public MemberProfile(String nickName, MemberMBTI mbti, String bio, PartyLocation preferredLocation, String link1, String link2) {
        this.nickName = nickName;
        this.mbti = mbti;
        this.bio = bio;
        this.preferredLocation = preferredLocation;
        this.link1 = link1;
        this.link2 = link2;
    }
}
