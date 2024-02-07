package phoenix.partyquest.domain.category;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Getter
@NoArgsConstructor
public class MiddleCate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "middle_cate_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_cate_id")
    private MajorCate majorCate;
    private String name;

    @OneToMany(mappedBy = "middleCate",fetch = FetchType.LAZY)
    private List<SmallCate> smallCates = new ArrayList<>();





}
