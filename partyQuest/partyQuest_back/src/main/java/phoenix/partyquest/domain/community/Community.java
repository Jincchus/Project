package phoenix.partyquest.domain.community;

import jakarta.persistence.*;
import lombok.*;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="community_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
//    @ManyToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_cate_id")
    private CommunityCate cate;
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 1000)
    private String content;

    @OneToOne (fetch = FetchType.LAZY, cascade =CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "community_file")
    private UploadFile file;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member member;

    @Enumerated(EnumType.STRING) // kj enum 기본값 주는법 확인해보기
    private CommunityStatus status;

    private int hit;
    private LocalDateTime rdate;


    @Builder
    public Community(Long id, CommunityCate cate, String title, String content, UploadFile file, Member writer, CommunityStatus status, int hit, LocalDateTime rdate) {
        this.id = id;
        this.cate = cate;
        this.title = title;
        this.content = content;
        this.file = file;
        this.member = writer;
        this.status = status;
        this.hit = hit;
        this.rdate = rdate;
    }

    public void setCate(CommunityCate communityCate){
        this.cate = communityCate;
    }

    public void changeFile(UploadFile file) {
        this.file = file;
    }

}
