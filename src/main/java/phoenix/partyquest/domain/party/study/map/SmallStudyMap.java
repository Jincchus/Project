package phoenix.partyquest.domain.party.study.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.category.SmallCate;
import phoenix.partyquest.domain.party.study.Study;

@Entity
@Getter
@NoArgsConstructor
public class SmallStudyMap {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "small_cate_id")
    private SmallCate smallCate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public void allocateStudy(Study study) {
        this.study = study;
    }

    public void allocateSmallCate(SmallCate smallCate) {
        this.smallCate = smallCate;
    }
}
