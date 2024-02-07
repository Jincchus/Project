package phoenix.partyquest.domain.cs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BoardCate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_cate_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "cate", fetch = FetchType.LAZY)
    private List<BoardMenu> menus = new ArrayList<>();
}
