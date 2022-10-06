<template>
  <div>
    <div class="fundingname">{{ fundingDetail.fundingContent.title }}</div>

    <div class="reason">지출 사유</div>

    <input
      type="text"
      class="reasoninput"
      v-model="reason"
      placeholder="  지출사유를 입력해 주세요" />
    <div class="cashouttext">지출 금액</div>
    <input type="number" min ="0" class="cashoutinput" v-model="cashout" />
    <button @click="useDeposit()" class="submit">제출하기</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      fundingId: "",
      fundingDetail: "",
      reason: "",
      cashout: "",
      staticUrl: "https://j7a306.p.ssafy.io",
    };
  },
  created() {
    this.fundingId = this.$route.query.id;

    const detailUrl =
      this.staticUrl + "/api/funding/detail?fundingId=" + this.fundingId;

    axios.get(detailUrl, {}).then(({ data }) => {
      this.fundingDetail = data;
    });
  },
  methods: {
    useDeposit: function () {
      console.log("deposit");
      const url = this.staticUrl + "/api/maker/coin-app";
      var headers = { Authorization: this.$store.state.Authorization };

      axios
        .post(
          url,
          {
            fundingId: this.fundingId,
            amountUsed: this.cashout,
            purpose: this.reason,
          },
          { headers: headers }
        )
        .then(({ data }) => {
          alert("펀딩 금액 사용이 완료되었습니다.");
          this.$router.go(-1);
        });
    },
  },
};
</script>

<style scoped>
.boxes {
  position: absolute;
  left: 645px;
  top: 557px;
}
.fundingname {
  position: absolute;
  width: 1400px;
  height: 60px;
  left: 70px;
  top: 360px;
  margin:auto;
  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.reason {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 300px;
  top: 540px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 30px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.cashouttext {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 300px;
  top: 670px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 30px;
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
  top: 535px;
  font-family: "NanumSquare";
  font-size: 25px;
  line-height: 32px;

  border: 1px solid #62b878;
  border-radius: 5px;
}
.cashoutinput {
  box-sizing: border-box;

  position: absolute;
  width: 600px;
  height: 70px;
  left: 632px;
  top: 670px;
  font-family: "NanumSquare";
  font-size: 32px;
  line-height: 32px;

  border: 1px solid #62b878;
  border-radius: 5px;
}
.submit {
  position: absolute;
  left: 660px;
  top: 900px;
  width: 400px;
  height: 70px;
  border: 0;
  outline: 0;
  background: #62b878;
  border-radius: 5px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 40px;
  text-align: center;

  color: #ffffff;
}
</style>
