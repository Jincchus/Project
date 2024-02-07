package phoenix.partyquest.domain.member.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.category.SmallCate;
import phoenix.partyquest.domain.member.profile.MemberProfile;

@Entity
@Getter
@NoArgsConstructor
public class SmallMemberMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberProfile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "small_cate_id")
    private SmallCate smallCate;

    public void allocateProfile(MemberProfile memberProfile) {
        this.profile = memberProfile;
    }

    public void allocateSmallCate(SmallCate smallCate) {
        this.smallCate = smallCate;
    }
}
