<template>
  <div>
   
  <!-- <div class="mockup"></div> -->
  <img class="mockup" v-bind:src=fundingDetail.bodyImages[0].img>

  </div>
</template>

<script>
import {mapState} from 'vuex';

export default {  
  data() {
    return {
      fundingId:"",
    }
  
  },
  computed:{
    ...mapState(
      ["fundingDetail", "supporters"]
    )

  },
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
