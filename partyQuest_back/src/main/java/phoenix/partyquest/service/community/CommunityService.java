package phoenix.partyquest.service.community;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.dto.UploadFileDto;
import phoenix.partyquest.api.request.community.CommunityDeleteRequest;
import phoenix.partyquest.api.request.community.CommunityModifyRequest;
import phoenix.partyquest.api.request.community.CommunityWriteRequest;
import phoenix.partyquest.api.response.community.CommunityListResponse;
import phoenix.partyquest.api.response.community.CommunityModifyResponse;
import phoenix.partyquest.api.response.community.CommunityViewResponse;
import phoenix.partyquest.constants.FileDirectoryConstants;
import phoenix.partyquest.domain.community.Community;
import phoenix.partyquest.domain.community.CommunityCate;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.repository.category.CommunityCateRepository;
import phoenix.partyquest.repository.community.CommunityRepository;
import phoenix.partyquest.repository.file.UploadFileRepository;
import phoenix.partyquest.repository.member.MemberRepository;
import phoenix.partyquest.service.file.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityCateRepository communityCateRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;
    private final UploadFileRepository uploadFileRepository;
    private final EntityManager em;

    @Transactional
    public Community createCommunity(CommunityWriteRequest communityWriteRequest) throws IOException {
        
        Member writer = memberRepository.findById(communityWriteRequest.getWriterId()).orElseThrow();
        CommunityCate cate = communityCateRepository.findById(communityWriteRequest.getCateId()).orElseThrow();

        Community.CommunityBuilder communityBuilder = communityWriteRequest.toCommunityBuilder(cate,writer);
        Community newCommunity = communityBuilder.build();

        return communityRepository.save(newCommunity);
    }

    @Transactional
    public void uploadFile(Long communityId,MultipartFile upload) throws IOException{
            Community community = communityRepository.findById(communityId).orElseThrow();
            UploadFileDto uploadFileDto = fileService.storeFile(upload, FileDirectoryConstants.COMMUNITYFILE);
            UploadFile newUpload = uploadFileRepository.save(uploadFileDto.toUploadFile());

            community.changeFile(newUpload);
    }

    @Transactional
    public CommunityViewResponse findCommunity(Long communityId){
        Community findCommunity = communityRepository.findById(communityId).orElseThrow(); //communityId 및 cate로 조회
        return new CommunityViewResponse(findCommunity);
    }

    @Transactional
    public List<CommunityListResponse> findCommunityList(Long cateId){
        List<Community> list = communityRepository.findByCateId(cateId);
        List<CommunityListResponse> responseList = new ArrayList<>();

        log.info(list.toString());
        log.info(responseList.toString());
        for (Community community : list){
            responseList.add(new CommunityListResponse(community));
        }
        log.info(responseList.toString());

        return responseList;
    }

    @Transactional
    public void deleteCommunity(CommunityDeleteRequest request){
        Community community = communityRepository.findById(request.getCommunityId()).orElseThrow();
        log.info(String.valueOf(community.getMember().getId()));
        log.info(String.valueOf(request.getWriteId()));
        if (community.getMember().getId()==request.getWriteId()){
            communityRepository.deleteById(request.getCommunityId());
        }
    }

    @Transactional
    public CommunityModifyResponse selectModifyCommunity(Long communityId){
        Community community = communityRepository.findById(communityId).orElseThrow();
        return new CommunityModifyResponse(community);
    }

    @Transactional
    public Community modifyCommunity(Long communityId, CommunityModifyRequest request) throws IOException {
        Community findCommunity = em.find(Community.class, communityId);

        log.info(request.toString());
        Member writer = memberRepository.findById(request.getWriterId()).orElseThrow();
        CommunityCate cate = communityCateRepository.findById(request.getCateId()).orElseThrow();

        Community updatedCommunity = request.updateCommunity(findCommunity);
        updatedCommunity.setMember(writer);
        updatedCommunity.setCate(cate);
        return updatedCommunity;

    }
}

