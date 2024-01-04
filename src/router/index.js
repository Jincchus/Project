import { createRouter, createWebHistory } from "vue-router";
import Main from "@/pages/Main.vue";
import Profile from "@/components/molecules/member/Profile.vue";
import Register from "@/components/molecules/member/Register.vue";
import Login from "@/components/molecules/member/Login.vue";
import WaitingList from "@/components/molecules/member/WaitingList.vue";
import StudyView from "@/components/molecules/study/StudyView.vue";
import createStudy from "@/components/molecules/study/CreateStudy.vue";
import ModifyStudy from "@/components/molecules/study/ModifyStudy.vue";
import TermsServicePolicy from "@/components/molecules/member/terms/TermsServicePolicy.vue";
import PrivacyPolicy from "@/components/molecules/member/terms/PrivacyPolicy.vue";
import PaymentPolicy from "@/components/molecules/member/terms/PaymentPolicy.vue";
import NoticeList from "@/components/molecules/board/NoticeList.vue";
import FaqList from "@/components/molecules/board/FaqList.vue";
import CommunityList from "@/components/molecules/board/CommunityList.vue";
import CommunityWrite from "@/components/molecules/board/CommunityWrite.vue";
import CommunityModify from "@/components/molecules/board/CommunityModify.vue";
import CommunityView from "@/components/molecules/board/CommunityView.vue";
import MyStudyPage from "@/components/molecules/member/MyStudyPage.vue";
import StudyMember from "@/components/molecules/member/StudyMember.vue";
import QnAList from "@/components/molecules/board/QnAList.vue";
import Aside from "@/components/molecules/admin/Aside.vue";
import StudyListPage from "@/pages/study/StudyListPage.vue";
import TestBed from "@/pages/TestBed.vue";
import PopUpAfterSignUp from "@/pages/member/PopUpAfterSignUp.vue";
import Message from "@/components/molecules/member/Message.vue";
import HyTestBed from "@/pages/HyTestBed.vue";
import MemberList from "@/pages/member/list/MemberList.vue";
import ProfileModify from "@/pages/member/profile/ProfileModify.vue";
import ProfileMain from "@/pages/member/profile/ProfileMain.vue";
import ProfileMyStudyPage from "@/pages/member/profile/ProfileMyStudyPage.vue";
import ProfileMyMessage from "@/pages/member/profile/ProfileMyMessage.vue";

// router 인스턴스 생성
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: Main },
    { path: "/index", component: Main },
    { path: "/profile", component: ProfileMain },
    { path: "/profile/update", component: ProfileModify },
    { path: "/register", component: Register },
    { path: "/register/popup", component: PopUpAfterSignUp ,
    props: route =>({
      userNickName: route.query.userNickName
    })},
    { path: "/login", component: Login },
    { path: "/waitingList/:hostId", component: WaitingList },
    { path: "/studyView/:studyId", component: StudyView },
    { path: "/createStudy", component: createStudy },
    { path: "/modifyStudy/:studyId", component: ModifyStudy },
    { path: "/termsServicePolicy",name:'termsServicePolicy', component: TermsServicePolicy },
    { path: "/privacyPolicy", name:'privacyPolicy', component: PrivacyPolicy },
    { path: "/paymentPolicy", name:'paymentPolicy', component: PaymentPolicy },
    { path: "/noticeList", name:'noticeList', component: NoticeList },
    { path: "/faqList", name:'faqList', component: FaqList },
    { path: "/communityList/:cateId", component: CommunityList },
    { path: "/communityView/:communityId", component: CommunityView },
    { path: "/communityWrite", component: CommunityWrite},
    { path: "/communityModify/:communityId", component: CommunityModify},
    { path: "/qnAList", component: QnAList},
    { path: "/myStudyPage", component: MyStudyPage },
    { path: "/aside", component: Aside },
    { path: "/studyMember/:studyId", component: StudyMember },
    { path: "/message", component: Message },
    {path:"/studies/search",component: StudyListPage,
      props: route => ({
        middleCateId: Number(route.query.middleCateId),
        smallCateIds: Number(route.query.smallCateIds),
        curMiddleKeySet: String(route.query.curMiddleKeySet),
        curMajorKeySet: String(route.query.curMajorKeySet)
      }),
    },
    { path: "/memberList", component: MemberList},
    {path:"/test-bed",component: TestBed},
    {path:"/hy-test-bed",component: HyTestBed},
    {path:"/profileMyStudyPage",component: ProfileMyStudyPage},
    {path:"/profileMyMessage",component: ProfileMyMessage},
  ],
});
// router 인스턴스 내보내기 -> main.js 에서 등록
export default router;
