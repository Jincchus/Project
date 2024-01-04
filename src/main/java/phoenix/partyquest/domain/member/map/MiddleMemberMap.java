package phoenix.partyquest.domain.member.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.member.profile.MemberProfile;

@Entity
@Getter
@NoArgsConstructor
public class MiddleMemberMap {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberProfile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "middle_cate_id")
    private MiddleCate middleCate;

    public void allocateProfile(MemberProfile profile) {
        this.profile = profile;
    }

    public void allocateMiddleCate(MiddleCate middleCate) {
        this.middleCate = middleCate;
    }
}
