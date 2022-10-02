<template>
  <div>
    <div class="headline">통장내역</div>
    <div class="accountbrif">
      이 프로젝트의 총 펀딩 금액은 {{fundingDetail.nowPrice}}원, 잔고는 {{history.totalRemain}}원
      입니다.
    </div>
    <!-- <div class="piechart"></div> -->
    <div class="accountevent">
      <div class="supporterjoin">
        <AccountEvent v-for="(item, i) in history.useHistory" :useHistory=history.useHistory[i] :key="i"/>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import AccountEvent from "../components/AccountEvent.vue";

export default {
  components:{
    AccountEvent: AccountEvent,
  },

  data(){
    return {
      fundingDetail: {},
      fundingId: "",
      history : {},
    }
  },

  created() {
    this.fundingId = this.$route.query.id;
    const UsageUrl =   "https://j7a306.p.ssafy.io/api/funding/usage?fundingId="+ this.fundingId
    const detailUrl =  "https://j7a306.p.ssafy.io/api/funding/detail?fundingId="+ this.fundingId

    var headers = {"Authorization": "Bearer " + this.$store.state.Authorization}
    console.log(headers);
    console.log(this.$store.state.Authorization);
    axios
      .get(detailUrl, {
      })
      .then(({ data }) => {
        // console.log(data);
        this.fundingDetail = data;
      })
   
   axios
    .get(UsageUrl, {"headers":headers})
    .then(({ data }) => {
      console.log("get history" );
      console.log(data);
      this.history = data;
    })
  }
};
</script>

<style scoped>
.headline {
  /* 통장내역 */
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
  width: 400px;
  height: 400px;
  left: 630px;
  top: 760px;
  border-radius: 50%;
  background: #d9d9d9;
}
.accountevent {
  width: 860px;
  height: 188px;
  left: 400px;
  top: 1220px;
}
</style>
