package phoenix.partyquest.service.community;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.api.request.community.CommunityWriteRequest;
import phoenix.partyquest.domain.community.Community;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.repository.community.CommunityRepository;
import phoenix.partyquest.repository.member.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
@Transactional
@SpringBootTest
class CommunityServiceTest {

    @Autowired CommunityService communityService;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("커뮤니티 글 생성")
    void createCommunity() throws Exception{
        // given
        CommunityWriteRequest request = new CommunityWriteRequest();

        //request.setWriteId(1L);
        request.setFile(null);
        request.setTitle("titleTest");
        request.setContent("contentTest");
        request.setCateId(1L);

        Member writer = new Member();
        // when
//        Community entity = request.toCommunityBuilder(cate,writer).build();
//        Community createCommunity = communityRepository.save(entity);

        // then
//        assertThat(createCommunity).isNotNull();
//        assertThat(createCommunity.getId()).isNotNull().isEqualTo(1L);


    }
}
