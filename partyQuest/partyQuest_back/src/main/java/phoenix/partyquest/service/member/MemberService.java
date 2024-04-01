package phoenix.partyquest.service.member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.dto.UploadFileDto;
import phoenix.partyquest.api.request.member.MemberCreateRequest;
import phoenix.partyquest.api.request.member.MemberListRequest;
import phoenix.partyquest.api.request.member.MemberLoginRequest;
import phoenix.partyquest.api.request.member.ProfileModifyRequest;
import phoenix.partyquest.api.response.member.MemberListResponse;
import phoenix.partyquest.api.response.member.MemberMessageResponse;
import phoenix.partyquest.api.response.member.ProfileModifyResponse;
import phoenix.partyquest.config.security.JwtProvider;
import phoenix.partyquest.constants.FileDirectoryConstants;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.category.SmallCate;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.map.MiddleMemberMap;
import phoenix.partyquest.domain.member.map.SmallMemberMap;
import phoenix.partyquest.domain.member.profile.MemberProfile;
import phoenix.partyquest.domain.party.location.PartyLocation;
import phoenix.partyquest.domain.token.AccessToken;
import phoenix.partyquest.domain.token.RefreshToken;
import phoenix.partyquest.repository.AccessTokenRepository;
import phoenix.partyquest.repository.RefreshTokenRepository;
import phoenix.partyquest.repository.category.MiddleCateRepository;
import phoenix.partyquest.repository.category.SmallCateRepository;
import phoenix.partyquest.repository.file.UploadFileRepository;
import phoenix.partyquest.repository.member.MemberRepository;
import phoenix.partyquest.repository.party.location.PartyLocationRepository;
import phoenix.partyquest.repository.party.study.ExampleRepository;
import phoenix.partyquest.service.file.FileService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final ExampleRepository exampleRepository;
    private final PartyLocationRepository partyLocationRepository;
    private final MiddleCateRepository middleCateRepository;
    private final SmallCateRepository smallCateRepository;
    private final UploadFileRepository uploadFileRepository;
    private final FileService fileService;
    // 동하니
    // 회원 가입 후에 바로 jwt 토큰 반환.
    @Transactional
    public String signUpMemberAndGetJWTToken(MemberCreateRequest dto, HttpServletResponse response) {
        Member.MemberBuilder memberBuilder = dto.toMemberBuilder();
        // password encoding처리
        String rawPassword = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Member newMember = memberBuilder.password(encodedPassword)
                .build();
        Member savedMember = memberRepository.save(newMember);
        // 기본 프로필 할당
        MemberProfile defaultProfile = new MemberProfile(savedMember);
        savedMember.allocateProfile(defaultProfile);

        Authentication authentication = createAuthentication(newMember.getEmail(), rawPassword);
        String jwt = jwtProvider.createAccessJWT(authentication);
        AccessToken newAccessToken = AccessToken.builder()
                .userEmail(newMember.getEmail())
                .build();
        accessTokenRepository.save(newAccessToken);
        addRefreshCookie(newMember.getEmail(), response);
        return jwt;
    }

    @Transactional
    public String singInMemberAndIssueJWTToken(MemberLoginRequest dto,HttpServletResponse response) {
        Authentication authentication = createAuthentication(dto.getEmail(), dto.getPassword());

        Optional<AccessToken> rawfindAccessToken = accessTokenRepository.findAccessTokenByUserEmail(dto.getEmail());
        if (rawfindAccessToken.isEmpty()) {
            // 이미 있는 회원의 경우 새로 토큰을 만들어 준다.
            Member findMember = memberRepository.findByEmail(dto.getEmail()).orElseThrow();
            AccessToken newUserAccessJWT = AccessToken.builder()
                    .userEmail(findMember.getEmail())
                    .build();

            Cookie cookie = jwtProvider.createCookie(findMember.getEmail());
            String newUserrefreshJWTs = cookie.getValue();
            RefreshToken newUserRefreshJWT = RefreshToken.builder()
                    .userEmail(findMember.getEmail())
                    .refreshToken(newUserrefreshJWTs)
                    .build();
            accessTokenRepository.save(newUserAccessJWT);
            refreshTokenRepository.save(newUserRefreshJWT);
            response.addCookie(cookie);

            return jwtProvider.createAccessJWT(findMember.getEmail());
        }
        AccessToken findAccessToken = rawfindAccessToken.get();
        String newAccessToken = jwtProvider.createAccessJWT(authentication);
        findAccessToken.reActivateAccessToken();

        Cookie cookie = jwtProvider.createCookie(dto.getEmail());
        RefreshToken findRefreshToken = refreshTokenRepository.findRefreshTokenByUserEmail(dto.getEmail()).orElseThrow();
        findRefreshToken.reActivateAccessToken(cookie.getValue());
        response.addCookie(cookie);
        return newAccessToken;
    }

    @Transactional
    public void signOut(Authentication authentication,HttpServletResponse response) {
        String email = authentication.getName();
        AccessToken findAccessToken = accessTokenRepository.findAccessTokenByUserEmail(email).orElseThrow();
        findAccessToken.invalidate();

        RefreshToken findRefreshToken = refreshTokenRepository.findRefreshTokenByUserEmail(email).orElseThrow();
        findRefreshToken.invalidate();
        response.addCookie(jwtProvider.createEmptyCookie());
    }

    public Authentication createAuthentication(String username, String password) {
        //LEARN: 필터 외부에서
        // 1. username(email) + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 MyMemberDetailsService 에서 만든 loadUserByUsername 메서드 실행
        return authenticationManagerBuilder.getObject().authenticate(authToken);
    }

    @Transactional
    public void addRefreshCookie(String email, HttpServletResponse response) {
        Cookie cookie = jwtProvider.createCookie(email);
        RefreshToken newRefreshJWT = RefreshToken.builder()
                .userEmail(email)
                .refreshToken(cookie.getValue())
                .build();
        refreshTokenRepository.save(newRefreshJWT);
        response.addCookie(cookie);
    }

    //동한
    @Transactional
    public void modifyProfile(Authentication authentication, ProfileModifyRequest dto) {
        Member findMember = memberRepository.findMemberByEmailWithProfile(authentication.getName()).orElseThrow();
        MemberProfile targetProfile = findMember.getProfile();

        MemberProfile.MemberProfileBuilder profileBuilder = dto.toMemberProfile();
        PartyLocation findLocation = partyLocationRepository.findById(dto.getPreferredLocationId()).orElseThrow();
        MemberProfile profileToUpdate = profileBuilder.preferredLocation(findLocation).build();

        //middleCates, smallCates 업데이트 해야하는 경우 기존의 것 날리고 재 할당한다.
        if (!dto.getFavoriteFields().isEmpty()) {
            targetProfile.clearFavoriteMiddles();
            List<MiddleCate> middleCates = middleCateRepository.findAllById(dto.getFavoriteFields());
            for (MiddleCate middleCate :
                    middleCates) {
                MiddleMemberMap middleMemberMap = new MiddleMemberMap();
                middleMemberMap.allocateMiddleCate(middleCate);

                targetProfile.addMiddle(middleMemberMap);
            }
        }
        if (!dto.getFavoriteTechs().isEmpty()) {
            targetProfile.clearFavoriteSmalls();
            List<SmallCate> smallCates = smallCateRepository.findAllById(dto.getFavoriteTechs());
            for (SmallCate smallCate : smallCates) {
                SmallMemberMap smallMemberMap = new SmallMemberMap();
                smallMemberMap.allocateSmallCate(smallCate);
                targetProfile.addSmall(smallMemberMap);
            }
        }
        targetProfile.update(profileToUpdate);
    }

    public ProfileModifyResponse getMemberProfile(Authentication authentication) {
        Member findMember = memberRepository.findMemberByEmailWithProfile(authentication.getName()).orElseThrow();
        MemberProfile targetProfile = findMember.getProfile();
        return new ProfileModifyResponse(targetProfile);
    }
    @Transactional
    public void updateProfileAvatar(Authentication authentication, MultipartFile avatar) throws IOException {
        Member findMember = memberRepository.findMemberByEmailWithProfile(authentication.getName()).orElseThrow();
        MemberProfile targetProfile = findMember.getProfile();
        UploadFile origAvatar = targetProfile.getAvatar();
        UploadFileDto uploadFileDto = fileService.storeFile(avatar, FileDirectoryConstants.MEMBER_AVATAR);
        UploadFile newAvatar = uploadFileRepository.save(uploadFileDto.toUploadFile());

        targetProfile.changeAvatar(newAvatar);
        //기존 파일 있으면 비동기적으로 삭제처리한다.
        log.info("[it must be called!!] 0");
        if(origAvatar != null)
            log.info("[it must be called!!] 1");
            fileService.deleteFile(origAvatar.getStoredName(), FileDirectoryConstants.MEMBER_AVATAR);
    }
    //동한 끝

    //경진

    //경진 끝

    //현정
    public int checkEmailDuplicate(String email){
        long count = memberRepository.countByEmail(email);
        if (count > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public Page<MemberListResponse> getMemberListWithCond(MemberListRequest memberListRequest){
        return memberRepository.getMemberListWithCond(memberListRequest);
    }

    //현정 끝

    //동일
    @Transactional
    public List<MemberMessageResponse> messageList(String memberId){
        List<MemberMessageResponse> messageList = exampleRepository.fintMessageList(memberId).stream().map(MemberMessageResponse::new).collect(Collectors.toList());
        return messageList;
    }
    //동일 끝
}
