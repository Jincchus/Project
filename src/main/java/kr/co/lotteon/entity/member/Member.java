package kr.co.lotteon.entity.member;

import jakarta.persistence.*;
import kr.co.lotteon.entity.product.ProductCartEntity;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "km_member")
@ToString(exclude = "carts")//toString으로 데이터를 확인하기 위해서 추가함//exclude="carts"를 통해서 carts에서는 toStirng을 사용하지 못하게 막응
public class Member {
    @Id
    private String uid;
    private String pass;
    private String name;
    @Enumerated(EnumType.STRING)
    private MemberGender gender;
    private String hp;
    @NotNull
    @Column(unique = true) //LEARN: unique 제약조건 column으로 지정
    private String email;
    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private Integer point;
    @Enumerated(EnumType.STRING)
    private MemberLevel level;
    private String zip;
    private String addr1;
    private String addr2;
    private String company;
    private String ceo;
    private String bizRegNum;
    private String comRegNum;
    private String tel;
    private String manager;
    private String managerHp;
    private String fax;
    private String regip; //TODO: 여러개의 ip를 받을 수 있도록 하여 다른 기기에서 로그인시 유효성 검사실시
    private LocalDateTime wdate;
    private LocalDateTime rdate;
    private Integer locationTerms; //TODO: 뭔지 모르겠다.

    @OneToMany(mappedBy = "uid",fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCartEntity> carts = new ArrayList<>();

    @Builder
    public Member(String uid, String pass, String name, MemberGender gender, String hp, String email, MemberRole role, MemberLevel level, String zip, String addr1, String addr2, String company, String ceo, String bizRegNum, String comRegNum, String tel, String manager, String managerHp, String fax, String regip, LocalDateTime wdate, LocalDateTime rdate, Integer locationTerms) {
        this.uid = uid;
        this.pass = pass;
        this.name = name;
        this.gender = gender;
        this.hp = hp;
        this.email = email;
        this.role = role == null ? MemberRole.ROLE_USER : role;
        this.point = 0;
        this.level = level == null ? MemberLevel.UNRANKED : level; // seller로 가입 할때는 SELLER 로 레벨 등록이 된다.
        this.zip = zip;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.company = company;
        this.ceo = ceo;
        this.bizRegNum = bizRegNum;
        this.comRegNum = comRegNum;
        this.tel = tel;
        this.manager = manager;
        this.managerHp = managerHp;
        this.fax = fax;
        this.regip = regip; //TODO: request로 부터 처리하기
        this.wdate = wdate;
        this.rdate = LocalDateTime.now();
        this.locationTerms = locationTerms;
    }

    public void addCart(ProductCartEntity cart) {
        this.carts.add(cart);
        cart.allocateMember(this);
    }
}
