package kr.co.lotteon.service.admin.cs;


import kr.co.lotteon.entity.admin.cs.ArticleStatus;
import kr.co.lotteon.entity.admin.cs.CsArticleComment;
import kr.co.lotteon.entity.admin.cs.CsArticleEntity;
import kr.co.lotteon.repository.admin.cs.CsArticleCommentRepository;
import kr.co.lotteon.repository.admin.cs.CsArticleRepository;
import kr.co.lotteon.request.admin.cs.CsArticleCommentRequest;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequestDTO;
import kr.co.lotteon.request.admin.cs.CsArticlePageRequestDTO;
import kr.co.lotteon.response.admin.cs.CsArticleCommentResponse;
import kr.co.lotteon.response.admin.cs.CsArticlePageResponseDTO;
import kr.co.lotteon.response.admin.cs.CsArticleResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsArticleService {

    private final CsArticleRepository csArticleRepository;
    private final CsArticleCommentRepository commentRepository;
    private final ModelMapper modelMapper;

/////////////////////////////////////////
////////////////////////////////////////
/*  admin/cs/notice  */
////////////////////////////////////////
///////////////////////////////////////


    public void save(CsArticleCreateRequestDTO csArticleCreateRequestDTO){
    log.info("sevice ArticleCreateRequest :"+ csArticleCreateRequestDTO.toString());

    CsArticleEntity csArticleEntity =  csArticleCreateRequestDTO.toEntity();
    log.info("Service Entity: "+csArticleEntity.toString());
    csArticleRepository.save(csArticleEntity);
    }

    /*
    public List<CsArticleResponseDTO> findAll(){
        List<CsArticleEntity>  listCsArticleEntity = csArticleRepository.findAll();
        List<CsArticleResponseDTO> csArticleResponses = new ArrayList<>();

        for(CsArticleEntity article : listCsArticleEntity){
            CsArticleResponseDTO resp =  article.respToEntity();
            csArticleResponses.add(resp);
        }

        return csArticleResponses;
    }
    */

    public CsArticlePageResponseDTO findByCate(CsArticlePageRequestDTO pageRequestDTO){

        //getPageable(  ) 안에 넣는 변수에 따라서 내림차순 정렬이 된다.
        //getPageable(  ) 안에 cate를 넣었는데 다 똑같으므로 내림차순 정렬이 되지 않았다.
        //getPageable(  ) 안에 넣는 값은 paging처리를 할 때 기준이 되는 값을 넣는다.
        Pageable pageable = pageRequestDTO.getPageable("articleId");
        Page<CsArticleEntity> result = null;
        if(pageRequestDTO.getMenu1().equals("0")){
            log.info("처음 조회");
            result = csArticleRepository.findByCate(pageRequestDTO.getCate(),pageable);
        }else if(!pageRequestDTO.getMenu2().equals("0")){
            log.info("menu2 선택");
            result= csArticleRepository.findByCateAndMenu1AndMenu2(pageRequestDTO.getCate(), pageRequestDTO.getMenu1(), pageRequestDTO.getMenu2(), pageable);
        }else {
            log.info("menu1 선택");
            result = csArticleRepository.findByCateAndMenu1(pageRequestDTO.getCate(),pageRequestDTO.getMenu1(),pageable);

        }

//        Page<CsArticleEntity> result = csArticleRepository.findByCateAndMenu1AndMenu2(pageRequestDTO.getCate(),pageRequestDTO.getMenu1(),pageRequestDTO.getMenu2(),pageable);
        log.info("result :" +result.toString());
        List<CsArticleResponseDTO> dtoList = result.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, CsArticleResponseDTO.class))
                .toList();

        int totalElement = (int) result.getTotalElements();

        return  CsArticlePageResponseDTO.builder()
                .csArticlePageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElement)
                .build();
    };


    public CsArticleResponseDTO findById(int articleId){

        CsArticleEntity findArticle = csArticleRepository.findById(articleId).orElseThrow();
        return new CsArticleResponseDTO(findArticle);
    }


    public void updateArticle(CsArticleCreateRequestDTO csArticleCreateRequestDTO){
        log.info("updateof modify serivce: "+csArticleCreateRequestDTO.toEntity());
        csArticleRepository.save(csArticleCreateRequestDTO.toEntity());
    };

    public void deleteArticle(int articleId){
        csArticleRepository.deleteById(articleId);
    }


    @Transactional
    public void insertComment(CsArticleCommentRequest commentRequest){
        log.info("commentRequestEntity : "+commentRequest.toEntity());
        commentRepository.save(commentRequest.toEntity());

        // article 업데이트 처리 더티체킹으로
        CsArticleEntity findArticle = csArticleRepository.findById(commentRequest.getArticleId()).orElseThrow();
        findArticle.setStatus(ArticleStatus.답변완료);
    }

    public CsArticleCommentResponse selectComment(int articleId){
        log.info("selectComment articleId:"+articleId);
        CsArticleComment comment = commentRepository.findCommentByArticleId(articleId);
        CsArticleCommentResponse response = comment.respToEntity();
        return  response;
    }

//    public CsArticleCommentResponse findCommentByArticleId(int articleId){
//        Optional<CsArticleComment> articleComment  =  commentRepository.findByArticleId(articleId);
//        log.info("articleComment :"+articleComment);
//        return articleComment.get().respToEntity();
//    }

}
