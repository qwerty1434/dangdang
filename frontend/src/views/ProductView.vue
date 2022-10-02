<template>
  <div>
    <div class="banner"></div>
    <div class="fundingtitle">{{fundingDetail.fundingContent.title}}</div>
    <router-link :to="{ path: '/product/story' , query: {id: fundingId}}">
      <div class="storybutton">스토리</div>
    </router-link>
    <router-link :to="{ path: '/product/supporter' , query: {id: fundingId}}">
      <div class="supporterbutton">서포터</div>
    </router-link>
    <router-link :to="{ path: '/product/account' , query: {id: fundingId}}">
      <div class="accountbutton">통장내역</div>
    </router-link>

    <div class="components">
      <router-view />
    </div>

    <div class="due">종료 {{fundingDetail.fundingContent.remainDays}}일 전</div>
    <div>
      <progress
        id="progress"
        v-bind:value=fundingDetail.fundingContent.achieveRate
        min="0"
        max="100"
        class="progressbar"></progress>
    </div>
    <div class="complete">{{fundingDetail.fundingContent.achieveRate}}% 달성</div>
    <div class="funded">{{fundingDetail.fundingContent.nowPrice}} 원 펀딩</div>
    <div class="supporter">{{supporters.length}} 명의 서포터</div>
    <router-link :to="{ path: '/product/purchase' , query: {id: fundingId}}">
      <button class="joinfunding">펀딩 참여</button>
    </router-link>    
    <div class="makertext">메이커 정보</div>
    <div class="maker">{{fundingDetail.maker.companyName}}</div>
    <div class="makerprofilepic"></div>
    <div class="makercompany">{{fundingDetail.maker.companyName}}</div>
    <div class="cumulativesupporter">누적서포터 {{fundingDetail.maker.supporter}}명</div>
    <div class="fundingdone">완료한 펀딩 {{fundingDetail.maker.fundingSum}}개</div>
    
    <RewardBox :class= "{reward1: i==0, reward2: i==1,reward3: i==2,reward4: i==3}" v-for="(item, i) in fundingDetail.rewards" :reward=fundingDetail.rewards[i] :key ="i" />

  </div>
</template>

<script>
import axios from "axios";
import RewardBox from "../components/RewardBox.vue";

export default {
  name:"Test",
  components: {
    RewardBox: RewardBox,
  },
  data() {
    return {
      fundingDetail: {},
      supporters: [],
      fundingId: "",
      // staticUrl: "http://localhost:8080",
      staticUrl: "https://j7a306.p.ssafy.io",

    }
  },
  created() {
    this.fundingId = this.$route.query.id;
    const detailUrl = this.staticUrl + "/api/funding/detail?fundingId=" + this.fundingId
    const supporterUrl = this.staticUrl + "/api/funding/supporter?fundingId=" + this.fundingId
   
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
.components {
  position: absolute;
  left: 400px;
  top: 540px;
}
.fundingtitle {
  position: absolute;
  width: 1920px;
  height: 82px;
  left: 0px;
  top: 288px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 72px;
  line-height: 82px;
  /* identical to box height */

  text-align: center;

  color: #ffffff;
}
.banner {
  position: absolute;
  width: 1920px;
  height: 210px;
  left: 0px;
  top: 224px;

  background: linear-gradient(0deg, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
    url("@/assets/thumbnailplaceholder.jpg");
  backdrop-filter: blur(2px);
}
.storybutton {
  position: absolute;
  width: 80px;
  height: 24px;
  left: 560px;
  top: 458px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 27px;

  color: #000000;
}
.supporterbutton {
  position: absolute;
  width: 80px;
  height: 24px;
  left: 916px;
  top: 458px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 27px;

  color: #000000;
}
.accountbutton {
  position: absolute;
  width: 100px;
  height: 24px;
  left: 1272px;
  top: 458px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 27px;

  color: #000000;
}
.due {
  position: absolute;
  width: 260px;
  height: 45px;
  left: 1304px;
  top: 540px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;

  color: #000000;
}
.progressbar {
  position: absolute;
  width: 260px;
  height: 20px;
  left: 1304px;
  top: 596px;

  background: #67be7e;
}
.complete {
  position: absolute;
  width: 260px;
  height: 32px;
  left: 1304px;
  top: 622px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
}
.funded {
  position: absolute;
  width: 260px;
  height: 32px;
  left: 1304px;
  top: 678px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
}
.supporter {
  position: absolute;
  width: 260px;
  height: 32px;
  left: 1304px;
  top: 734px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
}
.joinfunding {
  position: absolute;
  width: 260px;
  height: 60px;
  left: 1304px;
  top: 790px;

  background: #62b878;
  border-radius: 5px;
  border: 0;
  outline: 0;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;
  text-align: center;

  color: #ffffff;
}
.makertext {
  /* 메이커 정보 */

  position: absolute;
  width: 260px;
  height: 18px;
  left: 1304px;
  top: 912px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.maker {
  /* {메이커이름} */

  position: absolute;
  width: 260px;
  height: 36px;
  left: 1304px;
  top: 942px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
}
.makerprofilepic {
  position: absolute;
  width: 80px;
  height: 80px;
  left: 1372px;
  top: 988px;
  border-radius: 50%;

  background: #d9d9d9;
}
.makercompany {
  /* {사업자회사이름} */

  position: absolute;
  width: 260px;
  height: 18px;
  left: 1304px;
  top: 1080px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.cumulativesupporter {
  /* 누적서포터 {nnnnn} 명 */

  position: absolute;
  width: 260px;
  height: 18px;
  left: 1304px;
  top: 1110px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.fundingdone {
  /* 완료한 펀딩 {nnnnn} 개 */

  position: absolute;
  width: 260px;
  height: 18px;
  left: 1304px;
  top: 1140px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.reward1 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: 1304px;
  top: 1220px;
}
.reward2 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: 1304px;
  top: 1454px;
}
.reward3 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: 1304px;
  top: 1688px;
}
.reward4 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: 1304px;
  top: 1922px;
}
</style>
