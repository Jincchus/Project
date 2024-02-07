package phoenix.partyquest.repository.party.study;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import phoenix.partyquest.api.request.study.StudyChangeApplicantStatusRequest;
import phoenix.partyquest.api.request.study.StudyLeaveRequest;
import phoenix.partyquest.api.request.study.StudyMyPageRequest;
import phoenix.partyquest.domain.category.MajorCate;
import phoenix.partyquest.domain.community.Community;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.study.RecruitOption;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.*;
import phoenix.partyquest.repository.member.MemberRepository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ExampleRepository {
    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;

    //동한
    public Study findStudyById(Long studyId) {

        List<Study> results = em.createQuery("select s from Study s where s.id = :id", Study.class)
                .setParameter("id", studyId)
                .getResultList();
        return results.get(0);
    }
    //동한 끝
    //경진

    public Study findStudyFullFetch(Long studyId) {
        Study findStudy = em.createQuery("select s from Study s left join s.studyMemberList sm where s.id = :id", Study.class)
                .setParameter("id", studyId)
                .getSingleResult();
        return findStudy;
    }

    //경진 끝
    //현정
    //현정 끝
    //동일

    public List<MajorCate> findAllCateWithAllFetch() {
        List<MajorCate> results = em.createQuery("select distinct m from MajorCate m left join m.middleCates mid left join mid.smallCates sm", MajorCate.class)
                .getResultList();
        return results;
    }

    public List<Study> findStudyMadeInMeWithWaitingList(Long hostId){
        //host가 개설한 스터디 중 PNP인 스터디의 WaitingList 조회
        Member host = memberRepository.findById(hostId).orElseThrow();
        List<Study> studyList = em.createQuery("select s from Study s  left join s.waitingList ws  where s.recruitOption = :recruitOption and s.host = :host",Study.class)
                .setParameter("host",host)
                .setParameter("recruitOption", RecruitOption.PNP)
                .getResultList();
        return studyList;
    }

    public int deleteStudyMemberByMemberIdAndStudyId(StudyChangeApplicantStatusRequest statusRequest){
            Member member = memberRepository.findById(statusRequest.getApplicantId()).orElseThrow();
            Study selectedStudy = studyRepository.findById(statusRequest.getStudyId()).orElseThrow();
            int deleteMember = em.createQuery("delete  from StudyMember sm where sm.member = :applicant and sm.study = : studyId")
                    .setParameter("applicant", member)
                    .setParameter("studyId", selectedStudy).executeUpdate();
        return  deleteMember;
    }

    public List<StudyMember> findStudyIAttendedPaging(StudyMyPageRequest request){
        Member member = memberRepository.findByEmail(request.getHostId()).orElseThrow();
        List<StudyMember> studys = em.createQuery("select sm from StudyMember sm where sm.member = :member",StudyMember.class)
                .setParameter("member",member)
                .setFirstResult(request.getPg())
                .setMaxResults(request.getSize())
                .getResultList();
        return studys;
    }

    public List<Study> findStudyMadeByMePaging(StudyMyPageRequest request){
        Member host = memberRepository.findByEmail(request.getHostId()).orElseThrow();
        List<Study> studyList = em.createQuery("select s from Study s  where s.host = :host order by s.createdAt desc ",Study.class)
                .setParameter("host",host)
                .setFirstResult(request.getPg())
                .setMaxResults(request.getSize())
                .getResultList();

        return studyList;

    }

    public List<LikedStudyMemberMap> findStudyILiked(StudyMyPageRequest request){
        Member member = memberRepository.findByEmail(request.getHostId()).orElseThrow();
        List<LikedStudyMemberMap> likeList = em.createQuery("select ls from LikedStudyMemberMap ls where ls.member = :member",LikedStudyMemberMap.class)
                .setParameter("member",member)
                .setFirstResult(request.getPg())
                .setMaxResults(request.getSize())
                .getResultList();
        return  likeList;
    }

    public List<ApplicantStudyMap> findMyWaitingListPaging(StudyMyPageRequest request){
        Member member = memberRepository.findByEmail(request.getHostId()).orElseThrow();
        List<ApplicantStudyMap> myWaitingList = em.createQuery("select w from ApplicantStudyMap w where w.member = :member and w.status = :status", ApplicantStudyMap.class)
                .setParameter("member",member)
                .setParameter("status", ApplicationStatus.PENDING)
                .setFirstResult(request.getPg())
                .setMaxResults(request.getSize())
                .getResultList();
        return myWaitingList;
    }

    public List<ApplicantStudyMap> findMyRejectedListPaging(StudyMyPageRequest request) {
        Member member = memberRepository.findByEmail(request.getHostId()).orElseThrow();
        List<ApplicantStudyMap> myRejectedList = em.createQuery("select w from ApplicantStudyMap w where w.member = :member and w.status = :status", ApplicantStudyMap.class)
                .setParameter("member",member)
                .setParameter("status", ApplicationStatus.REJECTED)
                .setFirstResult(request.getPg())
                .setMaxResults(request.getSize())
                .getResultList();
        return myRejectedList;
    }

    //해당 스터디에서 탈퇴? 나올 때
    public void leaveStudy(){
        StudyLeaveRequest request = new StudyLeaveRequest();
        Member member = memberRepository.findByEmail(request.getMemberId()).orElseThrow();
        Study study = studyRepository.findById(request.getStudyId()).orElseThrow();

        ApplicantStudyMap waitingList = em.createQuery("select w from ApplicantStudyMap w where w.member= :member and w.study= :study", ApplicantStudyMap.class)
                .setParameter("member",member)
                .setParameter("study",study)
                .getSingleResult();


        if(waitingList.getId().equals(request.getMemberId())){
            System.out.println("대기목록존재");
        }else {

        }
    }

    //읽지 않은 메세지 조회
    public int findUnreadMessage(String memberEmail){

        Member receiver = memberRepository.findByEmail(memberEmail).orElseThrow();
        List<MessageMap> UnreadMessageList = em.createQuery("select m from MessageMap m where m.receiver= :receiver and m.messageStatus= : messageStatus",MessageMap.class)
                .setParameter("receiver",receiver)
                .setParameter("messageStatus", MessageStatus.UNREAD)
                .getResultList();
         return  UnreadMessageList.size();
    }

    public List<MessageMap> fintMessageList(String memberEmail){
        Member receiver = memberRepository.findByEmail(memberEmail).orElseThrow();
        List<MessageMap> messageList= em.createQuery("select m from MessageMap m where m.receiver= : receiver order by m.rdate desc ",MessageMap.class)
                .setParameter("receiver",receiver)
                .getResultList();

        //호출될 때 읽지않음 표시된 메세지들을 읽음으로 변경
        messageList.forEach(message -> {
            if(message.getMessageStatus().equals(MessageStatus.UNREAD))
            {message.changeMessageStatus(MessageStatus.READ);}
        });
        return messageList;
    }

        //동일 끝


}
