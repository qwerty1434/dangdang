<template>
  <div>
    <div class="headline">통장내역</div>
    <div class="accountbrif">
      이 프로젝트의 총 펀딩 금액은 {nnnnnnnnnnnn}원, 잔고는 {nnnnnnnnnnn}원
      입니다.
    </div>
    <div class="piechart"></div>
    <div class="accountevent"></div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.headline {
  /* 통장내역 */
  position: absolute;
  width: 860px;
  height: 90px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 64px;
  line-height: 73px;

  color: #233142;
}
.accountbrif {
  /* 이 프로젝트의 총 펀딩 금액은 {nnnnnnnnnnnn}원, 잔고는 {nnnnnnnnnnn}원 입니다. */

  position: absolute;
  width: 860px;
  height: 82px;
  left: 400px;
  top: 646px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 41px;

  color: #000000;
}
.piechart {
  position: absolute;
  width: 400px;
  height: 400px;
  left: 630px;
  top: 760px;
  border-radius: 50%;
  background: #d9d9d9;
}
.accountevent {
  position: absolute;
  width: 860px;
  height: 188px;
  left: 400px;
  top: 1220px;
}
</style>
