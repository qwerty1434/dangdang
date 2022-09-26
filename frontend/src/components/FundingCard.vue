<template>
  <div>
    <div class="thumbnail">
      <img
        src="@/assets/thumbnailplaceholder.jpg"
        style="width: 300px; height: 400px; box-sizing: border-box"
        alt="" />
    </div>
    <div class="title">제목호출하는API</div>
    <div class="category">카테고리</div>
    <div class="maker">사업자명</div>
    <div class="bar"></div>
    <progress
      id="progress"
      value="50"
      min="0"
      max="100"
      class="progressbar"></progress>
    <div class="percentage">300%</div>
    <div class="total">24141원(코인)</div>
    <div class="remain">20일 남음</div>
  </div>
</template>

<script>
export default {
  name: "FundingCard",
};
</script>

<style scoped>
.thumbnail {
  position: absolute;
  width: 300px;
  height: 400px;
  left: 0px;
  top: 0px;
  box-sizing: border-box;
}
.title {
  position: absolute;
  width: 300px;
  height: 20px;
  left: 0px;
  top: 406px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.category {
  position: absolute;
  width: 160px;
  height: 14px;
  left: 0px;
  top: 436px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;

  color: #000000;
}
.maker {
  position: absolute;
  width: 160px;
  height: 14px;
  left: 140px;
  top: 436px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;
  text-align: right;

  color: #000000;
}
.progressbar {
  position: absolute;
  width: 300px;
  height: 20px;
  left: 0px;
  top: 458px;

  background: #67be7e;
}
.bar {
  position: absolute;
  width: 300px;
  height: 20px;
  left: 0px;
  top: 458px;

  background: #f7f7f7;
}
.percentage {
  position: absolute;
  width: 60px;
  height: 14px;
  left: 0px;
  top: 484px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;

  color: #000000;
}
.total {
  position: absolute;
  width: 140px;
  height: 14px;
  left: 60px;
  top: 484px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;

  color: #000000;
}
.remain {
  position: absolute;
  width: 100px;
  height: 14px;
  left: 200px;
  top: 484px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;
  text-align: right;

  color: #000000;
}
</style>
