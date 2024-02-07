package phoenix.partyquest.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.category.MajorCate;
import phoenix.partyquest.repository.party.study.ExampleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ExampleRepository exampleRepository;

    public List<MajorAPIService> allCates(){
        List<MajorCate> allCate = exampleRepository.findAllCateWithAllFetch();
         return allCate.stream().map(MajorAPIService::new)
                 .collect(Collectors.toList());
    }

}
