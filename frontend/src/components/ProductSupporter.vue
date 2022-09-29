<template>
  <div>
    <div class="headline">서포터</div>
    <div class="supportercount">
      현재 이 프로젝트에 {{supporters.length}}명의 참여가 이루어졌습니다.
    </div>
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

export default {
  components: {
    FundingParticipation: FundingParticipation,
  },
   data() {
    return {
       fundingDetail: {},
       supporters: [],
    }
  },
  created() {
    const detailUrl = "http://localhost:8080/api/funding/detail?fundingId=05bf3602-242c-458d-9dba-75d0782f9402"
    const supporterUrl = "http://localhost:8080/api/funding/supporter?fundingId=05bf3602-242c-458d-9dba-75d0782f9402"

    axios
      .get(detailUrl, {
      })
      .then(({ data }) => {
        console.log(data);
        this.fundingDetail = data;
      })

    axios
      .get(supporterUrl, {
      })
      .then(({ data }) => {
        console.log(data);
        this.supporters = data;
      })
  }
};
</script>

<style scoped>
.headline {
  width: 860px;
  height: 90px;
  left: 400px;
  top: 540px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 64px;
  line-height: 73px;

  color: #233142;
}

.supportercount {
  /* 현재 이 프로젝트에 {931}명의 참여가 이루어졌습니다. */
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

.supporterjoin {
  /* supporterjoin 컴포넌트 */
  width: 860px;
  height: 72px;
  left: 400px;
  top: 888px;
}
</style>
