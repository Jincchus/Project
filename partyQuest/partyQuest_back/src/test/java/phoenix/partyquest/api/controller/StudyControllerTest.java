package phoenix.partyquest.api.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.api.request.study.*;
import phoenix.partyquest.api.response.study.StudyCurMemberAndWaitingListResponse;
import phoenix.partyquest.api.response.study.StudyMyPageMadeByMeResponse;
import phoenix.partyquest.api.response.study.StudyWaitingListAPI;
import phoenix.partyquest.domain.party.study.map.ApplicationStatus;
import phoenix.partyquest.service.study.StudyService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class StudyControllerTest {

    @Autowired  StudyService studyService;
    //동한
    //동한 끝
    //경진
    //경진 끝
    //현정
    //현정 끝
    //동일

    @Test
//    @Rollback(value = false)
    public void ApplyStudy(){
        StudyMemberJoinRequest studyMemberJoinRequest  = new StudyMemberJoinRequest();
        studyMemberJoinRequest.setApplicantId("ehddlf01723@gmail.com");
        //참가 신청을 하고 싶은 스터디
        studyMemberJoinRequest.setStudyId(Long.valueOf("152"));

        String answer =  studyService.ApplyStudy(studyMemberJoinRequest);
        System.out.println("answer :"+answer);
        assertThat(answer.length()).isGreaterThan(0);
    }


    @DisplayName("controller Test : findStudyMadeInMeWithWaitingList")
    public void findStudyMadeInMeWithWaitingList(){
        Long hostId = 52L;
        List<StudyWaitingListAPI> waitingListAPIList = studyService.findStudyMadeInMeWithWaitingList(hostId);
        assertThat(waitingListAPIList.size()).isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("controller Test : studyWaitingListMadeByMe")
    public void changeApplicationStatus(){
        StudyChangeApplicantStatusRequest req = new StudyChangeApplicantStatusRequest();
        req.setHostId("duki0105@gmail.com");
        req.setApplicantId(1);
        req.setStudyId(52);
        req.setStatus(ApplicationStatus.REJECTED);

        studyService.changeApplicationStatus(req);
    }

    @DisplayName("controller Test : findStudyMadeByMe")
    public void findStudyMadeByMe(){
        //given
        StudyMyPageRequest myPageRequest = new StudyMyPageRequest();
        myPageRequest.setHostId("duki0105@gmail.com");
        //when
        List<StudyMyPageMadeByMeResponse> studyMadeByMe = studyService.findStudyMadeByMePaging(myPageRequest);
        //then
        System.out.println("studyMadeByMe Size : "+studyMadeByMe.size());
        System.out.println("studyMadeByMe Paging : "+studyMadeByMe.toString());
        assertThat(studyMadeByMe.size()).isGreaterThan(0);
    }
    public void findStudyIAttendedPaging(){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("duki0105@gmail.com");

        //when
        List<StudyMyPageMadeByMeResponse> studyList = studyService.findStudyIAttendedPaging(request);

        //then
        assertThat(studyList.size()).isGreaterThan(0);
        System.out.println("List : "+studyList.toString());
    }
    public void findLikeList(){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("duki0105@gmail.com");
        //when
        List<StudyMyPageMadeByMeResponse> studyList = studyService.findLikeListPaging(request);
        //then
        assertThat(studyList.size()).isGreaterThan(0);
        System.out.println("List : "+studyList.toString());

    }
    public void studyMember(){
        StudyMyStudyMemberRequest request = new StudyMyStudyMemberRequest();
        request.setMemberId("bllor@gmail.com");
        request.setStudyId(52L);
        StudyCurMemberAndWaitingListResponse res = studyService.myStudyMember(request);
        System.out.println("res : "+res.toString());
        assertThat(res.getCurMember().size()).isGreaterThan(0);
//        assertThat(res.getWaitingList().size()).isGreaterThan(0);
    }
    public void myWaitingList(){
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("dddd@dddd.com");
        List<StudyMyPageMadeByMeResponse> MyWaitingList = studyService.findMyWaitingListPaging(request);
        System.out.println("waitingList :"+MyWaitingList.toString());
        assertThat(MyWaitingList.size()).isGreaterThan(0);
    }
    public void myRejectedList(){
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("dddd@dddd.com");
        List<StudyMyPageMadeByMeResponse> MyRejectedList = studyService.findRejectedListPaging(request);
        System.out.println("waitingList :"+MyRejectedList.toString());
        assertThat(MyRejectedList.size()).isGreaterThan(0);
    }

    //읽지 않은 메시지 조회
    @Test
    public void findUnreadMessage(){
        String memberId = "duki0105@gmail.com";
        int result = studyService.findUnreadMessage(memberId);
        System.out.println("Controller result : "+result);
        assertThat(result).isEqualTo(0);
    }


    //동일 끝

}