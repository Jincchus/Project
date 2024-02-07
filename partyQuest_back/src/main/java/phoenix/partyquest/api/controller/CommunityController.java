package phoenix.partyquest.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.request.community.CommunityDeleteRequest;
import phoenix.partyquest.api.request.community.CommunityModifyRequest;
import phoenix.partyquest.api.request.community.CommunityWriteRequest;
import phoenix.partyquest.api.request.study.StudyDeleteRequest;
import phoenix.partyquest.api.request.study.StudyModifyRequest;
import phoenix.partyquest.api.response.community.CommunityCreateResponse;
import phoenix.partyquest.api.response.community.CommunityListResponse;
import phoenix.partyquest.api.response.community.CommunityModifyResponse;
import phoenix.partyquest.api.response.community.CommunityViewResponse;
import phoenix.partyquest.api.response.study.StudyModifyResponse;
import phoenix.partyquest.api.response.study.UploadFileResponse;
import phoenix.partyquest.domain.community.Community;
import phoenix.partyquest.domain.community.CommunityCate;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.service.community.CommunityService;
import phoenix.partyquest.service.study.StudyService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class CommunityController {

    private final CommunityService communityService;


    @PostMapping("/communityWrite")
    public ResponseEntity<CommunityCreateResponse> communityWrite(@RequestBody CommunityWriteRequest communityWriteRequest) throws IOException {

        Community community = communityService.createCommunity(communityWriteRequest);
        CommunityCreateResponse communityCreateResponse = new CommunityCreateResponse(community);
        return ResponseEntity.ok().body(communityCreateResponse);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadfile(@RequestParam( value = "communityId") Long communityId, @RequestParam(value = "file", required = false) MultipartFile file){
        try {
            communityService.uploadFile(communityId,file);
            return ResponseEntity.ok("community file updated");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/communityView/{communityId}")
    public CommunityViewResponse communityView(@PathVariable Long communityId){
        CommunityViewResponse response = communityService.findCommunity(communityId);
        log.info("controller file?"+response.getFile());
        log.info(response.toString());
        return response;
    }
    @GetMapping("/communityList/{cateId}")
    public ResponseEntity<List<CommunityListResponse>> getCommunityListByCate(@PathVariable Long cateId) {
            List<CommunityListResponse> communityList = communityService.findCommunityList(cateId);
            log.info(communityList.toString());
            return ResponseEntity.ok().body(communityList);
    }
    @DeleteMapping("/deleteCommunity/{communityId}")
    public void deleteCommunity(@RequestBody CommunityDeleteRequest request){
        communityService.deleteCommunity(request);
    }

    @GetMapping("/modifyCommunity/{communityId}")
    public CommunityModifyResponse selectModifyCommunity(@PathVariable Long communityId){
        CommunityModifyResponse response = communityService.selectModifyCommunity(communityId);
        return response;
    }
    @PostMapping("/modifyCommunity")
    public ResponseEntity<CommunityModifyResponse> modifyCommunity(@RequestBody CommunityModifyRequest request) throws IOException {
        Long communityId = request.getCommunityId();
        log.info("[modify community called] : " + request);
        log.info("[modify communityId] : " + communityId);

        Community community = communityService.modifyCommunity(communityId, request);
        CommunityModifyResponse response = new CommunityModifyResponse(community);
        return ResponseEntity.ok().body(response);
    }
}
