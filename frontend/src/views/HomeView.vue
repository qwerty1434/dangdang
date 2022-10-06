<template>
  <div>
    <div class="background"></div>

    <div class="banner">
      <img
        src="@/assets/banner1.png"
        style="width: 1900px; height: auto"
        alt="" />
    </div>
    <div class="imminent">마감임박</div>

    <router-link
      :to="{ path: '/funding', query: { id: 'endedAt' } }"
      class="to_imminent"
      >더보기</router-link
    >
    <dead-line-fundings class="imminentfunding"></dead-line-fundings>
    <div class="popular">인기상품</div>
    <popular-fundings class="popularfunding"></popular-fundings>
    <router-link
      :to="{ path: '/funding', query: { id: 'popular' } }"
      class="to_popular"
      >더보기</router-link
    >
    <router-link
      :to="{ path: '/funding', query: { id: 'new' } }"
      class="to_fresh"
      >더보기</router-link
    >
    <div class="fresh">신규상품</div>
    <new-fundings class="newfunding"></new-fundings>
  </div>
</template>

<script>
import DeadLineFundings from "../components/DeadLineFundings.vue";
import NewFundings from "../components/NewFundings.vue";
import PopularFundings from "../components/PopularFundings.vue";

export default {
  components: { DeadLineFundings, PopularFundings, NewFundings },
  name: "HomeView",
};
</script>

<style scoped>
.background {
  float: left;
  width: 1900px;
  height: 3198px;
  z-index: -5;

  background: #ffffff;
}
.banner {
  position: absolute;
  left: 0px;
  top: 210px;
}
.imminent {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 760px;
  top: 850px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.popular {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 760px;
  top: 1578px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.fresh {
  position: absolute;
  width: 400px;
  height: 60px;
  left: 760px;
  top: 2306px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 60px;
  line-height: 68px;
  text-align: center;

  color: #000000;
}
.to_imminent {
  position: absolute;
  width: 100px;
  height: 14px;
  left: 1514px;
  top: 934px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;
  text-decoration-line: none;
  color: rgba(0, 0, 0, 0.5);
}
.to_popular {
  position: absolute;
  width: 100px;
  height: 14px;
  left: 1514px;
  top: 1662px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;
  text-decoration-line: none;
  color: rgba(0, 0, 0, 0.5);
}
.to_fresh {
  position: absolute;
  width: 100px;
  height: 14px;
  left: 1514px;
  top: 2390px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;
  text-decoration-line: none;
  color: rgba(0, 0, 0, 0.5);
}
.imminentfunding {
  position: absolute;
  top: 944px;
}
.popularfunding {
  position: absolute;
  top: 1672px;
}
.newfunding {
  position: absolute;
  top: 2400px;
}
</style>
