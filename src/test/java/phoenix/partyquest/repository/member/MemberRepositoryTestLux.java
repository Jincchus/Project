package phoenix.partyquest.repository.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;
import phoenix.partyquest.repository.party.study.StudyRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTestLux {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StudyRepository studyRepository;
    @Test
    @DisplayName("liked study가 create 될까요?")
    void isCreatable() {
        //given
        String name = "test";
        String password = "1234";
        String email = "test1@gmail.com";

        Member member = Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .build();

        Member newMember = memberRepository.save(member);

        Study study1 = Study.builder()
                .title("test3")
                .description("test1")
                .build();

        Study study2 = Study.builder()
                .title("test4")
                .description("test2")
                .build();
        studyRepository.saveAll(List.of(study1, study2));
        //when
        List<Study> studies = studyRepository.findAll();
        assertThat(studies.size()).isEqualTo(5);
        Study findStudy1 = studies.get(3);
        Study findStudy2 = studies.get(4);

        LikedStudyMemberMap ls1 = LikedStudyMemberMap.builder()
                .member(newMember)
                .study(findStudy1)
                .build();
        LikedStudyMemberMap ls2 = LikedStudyMemberMap.builder()
                .member(newMember)
                .study(findStudy2)
                .build();

        newMember.addLikedStudy(ls1);
        newMember.addLikedStudy(ls2);

        assertThat(newMember.getLikedStudyList().size()).isEqualTo(2);
        //then
    }
}