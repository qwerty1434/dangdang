<template>
  <div>
    <nav-bar></nav-bar>
  </div>
  <router-view />
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "app",
  components: {
    NavBar,
  },
};
</script>

<style>
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
</style>
