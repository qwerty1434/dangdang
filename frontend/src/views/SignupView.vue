<template>
  <div>
    <div class="background"></div>
    <h1>this is a signup page</h1>

    <div class="signuptext">회원가입</div>
    <div>
      <div class="emailtext">이메일</div>
      <div class="emailbox"></div>
      <button @click="checkRealEmail()" class="authNumSendButton">
        인증번호 보내기
      </button>
      <input
        type="text"
        placeholder="예: dangdangfunding@dangdang.com"
        class="emailinput"
        v-model="email"
      />
    </div>

    <div>
      <div class="validationtext">인증번호</div>
      <div class="validationbox"></div>
      <button @click="checkAuthNum()" class="authNumCheckButton">
        인증번호 확인
      </button>
      <input
        type="text"
        placeholder="이메일로 전달된 인증번호를 입력해 주세요"
        class="validationinput"
        v-model="inputAuth"
      />
    </div>
    <div>
      <div class="pwtext">비밀번호</div>
      <div class="pwbox"></div>
      <input
        type="password"
        placeholder="비밀번호를 입력해 주세요"
        class="pwinput"
        v-model="pw"
      />
    </div>

    <div>
      <div class="pwchecktext">비밀번호확인</div>
      <div class="pwcheckbox"></div>
      <input
        type="password"
        placeholder="비밀번호를 다시 한번 입력해 주세요"
        class="pwcheckinput"
        v-model="pwCheck"
      />
    </div>

    <div>
      <div class="aliastext">닉네임</div>
      <div class="aliasbox"></div>
      <button @click="checkNickname()" class="nickCheckButton">
        중복 확인
      </button>
      <input
        type="text"
        placeholder="닉네임을 입력해 주세요"
        class="aliasinput"
        v-model="inputNick"
      />
    </div>
    <div>
      <button @click="signup()" class="signupButton">가입하기</button>
    </div>
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
    return {
      email: "",
      checkEmail: "",
      authNum: "",
      isCheckEmail: true,
      isCheckAuthNum: false,
      inputAuth: "",
      pw: "",
      pwCheck: "",
      inputNick: "",
      checkNick: "",
      isCheckNick: true,
      flag: false,
      isClick: false,
      joinUser: { email: "", isAdmin: false, nickname: "", accessToken: "" },
      // staticUrl: "http://localhost:8080",
      // serverUrl: "https://j7a306.p.ssafy.io",
      staticUrl: "https://j7a306.p.ssafy.io",
    };
  },
  created() {},
  methods: {
    checkRealEmail() {
      const url = this.staticUrl + "/api/user/check/email";
      axios
        .post(url, {
          str: this.email,
        })
        .then(({ data }) => {
          // true는 중복 이메일 있는 것, false는 없는 것
          this.isCheckEmail = data;
          this.isCheckAuthNum = false;
          this.isCheckNick = true;
          this.authNum = "";
          this.checkEmail = "";
          this.checkNick = "";
          //이메일 형식인지 확인
          if (this.isCheckEmail) {
            alert("이미 가입된 이메일입니다. 다른 이메일을 입력해주세요.");
          } else {
            this.checkEmail = this.email;
            this.authEmail();
          }
        })
        .catch(({ data }) => {
          alert("이메일을 다시 입력해주세요.");
        });
    },
    authEmail() {
      const url = this.staticUrl + "/api/user/auth-email";
      axios
        .post(url, {
          email: this.email,
        })
        .then(({ data }) => {
          // 서버에서 정상적인 값이 넘어 왔을경우 실행.
          this.isValidEmail = data;
          this.authNum = data.authNum;
          alert(
            "입력하신 이메일로 인증 번호를 보냈습니다. 확인 후 입력해주세요."
          );
        })
        .catch(({ data }) => {
          alert("이메일 재입력 필요");
        });

      // /check/email 도 해야함
    },
    checkAuthNum() {
      if (this.authNum == this.inputAuth) {
        this.isCheckAuthNum = true;
        alert("인증되었습니다.");
      } else {
        alert("인증 번호를 다시 입력해주세요.");
      }
    },
    checkNickname() {
      const url = this.staticUrl + "/api/user/check/nick";
      if (this.inputNick == "") {
        alert("닉네임을 입력해주세요.");
      } else {
        axios
          .post(url, {
            str: this.inputNick,
          })
          .then(({ data }) => {
            // true는 중복 닉 있는 것, false는 없는 것, exception도 없는 것
            this.isCheckNick = data;
            if (this.isCheckNick) {
              alert("이미 존재하는 닉네임입니다.");
            } else {
              this.checkNick = this.inputNick;
              alert("사용 가능한 닉네임입니다.");
            }
          })
          .catch(({ data }) => {
            alert("닉네임 재입력 필요");
          });
      }
    },
    signup() {
      const url = this.staticUrl + "/api/user/join";
      this.flag = false;
      if (this.isCheckEmail || this.email != this.checkEmail) {
        alert("이메일 인증이 되지 않았거나 인증한 이메일이 수정되었습니다.");
        this.flag = true;
      }
      if (!this.isCheckAuthNum) {
        alert("인증번호를 다시 확인해주세요.");
        this.flag = true;
      }
      if (this.pw != this.pwCheck) {
        alert("비밀번호와 확인을 위해 입력받은 비밀번호가 다릅니다.");
        this.flag = true;
      }
      if (this.isCheckNick || this.checkNick != this.inputNick) {
        alert("닉네임 중복 확인을 해주세요.");
        this.flag = true;
      }
      if (!this.flag && !this.isClick) {
        this.isClick = true;
        axios
          .post(url, {
            email: this.checkEmail,
            password: this.pw,
            nickname: this.checkNick,
          })
          .then(({ data }) => {
            console.log(data);
            this.joinUser.isAdmin = data.admin;
            this.joinUser.nickname = data.nickname;
            this.joinUser.email = data.email;
            this.joinUser.accessToken = data.accessToken;
            this.$store.commit("registUser", this.joinUser);
            console.log(this.$store);
            this.$router.go(-1);
          })
          .catch(({ data }) => {
            alert("회원 가입에 실패했습니다.");
          });
      }
    },
  },
};
</script>
<!-- 이 페이지에만 반영되는 css -->
<style scoped>
input[type="password"] {
  font-family: "맑은 고딕", "malgun gothic", "돋움", Dotum, sans-serif;
}
input[type="password"]::placeholder {
  font-family: "NanumSquare";
}
.background {
  float: left top;
  width: 1920px;
  height: 1080px;
  z-index: -5;

  background: #ffffff;
}
.authNumSendButton {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 52px;
  left: 1202px;
  top: 424px;

  border: 0.5px solid #62b878;

  border-radius: 5px;
  background: #ffffff;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: center;

  color: #62b878;
}
.authNumCheckButton {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 52px;
  left: 1202px;
  top: 508px;

  border: 0.5px solid #62b878;
  border-radius: 5px;
  background: #ffffff;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: center;

  color: #62b878;
}
.nickCheckButton {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 52px;
  left: 1202px;
  top: 760px;

  border: 0.5px solid #62b878;
  border-radius: 5px;
  background: #ffffff;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: center;

  color: #62b878;
}
.signupButton {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 891px;
  top: 876px;

  background: #62b878;
  border-radius: 5px;
  border: 0;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
}

