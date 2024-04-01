package phoenix.partyquest.domain.member;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import phoenix.partyquest.domain.member.profile.MemberProfile;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"email","password"})
@Setter
@Table(indexes = @Index(name = "idx_member_email", columnList = "email"))
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;
    // 유저 필수 정보(email,password,name,phone)
    @Column(unique = true)
    private String email; // 로그인 아이디로 사용한다.
    private String password;
    private String name;
    private String phone;

    // OAuth2 추가 필드
    private String provider;
    private String providerId;
    private String loginId;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_profile_id")
    private MemberProfile profile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY,mappedBy = "member")
    private List<LikedStudyMemberMap> likedStudyList = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;
    public void allocateProfile(MemberProfile profile) {
        this.profile = profile;
        profile.allocateMember(this);
    }

    public void addLikedStudy(LikedStudyMemberMap likedStudy) {
        this.likedStudyList.add(likedStudy);
        likedStudy.allocateMember(this);
    }
    public void removeLikedStudy(LikedStudyMemberMap likedStudy){
        this.likedStudyList.remove(likedStudy);
    }
    
    @Builder
    public Member(String email, String password, String name, String phone, MemberRole role, String provider, String providerId, String loginId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role == null? MemberRole.ROLE_USER : role;
        this.provider = provider;
        this.providerId = providerId;
        this.loginId = loginId;
    }
}
