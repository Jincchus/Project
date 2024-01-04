package phoenix.partyquest.domain.party.study;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.file.UploadFile;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.Party;
import phoenix.partyquest.domain.party.PartyOnOff;
import phoenix.partyquest.domain.party.PartyStatus;
import phoenix.partyquest.domain.party.location.PartyLocation;
import phoenix.partyquest.domain.party.study.map.ApplicantStudyMap;
import phoenix.partyquest.domain.party.study.map.MiddleStudyMap;
import phoenix.partyquest.domain.party.study.map.SmallStudyMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("study")
public class Study extends Party {
    //TODO: major cate 가 필드로 있어야 할거 같다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<MiddleStudyMap> middleCates = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<SmallStudyMap> smallCates = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<StudyMember> studyMembers = new ArrayList<>();
    private Integer curMembersSize; // TODO: '왜 wrapper로 할까? primitive
    private Integer memberUpperLimit;
    private Integer likeCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ApplicantStudyMap> waitingList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<StudyMember> studyMemberList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RecruitOption recruitOption;

    private LocalDate studyStartDate;
    private LocalDate studyEndDate;

    @Builder
    public Study(Member host, UploadFile thumb, String title, String description, PartyStatus status, PartyOnOff onOff, PartyLocation location, Integer memberUpperLimit, RecruitOption recruitOption, LocalDate studyStartAt, LocalDate studyEndAt) {
        super(host, thumb, title, description, status, onOff, location);
        this.curMembersSize = 0;
        this.memberUpperLimit = memberUpperLimit;
        this.likeCount = 0;
        this.recruitOption = recruitOption;
        this.studyStartDate = studyStartAt;
        this.studyEndDate = studyEndAt;
    }

    public void changeRecruitOption(RecruitOption recruitOption){
        this.recruitOption = recruitOption == null ? this.recruitOption : recruitOption;
    }

    public void changeMemberUpperLimit(Integer memberUpperLimit){
        this.memberUpperLimit = memberUpperLimit == null ? this.memberUpperLimit : memberUpperLimit;
    }

    public void changeStudyEndDate(LocalDate studyEndDate){
        if (studyEndDate != null) {
            this.studyEndDate = studyEndDate;
        }
    }

    public void addMiddleCate(MiddleStudyMap middleCate) {
        middleCates.add(middleCate);
        middleCate.allocateStudy(this);
    }

    public void addSmallCate(SmallStudyMap smallCate) {
        smallCates.add(smallCate);
        smallCate.allocateStudy(this);
    }

    //동시성은 pessimistic lock으로 처리한다.
    public void addMember(StudyMember newMember) {
        //TODO: 에러처리 해줄것
        if (this.getStatus().equals(PartyStatus.CLOSED) || Objects.equals(this.curMembersSize, memberUpperLimit)) {
            return;
        }
        System.out.println("bf :" +studyMembers.size());
        studyMembers.add(newMember);
        System.out.println("af :" +studyMembers.size());
        this.curMembersSize += 1;
        if (this.getCurMembersSize() == memberUpperLimit) {
            //다 모집 했으면 모집 상태 종료로 바꿔주자
            this.changeStatus(PartyStatus.CLOSED);
        }
    }

    public void likeCountUp() {
        this.likeCount++;
    }
    public void likeCountDown() {
        this.likeCount--;
    }
    public void removeMember(StudyMember member) {
        if (this.curMembersSize < 1) {
            return;
        }
        studyMembers.remove(member);
        this.curMembersSize -= 1;
        if (this.getStatus().equals(PartyStatus.CLOSED) && this.curMembersSize < memberUpperLimit) {
            this.changeStatus(PartyStatus.OPENED);
        }
    }


}
