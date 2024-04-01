package phoenix.partyquest.api.dto.cs;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import phoenix.partyquest.api.request.cs.BoardListRequest;

@Data @NoArgsConstructor
public class BoardSearchDto {
    private Integer pg;
    private Integer size;
    private String cate;

    @Builder
    public BoardSearchDto(BoardListRequest requestDto, String cate) {
        this.pg = requestDto.getPg();
        this.size = requestDto.getSize();
        this.cate = cate;
    }
}
