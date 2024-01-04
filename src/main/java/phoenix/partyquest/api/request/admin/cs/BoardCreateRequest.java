package phoenix.partyquest.api.request.admin.cs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.domain.cs.Board;
import phoenix.partyquest.domain.cs.BoardCate;
import phoenix.partyquest.domain.cs.BoardMenu;
import phoenix.partyquest.domain.member.Member;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class BoardCreateRequest {

    private Long boardId;
    private List<Long> cateIds; // CS 대분류 카테고리
    private List<Long> menuIds; // CS 소분류 카테고리
    private String title;
    private String content;
    private String writer;
    private MultipartFile boardThumb;


    public Board.BoardBuilder toBoardBuilder(Member writer, BoardCate boardCate, BoardMenu boardMenu) {
        Board.BoardBuilder boardBuilder = Board.builder();
        boardBuilder = boardBuilder
                .title(this.title)
                .boardCate(boardCate)
                .boardMenu(boardMenu)
                .content(this.content)
                .writer(writer);
        return boardBuilder;

    }
}
