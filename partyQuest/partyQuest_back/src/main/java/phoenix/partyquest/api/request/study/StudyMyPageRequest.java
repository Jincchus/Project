package phoenix.partyquest.api.request.study;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter @Setter
public class StudyMyPageRequest {
    private String  hostId;

    //조회를 시작할 스터디 리스트의 번호
    private int pg;

    //조회할 때 가져올 리스트의 크기
    private int size;

    public StudyMyPageRequest() {
        this.pg = 0;
        this.size = 3;
    }

}
