<template>
  <div>
    <nav-bar></nav-bar>
    <funding-card class="farfar"></funding-card>
  </div>
  <router-view />
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import FundingCard from "@/components/FundingCard.vue";
export default {
  name: "app",
  components: {
    NavBar,
    FundingCard,
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
</style>
