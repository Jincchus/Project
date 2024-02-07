package phoenix.partyquest.repository.member;

import org.springframework.data.domain.Page;
import phoenix.partyquest.api.request.member.MemberListRequest;
import phoenix.partyquest.api.response.member.MemberListResponse;

public interface MemberRepositoryCustom {

    // 멤버 리스트 _ 페이징 & 검색
    public Page<MemberListResponse> getMemberListWithCond(MemberListRequest memberListRequest);

}
