package phoenix.partyquest.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.exception.CustomException;
import phoenix.partyquest.api.request.study.*;
import phoenix.partyquest.api.response.study.*;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.service.file.FileService;
import phoenix.partyquest.service.study.StudyService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    // 현정
    private final StudyService studyService;
    private final FileService fileService;
    @GetMapping
    public Study study(){

        return null;
    }

    /**
     * 스터디 생성
     *
     * @param dto 스터디 생성할 때 필수적인 DTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<StudyCreateResponse> createStudy(@RequestBody StudyCreateRequest dto) {
        Study study = studyService.createStudy(dto);
        StudyCreateResponse response = new StudyCreateResponse(study);
        return ResponseEntity.ok().body(response);

    }
    /**
     * 썸네일 업로드_더티 체킹
     * @return
     */
    @PostMapping("/uploadThumb")
    public ResponseEntity<String> uploadThumb(@RequestParam(value = "studyId") Long studyId,@RequestParam(value = "thumb", required = false) MultipartFile thumb){
        try {
            studyService.reallocateThumb(studyId, thumb);
            return ResponseEntity.ok("thumb nail updated");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/uploadFile")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("upload") MultipartFile upload) {
        try {
            String resourceURL = studyService.uploadFile(upload);
            return ResponseEntity.ok(new UploadFileResponse(resourceURL));
        } catch (Exception e) {
            log.error("Error uploading file: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 좋아요 기능 구현
     * @param dto
     */
    @PutMapping("/updateLike")
    public void updateLike(@RequestBody StudyHeartRequest dto) {
        try {
            studyService.updateLikeStudy(dto);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new CustomException(CustomException.MAIN_ERROR.NOTFOUND);
        }
    }

    //현정 끝

    //동한
    @GetMapping("/locations")
    public ResponseEntity<List<PartyLocationResponse>> getLocations() {
        return ResponseEntity.ok()
                .body(studyService.getLocationList());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<StudyListResponse>> getPagedStudyWithCond(@ModelAttribute StudyListCondRequest searchCond) {
        log.info("[STUDY SEARCH CONTROLLER] search cond = {}",searchCond);
        Page<StudyListResponse> studyPagedList = studyService.getStudyPagedList(searchCond);
        return ResponseEntity.ok()
                .body(studyPagedList);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudyListResponse>> getStudyListForMainPage(@RequestParam("sort") String sort) {
        List<StudyListResponse> results = studyService.getStudyListForMain(sort);
        return ResponseEntity.ok()
                .body(results);
    }
    //동한 끝

    //경진
    @GetMapping("/studyView/{studyId}")
    public StudyViewResponse findStudy(@PathVariable Long studyId){
         StudyViewResponse response= studyService.findStudy(studyId);
        return response;
    }

    @GetMapping("/modifyStudy/{studyId}")
    public StudyModifyResponse selectModifyStudy(@PathVariable Long studyId){
        // KJ 클라이언트에서 넘어오는 member정보와 서버의 hostId정보가 동일 할 시에만 select가능하게 해야함
        StudyModifyResponse response = studyService.selectModifyStudy(studyId);

        return response;
    }

    @DeleteMapping("/deleteStudy/{studyId}")
    public void deleteStudy(@RequestBody StudyDeleteRequest request){
        studyService.deleteStudy(request);
    }

    @PostMapping("/modifyStudy")
    public ResponseEntity<StudyModifyResponse> modifyStudy(@RequestBody StudyModifyRequest request) {

        Long studyId = request.getStudyId();
        Study study = studyService.modifyStudy(studyId,request);
        StudyModifyResponse response = new StudyModifyResponse(study);
        return ResponseEntity.ok().body(response);

    }
    //경진 끝



    //동일
    //스터디에 참여
    @PostMapping ("/ApplyStudy")
    public ResponseEntity<String> ApplyStudy(@RequestBody StudyMemberJoinRequest studyMemberJoinRequest){
        String answer =  studyService.ApplyStudy(studyMemberJoinRequest);
        return  ResponseEntity.ok()
                .body(answer);
    }
    //내가 만든 스터디 중 대기목록이 있는 모든 스터디를 조회
    @GetMapping ("/studyWaitingListMadeByMe/{hostId}")
    public List<StudyWaitingListAPI> studyWaitingListMadeByMe(@PathVariable Long hostId){
        return studyService.findStudyMadeInMeWithWaitingList(hostId);
    }
    //대기목록에서 승인 or 거절 시 상태가 변경되는 것
    @PostMapping("/changeApplicationStatus")
    public void changeApplicationStatus(@RequestBody StudyChangeApplicantStatusRequest request){
        studyService.changeApplicationStatus(request);
    }
    //내가 만든 스터디를 조회
    @PostMapping ("/findStudyMadeByMe")
    public List<StudyMyPageMadeByMeResponse> findStudyMadeByMe(@RequestBody StudyMyPageRequest myPageRequest){
        List<StudyMyPageMadeByMeResponse> studyListMadeByMe = studyService.findStudyMadeByMePaging(myPageRequest);
        return studyListMadeByMe;
    }

    //내가 참여하는 스터디 조회
    @PostMapping ("/findStudyIAttended")
    public List<StudyMyPageMadeByMeResponse> findStudyIAttendedPaging(@RequestBody StudyMyPageRequest myPageRequest){
        List<StudyMyPageMadeByMeResponse> StudyIAttended = studyService.findStudyIAttendedPaging(myPageRequest);
        return  StudyIAttended;
    }
    //좋아요를 누른 스터디 조회
    @PostMapping ("/findLikeList")
    public List<StudyMyPageMadeByMeResponse> findLikeList (@RequestBody StudyMyPageRequest request){
        List<StudyMyPageMadeByMeResponse> likeList = studyService.findLikeListPaging(request);
        return likeList;
    }
    //대기 중인 스터디 조회
    @PostMapping ("/findMyWaitingList")
    public List<StudyMyPageMadeByMeResponse> findMyWaitingList (@RequestBody StudyMyPageRequest request){
        System.out.println("waitingList : "+request.getHostId());
        List<StudyMyPageMadeByMeResponse> myWaitingList = studyService.findMyWaitingListPaging(request);
        return myWaitingList;
    }

    //거절당한 스터디 조회
    @PostMapping ("/findMyRejectedList")
    public List<StudyMyPageMadeByMeResponse> findMyRejectedList (@RequestBody StudyMyPageRequest request){
        List<StudyMyPageMadeByMeResponse> myRejectedList = studyService.findRejectedListPaging(request);
        return myRejectedList;
    }
    //스터디 멤버 조회
    @PostMapping("/studyMember")
    public StudyCurMemberAndWaitingListResponse myStudyMember(@RequestBody StudyMyStudyMemberRequest request){
        StudyCurMemberAndWaitingListResponse studyMember = studyService.myStudyMember(request);
        return studyMember;
    }

    //읽지 않은 메세지 조회


    //동일 끝
}
