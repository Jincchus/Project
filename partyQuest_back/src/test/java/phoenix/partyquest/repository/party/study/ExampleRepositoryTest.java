package phoenix.partyquest.repository.party.study;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import phoenix.partyquest.api.request.study.StudyChangeApplicantStatusRequest;
import phoenix.partyquest.api.request.study.StudyMyPageRequest;
import phoenix.partyquest.api.request.study.StudyViewRequest;
import phoenix.partyquest.api.response.study.StudyMyPageMadeByMeResponse;
import phoenix.partyquest.domain.category.MajorCate;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.ApplicantStudyMap;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;
import phoenix.partyquest.domain.party.study.map.MessageMap;
import phoenix.partyquest.repository.member.MemberRepository;
import phoenix.partyquest.repository.party.study.map.ApplicantStudyMapRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class ExampleRepositoryTest {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ApplicantStudyMapRepository applicantStudyMapRepository;

    //동한
    @DisplayName("커스텀 레포지토리")
    void test() {
        //given
        String testTitle = "dragon";
        Study testStudy = Study.builder()
                .title(testTitle)
                .build();
        //when
        Study newStudy = studyRepository.save(testStudy);

        Study toTestStudy = exampleRepository.findStudyById(newStudy.getId());
        //then
        assertThat(toTestStudy.getTitle()).isEqualTo(testTitle);
    }
    //동한 끝
    //경진
    @DisplayName("findStudyView")
    @Transactional
    void findStudy(){
        // given
        StudyViewRequest viewRequest = new StudyViewRequest();
        viewRequest.setStudyId(Long.valueOf("2"));


        // when
        Study findByStudy = studyRepository.findById(viewRequest.getStudyId()).orElseThrow();
        System.out.println(findByStudy.toString());

        String thumb = String.valueOf(findByStudy.getThumb());
        Long hostId = findByStudy.getHost().getId();
        RecruitOption recruitOption = findByStudy.getRecruitOption();
        String title = findByStudy.getTitle();
        String smallCate = findByStudy.getSmallCates().toString(); // SMALLCATE 가 []으로 나옴
        String nickName = findByStudy.getHost().getProfile().getNickName();

        System.out.println(thumb);
        System.out.println(hostId);
        System.out.println(recruitOption);
        System.out.println(title);
        System.out.println(smallCate);
        System.out.println(nickName);

        // then
        assertNotNull(findByStudy); // 특정 스터디를 찾았는지 확인

// 예상되는 결과값과 실제 값이 일치하는지 확인
        assertEquals(52, hostId); // expectedHostId는 예상되는 hostId 값
        assertEquals("FCFS", recruitOption); // expectedRecruitOption은 예상되는 recruitOption 값
        assertEquals("타이틀", title); // expectedTitle은 예상되는 title 값
        assertEquals("닉네임", nickName); // expectedNickName은 예상되는 nickName 값

    }

    //경진 끝
    //현정
    //현정 끝
    //동일
    @DisplayName("cate test")
    @Transactional
    void cateTest(){
        //given

        //when
//        List<MajorCate> majorCates = exampleRepository.findAllCate();
        List<MajorCate> majorCates = exampleRepository.findAllCateWithAllFetch();
        //then
        assertThat(majorCates.size()).isGreaterThan(0);
        System.out.println("cateTest Result : "+majorCates.toString());

    }


    void studyWaitingListMadeByMe(){
        //given
        long hostId= 52L;
        //when
        List<Study> studyList = exampleRepository.findStudyMadeInMeWithWaitingList(hostId);
        //then
        assertThat(studyList.size()).isGreaterThan(0);
    }

    @Rollback(value = false)
    public void deleteStudyMemberByMemberIdAndStudyId(){
        StudyChangeApplicantStatusRequest req = new StudyChangeApplicantStatusRequest();
        //스터디 생성자Id
        req.setHostId("1");
        //스터디 참여자 Id(승인 or 거절될 참여자의 Id)
        req.setApplicantId(52);
        //해당 Study의 Id
        req.setStudyId(102);

        Member member = memberRepository.findById(req.getApplicantId()).orElseThrow();
        Study selectedStudy = studyRepository.findById(req.getStudyId()).orElseThrow();
        int beforeSize = selectedStudy.getStudyMembers().size();
        int studyMemberList = exampleRepository.deleteStudyMemberByMemberIdAndStudyId(req);
        System.out.println("studyMemberList.size() :"+studyMemberList ) ;
        int afterSize = selectedStudy.getStudyMembers().size();
        assertThat(afterSize).isGreaterThan(beforeSize);
    }
    public void findStudyIAttendedPaging (){
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("duki0105@gmail.com");
        request.setPg(1);
        List<StudyMember> studyListIAttended = exampleRepository.findStudyIAttendedPaging(request);
        System.out.println("size : "+studyListIAttended.size());
        assertThat(studyListIAttended.size()).isGreaterThan(0);

    }


    public void findStudyMadeByMePaging(){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("duki0105@gmail.com");
        //when
        List<Study> studyMadeByMePaging = exampleRepository.findStudyMadeByMePaging(request);
        System.out.println("studyMadeByMePaging : "+studyMadeByMePaging.size());
        //then
        assertThat(studyMadeByMePaging.size()).isGreaterThan(0);
    }
    public  void findStudyILiked(){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("duki0105@gmail.com");
        //when
        List<LikedStudyMemberMap> likeList = exampleRepository.findStudyILiked(request);
        //then
        assertThat(likeList.size()).isGreaterThan(0);
    }
    public void myWaitingList (){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("dddd@dddd.com");
        //when
        List<ApplicantStudyMap> waitingList = exampleRepository.findMyWaitingListPaging(request);
        //then
        assertThat(waitingList.size()).isGreaterThan(0);

    }
    public void myRejectedList (){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("dddd@dddd.com");
        //when
        List<ApplicantStudyMap> rejectedList = exampleRepository.findMyRejectedListPaging(request);
        //then
        List<StudyMyPageMadeByMeResponse> myRejectedList = rejectedList.stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        System.out.println("myRejectedList :"+myRejectedList.toString());
        assertThat(rejectedList.size()).isGreaterThan(0);

    }
    public void findUnreadMessage(){
        //given
        String memberEmail = "dddd@dddd.com";
        //when
        int result = exampleRepository.findUnreadMessage(memberEmail);
        //then
        System.out.println("result : "+result);
        assertThat(result).isEqualTo(1);
    }
    @Test
    @Rollback(value = false)
    public void findMessageList (){
        //given
        String memberEmail ="dddd@dddd.com";
        //when
        List<MessageMap> messageList = exampleRepository.fintMessageList(memberEmail);
        //then
        System.out.println("size : "+messageList.size());
        assertThat(messageList.size()).isGreaterThan(0);
    }

    //동일 끝



}