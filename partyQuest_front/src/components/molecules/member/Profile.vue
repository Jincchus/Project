<script setup></script>
<style lang="scss" scoped></style>

<template>
  <!-- Start Profile -->
  <section>
    <div class="container py-5">
      <div class="row py-5">
        <div class="col-md-9 m-auto" id="profileMain">
          <div id="pictureDiv">
            <img src="http://placehold.it/150X150" alt="#" id="myPicture" />
          </div>
          <div id="profileMainSection">
            <label class="form-label" id="nickName">벨라리</label>
            <a href="#">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-pencil-fill"
                viewBox="0 0 16 16"
              >
                <path
                  d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"
                />
              </svg>
            </a>
          </div>
          <div id="introduce" class="form-text">
            저는 백엔드 엔지니어를 꿈꾸고 있는 열정 넘치는 주니어 개발자입니다
          </div>
        </div>

        <form class="col-md-9 m-auto" method="post" role="form">
          <section id="profileSec">
            <label class="form-label">선호 지역 *</label>
            <span id="Location" class="form-text">
              ✔ 파티의 거점지가 될 장소를 알려주세요! 선호에 맞춰 추천해
              드릴게요
            </span>

            <div class="Location">
              <button
                type="button"
                id="groupLocation2"
                class="btn dropdown-toggle btn-outline-danger"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                서울특별시
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">서울특별시</a></li>
                <li><a class="dropdown-item" href="#">부산광역시</a></li>
              </ul>
            </div>
          </section>

          <hr class="sectionLine" />

          <section id="introduce">
            <div id="slefIntroduce" class="form-text">
              <label class="form-label">자기소개</label>
              ✔ 자기 소개에 보여질 부분이에요! 남들에게 보여주고 싶은 나를
              소개해보세요
            </div>
            <div id="txtBoxDiv">
              <input
                type="txt"
                id="introBox"
                class="form-control w-75"
                aria-describedby="selfIntr"
              />
              <button type="button" id="insertBtn" class="btn btn-danger">
                추가
              </button>
            </div>
          </section>
          <hr class="sectionLine" id="introNextHr" />

          <section id="mbtiSec">
            <label class="form-label">MBTI *</label>
            <span id="mbtiSpan" class="form-text">
              ✔ MBTI를 설정하면 특별한 칭호가 부여됩니다!
            </span>
            <div class="mbtiDiv">
              <button
                type="button"
                id="mbtiBtn"
                class="btn dropdown-toggle btn-outline-danger"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                MBTI 선택
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">ISTJ</a></li>
                <li><a class="dropdown-item" href="#">ISFJ</a></li>
                <li><a class="dropdown-item" href="#">INFJ</a></li>
                <li><a class="dropdown-item" href="#">ESTP</a></li>
                <li><a class="dropdown-item" href="#">ESFP</a></li>
                <li><a class="dropdown-item" href="#">ENFP</a></li>
              </ul>
            </div>
          </section>

          <hr class="sectionLine" />

          <!-- 카테고리 버튼 태그 사용 => select 버튼은 범위가 큼 동적처리해주면될듯-->
          <div class="form-text">
            <label class="form-label">나의 관심 기술</label>
            ✔ 관심있는 키워드를 선택해 주세요
            <section>
              <input
                type="txt"
                id="introBox"
                class="form-control"
                aria-describedby="selfIntr"
              />
              <div class="btn-group">
                <div id="majorCate">
                  <button
                    type="button"
                    id="cate1"
                    class="btn dropdown-toggle btn-outline-danger"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    MajorCate
                  </button>
                  <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">CATE1</a></li>
                    <li><a class="dropdown-item" href="#">IT 개발</a></li>
                    <li><a class="dropdown-item" href="#">등등등</a></li>
                  </ul>
                </div>

                <div id="middleCate">
                  <button
                    type="button"
                    id="cate2"
                    class="btn dropdown-toggle btn-outline-danger"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    MiddleCate
                  </button>
                  <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">CATE2</a></li>
                    <li><a class="dropdown-item" href="#">백엔드</a></li>
                    <li><a class="dropdown-item" href="#">프론트엔드</a></li>
                  </ul>
                </div>

                <div id="SmallCate">
                  <button
                    type="button"
                    id="cate3"
                    class="btn dropdown-toggle btn-outline-danger"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    SmallCate
                  </button>
                  <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">CATE3</a></li>
                    <li><a class="dropdown-item" href="#">Spring</a></li>
                    <li><a class="dropdown-item" href="#">Vue.js</a></li>
                  </ul>
                </div>
              </div>
            </section>

            <hr class="sectionLine" />

            <section id="portfolio">
              <div id="portfolioDiv" class="form-text">
                <label class="form-label">포트폴리오 / URL</label>
                ✔ 포트폴리오나 Github 등 자신을 알릴 수 있는 모든 것을
                입력해보세요!
              </div>
              <div id="pfTxtBoxDiv">
                <input
                  type="txt"
                  id="portfolioBox"
                  class="form-control w-75"
                  aria-describedby="selfIntr"
                />
                <button type="button" id="pfInsertBtn" class="btn btn-danger">
                  입력
                </button>
                <button type="button" id="pfIddBtn" class="btn btn-danger">
                  추가
                </button>
              </div>
            </section>
            <hr class="sectionLine" id="introNextHr" />
          </div>

          <div class="row">
            <div class="d-grid gap-2">
              <button class="btn btn-danger" type="button">프로필 등록</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </section>
  <!-- End profile Description -->
</template>
