<template>
  <div>
    <div class="modalsize"></div>
    <div class="headline">펀딩참여 내역</div>
    <div class="fundinginfomation">
      {사업자이름}<br />
      {펀딩이름 ex / 유패드미니 등등 title}
    </div>
    <div>
      <div class="rewardname1">{리워드이름1}</div>
      <div class="rewardnumber1">{리워드 가격}원 / {n}개</div>
    </div>
    <div>
      <div class="rewardname2">{리워드이름2}</div>
      <div class="rewardnumber2">{리워드 가격}원 / {n}개</div>
    </div>
    <div>
      <div class="rewardname3">{리워드이름3}</div>
      <div class="rewardnumber3">{리워드 가격}원 / {n}개</div>
    </div>
    <div>
      <div class="rewardname4">{리워드이름4}</div>
      <div class="rewardnumber4">{리워드 가격}원 / {n}개</div>
    </div>
    <div class="rewardsum">총 참여 금액 : {nnnnn} 원</div>
     <button class="goback">뒤로가기</button>
  </div>
 
</template>

<script>
export default {
  name: "MyOrder",
};
</script>

<style scoped>
.modalsize {
  /* Frame 10 */

  width: 860px;
  height: 860px;

  background: #ffffff;
}
.headline {
  position: absolute;
  width: 860px;
  height: 64px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 64px;
  line-height: 73px;

  color: #000000;
}
.fundinginfomation {
  /* {사업자이름} {펀딩이름 ex) 유패드미니 등등 title} */

  position: absolute;
  width: 860px;
  height: 82px;
  left: 0px;
  top: 140px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 41px;

  color: #000000;
}
.rewardname1 {
  /* {리워드이름1} */

  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 290px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.rewardnumber1 {
  /* {리워드 가격}원 / {n}개 */

  position: absolute;
  width: 860px;
  height: 24px;
  left: 0px;
  top: 336px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 27px;

  color: #000000;
}
.rewardname2 {
  /* {리워드이름1} */

  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 390px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.rewardnumber2 {
  /* {리워드 가격}원 / {n}개 */

  position: absolute;
  width: 860px;
  height: 24px;
  left: 0px;
  top: 436px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 27px;

  color: #000000;
}
.rewardname3 {
  /* {리워드이름1} */

  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 490px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.rewardnumber3 {
  /* {리워드 가격}원 / {n}개 */

  position: absolute;
  width: 860px;
  height: 24px;
  left: 0px;
  top: 536px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 27px;

  color: #000000;
}
.rewardname4 {
  /* {리워드이름1} */

  position: absolute;
  width: 860px;
  height: 32px;
  left: 0px;
  top: 590px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.rewardnumber4 {
  /* {리워드 가격}원 / {n}개 */

  position: absolute;
  width: 860px;
  height: 24px;
  left: 0px;
  top: 636px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 27px;

  color: #000000;
}
.rewardsum {
  /* 총 참여 금액 : {nnnnn} 원 */

  position: absolute;
  width: 860px;
  height: 36px;
  left: 0px;
  top: 700px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 41px;
  text-align: center;

  color: #000000;
}
.goback {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 360px;
  top: 768px;

  background: #62b878;
  border-radius: 5px;
  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #ffffff;
}
</style>
