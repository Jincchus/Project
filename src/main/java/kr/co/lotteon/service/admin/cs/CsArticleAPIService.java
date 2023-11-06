package kr.co.lotteon.service.admin.cs;

import kr.co.lotteon.entity.admin.cs.CsArticleCateEntity;
import kr.co.lotteon.repository.admin.cs.CsArticleCateRepository;
import kr.co.lotteon.response.admin.cs.CsArticleCateAPIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class CsArticleAPIService {

    private final CsArticleCateRepository articleCateRepository;
    public CsArticleCateAPIResponse getArticleCate(String cate){
        List<CsArticleCateEntity> values = articleCateRepository.getArticleCate(cate);
        log.info("values :"+values.toString());

        CsArticleCateAPIResponse result = new CsArticleCateAPIResponse(values);
        log.info("results: "+result.toString());
        return result;
    }

}
