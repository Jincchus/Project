package phoenix.partyquest.api.request.cs;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
public class BoardListRequest {
    @Builder.Default
    private Integer pg =1;
    @Builder.Default
    private Integer size =10;
    @Builder.Default
    private Long cateId = 1L;




    public BoardListRequest(Integer pg, Integer size, Long cateId) {
        this.pg = pg;
        this.size = size;
        this.cateId = cateId;
    }

    public Pageable getPageable(String sort){
        return PageRequest.of(this.pg-1, this.size, Sort.by(sort).descending());
    }


}
