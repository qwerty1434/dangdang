<template>
  <div>
     {{fundingId}} 이미지 뿌리기
    <div class="mockup"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fundingId:"",
    }
  
  },
  created() {
    this.fundingId = this.$route.query.id;
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
