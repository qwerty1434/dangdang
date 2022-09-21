<template>
  <div>
    <div class="background"></div>

    <div class="banner"></div>
    <div class="imminent">마감임박</div>
    <div class="carousel1"></div>
    <div class="to_imminent">더보기</div>
    <div class="popular">인기상품</div>
    <div class="carousel2"></div>
    <div class="to_popular">더보기</div>
    <div class="fresh">신규상품</div>
    <div class="carousel3"></div>
    <div class="to_fresh">더보기</div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.background {
  float: left;
  width: 1920px;
  height: 3198px;
  z-index: -5;

  background: #ffffff;
}
.banner {
  position: absolute;
  width: 100%;
  height: 550px;
  left: 0px;
  top: 230px;

  background: url("@/assets/550banner.png");
}
.imminent {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 760px;
  top: 850px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.popular {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 760px;
  top: 1578px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.fresh {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 760px;
  top: 2306px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.carousel1 {
  position: absolute;
  width: 1380px;
  height: 490px;
  left: 270px;
  top: 968px;
  background: url("@/assets/캐러셀1.png");
}
.carousel2 {
  position: absolute;
  width: 1380px;
  height: 490px;
  left: 270px;
  top: 1696px;
  background: url("@/assets/캐러셀2.png");
}
.carousel3 {
  position: absolute;
  width: 1380px;
  height: 490px;
  left: 270px;
  top: 2424px;
  background: url("@/assets/캐러셀3.png");
}
.to_imminent {
  position: absolute;
  width: 100px;
  height: 14px;
  left: 1514px;
  top: 934px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: rgba(0, 0, 0, 0.5);
}
.to_popular {
  position: absolute;
  width: 100px;
  height: 14px;
  left: 1514px;
  top: 1662px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: rgba(0, 0, 0, 0.5);
}
.to_fresh {
  position: absolute;
  width: 100px;
  height: 14px;
  left: 1514px;
  top: 2390px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: rgba(0, 0, 0, 0.5);
}
</style>
