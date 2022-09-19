<template>
  <div>
    <div class="logo"></div>
    <div class="logotext">당당</div>
    <div class="searchbar"></div>
    <div class="searchbutton"></div>
    <div class="searchtext">검색</div>
    <div class="login">로그인</div>
    <div class="bar1">|</div>
    <div class="assign">회원가입</div>
    <div class="bar2">|</div>
    <div class="category">카테고리</div>
    <div class="plan">펀딩예정</div>
    <div class="funding">당당펀딩</div>
    <div class="charity">당당후원</div>
    <div class="notification">공지사항</div>
    <div class="imminent">마감임박</div>
    <div class=""></div>
    <div class=""></div>
  </div>
</template>

<script>
export default {
  name: "NavBar",
};
</script>

<style scoped>
.logo {
  position: absolute;
  width: 120px;
  height: 60px;
  left: 170px;
  top: 60px;

  background: rgba(52, 168, 83, 0.5);
}
.logotext {
  position: absolute;
  width: 73px;
  height: 45px;
  left: 194px;
  top: 68px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;

  color: #ffffff;
}
.searchbar {
  box-sizing: border-box;

  position: absolute;
  width: 670px;
  height: 70px;
  left: 546px;
  top: 60px;

  border: 2px solid #3c9a03;
  border-radius: 5px;
}
.searchbutton {
  position: absolute;
  width: 100px;
  height: 70px;
  left: 1238px;
  top: 60px;

  background: #62b878;
  border-radius: 5px;
}
.searchtext {
  position: absolute;
  width: 52px;
  height: 28px;
  left: 1262px;
  top: 81px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 32px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #ffffff;
}
.login {
  position: absolute;
  width: 60px;
  height: 20px;
  left: 1558px;
  top: 18px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #000000;
}
.assign {
  position: absolute;
  width: 60px;
  height: 20px;
  left: 1638px;
  top: 18px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #000000;
}
.bar1 {
  position: absolute;
  width: 5px;
  height: 18px;
  left: 1626px;
  top: 20px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.bar2 {
  position: absolute;
  width: 5px;
  height: 18px;
  left: 1706px;
  top: 20px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.category {
  position: absolute;
  width: 102px;
  height: 32px;
  left: 170px;
  top: 170px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
}
.plan {
  position: absolute;
  width: 102px;
  height: 32px;
  left: 555px;
  top: 170px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
}
.funding {
  position: absolute;
  width: 102px;
  height: 32px;
  left: 779px;
  top: 170px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
}
.charity {
  position: absolute;
  width: 102px;
  height: 32px;
  left: 1003px;
  top: 170px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
}
.notification {
  position: absolute;
  width: 102px;
  height: 32px;
  left: 1227px;
  top: 170px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  /* identical to box height */

  color: #000000;
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
</style>
