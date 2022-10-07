<template>
  <div>
    <div>
      <router-link :to="{ name: 'home' }">
        <div class="logo">
          <img
            src="@/assets/logo.png"
            style="height: 60px; width: auto"
            alt="" />
        </div>
        <div class="logotext"></div>
      </router-link>
    </div>
    <div>
      <input
        type="text"
        placeholder="   어떤 프로젝트를 찾고 계신가요?"
        class="searchbar"
        v-model="keyword"
        v-on:keyup.enter="searchFunding()" />
    </div>

    <div class="searchbutton"></div>
    <div @click="searchFunding()" class="searchtext">검색</div>
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
      :to="{ path: '/mypage/onfunding' }"
      class="signup"
      >마이페이지</router-link
    >
    <router-link v-else :to="{ name: 'signup' }" class="signup"
      >회원가입</router-link
    >
    <div class="bar2">|</div>
    <div class="stop-dragging" @click="toggleShow">
      <div class="category">카테고리</div>
      <div v-if="show">
        <category-list class="categorylist"></category-list>
      </div>
    </div>

    <router-link :to="{ path: '/funding', query: { id: 'plan' } }" class="plan"
      >펀딩예정</router-link
    >

    <router-link
      :to="{ path: '/funding', query: { id: 'new' } }"
      class="funding"
      >당당펀딩</router-link
    >
    <!-- <router-link
      :to="{ path: '/funding', query: { id: 'charity' } }"
      class="charity"
      >당당후원</router-link
    > -->
    <div class="charity" @click="alert()"> 당당후원</div>
    <div class="notification" @click="alert()">공지사항</div>
    <div class="shadow"></div>
  </div>
</template>

<script>
import CategoryList from "@/components/CategoryList.vue";
import axios from "axios";

export default {
  name: "NavBar",
  data() {
    return {
      keyword: "",
      searchFundings: [],
      show: false,
    };
  },
  methods: {
    alert(){
      alert("서비스 점검중입니다")
    },
    toggleShow() {
      this.show = !this.show;
    },
    logout() {
      this.$store.commit("logout");
      alert("로그아웃 되었습니다.");
      this.$router.push({ path: "/" });
    },
    searchFunding() {
      console.log(this.keyword);
      // const url = "http://localhost:8080/api/funding/searchtitle"
      const url = "https://j7a306.p.ssafy.io/api/funding/searchtitle";
      axios
        .get(url, {
          params: {
            keyword: this.keyword,
          },
        })
        .then(({ data }) => {
          this.searchFundings = data.fundingList;
          if (this.searchFundings.length == 0) {
            alert("해당 펀딩이 존재하지 않습니다 :(");
            this.keyword = "";
          } else {
            this.$store.commit("SearchFunding", data.fundingList);
            this.$router.push({
              path: "funding",
              query: { search: this.keyword },
            });
            this.keyword = "";
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
  components: {
    CategoryList,
  },
};
</script>

<style scoped>
input:focus {
  outline: 1px solid #62b878;
}
.logo {
  position: absolute;
  left: 170px;
  top: 60px;
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
  font-size: 25px;
  line-height: 45px;
  display: flex;
  align-items: center;

  border: 2px solid #62b878;
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
  cursor: pointer;
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
.categorylist {
  position: absolute;
  z-index: 10;
  position: absolute;
  width: 240px;
  height: 320px;
  left: 100px;
  top: 202px;
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
  width: 1900px;
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
