<template>
  <div>
    <nav-bar></nav-bar>
    <my-order class="checkOrder"></my-order>
    <!-- <user-join-on-fundings class="checkEnd"></user-join-on-fundings> -->
    <user-join-end-fundings class="checkEnd"></user-join-end-fundings>
  </div>
  <router-view />
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import FundingCard from "@/components/FundingCard.vue";
import MyOrder from './components/MyOrder.vue';
import UserJoinOnFundings from './components/UserJoinOnFundings.vue';
import UserJoinEndFundings from './components/UserJoinEndFundings.vue';

export default {
  name: "app",
  components: {
    NavBar,
    FundingCard,
    MyOrder,
    UserJoinOnFundings,
    UserJoinEndFundings,
  },
};
</script>

<style>
.v-application {
  font-family: "NanumSquare", sans-serif !important;
}

@font-face {
  font-family: "NanumSquare";
  font-weight: 400;
  src: url("@/assets/fonts/NanumSquareR.ttf");
  src: url("@/assets/fonts/NanumSquareR.ttf") format("truetype");
}
@font-face {
  font-family: "NanumSquare";
  font-weight: 700;
  src: url("@/assets/fonts/NanumSquareB.ttf");
  src: url("@/assets/fonts/NanumSquareB.ttf") format("truetype");
}

@font-face {
  font-family: "NanumSquare";
  font-weight: 300;
  src: url("@/assets/fonts/NanumSquareL.ttf");
  src: url("@/assets/fonts/NanumSquareL.ttf") format("truetype");
}
.farfar {
  position: absolute;
  left: 400px;
  top: 4000px;
}
.checkEnd{
  position: absolute;
  top: 4000px;
}
.checkOrder{
  position: absolute;
  top: 3000px;
}
</style>
