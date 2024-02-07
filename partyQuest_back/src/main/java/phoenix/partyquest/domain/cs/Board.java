package phoenix.partyquest.domain.cs;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id")
    private BoardCate boardCate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_menu_id")
    private BoardMenu boardMenu;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_email")
    private Member writer;

    @OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "board_thumb")
    private UploadFile boardThumb;

    @CreationTimestamp
    private LocalDate rdate;

    @Builder
    public Board(BoardCate boardCate, BoardMenu boardMenu, String title, String content, Member writer, UploadFile boardThumb) {
        this.boardCate = boardCate;
        this.boardMenu = boardMenu;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardThumb = boardThumb;
    }
}
