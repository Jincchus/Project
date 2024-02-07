package phoenix.partyquest.service.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.category.MajorCate;
import phoenix.partyquest.repository.party.study.ExampleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    ExampleRepository exampleRepository;

    @Test
    @DisplayName("serviceTest")
    @Transactional
    public void cateTest(){
        List<MajorCate> allCate = exampleRepository.findAllCateWithAllFetch();
        Assertions.assertThat(allCate.size()).isGreaterThan(0);
        System.out.println("allCate :"+allCate.toString());
    }


}