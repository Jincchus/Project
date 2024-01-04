package phoenix.partyquest.domain.party.study.map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.party.study.Study;

@Entity
@Getter
@NoArgsConstructor
public class MiddleStudyMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "middle_cate_id")
    private MiddleCate middleCate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public void allocateStudy(Study study) {
        this.study = study;
    }

    public void allocateMiddleCate(MiddleCate middleCate) {
        this.middleCate = middleCate;
    }

}
