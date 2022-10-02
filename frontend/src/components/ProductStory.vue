<template>
  <div>
   
  <div class="mockup"></div>
    <!-- <img v-bind:src=test>  -->

  </div>
</template>

<script>
export default {
  data() {
    return {
      fundingDetail: {},
      fundingId:"",
      test:"@/assets/상세페이지.png",
    }
  
  },
  created() {
    this.fundingId = this.$route.query.id;
    // const detailUrl =  "https://j7a306.p.ssafy.io/api/funding/detail?fundingId="+ this.fundingId

    // axios
    //   .get(detailUrl, {
    //   })
    //   .then(({ data }) => {
    //     // console.log(data);
    //     this.fundingDetail = data;
    //   })
  }
};
</script>

<style>
.mockup {
  box-sizing: border-box;

  position: absolute;
  width: 860px;
  height: 3680px;

  background: url("@/assets/상세페이지.png");
}
</style>
