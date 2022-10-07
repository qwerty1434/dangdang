<template>
  <div>
    <div class="headline">서포터</div>
    <hr color="#62b878" size=3px width="70%">

    <div class="supportercount">
      현재 이 프로젝트에 <br> {{supporters.length}}명의 참여가 이루어졌습니다.
    </div>
    <hr color="#62b878" size=3px width="70%">

    <div>
      <div class="supporterjoin">
        <FundingParticipation v-for="(item, i) in supporters" :user=supporters[i] :key="i"/>
      </div>
    </div>

    <div class=""></div>
    <div class=""></div>
    <div class=""></div>
  </div>
</template>

<script>
import axios from "axios";
import FundingParticipation from "../components/FundingParticipation.vue";
import {mapState} from 'vuex';

export default {
  components: {
    FundingParticipation: FundingParticipation,
  },
  data() {
    return {
      //  fundingDetail: {},
      //  supporters: [],
       fundingId: "",
    }
  },
  computed:{
    ...mapState(
      ["fundingDetail", "supporters"]
    )

  },
  created() {
    this.fundingId = this.$route.query.id;
    // const detailUrl = "https://j7a306.p.ssafy.io/api/funding/detail?fundingId="+ this.fundingId
    // const supporterUrl = "https://j7a306.p.ssafy.io/api/funding/supporter?fundingId="+ this.fundingId
    // var headers = { Authorization: this.$store.state.Authorization };

    // axios
    //   .get(detailUrl, {"headers":headers})
    //   .then(({ data }) => {
    //     this.fundingDetail = data;
    //   })

    // axios
    //   .get(supporterUrl, {
    //   })
    //   .then(({ data }) => {
    //     console.log(data);
    //     this.supporters = data;
    //   })
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
  text-align:center;
  margin-bottom:30px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 64px;
  line-height: 73px;

  color: #233142;
}

.supportercount {
  /* 이 프로젝트의 총 펀딩 금액은 {nnnnnnnnnnnn}원, 잔고는 {nnnnnnnnnnn}원 입니다. */

  width: 860px;
  height: 82px;
  left: 400px;
  top: 646px;
  margin-top:25px;
  margin-bottom:25px;
  text-align: center;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 30px;
  line-height: 41px;

  color: #000000;
}

.supporterjoin {
  /* supporterjoin 컴포넌트 */
  width: 860px;
  height: 72px;
  left: 400px;
  top: 888px;
}
</style>
