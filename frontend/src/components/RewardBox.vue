<template>
  <div>
    <div class="outline"></div>
    <div class="rewardname">{{reward.title}}</div>
    <div class="rewardprice">{{reward.price}} 펀딩</div>
    <div class="rewarddetail">
     {{reward.content}}
    </div>
  </div>
</template>

<script>
export default {
      props : {
      reward: Object,
    }
};
</script>

<style scoped>
.outline {
  box-sizing: border-box;

  position: absolute;
  width: 216px;
  height: 216px;
  left: 0px;
  top: 0px;

  border: 0.1px solid #000000;
  border-radius: 5px;
}
.rewardname {
  position: absolute;
  width: 200px;
  height: 35px;
  left: 16px;
  top: 20px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 25px;
  line-height: 32px;
  display: flex;
  overflow:hidden;
  text-overflow: ellipsis;
  color: #000000;
}
.rewardprice {
  position: absolute;
  width: 160px;
  height: 60px;
  left: 16px;
  top: 47px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 14px;
  display: flex;
  align-items: center;

  color: #000000;
}
.rewarddetail {
  position: absolute;
  width: 200px;
  height: 100px;
  left: 8px;
  top: 105px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
  overflow: scroll;
  overflow-x: hidden;
}
.rewarddetail::-webkit-scrollbar{
  width: 10px;
  background-color:transparent;
}
.rewarddetail::-webkit-scrollbar-thumb{
  background-color:#d9d9d9;
}


</style>
