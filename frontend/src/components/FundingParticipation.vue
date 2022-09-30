<template>
  <div>
    <div class="time">{{(user.time+'').substr(0,10) +' ' + (user.time+'').substr(11,8)}}</div>
    <div class="detail">{{getUserNickname()}}님이 {{user.won}}원 펀딩으로 참여하셨습니다.</div>
  </div>
</template>

<script>
  export default {
    name: "FundingParticipation",
    
    data() {
      return {

      };
    },

    methods:{
        getUserNickname: function () {
        if (this.user.anonymous == true) {
          return "익명";
        }
        return this.user.nickname;
      },
    },

    props : {
      user: Object,
    }
  }

</script>

<style scoped>
.time {
  width: 860px;
  height: 24px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 20px;

  color: #000000;
}
.detail {
  /* AA님이 450,000원 펀딩으로 참여하셨습니다. */
  width: 860px;
  height: 48px;
  left: 0px;
  top: 24px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 36px;
  margin-bottom: 20px;
  color: #000000;
}
</style>
