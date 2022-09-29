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

      history : {
        "useHistory": [
          {
            "address": "0x37e27e5f784cf0a7a7ffe722980bcdc8d5d188b1",
            "spendMoney": 190,
            "leftMoney": 4410,
            "time": "2022-09-29T01:42:24.000+00:00",
            "purpose": "테스트 목적"
          },
          {
            "address": "0x37e27e5f784cf0a7a7ffe722980bcdc8d5d188b1",
            "spendMoney": 210,
            "leftMoney": 4200,
            "time": "2022-09-29T01:43:00.000+00:00",
            "purpose": "테스트 목적2"
          }
        ],
        "totalRemain": 4200
      },
    }
  },

  created() {
    const UseUrl = "http://localhost:8080/api/funding/supporter?fundingId=05bf3602-242c-458d-9dba-75d0782f9402"
    const detailUrl = "http://localhost:8080/api/funding/detail?fundingId=05bf3602-242c-458d-9dba-75d0782f9402"
    axios
      .get(detailUrl, {
      })
      .then(({ data }) => {
        console.log(data);
        this.fundingDetail = data;
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
