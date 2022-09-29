<template>
  <div>
    <div class="headline"></div>
    <div class="이거는 컴퍼넌트 들어가는 자리">
      <div class="productnumbertext">수량:</div>
      <div class="productnumber"></div>
    </div>
    <div class="orderinfomationtext">주문 정보</div>
    <div class="addresstext">주소</div>
    <div class="addressfindtext">주소 찾기</div>
    <div class="addressdetailtext">상세 주소</div>
    <div class="contacttext">연락처</div>
    <div class="emailtext">이메일</div>
    <div class="ordertext">주문하기</div>
    <div class="addressbox"></div>
    <div class="addressfindbox"></div>
    <div class="addressdetailbox"></div>
    <div class="contactbox"></div>
    <div class="emailbox"></div>
    <div class="orderbox"></div>
    <div>
      <input type="text" class="addressinput" />
    </div>
    <div class="addressdetailinput"></div>
    <div class="contactinput"></div>
    <div class="emailinput"></div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.headline {
  /* 펀딩참여 */

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
.productnumber {
  /* {nn} */

  position: absolute;
  width: 120px;
  height: 20px;
  left: 56px;
  top: 232px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #000000;
}
.productnumbertext {
  /* 수량 : */

  position: absolute;
  width: 60px;
  height: 20px;
  left: 0px;
  top: 232px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.orderinfomationtext {
  /* 주문 정보 */

  position: absolute;
  width: 180px;
  height: 20px;
  left: 378px;
  top: 490px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: center;

  color: #000000;
}
.addresstext {
  /* 주소 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 592px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.addressdetailtext {
  /* 상세 주소 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 692px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.contacttext {
  /* 연락처 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 792px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.emailtext {
  /* 이메일 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 892px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.addressfindtext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 736px;
  top: 592px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #62b878;
}
.addressbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 570px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.addressfindbox {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 62px;
  left: 726px;
  top: 570px;

  background: #ffffff;
  border: 0.5px solid #62b878;
  border-radius: 5px;
}
.addressdetailbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 670px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.contactbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 770px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.emailbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 870px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.orderbox {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 398px;
  top: 970px;

  background: #62b878;
  border-radius: 5px;
}
.addressinput {
  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 572px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;
}
.addressdetailinput {
  /* 상세 주소를 입력해주세요. */

  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 672px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;
}
.contactinput {
  /* 연락이 가능한 휴대전화번호를 남겨주세요 */

  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 772px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;
}
.emailinput {
  /* 이메일을 통해 주문처리과정을 보내드립니다. */

  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 872px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;
}
</style>
