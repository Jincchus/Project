<script setup xmlns="http://www.w3.org/1999/html">

</script>

<template>
  <div
      id="template-mo-zay-hero-carousel"
      class="carousel slide d-flex"
      data-bs-ride="carousel"
      style="height: 350px"
  >
    <ol class="carousel-indicators" style="z-index: 1">
      <li
          data-bs-target="#template-mo-zay-hero-carousel"
          style="margin-top:0px"
          data-bs-slide-to="0"
          class="active"
      ></li>
      <li
          data-bs-target="#template-mo-zay-hero-carousel"
          data-bs-slide-to="1"
          style="margin-top:0px"
      ></li>
      <li
          data-bs-target="#template-mo-zay-hero-carousel"
          data-bs-slide-to="2"
          style="margin-top:0px"
      ></li>
    </ol>
    <div class="carousel-inner container py-3">
      <div class="carousel-item active">
        <div class="w-100 d-flex justify-content-center">
          <div class="w-75">
            <div class="row p-2">
              <div class="mx-auto col-md-4 col-lg-4 order-lg-last">
                <img class="banner img-fluid mw-100" src="/img/banner_img_01.png" />
              </div>
              <div class="col-lg-7 mb-0 d-flex align-items-center banner_description">
                <div class="text-align-left align-self-center">
                  <h1 class="h1 text-success">
                    <b>EVENT</b>
                  </h1>
                  <h3 class="h2">삼삼오오 파티원들을 구하라!</h3>
                  <p>6명 이상 모이면 스터디룸 쿠폰이 쏟아진다!</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="w-100 d-flex justify-content-center">
          <div class="w-75">
            <div class="row p-2">
              <div class="mx-auto col-md-8 col-lg-4 order-lg-last">
                <img class="banner img-fluid" src="/img/banner_img_02.png" alt="" />
              </div>
              <div class="col-lg-6 mb-0 d-flex align-items-center banner_description">
                <div class="text-align-left">
                  <h1 class="h1">Come with us</h1>
                  <h5>함께 성장할 스터디를 모집해보세요. <br/>파티퀘스트에서는 다양한 스터디가 준비되어 있어요.</h5>

                  <p>혼자 하던 공부를 더 나아가 함께 스터디까지!</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="w-100 d-flex justify-content-center">
          <div class="w-75">
            <div class="row p-2">
              <div class="mx-auto col-md-8 col-lg-4 order-lg-last">
                <img class="banner img-fluid" src="/img/banner_img_03.png" alt="" />
              </div>
              <div class="col-lg-7 mb-0 d-flex align-items-center banner_description">
                <div class="text-align-left">
                  <h1 class="h1">파티원을 구하세요!</h1>
                  <p>
                    혼자서 힘들다면 파티원을 구해서 능률을 높여보세요
                    파티퀘스트에서 프로그래밍, 외국어, 독서 등 다양한 모임을
                    찾을 수 있어요
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <a
        class="carousel-control-prev text-decoration-none w-auto ps-3"
        href="#template-mo-zay-hero-carousel"
        role="button"
        data-bs-slide="prev"
    >
      <i class="fas fa-chevron-left"></i>
    </a>
    <a
        class="carousel-control-next text-decoration-none w-auto pe-3"
        href="#template-mo-zay-hero-carousel"
        role="button"
        data-bs-slide="next"
    >
      <i class="fas fa-chevron-right"></i>
    </a>
  </div>
</template>

<style scoped>
.banner_description{
  margin-left: -15%;

}
.banner{
  margin-left: 30%;
}
.h2{
  font-weight: bold;
}
</style>