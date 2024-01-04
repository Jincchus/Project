package phoenix.partyquest.repository.party.study;

import org.springframework.data.domain.Page;
import phoenix.partyquest.api.request.study.StudyListCondRequest;
import phoenix.partyquest.api.response.study.StudyListResponse;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;

import java.util.List;
import java.util.Optional;

public interface StudyRepositoryCustom {
    //동한
    public Page<StudyListResponse> getStudyListWithCond(StudyListCondRequest condRequest);

    /**
     * main 페이지에서 사용할 임의 리스트이다.
     * @param orderCond : 정렬기준별 리스트를 불러 올때 사용할 기준이다. createdAt, likeCnt가 있다.
     * @return
     */
    public List<StudyListResponse> getMainStudyListWithOrderCond(String orderCond);
    //동한 끝

    //경진

    //경진 끝

    //현정
    public Optional<LikedStudyMemberMap> findByMemberAndStudy(Member member, Study study);

    //현정 끝
    //동일


    //동일 끝
}