.signuptext {
  position: absolute;
  width: 300px;
  height: 40px;
  left: 811px;
  top: 330px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;
  text-align: center;

  color: #000000;
}
.emailtext {
  position: absolute;
  width: 80px;
  height: 20px;
  left: 576px;
  top: 440px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.validationtext {
  position: absolute;
  width: 80px;
  height: 20px;
  left: 576px;
  top: 524px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.pwtext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 576px;
  top: 608px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.pwchecktext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 576px;
  top: 692px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.aliastext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 576px;
  top: 776px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.emailbox {
  box-sizing: border-box;

  position: absolute;
  width: 470px;
  height: 52px;
  left: 706px;
  top: 424px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.validationbox {
  box-sizing: border-box;

  position: absolute;
  width: 470px;
  height: 52px;
  left: 706px;
  top: 508px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.pwbox {
  box-sizing: border-box;

  position: absolute;
  width: 470px;
  height: 52px;
  left: 706px;
  top: 592px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.pwcheckbox {
  box-sizing: border-box;

  position: absolute;
  width: 470px;
  height: 52px;
  left: 706px;
  top: 676px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.aliasbox {
  box-sizing: border-box;

  position: absolute;
  width: 470px;
  height: 52px;
  left: 706px;
  top: 760px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.validationrequestbox {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 52px;
  left: 1202px;
  top: 424px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.validationrequesttext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 1212px;
  top: 440px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: center;

  color: #000000;
}
.emailinput {
  position: absolute;
  width: 400px;
  height: 50px;
  left: 720px;
  top: 426px;

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
.validationinput {
  position: absolute;
  width: 400px;
  height: 50px;
  left: 720px;
  top: 510px;

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
.pwinput {
  position: absolute;
  width: 400px;
  height: 50px;
  left: 720px;
  top: 594px;

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
.pwcheckinput {
  position: absolute;
  width: 400px;
  height: 50px;
  left: 720px;
  top: 678px;

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
.aliasinput {
  position: absolute;
  width: 400px;
  height: 50px;
  left: 720px;
  top: 762px;

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
</style>
