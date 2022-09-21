<template>
  <div>
    <div class="logintext">로그인</div>
    <div class="loginbox"></div>
    <input type="text" placeholder="아이디를 입력해주세요" class="logininput" />
    <div class="passwordbox"></div>
    <input
      type="password"
      placeholder="비밀번호를 입력해주세요"
      class="pwinput" />
    <div>
      <div class="loginbutton"></div>
      <div class="loginboxtext">로그인</div>
    </div>
    <router-link :to="{ name: 'signup' }">
      <div class="signupbutton"></div>

      <div class="signuptext">회원가입</div>
    </router-link>

    <div class="findid">아이디 찾기</div>
    <div class="findpw">비밀번호 찾기</div>
    <div class="bar">|</div>
    <h1>this is a login page</h1>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
input[type="password"] {
  font-family: "맑은 고딕", "malgun gothic", "돋움", Dotum, sans-serif;
}
input[type="password"]::placeholder {
  font-family: "NanumSquare";
}
.logintext {
  position: absolute;
  width: 300px;
  height: 40px;
  left: 810px;
  top: 330px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;
  text-align: center;

  color: #000000;
}
.logininput {
  position: absolute;
  width: 400px;
  height: 20px;
  left: 763px;
  top: 472px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.loginbox {
  box-sizing: border-box;

  position: absolute;
  width: 420px;
  height: 64px;
  left: 750px;
  top: 450px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.loginboxtext {
  position: absolute;
  width: 150px;
  height: 20px;
  left: 885px;
  top: 742px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
}
.passwordbox {
  box-sizing: border-box;

  position: absolute;
  width: 420px;
  height: 64px;
  left: 750px;
  top: 530px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.pwinput {
  position: absolute;
  width: 400px;
  height: 20px;
  left: 763px;
  top: 552px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.loginbutton {
  position: absolute;
  width: 420px;
  height: 64px;
  left: 750px;
  top: 720px;

  background: #62b878;
  border-radius: 5px;
}

.signupbutton {
  box-sizing: border-box;

  position: absolute;
  width: 420px;
  height: 64px;
  left: 750px;
  top: 800px;

  background: #ffffff;
  border: 1px solid #62b878;
  border-radius: 5px;
}
.signuptext {
  position: absolute;
  width: 150px;
  height: 20px;
  left: 885px;
  top: 822px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #62b878;
}
.findid {
  position: absolute;
  width: 77px;
  height: 18px;
  left: 982px;
  top: 610px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.findpw {
  position: absolute;
  width: 92px;
  height: 18px;
  left: 1080px;
  top: 610px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.bar {
  position: absolute;
  width: 5px;
  height: 18px;
  left: 1067px;
  top: 610px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
</style>
