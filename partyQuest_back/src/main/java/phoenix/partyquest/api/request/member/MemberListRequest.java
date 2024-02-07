package phoenix.partyquest.api.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberListRequest {

    private String nickname;
    private Long preferredLocation;
    private Long middleCateId;

    @Builder.Default
    private List<Long> smallCateIds = null;

    @Builder.Default
    private Integer pg = 1;
    @Builder.Default
    private Integer size=16;
    private final int MAX_SIZE = 50;
    @Builder.Default
    private String sort = "createdAt";
    @Builder.Default
    private String desc = "desc";
    public long getOffset() {
        return (long) Math.max(0, pg - 1) * Math.min(MAX_SIZE, size);
    }

    public Pageable createPageable() {
        return PageRequest.of(pg-1, size, Sort.Direction.DESC,sort);
    }

}
