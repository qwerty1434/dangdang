<template>
  <div>
    <div class="headline">서포터</div>
    <div class="supportercount">
      현재 이 프로젝트에 {931}명의 참여가 이루어졌습니다.
    </div>
    <div>
      <div class="supporterjoin"></div>
    </div>

    <div class=""></div>
    <div class=""></div>
    <div class=""></div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.headline {
  position: absolute;
  width: 860px;
  height: 90px;
  left: 400px;
  top: 540px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 64px;
  line-height: 73px;

  color: #233142;
}

.supportercount {
  /* 현재 이 프로젝트에 {931}명의 참여가 이루어졌습니다. */

  position: absolute;
  width: 860px;
  height: 82px;
  left: 400px;
  top: 646px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 41px;

  color: #000000;
}

.supporterjoin {
  /* supporterjoin 컴포넌트 */
  position: absolute;
  width: 860px;
  height: 72px;
  left: 400px;
  top: 888px;
}
</style>
