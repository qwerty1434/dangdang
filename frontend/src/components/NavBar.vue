<template>
  <div>
    <div>
      <router-link :to="{ name: 'home' }">
        <div class="logo"></div>
        <div class="logotext">당당</div>
      </router-link>
    </div>
    <div>
      <input
        type="text"
        placeholder="어떤 프로젝트를 찾고 계신가요?"
        class="searchbar"
      />
    </div>

    <div class="searchbutton"></div>
    <div class="searchtext">검색</div>
    <router-link
      v-if="this.$store.state.Authorization"
      :to="{ name: '' }"
      class="login"
      @click="logout()"
      >로그아웃</router-link
    >
    <router-link v-else :to="{ name: 'login' }" class="login"
      >로그인</router-link
    >
    <div class="bar1">|</div>
    <router-link
      v-if="this.$store.state.Authorization"
      :to="{ name: 'mypage' }"
      class="signup"
      >마이페이지</router-link
    >
    <router-link v-else :to="{ name: 'signup' }" class="signup"
      >회원가입</router-link
    >
    <div class="bar2">|</div>
    <div class="category">카테고리</div>
    <router-link :to="{ name: 'plan' }" class="plan">펀딩예정</router-link>

    <router-link :to="{ name: 'funding' }" class="funding"
      >당당펀딩</router-link
    >
    <router-link :to="{ name: 'charity' }" class="charity"
      >당당후원</router-link
    >
    <div class="notification">공지사항</div>

    <div class="shadow"></div>
    <div class=""></div>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  data() {
    return {};
  },
  methods: {
    logout() {
      this.$store.commit("logout");
      alert("로그아웃 되었습니다.");
    },
  },
};
</script>

<style scoped>
input:focus {
  outline: 1px solid #3c9a03;
}
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
  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;
  display: flex;
  align-items: center;

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
  width: 100px;
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
  text-decoration-line: none;
  color: #000000;
}
.signup {
  position: absolute;
  width: 100px;
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
  text-decoration-line: none;
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
  text-decoration-line: none;
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
  text-decoration-line: none;
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
  text-decoration-line: none;
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
  text-decoration-line: none;
  color: #000000;
}
.shadow {
  position: absolute;
  width: 1920px;
  height: 10px;
  left: 0px;
  top: 230px;

  background: linear-gradient(
    180deg,
    #e7e7e7 0%,
    rgba(231, 231, 231, 0) 80.73%
  );
}
</style>
