package kr.co.lotteon.request.admin.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
@Data
public class CsArticlePageRequestDTO {

    //인스턴스를 만들 때 특정 필드를 특정 값으로 초기화할 때 사용한다.
    private int pg;

    private int size;

    private String cate;

    private String menu1;

    private String menu2;

    public CsArticlePageRequestDTO() {
        this.pg = 1;
        this.size = 10;
        this.cate = "0";
        this.menu1 = "0";
        this.menu2 = "0";
    }

    @Builder
    public CsArticlePageRequestDTO(int pg, int size, String cate, String menu1, String menu2) {
        this.pg = pg;
        this.size = size;
        this.cate = cate;
        this.menu1 = menu1;
        this.menu2 = menu2;
    }

    public Pageable getPageable(String sort){
        return PageRequest.of(this.pg-1, this.size, Sort.by(sort).descending());
    }


}
