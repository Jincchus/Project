package phoenix.partyquest.api.response.cs;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import phoenix.partyquest.domain.cs.Board;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BoardListResponse {
    private List<BoardListDetail> boardList = new ArrayList();

    private int pg;
    private int size;
    private int total;
    private int start, end;
    private boolean prev, next;
    @Builder
    public  BoardListResponse (Page<Board> boards, int total) {
        boardList =  boards.stream()
                .map(BoardListDetail::new)
                .collect(Collectors.toList());
        this.total = total;
        this.end = (int)(Math.ceil(this.pg/10.0))*10;
        this.start = this.end - 9;
        int last = (int)(Math.ceil(total / (double) size));

        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
    @Data
    public static class BoardListDetail{
        private Long boardId;
        private String cate;
        private String menu;
        private String title;
        private String writer;
        private String content;
        private String boardThumb;
        private String rdate;

       public BoardListDetail(Board board) {
            this.boardId = board.getId();;
            this.cate = board.getBoardCate().getName();
            this.menu = board.getBoardMenu().getName();
            this.title = board.getTitle();
            this.content = board.getContent();
            if(boardThumb != null){
                this.boardThumb = board.getBoardThumb().getStoredName();
            }
            if (board.getWriter() != null) {
                this.writer = board.getWriter().getEmail();
            }
            this.rdate = board.getRdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
