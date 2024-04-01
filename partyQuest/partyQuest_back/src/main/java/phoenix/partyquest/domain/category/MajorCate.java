package phoenix.partyquest.domain.category;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Getter
@NoArgsConstructor
public class MajorCate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "major_cate_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "majorCate",fetch = FetchType.LAZY)
    private List<MiddleCate> middleCates = new ArrayList<>();

    @Builder
    public MajorCate(String name) {
        this.name = name;
    }

}
//TODO: sequence 1-100 => sequence를 왜 쓸까?
