package phoenix.partyquest.service.study;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.api.request.study.*;
import phoenix.partyquest.api.response.member.MemberMessageResponse;
import phoenix.partyquest.api.response.study.*;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.StudyHistory;
import phoenix.partyquest.domain.member.profile.MemberProfile;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.*;
import phoenix.partyquest.repository.category.MiddleCateRepository;
import phoenix.partyquest.repository.category.SmallCateRepository;
import phoenix.partyquest.repository.member.MemberRepository;
import phoenix.partyquest.repository.party.location.PartyLocationRepository;
import phoenix.partyquest.repository.party.study.ExampleRepository;
import phoenix.partyquest.repository.party.study.map.MessageMapRepository;
import phoenix.partyquest.repository.party.study.map.StudyMemberRepository;
import phoenix.partyquest.repository.party.study.StudyRepository;
import phoenix.partyquest.repository.party.study.map.ApplicantStudyMapRepository;
import phoenix.partyquest.repository.party.study.map.StudyMemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class StudyServiceTest {

    @Autowired StudyService studyService;
    @Autowired
    StudyRepository studyRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired PartyLocationRepository partyLocationRepository;
    @Autowired MiddleCateRepository middleCateRepository;
    @Autowired SmallCateRepository smallCateRepository;
    @Autowired ApplicantStudyMapRepository applicantStudyMapRepository;
    @Autowired EntityManager em;
    @Autowired StudyMemberRepository studyMemberRepository;
    @Autowired ExampleRepository exampleRepository;
    @Autowired MessageMapRepository messageMapRepository;


//동한
//동한 끝
//경진
    @DisplayName("studyView !")
    void findStudy() throws Exception{
        // given
        StudyViewRequest viewRequest = new StudyViewRequest();
        viewRequest.setStudyId(Long.valueOf("2"));
        List<LikedStudyMemberResponse> likedStudyMembers = new ArrayList<>();
        likedStudyMembers.add(new LikedStudyMemberResponse());
        Study findByStudy = studyRepository.findById(viewRequest.getStudyId()).orElseThrow();

        System.out.println(findByStudy.toString());

        String thumb = String.valueOf(findByStudy.getThumb());
        Long hostId = findByStudy.getHost().getId();
        String hostNickName = findByStudy.getHost().getProfile().getNickName();
        RecruitOption recruitOption = findByStudy.getRecruitOption();
        String title = findByStudy.getTitle();
        String smallCate = findByStudy.getSmallCates().toString();
        String nickName = findByStudy.getHost().getProfile().getNickName();
        Study findStudy = exampleRepository.findStudyFullFetch(viewRequest.getStudyId());
        StudyViewResponse studyViewResponse = new StudyViewResponse(findStudy,likedStudyMembers);
        List<StudyMemberResponse> studyMemberList = studyViewResponse.getStudyMemberInfo();

        System.out.println("studyMemberList : " + studyViewResponse.getStudyMemberInfo().toString());

        System.out.println(thumb);
        System.out.println(hostId);
        System.out.println(hostNickName);
        System.out.println(recruitOption);
        System.out.println(title);
        System.out.println(smallCate);
        System.out.println(nickName);

        // when
        studyViewResponse = studyService.findStudy(viewRequest.getStudyId());
        System.out.println(studyViewResponse);

        // then
        assertEquals("titleTest", studyViewResponse.getTitle());
        assertEquals("test3", studyViewResponse.getHostNickName());
    }
    @Rollback(value = false)
    @DisplayName("deleteStudy")
    void deleteStudy(){
        StudyDeleteRequest request = new StudyDeleteRequest();

        request.setHostId(Long.valueOf(52));
        request.setStudyId(Long.valueOf(2));

        Study study = studyRepository.findById(request.getStudyId()).orElseThrow();

        // 삭제 전 study 갯수 확인
        Long beforeStudyCount = studyRepository.count();

        if (study.getHost().getId()==request.getHostId()){
           studyRepository.deleteById(request.getStudyId());
        }else {
            System.out.println("domain hostId != request hostId");

        }

        // 삭제 후 study 갯수 확인
        Long afterStudyCount = studyRepository.count();


        assertThat(afterStudyCount).isEqualTo(beforeStudyCount - 1);

    }

    @DisplayName("selectModifyStudy !")
//    void selectModifyStudy(){
//        Long studyId = Long.valueOf(2);
//        Study study = studyRepository.findById(studyId).orElseThrow();
//
//        //when
//        StudyModifyResponse studyModifyResponse = new StudyModifyResponse(study);
//
//        Long hostId = studyModifyResponse.getHostId();
//        String thumb = studyModifyResponse.getThumb();
//        String title = studyModifyResponse.getTitle();
//        String description = studyModifyResponse.getDescription();
//        String partyOnOff = studyModifyResponse.getPartyOnOff();
//        Long locationId = studyModifyResponse.getLocationId();
//        String recruitOption = studyModifyResponse.getRecruitOption();
//        Integer memberUpperLimit = studyModifyResponse.getMemberUpperLimit();
//        List<StudyModifyResponse.MiddleCateIdNameResponse> middlecates = studyModifyResponse.getMiddlecates();
//        List<StudyModifyResponse.SmallCateIdNameResponse> smallcates = studyModifyResponse.getSmallcates();
//        LocalDate studyStartDate = studyModifyResponse.getStudyStartDate();
//        LocalDate studyEndDate = studyModifyResponse.getStudyEndDate();
//
//
//        // then
//        assertNotNull(studyModifyResponse);
//
//        assertEquals(study.getHost().getId(), hostId);
//        assertEquals(String.valueOf(study.getThumb()), thumb);
//        assertEquals(study.getTitle(), title);
//        assertEquals(study.getDescription(), description);
//        assertEquals(String.valueOf(study.getOnOff()), partyOnOff);
//        assertEquals(study.getLocation().getId(), locationId);
//        assertEquals(String.valueOf(study.getRecruitOption()), recruitOption);
//        assertEquals(study.getMemberUpperLimit(), memberUpperLimit);
//        assertNotNull(middlecates);
//        assertNotNull(smallcates);
//        assertEquals(study.getMiddleCates().size(), middlecates.size());
//        assertEquals(study.getSmallCates().size(), smallcates.size());
//        assertEquals(study.getStudyStartDate(), studyStartDate);
//        assertEquals(study.getStudyEndDate(), studyEndDate);
//
//        // Print the results (optional)
//        System.out.println("studyModifyResponse : " + studyModifyResponse.toString());
//        System.out.println("hostId : " + hostId);
//        System.out.println("thumb : " + thumb);
//        System.out.println("title : " + title);
//        System.out.println("description : " + description);
//        System.out.println("partyOnOff : " + partyOnOff);
//        System.out.println("locationId : " + locationId);
//        System.out.println("recruitOption : " + recruitOption);
//        System.out.println("memberUpperLimit : " + memberUpperLimit);
//        System.out.println("middlecates : " + middlecates);
//        System.out.println("smallcates : " + smallcates);
//        System.out.println("studyStartDate : " + studyStartDate);
//        System.out.println("studyEndDate : " + studyEndDate);
//
//
//
//    }

//경진 끝
    //현정
    @Test
    void createStudy() throws Exception{
        // given
        // 회원 아닌 사람 검증
        Long maxId = Long.MAX_VALUE;
        StudyCreateRequest invalidRequest = new StudyCreateRequest();
        invalidRequest.setHostId(maxId);

        assertThatThrownBy(() -> studyService.createStudy(invalidRequest))
                .isInstanceOf(RuntimeException.class);

        // 멤버 등록
        Member member1 = Member.builder()
                .name("이현정")
                .password("1234")
                .build();
        memberRepository.save(member1);
        assertThat(member1.getName()).isEqualTo("이현정");

        //유효한 요청 -> 존재하는 유저가 호스트id, 온라인 스터디 모임
        StudyCreateRequest validRequest = new StudyCreateRequest();
        validRequest.setHostId(member1.getId());
        // valueOf 값들은 모두 설정
        validRequest.setPartyOnOff("ON");
        validRequest.setRecruitOption("FCFS");

        // when
        Study entity = validRequest.
                toStudyBuilder(member1).build();
        Study savedStudy = studyRepository.save(entity);

        // then
        assertThat(savedStudy.getHost().getId()).isEqualTo(member1.getId());
    }

    @DisplayName("좋아요 기능이 될까요?_liked")
    void likeStudy() throws Exception{
        // given
        Member member = Member.builder()
                .name("이현정")
                .password("1234")
                .build();
        Study targetStudy = Study.builder()
                .title("좋아요 눌러줘")
                .build();

        member = memberRepository.save(member);
        targetStudy = studyRepository.save(targetStudy);
        assertThat(member.getName()).isEqualTo("이현정");

        // when
        studyService.likeStudy(member,targetStudy);
        Member savedMember = memberRepository.findById(member.getId()).orElse(null);
        Study savedStudy = studyRepository.findById(targetStudy.getId()).orElse(null);

        // then
        assertNotNull(savedMember);
        assertNotNull(savedStudy);
        assertEquals(1, savedStudy.getLikeCount());
        assertThat(savedMember.getLikedStudyList().size()).isEqualTo(1);
    }
    @DisplayName("좋아요 취소 기능이 될까요?_unliked")
    void unlikeStudy() throws Exception{
        // given
        Member member = Member.builder()
                .name("이현정")
                .password("1234")
                .build();
        Study targetStudy = Study.builder()
                .title("좋아요 취소해줘")
                .build();

        member = memberRepository.save(member);
        targetStudy = studyRepository.save(targetStudy);
        assertThat(member.getName()).isEqualTo("이현정");

        // when
        studyService.likeStudy(member,targetStudy);
        studyService.unlikeStudy(member,targetStudy);
        Member savedMember = memberRepository.findById(member.getId()).orElse(null);
        Study savedStudy = studyRepository.findById(targetStudy.getId()).orElse(null);

        // then
        assertNotNull(savedMember);
        assertNotNull(savedStudy);
        assertEquals(0, savedStudy.getLikeCount());
        assertThat(savedMember.getLikedStudyList().size()).isEqualTo(0);
    }

    @DisplayName("좋아요 기능이 완벽히 될까요?")
    void updateLike() throws Exception{
        // given
        Member member = Member.builder()
                .name("이현정")
                .password("1234")
                .build();
        Study targetStudy = Study.builder()
                .title("좋아요")
                .build();

        member = memberRepository.save(member);
        targetStudy = studyRepository.save(targetStudy);
        assertThat(member.getName()).isEqualTo("이현정");

        // when
        StudyHeartRequest likedStudy = new StudyHeartRequest();

        likedStudy.setMemberId(member.getId());
        likedStudy.setStudyId(targetStudy.getId());
        // 테스트 전 likeCount & likedStudyList 비어 있는지 재확인
        assertEquals(0, targetStudy.getLikeCount());
        assertTrue(member.getLikedStudyList().isEmpty());

        // 좋아요 추가
        studyService.updateLikeStudy(likedStudy);

        // then
        Member savedMember = memberRepository.findById(member.getId()).orElse(null);
        Study savedStudy = studyRepository.findById(targetStudy.getId()).orElse(null);
        assertNotNull(savedMember);
        assertNotNull(savedStudy);
        // 좋아요 추가 했을 시 count 수 확인
        assertEquals(1, savedStudy.getLikeCount());
        assertThat(savedMember.getLikedStudyList().size()).isEqualTo(1);

        // 좋아요 취소
        studyService.updateLikeStudy(likedStudy);
        savedStudy = studyRepository.findById(targetStudy.getId()).orElse(null);
        assertEquals(0, savedStudy.getLikeCount());
        assertThat(savedMember.getLikedStudyList()).isEmpty();
    }
//현정 끝
//동일
    @Test
//    @Rollback(value = false)//transactional 되지 않고 저장이 되게 함
    void ApplyStudy() {
        //TODO: 이미 신청한 회원일 경우, 이미 신청했다는 것을 프론트로 보내주기
        // StudyApplicationStudyMapRequest 생성
        StudyMemberJoinRequest req = new StudyMemberJoinRequest();
        req.setApplicantId("bllor@gmail.com");
        req.setStudyId(Long.valueOf("152"));

        // 신청한 멤버와 스터디 정보 가져오기
        Member saveMember = memberRepository.findByEmail(req.getApplicantId()).orElseThrow();
        Study selectStudy = studyRepository.findById(req.getStudyId()).orElseThrow();
        
        //스터디멤버리스트의 크기
        int currentMemberList = selectStudy.getCurMembersSize();

        //스터디 호스트가 참가 신청했는지 확인
        if(selectStudy.getHost().getId().equals(saveMember.getId())){
         System.out.println("본인이 만든 스터디에 신청할 수 없습니다.");
        }else {

            // PNP 여부 확인
            if (selectStudy.getRecruitOption().equals(RecruitOption.PNP)) {
                System.out.println("PNP신청");

                // 중복 신청 여부 확인
                boolean alreadyApplied = selectStudy.getWaitingList()
                        .stream()
                        .anyMatch(wait -> wait.getMember().getId().equals(saveMember.getId()));

                if (!alreadyApplied) {

                    // WaitingList 생성 및 저장
                    ApplicantStudyMap createWaitingList = new ApplicantStudyMap(selectStudy, saveMember, ApplicationStatus.PENDING);
                    ApplicantStudyMap waitingList = applicantStudyMapRepository.save(createWaitingList);
                    System.out.println("신청 완료되었습니다.");

                    // 검증
                    assertThat(waitingList.getMember().getId()).isEqualTo(saveMember.getId());
                    assertThat(waitingList.getStudy().getId()).isEqualTo(selectStudy.getId());
                } else {
                    boolean rejectMember = selectStudy.getWaitingList().stream().filter(reject -> reject.getStatus().equals(ApplicationStatus.REJECTED)).anyMatch(wait ->  wait.getMember().getId().equals(saveMember.getId()));
                    boolean appliedMember = selectStudy.getWaitingList().stream().filter(apply -> apply.getStatus().equals(ApplicationStatus.ACCEPTED)).anyMatch(wait -> wait.getMember().getId().equals(saveMember.getId()));

                    if (appliedMember) {
                        System.out.println("이미 참여 중입니다.: " + saveMember.getId());
                    } else if (rejectMember) {
                        System.out.println("해당 스터디에서 거절 당했습니다.: " + saveMember.getId());
                    } else {
                        System.out.println("이미 신청 하였습니다.: " + saveMember.getId());
                    }

                    assertThat(saveMember.getId()).isEqualTo(saveMember.getId());
                }

            } else {
                System.out.println("선착순 스터디");
                assertThat(selectStudy.getRecruitOption()).isEqualTo(RecruitOption.FCFS);

                // 중복 신청 여부 확인
                boolean alreadyApplied = selectStudy.getStudyMembers()
                        .stream()
                        .anyMatch(studyMember -> studyMember.getMember().getId().equals(saveMember.getId()));

                if (!alreadyApplied) {
                    System.out.println("처음 신청한 멤버");

                    // 스터디 모임에 인원 추가
                    StudyMember newStudyMember = StudyMember.builder()
                            .study(selectStudy)
                            .member(saveMember)
                            .build();
                    selectStudy.addMember(newStudyMember);

                    // 멤버 스터디 히스토리 등록
                    MemberProfile memberProfile = saveMember.getProfile();
                    StudyHistory studyHistoryOfMember = StudyHistory.builder()
                            .study(selectStudy)
                            .build();
                    memberProfile.addStudyHistory(studyHistoryOfMember);

                    // 검증
                    // 현재 신청한 멤버리스트의 크기가 증가하였는지 검증
                    assertThat(selectStudy.getStudyMembers().size()).isGreaterThan(currentMemberList);

                } else {
                    System.out.println("이미신청한 멤버의 Id: " + saveMember.getId());
                    System.out.println("이미 신청한 멤버");
                    assertThat(saveMember.getId()).isEqualTo(saveMember.getId());
                }
            }
        }
    }


    @DisplayName("serviceTest")
    void findStudyMadeInMeWithWaitingList(){
        Long hostId = 52L;
        List<Study> studyList = exampleRepository.findStudyMadeInMeWithWaitingList(hostId);
        List<StudyWaitingListAPI> waitingListAPIList = new ArrayList<>();
        for(Study study : studyList){
            List<StudyWaitingListResponse> response = study.getWaitingList().stream().map(StudyWaitingListResponse::new).toList();
            StudyWaitingListAPI api = new StudyWaitingListAPI(study);
            waitingListAPIList.add(api);
        }
        System.out.println("waitingListAPIList : "+waitingListAPIList.toString());
        System.out.println("waitingListAPIListSize : "+waitingListAPIList.size());
        assertThat(waitingListAPIList.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    void changeApplicationStatus(){
        StudyChangeApplicantStatusRequest req = new StudyChangeApplicantStatusRequest();
        //스터디 생성자Id
        req.setHostId("duki0105@gmail.com");
        //스터디 참여자 Id(승인 or 거절될 참여자의 Id)
        req.setApplicantId(1);
        //해당 Study의 Id
        req.setStudyId(52);
        //변경될 참여자의 상태
        req.setStatus(ApplicationStatus.REJECTED);

        //상태변경될 멤버 조회
        Member selectedMember = memberRepository.findById(req.getApplicantId()).orElseThrow();

        //호스트의 정보 조회
        Member host = memberRepository.findByEmail(req.getHostId()).orElseThrow();


        //host가 선택한 스터디 조회
        Study selectedStudy = studyRepository.findById(req.getStudyId()).orElseThrow();

        //멤버의 바뀌기 전 상태
        ApplicationStatus beforeUpdateStatus = null;

        //현재 스터티 멤버의 수
        int curMemberListSize = selectedStudy.getStudyMembers().size();

        //현재 스터디 히스토리의 수
        int curStudyHistorySize = selectedMember.getProfile().getStudyHistories().size();


        //해당 스터디의 참여대기자 목록
        List<ApplicantStudyMap> waitingLists = selectedStudy.getWaitingList();
        for(ApplicantStudyMap waitingList : waitingLists){

            //업데이트될 멤버와 대기명단에 있는 멤버가 같은지 확인
            if(req.getApplicantId()==waitingList.getMember().getId()){
                //업데이트 될 멤버와 대기명단에 있는 멤버가 같을 경우
                //changeStatus를 통해서 상태를 변경된 값을 넣어줌
                waitingList.changeStatus(req.getStatus());

                //ACCEPTED일 경우, 스터디 모임에 인원 추가
                if(waitingList.getStatus().equals(ApplicationStatus.ACCEPTED)){
                    //ACCEPTED인 경우
                    //front에서는 승낙일 경우, 거절할 수 있는 버튼만 출력할 것이므로 현재 스터디 모임에 멤버가 있는지 조회하지는 않을 예정

                    // 중복 신청 여부 확인
                    boolean alreadyApplied = selectedStudy.getStudyMembers()
                            .stream()
                            .anyMatch(studyMember -> studyMember.getMember().getId().equals(selectedMember.getId()));

                    if (!alreadyApplied) {
                        //멤버에 존재하지 않을 경우


                        // 스터디 모임에 인원 추가
                        StudyMember newStudyMember = StudyMember.builder()
                                .study(selectedStudy)
                                .member(selectedMember)
                                .build();
                        selectedStudy.addMember(newStudyMember);

                        // 멤버 스터디 히스토리 등록
                        MemberProfile memberProfile = selectedMember.getProfile();
                        StudyHistory studyHistoryOfMember = StudyHistory.builder()
                                .study(selectedStudy)
                                .build();
                        memberProfile.addStudyHistory(studyHistoryOfMember);

                        //메세지 추가하기
                        MessageMap message = new MessageMap(host,selectedMember,selectedStudy,req.getStatus());
                        MessageMap m1 =  messageMapRepository.save(message);

                        //메세지 검증 - 승낙 시
                        assertThat(m1.getReceiver()).isEqualTo(selectedMember);
                        assertThat(m1.getApplicationStatus()).isEqualTo(req.getStatus());
                        // 현재 신청한 멤버리스트의 크기가 증가하였는지 검증
                        assertThat(selectedStudy.getStudyMembers().size()).isGreaterThan(curMemberListSize);
                        //studyHistory가 증가하였는지 검증
                        assertThat(selectedMember.getProfile().getStudyHistories().size()).isGreaterThan(curStudyHistorySize);

                    } else {
                        //todo:이미신청한 멤버라는 것 출력
                        System.out.println("이미 신청한 멤버");
                        assertThat(selectedMember.getId()).isEqualTo(selectedMember.getId());
                    }
                }else {
                    //REJECTED 거절을 한 경우

                    System.out.println("거절!!!!!!!!");
                    System.out.println("거절!!!!!!!!");
                    System.out.println("거절!!!!!!!!");
                    //현재 스터디 모임에 거절된 멤버가 있는지 확인한 후 제거하기
                    // 중복 신청 여부 확인
                    boolean alreadyApplied = selectedStudy.getStudyMembers()
                            .stream()
                            .anyMatch(studyMember -> studyMember.getMember().getId().equals(selectedMember.getId()));
                    if (alreadyApplied){
                        //MemberList에 존재하는 대기자를 삭제

                        //제거될 멤버의 정보
                        StudyMember targetMember = selectedStudy.getStudyMemberList().stream()
                                .filter(studyMember -> studyMember.getMember().getId().equals(selectedMember.getId()))
                                .toList().get(0);

                        //현재스터디 멤버에서 거절된 멤버 삭제
                        selectedStudy.removeMember(targetMember);

                        System.out.println("히스토리 삭제!!!");
                        //MemberHistory에서 삭제
                        MemberProfile memberProfile = selectedMember.getProfile();
                        StudyHistory removeHistoryofMember = selectedMember.getProfile().getStudyHistories().stream().filter(studyHistory -> studyHistory.getMemberProfile().equals(selectedMember.getProfile()))
                                        .collect(Collectors.toList()).get(0);
                        System.out.println("제거될 멤버 : "+removeHistoryofMember.toString());
                        memberProfile.removeStudyHistory(removeHistoryofMember);


                        System.out.println("메세지 보내기!!!");
                        //메세지 추가하기
                        MessageMap message = new MessageMap(host,selectedMember,selectedStudy,req.getStatus());
                        MessageMap m1 =  messageMapRepository.save(message);

                        //메세지 검증 - 거절 시
                        assertThat(m1.getReceiver()).isEqualTo(selectedMember);
                        assertThat(m1.getApplicationStatus()).isEqualTo(req.getStatus());


                        // 현재 신청한 멤버리스트의 크기가 감소하였는지 검증
                        assertThat(selectedStudy.getStudyMembers().size()).isLessThan(curMemberListSize);
                        //studyHistory가 감소하였는지 검증
                        assertThat(selectedMember.getProfile().getStudyHistories().size()).isLessThan(curStudyHistorySize);
                    }else{
                        //memberList에 존재하지 않을 경우

                        //메세지 추가하기
                        MessageMap message = new MessageMap(host,selectedMember,selectedStudy,req.getStatus());
                        MessageMap m1 =  messageMapRepository.save(message);

                        //메세지 검증 - 거절 시
                        assertThat(m1.getReceiver()).isEqualTo(selectedMember);
                        assertThat(m1.getApplicationStatus()).isEqualTo(req.getStatus());
                    }
                }
            }
        }
    }

     void delete_studymemberByStudy(){
         StudyChangeApplicantStatusRequest req = new StudyChangeApplicantStatusRequest();
         //스터디 생성자Id
         req.setHostId("dddd@dddd.com");
         //스터디 참여자 Id(승인 or 거절될 참여자의 Id)
         req.setApplicantId(52);
         //해당 Study의 Id
         req.setStudyId(102);
         Member selectedMember = memberRepository.findById(req.getApplicantId()).orElseThrow();
         Study selectedStudy = studyRepository.findById(req.getStudyId()).orElseThrow();
         List<StudyMember> studyMembers = studyRepository.findById(req.getStudyId()).get().getStudyMembers();
         boolean alreadyApplied = selectedStudy.getStudyMembers()
                 .stream()
                 .anyMatch(studyMember -> studyMember.getMember().getId().equals(selectedMember.getId()));

         if(!alreadyApplied){
             System.out.println("없음");
         }else {
             System.out.println("있음");
             //studyRepository.dele
         }
    }



    public void findStudyMadeByMePaging(){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("52L");
        request.setPg(3);
        request.setSize(3);
        //when
        List<StudyMyPageMadeByMeResponse> StudyMadeByMePaging = exampleRepository.findStudyMadeByMePaging(request)
                .stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        //then
        System.out.println("StudyMadeByMePaging : "+StudyMadeByMePaging.toString());
        assertThat(StudyMadeByMePaging.size()).isGreaterThan(0);
    }

    @Test
    public void findStudyIAttendedPaging(){
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("duki0105@gmail.com");

        List<StudyMyPageMadeByMeResponse> studyList = exampleRepository.findStudyIAttendedPaging(request).stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        assertThat(studyList.size()).isGreaterThan(0);
    }

    public void findStudyILiked(){
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("duki0105@gmail.com");
        request.setPg(2);
        List<StudyMyPageMadeByMeResponse> likeList = exampleRepository.findStudyILiked(request).stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        assertThat(likeList.size()).isGreaterThan(0);
    }
    @Test
    public void MyStudyMember(){
        StudyMyStudyMemberRequest request = new StudyMyStudyMemberRequest();
        request.setMemberId("dddd@dddd.com");
        request.setStudyId(152L);
        Member member = memberRepository.findByEmail(request.getMemberId()).orElseThrow();
        Study study = studyRepository.findById(request.getStudyId()).orElseThrow();
        if(study.getHost().getEmail().equals(request.getMemberId())){
            System.out.println("같음");

            StudyCurMemberAndWaitingListResponse res = new StudyCurMemberAndWaitingListResponse(study,study.getStudyMembers(),study.getWaitingList());
            System.out.println("res : "+res.toString());
            List<StudyMember> studyMember = study.getStudyMembers();
            List<ApplicantStudyMap> waitingList = study.getWaitingList();
            assertThat(study.getHost().getEmail()).isEqualTo(request.getMemberId());
        }else{
            System.out.println("다름");
            StudyCurMemberAndWaitingListResponse res  = new StudyCurMemberAndWaitingListResponse(study,study.getStudyMembers());
            System.out.println("res : "+res.toString());
            List<StudyMember> studyMember = study.getStudyMembers();
            assertThat(study.getHost().getEmail()).isNotEqualTo(request.getMemberId());
        }

    }
    public void myWaitingList(){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("dddd@dddd.com");
        //when
        List<ApplicantStudyMap> waitingList = exampleRepository.findMyWaitingListPaging(request);
        List<StudyMyPageMadeByMeResponse> MyWaitingList = waitingList.stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        //then
        assertThat(MyWaitingList.size()).isGreaterThan(0);
    }

    @Test
    public void myRejectedList(){
        //given
        StudyMyPageRequest request = new StudyMyPageRequest();
        request.setHostId("dddd@dddd.com");
        //when
        List<ApplicantStudyMap> rejectedList = exampleRepository.findMyRejectedListPaging(request);
        List<StudyMyPageMadeByMeResponse> MyRejectedList = rejectedList.stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        //then
        assertThat(MyRejectedList.size()).isGreaterThan(0);
    }

    public void leaveStudy(){
    //given

    }
    @Test
    @Rollback(value = false)
    public void sendMessage(){
        String hostEmail = "duki0105@gmail.com";
        String applicantEmail = "dddd@dddd.com";
        Long StudyId = 52L;
        Member host = memberRepository.findByEmail(hostEmail).orElseThrow();
        Member applicant = memberRepository.findByEmail(applicantEmail).orElseThrow();
        Study study = studyRepository.findById(StudyId).orElseThrow();
        ApplicationStatus status = ApplicationStatus.ACCEPTED;
        MessageMap message = new MessageMap(host,applicant,study,status);
        MessageMap m1 =  messageMapRepository.save(message);
        assertThat(m1.getReceiver()).isEqualTo(applicant);
    }

    //읽지 않은 메세지 조회
    public void unReadMessage(){
        //given
        String memberEmail = "dddd@dddd.com";
        //when
        int result = exampleRepository.findUnreadMessage(memberEmail);
        //then
        System.out.println("result : "+result);
        assertThat(result).isEqualTo(1);

    }



//동일 끝
}