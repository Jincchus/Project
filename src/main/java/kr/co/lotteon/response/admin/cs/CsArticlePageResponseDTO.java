package kr.co.lotteon.response.admin.cs;

import kr.co.lotteon.request.admin.cs.CsArticlePageRequestDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Data
public class CsArticlePageResponseDTO {

    private List<CsArticleResponseDTO> dtoList;
    private String cate;
    private int pg;
    private int size;
    private int total;

    private int start, end;
    private boolean prev, next;

    @Builder
    public CsArticlePageResponseDTO(CsArticlePageRequestDTO csArticlePageRequestDTO, List<CsArticleResponseDTO> dtoList,int total){
        this.cate = csArticlePageRequestDTO.getCate();
        this.pg = csArticlePageRequestDTO.getPg();
        this.size = csArticlePageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.pg/10.0))*10;
        this.start = this.end - 9;
        int last = (int)(Math.ceil(total / (double) size));

        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;


    }


}
