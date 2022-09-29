<template>
  <div>
    <div class="eventtime">{{(useHistory.time+'').substr(0,10) +' ' + (useHistory.time+'').substr(11,8)}}</div>
    <!-- 입금량이 -인지 +인지 구분해서 입금 출금으로 표시해주면 좋겠습니다 -->
    <div class="eventchange">출금 : {{useHistory.spendMoney}}원</div>
    <!-- <div class="category">카테고리 : {사업비추진}</div> -->
    <!-- 출금시에는 기입된 목적을, 입금시에는 펀딩 참여로 기재 -->
    <div class="purpose">목적 : {{useHistory.purpose}}</div>
    <div class="balance">잔고 :{{useHistory.leftMoney}}원</div>
  </div>
</template>

<script>
export default {
   data() {
      return {

      };
    },

    methods:{
   
    },

    props : {
      useHistory: Object,
    }
};
</script>

<style scoped>
.eventtime {
  /* 1분 전 */

  width: 860px;
  height: 18px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 20px;
  margin-bottom: 10px;

  color: #000000;
}
.eventchange {
  width: 860px;
  height: 32px;
  left: 0px;
  top: 24px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;
  margin-bottom: 10px;

  color: #000000;
}
.category {
  /* 카테고리 : {카테고리} */

  width: 860px;
  height: 32px;
  left: 0px;
  top: 68px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;
  margin-bottom: 10px;

  color: #000000;
}
.purpose {
  /* 목적 : {목적} */

  width: 860px;
  height: 32px;
  left: 0px;
  top: 112px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;
  margin-bottom: 10px;
  color: #000000;
}
.balance {
  /* 잔고 : {nnnnnnnnnnn} */

    width: 860px;
  height: 32px;
  left: 0px;
  top: 156px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;

  color: #000000;
  margin-bottom: 50px;

}
</style>
