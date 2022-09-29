<template>
  <div>
    <div class="background"></div>
    <div class="logintext">로그인</div>
    <div class="loginbox"></div>
    <input type="text" placeholder="아이디를 입력해주세요" class="logininput" v-model="email"/>
    <div class="passwordbox"></div>
    <input
      type="password"
      placeholder="비밀번호를 입력해주세요"
      class="pwinput"
      v-model="pw" 
      />
    <div>
      <button @click="login()" class="loginBtn">로그인</button>
    </div>
      <button type="button" @click="join()" class="joinBtn">회원가입</button>

    <div class="findid">아이디 찾기</div>
    <div class="findpw">비밀번호 찾기</div>
    <div class="bar">|</div>
    <h1>this is a login page</h1>
  </div>
</template>

<script>
  import axios from "axios";
  const instance = axios.create({
    baseURL: "/",
    headers: {
      Authrozation: "test",
    },
  });
export default {
  data() {
    return{
      email:"",
      pw:"",
      isClick:false,
      isInputId:false,
      isInputPw:false,
      user: { email: "", isAdmin: false, nickname: "", accessToken: "" },
      // staticUrl: "http://localhost:8080",
      // serverUrl: "https://j7a306.p.ssafy.io",
      staticUrl: "https://j7a306.p.ssafy.io",
    }
  },
  methods: {
    login(){
      const url = this.staticUrl + "/api/user/login";
      console.log("1");
      if(this.email==""){
        alert("이메일을 입력해주세요.");
        this.isInputId = false;
      } else { this.isInputId = true; }
      if(this.email!="" && this.pw==""){
        alert("비밀번호를 입력해주세요.");
        this.isInputPw = false;
      } else { this.isInputPw = true; }
      if(!this.isClick && this.isInputId && this.isInputPw){
        axios
          .post(url, {
            email: this.email,
            password: this.pw,
          })
          .then(({ data }) => {
            //이메일 형식인지 확인
              this.isClick=true;
              console.log(data);
              this.user.isAdmin = data.admin;
              this.user.nickname = data.nickname;
              this.user.email = data.email;
              this.user.accessToken = data.accessToken;
              this.$store.commit("registUser", this.user);
              console.log("page 이동 전");
              console.log(this.$store.state);
              this.$router.go(-1);
              console.log("page 이동 후");
              console.log(this.$store.state);
          })
          .catch(({ data }) => {
            alert("이메일과 비밀번호를 다시 확인해주세요.");
          });
      }
      
    },
    join(){
      this.$router.push({ path: 'signup' });
    },
  },

};
</script>

<style scoped>
input[type="password"] {
  font-family: "맑은 고딕", "malgun gothic", "돋움", Dotum, sans-serif;
}
input[type="password"]::placeholder {
  font-family: "NanumSquare";
}
.background {
  float: left top;
  width: 1900px;
  height: 1060px;
  z-index: -5;

  background: #ffffff;
}
.loginBtn{
  position: absolute;
  width: 420px;
  height: 64px;
  left: 750px;
  top: 720px;
  border: 0;

  background: #62b878;
  border-radius: 5px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
}
.joinBtn{
  box-sizing: border-box;

  position: absolute;
  width: 420px;
  height: 64px;
  left: 750px;
  top: 800px;

  background: #ffffff;
  border: 1px solid #62b878;
  border-radius: 5px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #62b878;
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
  height: 60px;
  left: 760px;
  top: 452px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  color: #000000;
  border: none;
  background: none;
  outline: none;
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
  height: 60px;
  left: 760px;
  top: 532px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  border: none;
  background: none;
  outline: none;
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
