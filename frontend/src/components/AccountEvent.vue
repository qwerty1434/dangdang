<template>
  <div>
    <div class="eventtime">{1}분 전</div>
    <!-- 입금량이 -인지 +인지 구분해서 입금 출금으로 표시해주면 좋겠습니다 -->
    <div class="eventchange">{입금}|{출금} : {2,300,000}</div>
    <div class="category">카테고리 : {사업비추진}</div>
    <!-- 출금시에는 기입된 목적을, 입금시에는 펀딩 참여로 기재 -->
    <div class="purpose">목적 : {한우꽃등심}</div>
    <div class="balance">잔고 : {nnnnnnnnnn}</div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.eventtime {
  /* 1분 전 */

  position: absolute;
  width: 860px;
  height: 18px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 20px;

  color: #000000;
}
.eventchange {
  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 24px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.category {
  /* 카테고리 : {카테고리} */

  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 68px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.purpose {
  /* 목적 : {목적} */

  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 112px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.balance {
  /* 잔고 : {nnnnnnnnnnn} */

  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 156px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
</style>
