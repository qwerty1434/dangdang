<template>
  <div>
    <div class="fundingname">{this.펀딩이름}</div>
    <div class="reason">지출 사유?</div>
    <div class="boxes">
      <label for="material">
        <input
          type="checkbox"
          v-model="material"
          id="material"
          class="materialinput"
        />
        재료비</label
      >
      <label for="labor">
        <input type="checkbox" v-model="labor" id="labor" class="laborinput" />
        자체 인건비</label
      >
      <label for="outsource">
        <input
          type="checkbox"
          v-model="outsource"
          id="outsource"
          class="outsourceinput"
        />
        외주 인건비</label
      >
      <label for="facility">
        <input
          type="checkbox"
          v-model="facility"
          id="facility"
          class="facilityinput"
        />
        시설비</label
      >
      <label for="margin">
        <input
          type="checkbox"
          v-model="margin"
          id="margin"
          class="margininput"
        />
        마진</label
      >
      <label for="guitar">
        <input
          type="checkbox"
          v-model="guitar"
          id="guitar"
          class="guitarinput"
        />
        기타</label
      >
    </div>
    <input
      type="text"
      class="reasoninput"
      placeholder="지출사유를 입력해 주세요"
    />
    <div class="cashouttext">지출 금액</div>
    <input type="text" class="cashoutinput" />
    <button class="submit">제출하기</button>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.boxes {
  position: absolute;
  left: 645px;
  top: 557px;
}
.fundingname {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 155px;
  top: 360px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.reason {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 155px;
  top: 540px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.cashouttext {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 155px;
  top: 747px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.reasoninput {
  box-sizing: border-box;

  position: absolute;
  width: 600px;
  height: 70px;
  left: 632px;
  top: 600px;

  border: 1px solid #3c9a03;
  border-radius: 5px;
}
.cashoutinput {
  box-sizing: border-box;

  position: absolute;
  width: 600px;
  height: 70px;
  left: 632px;
  top: 742px;

  border: 1px solid #3c9a03;
  border-radius: 5px;
}
.submit {
  position: absolute;
  left: 660px;
  top: 900px;
  width: 400px;
  height: 70px;
  background: #3c9a03;
}
</style>
