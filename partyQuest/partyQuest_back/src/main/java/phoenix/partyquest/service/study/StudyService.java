package phoenix.partyquest.service.study;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.dto.UploadFileDto;
import phoenix.partyquest.api.exception.StudyNotFoundException;
import phoenix.partyquest.api.request.study.*;
import phoenix.partyquest.api.response.study.*;
import phoenix.partyquest.constants.FileDirectoryConstants;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.category.SmallCate;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.StudyHistory;
import phoenix.partyquest.domain.member.profile.MemberProfile;
import phoenix.partyquest.domain.party.PartyOnOff;
import phoenix.partyquest.domain.party.PartyStatus;
import phoenix.partyquest.domain.party.location.PartyLocation;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.*;
import phoenix.partyquest.repository.category.MiddleCateRepository;
import phoenix.partyquest.repository.category.SmallCateRepository;
import phoenix.partyquest.repository.file.UploadFileRepository;
import phoenix.partyquest.repository.member.MemberRepository;
import phoenix.partyquest.repository.party.location.PartyLocationRepository;
import phoenix.partyquest.repository.party.study.ExampleRepository;
import phoenix.partyquest.repository.party.study.StudyRepository;
import phoenix.partyquest.repository.party.study.map.ApplicantStudyMapRepository;
import phoenix.partyquest.repository.party.study.map.LikedStudyMemberRepository;
import phoenix.partyquest.repository.party.study.map.MessageMapRepository;
import phoenix.partyquest.service.file.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final PartyLocationRepository partyLocationRepository;
    private final MiddleCateRepository middleCateRepository;
    private final SmallCateRepository smallCateRepository;
    private final ApplicantStudyMapRepository applicantStudyMapRepository;
    private final ExampleRepository exampleRepository;
    private final UploadFileRepository uploadFileRepository;
    private final LikedStudyMemberRepository likedStudyMemberRepository;
    private final FileService fileService;
    private final EntityManager em;
    private final MessageMapRepository messageMapRepository;

    @Transactional
    public Study createStudy(StudyCreateRequest dto) {
        Member host = memberRepository.findById(dto.getHostId()).orElseThrow(); // 찾는 사용자가 없는 경우 커스텀 에러

        Study.StudyBuilder studyBuilder = dto.toStudyBuilder(host);

        PartyOnOff partyOnOff = PartyOnOff.valueOf(dto.getPartyOnOff());
        PartyLocation location = partyLocationRepository.findById(dto.getLocationId()).orElseThrow();
        studyBuilder = studyBuilder.location(location);
        Study newStudy = studyBuilder.build();

        List<MiddleCate> middleCates = middleCateRepository.findAllById(dto.getMiddleCateIds());
        List<SmallCate> smallCates = smallCateRepository.findAllById(dto.getSmallCateIds());

        for (MiddleCate middleCate :
                middleCates) {
            MiddleStudyMap middleStudyMap = new MiddleStudyMap();
            middleStudyMap.allocateMiddleCate(middleCate);

            newStudy.addMiddleCate(middleStudyMap);
        }
        for (SmallCate smallCate : smallCates) {
            SmallStudyMap smallStudyMap = new SmallStudyMap();
            smallStudyMap.allocateSmallCate(smallCate);

            newStudy.addSmallCate(smallStudyMap);
        }
        return studyRepository.save(newStudy);
    }

    /**
     * FCFS(first come first serve) 모집 방식의 스터디인 경우 신청시 바로 조인 처리
     * Pessimistic lock으로 study 선착순 신청시 동시성 문제를 해결한다. -> TODO:레디스 기반으로 바꾸기
     */
    @Transactional
    public void createFCFSRequest(StudyMemberJoinRequest dto) {
        Study findStudy = studyRepository.findStudyWithPessiLockById(dto.getStudyId()).orElseThrow();
        if (!findStudy.getStatus().equals(PartyStatus.OPENED) || !findStudy.getRecruitOption().equals(RecruitOption.FCFS)) {
            // 모집중이 아니거나, 선착순 방식의 모집이 아니면 에러 반환
            throw new RuntimeException(); //TODO: 400에러로 반환해주기
        }
        // lock 이용하여 동시성 처리한다.
        Member findMember = memberRepository.findMemberWithProfileById(dto.getApplicantId()).orElseThrow();
        MemberProfile memberProfile = findMember.getProfile();

        //스터디 모임에 인원 추가
        StudyMember newStudyMember = StudyMember.builder()
                .study(findStudy)
                .member(findMember)
                .build();
        findStudy.addMember(newStudyMember);

        //멤버 스터디 히스토리 등록
        StudyHistory studyHistoryOfMember = StudyHistory.builder()
                .study(findStudy)
                .build();
        memberProfile.addStudyHistory(studyHistoryOfMember);
    }

    //동한
    public List<PartyLocationResponse> getLocationList() {
        List<PartyLocation> results = partyLocationRepository.findAll();
        return results.stream()
                .map(PartyLocationResponse::new)
                .collect(Collectors.toList());
    }
    public Page<StudyListResponse> getStudyPagedList(StudyListCondRequest cond) {
        return studyRepository.getStudyListWithCond(cond);
    }
    public List<StudyListResponse> getStudyListForMain(String order) {
        return studyRepository.getMainStudyListWithOrderCond(order);
    }
    //동한 끝


    //경진
    @Transactional
    public StudyViewResponse findStudy(Long studyId) {
        Study findStudy = exampleRepository.findStudyFullFetch(studyId);
        List<LikedStudyMemberMap> memberList = likedStudyMemberRepository.findByStudyId(studyId);
        List<LikedStudyMemberResponse> results = memberList.stream()
                    .map(LikedStudyMemberResponse::new)
                    .collect(Collectors.toList());

        System.out.println("service findStudy : " + findStudy);
        return new StudyViewResponse(findStudy,results);

    }

    @Transactional
    public void deleteStudy(StudyDeleteRequest request){
        Study study = studyRepository.findById(request.getStudyId()).orElseThrow();
        if(study.getHost().getId()==request.getHostId()){
            studyRepository.deleteById(request.getStudyId());
        } //kj else부분 throw custom
    }

    @Transactional
    public StudyModifyResponse selectModifyStudy(Long studyId){
        Study study = studyRepository.findById(studyId).orElseThrow(()->new StudyNotFoundException("찾는 스터디가 없어요"));

            return new StudyModifyResponse(study);

         //kj esle부분 custom error

    }

    @Transactional
    public Study modifyStudy(Long studyId,StudyModifyRequest request) {

        Study findStudy = em.find(Study.class, studyId);

        Member host = memberRepository.findById(request.getHostId()).orElseThrow(); // 찾는 사용자가 없는 경우 커스텀 에러

        // Location 수정
        PartyLocation location = partyLocationRepository.findById(request.getLocationId()).orElseThrow();
        findStudy.changeLocation(location);

        // MiddleCate 수정
        List<MiddleCate> middleCates = middleCateRepository.findAllById(request.getMiddleCateIds());
//        findStudy.clearMiddleCates(); // 기존의 중분류 삭제
        for (MiddleCate middleCate : middleCates) {
            MiddleStudyMap middleStudyMap = new MiddleStudyMap();
            middleStudyMap.allocateMiddleCate(middleCate);
            findStudy.addMiddleCate(middleStudyMap);
        }

        // SmallCate 수정
        List<SmallCate> smallCates = smallCateRepository.findAllById(request.getSmallCateIds());
//        findStudy.clearSmallCates(); // 기존의 소분류 삭제
        for (SmallCate smallCate : smallCates) {
            SmallStudyMap smallStudyMap = new SmallStudyMap();
            smallStudyMap.allocateSmallCate(smallCate);
            findStudy.addSmallCate(smallStudyMap);
        }

        Study updatedStudy = request.updateStudy(findStudy);
        return updatedStudy;
    }


    //경진 끝

    //현정

    @Transactional
    public void reallocateThumb(Long studyId, MultipartFile thumb) throws IOException {
        Study study = studyRepository.findById(studyId).orElseThrow();
        UploadFileDto uploadFileDto = fileService.storeFile(thumb, FileDirectoryConstants.THUMB);
        UploadFile newThumb = uploadFileRepository.save(uploadFileDto.toUploadFile());

        study.changeThumb(newThumb);
    }
    @Transactional
    public String uploadFile(MultipartFile upload) {
        try {
            UploadFileDto uploadFileDto = fileService.storeFile(upload, FileDirectoryConstants.DESCRIPTION);
            UploadFile newUpload = uploadFileRepository.save(uploadFileDto.toUploadFile());
            return fileService.getFullPathWithDir(FileDirectoryConstants.DESCRIPTION, newUpload.getStoredName());
        } catch (IOException e) {
            // Log the exception details
            log.error("Error uploading file: {}", e.getMessage(), e);
            // Rethrow the exception or handle it as needed
            throw new RuntimeException("Error uploading file", e);
        }
    }

    public void likeStudy(Member member, Study targetStudy) {
        LikedStudyMemberMap like = LikedStudyMemberMap.builder()
                .study(targetStudy)
                .member(member)
                .build();
        member.addLikedStudy(like);
        targetStudy.likeCountUp();
    }


    public void unlikeStudy(Member member, Study targetStudy){
        // 스트림 필터로 원하는 liked study 리스트 가져오기
        List<LikedStudyMemberMap> likedStudyList = member.getLikedStudyList().stream()
                .filter(likedStudy -> likedStudy.getStudy().equals(targetStudy))
                .collect(Collectors.toList());

        if (!likedStudyList.isEmpty()) {
            LikedStudyMemberMap targetStudyMap = likedStudyList.get(0);
            member.removeLikedStudy(targetStudyMap);
            targetStudy.likeCountDown();
        }
    }
    @Transactional
    public void updateLikeStudy(StudyHeartRequest studyHeartRequest) throws ChangeSetPersister.NotFoundException {
        // 기본 설정은 unliked
        boolean alreadyLiked = false;
        // liked 한 멤버 아이디 찾기/ 없을 경우 예외 처리
        Member member = memberRepository.findMemberWithStudyListById(studyHeartRequest.getMemberId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // liked한 study 찾기 / 없을 경우 예외 처리
        Study study = studyRepository.findById(studyHeartRequest.getStudyId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        // 존재하면 alreadyLike 활성화
        // alreadyLike 인 경우 취소(unlike) 가능
        List<LikedStudyMemberMap> likedStudyList = member.getLikedStudyList();
        long likedCount = likedStudyList.stream()
                .filter(el -> el.getStudy().getId() == study.getId())
                .count();

        if (likedCount > 0) alreadyLiked = true;
        if(alreadyLiked) unlikeStudy(member,study);
        else likeStudy(member,study);
    }
    //현정 끝

    //동일
    @Transactional
    public String ApplyStudy(StudyMemberJoinRequest req){
        //todo:이미 신청한 멤버일 경우 이미신청했다는 것을 front로 보내주기

        // 신청한 멤버와 스터디 정보 가져오기
        Member saveMember = memberRepository.findByEmail(req.getApplicantId()).orElseThrow();
        Study selectStudy = studyRepository.findById(req.getStudyId()).orElseThrow();

        String answer = "";

        //스터디 호스트가 참가 신청을 한 경우
        if(saveMember.getId().equals(selectStudy.getHost().getId())) {
            answer = "본인이 만든 스터디에 참가 신청할 수 없습니다.";
        }else{
            // PNP 여부 확인
            if (selectStudy.getRecruitOption().equals(RecruitOption.PNP)) {

                // 중복 신청 여부 확인
                boolean alreadyApplied = selectStudy.getWaitingList()
                        .stream()
                        .anyMatch(wait -> wait.getMember().getId().equals(saveMember.getId()));

                if (!alreadyApplied) {
                    answer = "신청이 완료되었습니다.";
                    // WaitingList 생성 및 저장
                    ApplicantStudyMap createWaitingList = new ApplicantStudyMap(selectStudy, saveMember, ApplicationStatus.PENDING);
                    ApplicantStudyMap waitingList = applicantStudyMapRepository.save(createWaitingList);

                } else {
                    //거절 된 적 있는지 확인
                    boolean rejectMember = selectStudy.getWaitingList().stream()
                            .filter(reject -> reject.getStatus().equals(ApplicationStatus.REJECTED))
                            .anyMatch(wait ->  wait.getMember().getId().equals(saveMember.getId()));

                    //이미 참여하고 있는지 확인
                    boolean appliedMember = selectStudy.getWaitingList().stream()
                            .filter(apply -> apply.getStatus().equals(ApplicationStatus.ACCEPTED))
                            .anyMatch(wait -> wait.getMember().getId().equals(saveMember.getId()));

                    if (appliedMember) {
                        answer ="이미 참여 중 입니다.";
                    } else if (rejectMember) {
                        answer = "해당 스터디에서 거절 되었습니다.";
                    } else {
                        answer = "이미 신청 하였습니다.";
                    }
                }
            } else {

                    // 중복 신청 여부 확인
                    boolean alreadyApplied = selectStudy.getStudyMembers()
                            .stream()
                            .anyMatch(studyMember -> studyMember.getMember().getId().equals(saveMember.getId()));

                    if (!alreadyApplied) {
                        answer = "신청이 완료되었습니다.";
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

                    } else {
                        answer = "이미 참여 중 입니다.";
                    }
            }

        }
        return answer;
    }

    public List<StudyWaitingListAPI> findStudyMadeInMeWithWaitingList(Long hostId){
        List<Study> studyList = exampleRepository.findStudyMadeInMeWithWaitingList(hostId);
        List<StudyWaitingListAPI> waitingListAPIList = new ArrayList<>();
        for(Study study : studyList){
            List<StudyWaitingListResponse> response = study.getWaitingList().stream().map(StudyWaitingListResponse::new).toList();
            StudyWaitingListAPI api = new StudyWaitingListAPI(study);
            waitingListAPIList.add(api);
        }
        return waitingListAPIList;
    }

    @Transactional
    //상단에 @Transactional(readOnly = true)가 있어서 update가 되지 않았다.
    //사용할 메서드 위에 @Transactional을 선언하면 update가 된다.
    // *@Transactional의 기본 값은 false 이다.*
    public void changeApplicationStatus(StudyChangeApplicantStatusRequest req){
        //상태변경될 멤버 조회
        Member selectedMember = memberRepository.findById(req.getApplicantId()).orElseThrow();

        //모임 생성자 조회
        Member host = memberRepository.findByEmail(req.getHostId()).orElseThrow();

        //host가 선택한 스터디 조회
        Study selectedStudy = studyRepository.findById(req.getStudyId()).orElseThrow();
        //해당 스터디의 참여대기자 목록
        List<ApplicantStudyMap> waitingLists = selectedStudy.getWaitingList();
        //업데이트될 멤버와 대기명단에 있는 멤버가 같은지 확인
        for(ApplicantStudyMap waitingList : waitingLists){
            if(req.getApplicantId()==waitingList.getMember().getId()){
                //상태 업데이트 될 멤버와 대기명단에 있는 멤버가 같음


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

                        //메세지 보내기
                        MessageMap message = new MessageMap(host,selectedMember,selectedStudy,req.getStatus());
                        MessageMap m1 =  messageMapRepository.save(message);
                    } else {
                        //todo:이미신청한 멤버라는 것 출력
                    }
                }else {
                    //REJECTED 거절을 한 경우

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

                        //MemberHistory에서 삭제
                        MemberProfile memberProfile = selectedMember.getProfile();
                        StudyHistory removeHistoryofMember = selectedMember.getProfile().getStudyHistories().stream().filter(studyHistory -> studyHistory.getMemberProfile().equals(selectedMember.getProfile()))
                                .collect(Collectors.toList()).get(0);
                        memberProfile.removeStudyHistory(removeHistoryofMember);
                        //메세지 보내기
                        MessageMap message = new MessageMap(host,selectedMember,selectedStudy,req.getStatus());
                        MessageMap m1 =  messageMapRepository.save(message);
                    }else{
                        //메세지 보내기
                        MessageMap message = new MessageMap(host,selectedMember,selectedStudy,req.getStatus());
                        MessageMap m1 =  messageMapRepository.save(message);
                    }
                }
            }
        }
    }

    //내가 만든 스터디
    public  List<StudyMyPageMadeByMeResponse> findStudyMadeByMePaging(StudyMyPageRequest request){

        List<StudyMyPageMadeByMeResponse> StudyMadeByMePaging = exampleRepository.findStudyMadeByMePaging(request)
                .stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        return StudyMadeByMePaging;
    }//end

    //내가 참여 중인 스터디
    public  List<StudyMyPageMadeByMeResponse> findStudyIAttendedPaging(StudyMyPageRequest request){
        List<StudyMyPageMadeByMeResponse> StudyIAttended = exampleRepository.findStudyIAttendedPaging(request)
                .stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        return StudyIAttended;
    }

    //내가 좋아요 누른 스터디
    public  List<StudyMyPageMadeByMeResponse> findLikeListPaging(StudyMyPageRequest request){
        List<StudyMyPageMadeByMeResponse> likeList = exampleRepository.findStudyILiked(request).stream().map(StudyMyPageMadeByMeResponse::new).collect(Collectors.toList());
        return  likeList;
    }

    //대기 중인 스터디
    public List<StudyMyPageMadeByMeResponse> findMyWaitingListPaging(StudyMyPageRequest request){
        List<StudyMyPageMadeByMeResponse> myWaitingList = exampleRepository.findMyWaitingListPaging(request)
                                                                            .stream()
                                                                            .map(StudyMyPageMadeByMeResponse::new)
                                                                            .collect(Collectors.toList());
        return myWaitingList;
    }

    //거절당한 스터디
    public List<StudyMyPageMadeByMeResponse> findRejectedListPaging(StudyMyPageRequest request){
        List<StudyMyPageMadeByMeResponse> myRejectedList = exampleRepository.findMyRejectedListPaging(request)
                .stream()
                .map(StudyMyPageMadeByMeResponse::new)
                .collect(Collectors.toList());
        return myRejectedList;
    }

    //스터디의 현재 멤버 조회
    public StudyCurMemberAndWaitingListResponse myStudyMember(StudyMyStudyMemberRequest request){
        Member member = memberRepository.findByEmail(request.getMemberId()).orElseThrow();
        Study study = studyRepository.findById(request.getStudyId()).orElseThrow();
        StudyCurMemberAndWaitingListResponse memberList = new StudyCurMemberAndWaitingListResponse();
        if(study.getHost().getEmail().equals(request.getMemberId())){
            //member의 아이디와 study를 만든 사람의 아이디가 같을 경우, 대기명단까지 보이게 만들기
            memberList = new StudyCurMemberAndWaitingListResponse(study,study.getStudyMembers(),study.getWaitingList());
        }else{
            //member의 아이디와 study를 만든 사람의 아이디가 다를경우, 현재 참여하고 있는 멤버만 보이게 만들기
            memberList  = new StudyCurMemberAndWaitingListResponse(study,study.getStudyMembers());
        }
        return memberList;
    }

    //읽지 않은 메세지 조회
    public int findUnreadMessage(String memberId){
        int result = exampleRepository.findUnreadMessage(memberId);
        return  result;
    }

    //동일 끝
}
