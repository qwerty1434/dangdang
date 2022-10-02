<template>
  <div>
   
  <!-- <div class="mockup"></div> -->
  <img class="mockup" v-bind:src=test>

  </div>
</template>

<script>
import axios from "axios";

export default {  
  data() {
    return {
      fundingDetail: {},
      fundingId:"",
      test:"https://www.heart.org/-/media/Images/Health-Topics/Congenital-Heart-Defects/50_1683_44bc_ASD-Repairs.jpg?h=361&w=600&hash=AE0CA984C624B94BD1A0DA8571D120D5",
    }
  
  },
  created() {
    this.fundingId = this.$route.query.id;
    const detailUrl =  "https://j7a306.p.ssafy.io/api/funding/detail?fundingId="+ this.fundingId

    axios
      .get(detailUrl, {
      })
      .then(({ data }) => {
        // console.log(data);
        this.fundingDetail = data;
        this.test = data.bodyImages[0].img;
      })
  }
};
</script>

<style>
.mockup {
  box-sizing: border-box;

  position: absolute;
  width: 860px;
  /* height: 3680px; */

  /* background: url("@/assets/상세페이지.png"); */
}
</style>
