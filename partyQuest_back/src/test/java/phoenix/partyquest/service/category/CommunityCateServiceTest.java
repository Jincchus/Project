package phoenix.partyquest.service.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.community.CommunityCate;
import phoenix.partyquest.repository.category.CommunityCateRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CommunityCateServiceTest {
    @Autowired
    CommunityCateRepository communityCateRepository;

    @Test @DisplayName("communityCateTest") @Transactional
    public void commuCateTest(){
        List<CommunityCate> commuCate = communityCateRepository.findAll();
        Assertions.assertThat(commuCate.size()).isGreaterThan(0);
        System.out.println("CommuCate :"+commuCate.toString());
    }
}
