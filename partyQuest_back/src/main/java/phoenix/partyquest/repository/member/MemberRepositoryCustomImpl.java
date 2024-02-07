package phoenix.partyquest.repository.member;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import phoenix.partyquest.api.request.member.MemberListRequest;
import phoenix.partyquest.api.response.member.MemberListResponse;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.profile.MemberMBTI;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;
import static phoenix.partyquest.domain.category.QMiddleCate.middleCate;
import static phoenix.partyquest.domain.category.QSmallCate.smallCate;
import static phoenix.partyquest.domain.member.QMember.member;
import static phoenix.partyquest.domain.member.map.QMiddleMemberMap.middleMemberMap;
import static phoenix.partyquest.domain.member.map.QSmallMemberMap.smallMemberMap;
import static phoenix.partyquest.domain.member.profile.QMemberProfile.memberProfile;
import static phoenix.partyquest.domain.party.location.QPartyLocation.partyLocation;
import static phoenix.partyquest.domain.party.study.QStudy.study;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final Long LIMIT_SIZE = 10L;
    private final JPAQueryFactory query;

    public Page<MemberListResponse> getMemberListWithCond(MemberListRequest memberListRequest) {
        List<Member> results = query.select(member)
                .from(member)
                .join(member.profile, memberProfile).fetchJoin()
                .leftJoin(memberProfile.preferredLocation, partyLocation).fetchJoin()
                .leftJoin(memberProfile.favoriteMiddles, middleMemberMap)
                .leftJoin(middleMemberMap.middleCate, middleCate)
                .leftJoin(memberProfile.favoriteSmalls, smallMemberMap).fetchJoin()
                .leftJoin(smallMemberMap.smallCate, smallCate).fetchJoin()
                .where(
                        nickNameContains(memberListRequest.getNickname()),
                        locationIn(memberListRequest.getPreferredLocation()),
                        middleCateEq(memberListRequest.getMiddleCateId()),
                        smallCateIn(memberListRequest.getSmallCateIds())
                )
                .offset(memberListRequest.getOffset())
                .limit(memberListRequest.getSize())
                .orderBy(getOrder(memberListRequest.getSort()))
                .distinct()
                .fetch();

        List<MemberListResponse> content = results.stream()
                .map(MemberListResponse::new)
                .collect(Collectors.toList());

        JPAQuery<Member> preCnt = query.select(member)
                .from(member)
                .join(member.profile, memberProfile).fetchJoin()
                .leftJoin(memberProfile.preferredLocation, partyLocation).fetchJoin()
                .leftJoin(memberProfile.favoriteMiddles, middleMemberMap)
                .leftJoin(middleMemberMap.middleCate, middleCate)
                .leftJoin(memberProfile.favoriteSmalls, smallMemberMap).fetchJoin()
                .leftJoin(smallMemberMap.smallCate, smallCate).fetchJoin()
                .where(
                        nickNameContains(memberListRequest.getNickname()),
                        locationIn(memberListRequest.getPreferredLocation()),
                        middleCateEq(memberListRequest.getMiddleCateId()),
                        smallCateIn(memberListRequest.getSmallCateIds())
                )
                .offset(memberListRequest.getOffset())
                .limit(memberListRequest.getSize())
                .distinct()
                .orderBy(getOrder(memberListRequest.getSort()));
        return PageableExecutionUtils.getPage(content, memberListRequest.createPageable(), preCnt::fetchCount);
    }

    private OrderSpecifier<?> getOrder(String sort) {
        if (!sort.isEmpty()) {
            switch (sort) {
                case "createdAt":
                    return new OrderSpecifier<>(Order.DESC, member.createdAt);
            }
        }
        return new OrderSpecifier<>(Order.DESC, member.createdAt);
    }
    private BooleanExpression locationIn(Long preferredLocation) {
        return preferredLocation != null ? partyLocation.id.eq(preferredLocation) : null;
    }


    private BooleanExpression smallCateIn(List<Long> smallCateIds) {
        return (smallCateIds != null && !smallCateIds.isEmpty())  ? study.smallCates.any().smallCate.id.in(smallCateIds) : null;
    }

    private BooleanExpression middleCateEq(Long middleCateId) {
        return middleCateId != null ? middleCate.id.eq(middleCateId) : null;
    }

    private BooleanExpression nickNameContains(String nickName) {
        return hasText(nickName) ? memberProfile.nickName.contains(nickName) : null;
    }


}
