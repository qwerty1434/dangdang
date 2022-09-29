<template>
  <div>
    <div class="jointime">{n}분 전</div>
    <div class="supporterjoin">
      {AA}님이 {450,000}원 펀딩으로 참여하셨습니다.
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.jointime {
  position: absolute;
  width: 860px;
  height: 24px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 20px;

  color: #000000;
}
.supporterjoin {
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
</style>
