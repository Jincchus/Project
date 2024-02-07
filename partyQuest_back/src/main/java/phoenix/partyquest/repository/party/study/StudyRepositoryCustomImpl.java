package phoenix.partyquest.repository.party.study;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import phoenix.partyquest.api.request.study.StudyListCondRequest;
import phoenix.partyquest.api.response.study.StudyListResponse;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;
import static phoenix.partyquest.domain.category.QMiddleCate.middleCate;
import static phoenix.partyquest.domain.category.QSmallCate.smallCate;
import static phoenix.partyquest.domain.member.QMember.member;
import static phoenix.partyquest.domain.party.location.QPartyLocation.partyLocation;
import static phoenix.partyquest.domain.party.study.QStudy.study;
import static phoenix.partyquest.domain.party.study.map.QMiddleStudyMap.middleStudyMap;
import static phoenix.partyquest.domain.party.study.map.QSmallStudyMap.smallStudyMap;

@Repository
@RequiredArgsConstructor
public class StudyRepositoryCustomImpl  implements StudyRepositoryCustom{
    private final Long LIMIT_SIZE = 10L;
    private final JPAQueryFactory query;

    @Override
    public Optional<LikedStudyMemberMap> findByMemberAndStudy(Member member, Study study) {
        return null;
    }

    //TODO: middle cate in으로 조회 성능 높이기
    @Override
    public List<StudyListResponse> getMainStudyListWithOrderCond(String orderCond) {
        List<Study> results = query.selectFrom(study)
                .leftJoin(study.host, member).fetchJoin()
                .leftJoin(study.location, partyLocation).fetchJoin()
                .leftJoin(study.middleCates, middleStudyMap)
                .join(middleStudyMap.middleCate, middleCate)
                .leftJoin(study.smallCates, smallStudyMap).fetchJoin()
                .leftJoin(smallStudyMap.smallCate, smallCate).fetchJoin()
                .limit(LIMIT_SIZE)
                .orderBy(getOrder(orderCond))
                .distinct()
                .fetch();

        return results.stream()
                .map(StudyListResponse::new)
                .collect(Collectors.toList());
    }

    /**
     *  multibean bags 해결하기 위해서 fetch join을
     *  다수의 OneToMany중 하나에만 걸어주어 해결할 수 있으나
     *  N+1 문제를 발생시킨다
     */
    @Override
    public Page<StudyListResponse> getStudyListWithCond(StudyListCondRequest condRequest) {
        List<Study> results = query.select(study)
                .from(study)
                .join(study.host, member).fetchJoin()
                .leftJoin(study.location, partyLocation).fetchJoin()
                .leftJoin(study.middleCates, middleStudyMap)
                .join(middleStudyMap.middleCate, middleCate)
                .leftJoin(study.smallCates, smallStudyMap).fetchJoin()
                .join(smallStudyMap.smallCate, smallCate).fetchJoin()
                .where(
                        titleOrHostNameContains(condRequest.getTitle()),
                        middleCateEq(condRequest.getMiddleCateId()),
                        smallCateIn(condRequest.getSmallCateIds())
                )
                .offset(condRequest.getOffset())
                .limit(condRequest.getSize())
                .orderBy(getOrder(condRequest.getSort()))
                .distinct()
                .fetch();

        List<StudyListResponse> content = results.stream()
                .map(StudyListResponse::new)
                .collect(Collectors.toList());

        JPAQuery<Study> preCnt = query.select(study) //LEARN: study.count()도 순수한 객체가 아니어서 에라 발생 Caused by: org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list
                .from(study)
                .join(study.host, member).fetchJoin()
                .leftJoin(study.location, partyLocation).fetchJoin()
                .leftJoin(study.middleCates, middleStudyMap)
                .join(middleStudyMap.middleCate, middleCate)
                .leftJoin(study.smallCates, smallStudyMap).fetchJoin()
                .join(smallStudyMap.smallCate, smallCate).fetchJoin()
                .where(
                        titleOrHostNameContains(condRequest.getTitle()),
                        middleCateEq(condRequest.getMiddleCateId()),
                        smallCateIn(condRequest.getSmallCateIds())
                )
                .offset(condRequest.getOffset())
                .limit(condRequest.getSize())
                .distinct()
                .orderBy(getOrder(condRequest.getSort()));

        //return new PageImpl<>(content, condRequest.createPageable(), total);
        return PageableExecutionUtils.getPage(content, condRequest.createPageable(), preCnt::fetchCount);
    }

    private OrderSpecifier<?> getOrder(String sort) {
        if (!sort.isEmpty()) {
            switch (sort) {
                case "likeCnt":
                    return new OrderSpecifier<>(Order.DESC, study.likeCount);
                case "createdAt":
                    return new OrderSpecifier<>(Order.DESC, study.createdAt);
            }
        }
        return new OrderSpecifier<>(Order.DESC, study.createdAt);
    }

    private BooleanExpression smallCateIn(List<Long> smallCateIds) {
        // LEARN: study entity에서 출발 하기 때문에 any로 검사 해주어야 in 조회가 정상적으로 작동한다.
        return (smallCateIds != null && !smallCateIds.isEmpty())  ? study.smallCates.any().smallCate.id.in(smallCateIds) : null;
    }

    private BooleanExpression middleCateEq(Long middleCateId) {
        return middleCateId != null ? middleCate.id.eq(middleCateId) : null;
    }

    private BooleanExpression hostNameContains(String hostName) {
        return hasText(hostName) ? study.host.name.contains(hostName) : null;
    }

    private BooleanExpression titleContains(String studyTitle) {
        return hasText(studyTitle) ? study.title.contains(studyTitle) : null;
    }
    private BooleanBuilder titleOrHostNameContains(String target){
        BooleanBuilder rootBooleanExpression = new BooleanBuilder();
        if (hasText(target)) {
            rootBooleanExpression.or(study.title.contains(target));
            rootBooleanExpression.or(study.host.name.contains(target));
        }
        return rootBooleanExpression;
    }

}
