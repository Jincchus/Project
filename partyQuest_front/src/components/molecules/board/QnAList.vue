<script setup>
import Banner from "@/components/molecules/board/Banner.vue";
import CommunityAside from "@/components/molecules/board/CommunityAside.vue";
</script>
<template>
  <main id="main">
    <Banner title="다른 파티의 파티원들과 소통 할 수 있어요!" subTitle="자유게시판, 묻고 답하기에는 여러 파티원들이 북적 북적 !" />
    <section class="community-body">
      <!--왼쪽 어사이드 시작-->
      <CommunityAside></CommunityAside>
      <!--왼쪽 어사이드 끝-->
      <!-- 상단 카테고리 시작 -->
      <div class ="community-body__content">
        <section>
          <div class="status">
            <div class="e-status active" data-status="">

              <button class="ac-button is-md is-text tab-button ">전체</button>
            </div>
            <div class="e-status " data-status="unresolved">

              <button class="ac-button is-md is-text tab-button ">미해결</button>
            </div>
            <div class="e-status " data-status="resolved">

              <button class="ac-button is-md is-text tab-button ">해결됨</button>
            </div>
            <button class="btn btn-danger" style="float : right" ><span class="infd-icon"><svg width="16" height="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path fill="#ffffff" fill-rule="evenodd" d="M11.508 1.451c.456-.455 1.195-.455 1.65 0l1.724 1.724c.456.456.456 1.194 0 1.65L13.02 6.687l-.008.008-7.219 6.89c-.132.126-.291.22-.466.273l-3.681 1.12c-.177.054-.369.006-.5-.124-.13-.131-.178-.323-.124-.5l1.115-3.666c.059-.192.166-.365.311-.504L9.651 3.31l1.857-1.858zM9.992 4.366l-6.854 6.542c-.02.02-.036.044-.044.072l-.843 2.769 2.785-.848c.025-.007.048-.02.067-.039l6.848-6.537-1.96-1.96zm2.675 1.26l1.508-1.508c.065-.065.065-.17 0-.236l-1.724-1.724c-.065-.065-.17-.065-.236 0l-1.508 1.509 1.96 1.96z" clip-rule="evenodd"></path></svg></span> <span class="posts-container-header__button-text">글쓰기</span></button>

          </div>
        </section>
        <!-- 상단 카테고리 끝 -->
        <!-- Content List 시작-->
        <hr class="sectionLine" />
        <section class="community-list-container">
          <div class="card qnaList">
            <div class="card-header">
              <button class="btn btn-outline-danger qnaBtn">미해결</button>
              <label>제 2회 기출 작업형 2번 문제 관련 질문입니다.</label>
            </div>
            <div class="card-body">
              <blockquote class="blockquote mb-0 ">
                <p>안녕하세요 선생님. 문제 풀이 궁금증이 있어 글 남깁니다. 작업형 2번 문제중 X_train ...</p>
                <footer class="blockquote-footer">Bacchus · 25분 전</footer>
              </blockquote>
            </div>
          </div>
          <div class="card qnaList">
            <div class="card-header">
              <button class="btn btn-danger qnaBtn">해결</button>
              <label>제 2회 기출 작업형 2번 문제 관련 질문입니다.</label>
            </div>
            <div class="card-body qnaList">
              <blockquote class="blockquote mb-0">
                <p>안녕하세요 선생님. 문제 풀이 궁금증이 있어 글 남깁니다. 작업형 2번 문제중 X_train ...</p>
                <footer class="blockquote-footer">Bacchus · 25분 전</footer>
              </blockquote>
            </div>
          </div>
        </section>
        <!-- Content List 끝 -->
      </div>

      <!-- 오른쪽 어사이드 시작-->
      <sec>
        <div class="qnaRightAside">
          <div class="card QRA1" style="width: 18rem;">
            <div class="card-body">
              <h5 class="card-title">답변 많은 글</h5>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <div class="card-body" >
              <a>더보기</a>
            </div>
          </div>

          <div class="card " style="width: 18rem;">
            <div class="card-body">
              <h5 class="card-title">많이 본 글</h5>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>          <ul class="list-group list-group-flush">
            <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
              <div class="card-body">
                <a href="#" class="card-link">작성자 닉네임</a>
              </div>
            </li>
          </ul>
            <div class="card-body">
              <a href="#" class="card-link"></a>
            </div>
          </div>
        </div>
      </sec>

      <!-- 오른쪽 어사이드 끝-->
    </section>
  </main>
</template>
<style scoped>
* {
  font-family: "Pretendard Variable", Pretendard, -apple-system,
  BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI",
  "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji",
  "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
#main {
  min-height: 800px;
}
*, :after, :before {
  box-sizing: inherit;
}
main {
  display: block;
}
/*왼쪽 어사이드*/
.community-left-side {
  margin-top: 44px;
  margin-right: 40px;
  width: 168px;
}
.community-body__navigation {
  flex: 1 0 172px;
  max-width: 172px;
}
ol, ul {
  list-style: none;
}
.community-aside__group:first-of-type {
  margin-top: 0;
}
.community-aside__group-name {
  margin-bottom: 1em;
  height: 20px;
  font-size: 12px;
  font-weight: 500;
  color: #adb5bd;
}
.community-aside__menu {
  padding: 0.5em 0.75em;
}
.community-aside__menu a {
  font-size: 17px;
  color: #343a40;
  font-weight: 400;
  line-height: 1.5;
}
.community-aside__menu--selected a {
  font-weight: 700;
  color: rgba(255, 21, 21, 0.8);
}
/*content*/
.community-body {
  display: flex;
  flex-direction: row;
  margin: 0 auto;
  padding: 0 32px;
  max-width: 1200px;
}
.community-body__content {
  padding: 32px 0 64px;
  max-width: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
}
.qnaBtn{
  border-radius : 2em;
}
.qnaList{
//border-radius : 0%;
}

/*status 바*/
.status > div {
  display : inline-block;
}
.ac-button.is-text {
  border: unset;
  background-color: unset;
  color: #495057;
  font-weight: 500;
}
.ac-button.is-md {
  padding: 0 12px;
  height: 40px;
  line-height: 1.43;
  font-size: 14px;
  letter-spacing: -.3px;
}
.ac-button {
  position: relative;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border-radius: 4px;
  line-height: 1.47;
  font-weight: 500;
  cursor: pointer;
  -webkit-appearance: none;
  padding: 0 12px;
  height: 40px;
  line-height: 1.43;
  font-size: 14px;
  letter-spacing: -.3px;
  border: 1px solid;
  color: #fff;
}

/* 오른쪽 어사이드 */
sec{
  margin-left : 4%;
  margin-top : 5%
}

.QRA1 {
  margin-bottom : 5%;
}
</style>