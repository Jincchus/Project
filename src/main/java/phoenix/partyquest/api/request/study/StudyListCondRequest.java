package phoenix.partyquest.api.request.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Note: @ModelAttribute로 request param 받을때
 * NoArgs, AllArgs 있어야 Default적용 가능하다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyListCondRequest {
    private String title; // 제목으로 검색
    private Long middleCateId;

    @Builder.Default
    private List<Long> smallCateIds = null;

    // paging section
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size=8;
    private final int MAX_SIZE = 50;
    @Builder.Default
    private String sort = "createdAt";
    @Builder.Default
    private String desc = "desc";
    public long getOffset() {
        return (long) Math.max(0, page - 1) * Math.min(MAX_SIZE, size);
    }

    public Pageable createPageable() {
        return PageRequest.of(page-1, size, Sort.Direction.DESC,sort);
    }
}
