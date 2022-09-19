<template>
  <div>
    <nav-bar></nav-bar>
    <div class="banner"></div>
  </div>
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

<style scoped>
.banner {
  position: absolute;
  width: 100%;
  height: 550px;
  left: 0px;
  top: 230px;

  background: url("@/assets/550banner.png");
}
</style>
