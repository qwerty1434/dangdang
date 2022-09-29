<template>
  <div>
    <div class="outline"></div>
    <div class="rewardname">유패드 미니</div>
    <div class="rewardprice">400,000 펀딩</div>
    <div class="rewarddetail">
      유패드 미니는 어쩌고 저쩌고 유구한 전통을 가졌니 마니. 3대째 내려오는
      비법웨이퍼로 눅진하게 찍어낸 반도체가 포인트.
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.outline {
  box-sizing: border-box;

  position: absolute;
  width: 216px;
  height: 216px;
  left: 0px;
  top: 0px;

  border: 0.1px solid #000000;
  border-radius: 5px;
}
.rewardname {
  position: absolute;
  width: 200px;
  height: 20px;
  left: 16px;
  top: 20px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 32px;
  display: flex;
  align-items: center;

  color: #000000;
}
.rewardprice {
  position: absolute;
  width: 160px;
  height: 20px;
  left: 16px;
  top: 47px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 14px;
  display: flex;
  align-items: center;

  color: #000000;
}
.rewarddetail {
  position: absolute;
  width: 200px;
  height: 120px;
  left: 8px;
  top: 78px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #000000;
}
</style>
