<template>
  <div>
    <div class="background"></div>
    <h1>this is a signup page</h1>

    <div class="signuptext">회원가입</div>

    <div>
      <div class="emailtext">이메일</div>
      <div class="emailbox"></div>
      <div class="validationrequestbox"></div>
      <div class="validationrequesttext">인증번호 보내기</div>
      <input
        type="text"
        placeholder="예: dangdangfunding@dangdang.com"
        class="emailinput"
      />
    </div>

    <div>
      <div class="validationtext">인증번호</div>
      <div class="validationbox"></div>
      <div class="validationchecktext">인증번호 확인</div>
      <div class="validationcheckbox"></div>
      <input
        type="text"
        placeholder="이메일로 전달된 인증번호를 입력해 주세요"
        class="validationinput"
      />
    </div>
    <div>
      <div class="pwtext">비밀번호</div>
      <div class="pwbox"></div>
      <input
        type="password"
        placeholder="비밀번호를 입력해 주세요"
        class="pwinput"
      />
    </div>

    <div>
      <div class="pwchecktext">비밀번호확인</div>
      <div class="pwcheckbox"></div>
      <input
        type="password"
        placeholder="비밀번호를 다시 한번 입력해 주세요"
        class="pwcheckinput"
      />
    </div>

    <div>
      <div class="aliastext">닉네임</div>
      <div class="aliasbox"></div>
      <div class="aliasisuniquetext">중복 확인</div>
      <div class="aliasisuniquebox"></div>
      <input
        type="text"
        placeholder="닉네임을 입력해 주세요"
        class="aliasinput"
      />
    </div>
    <div>
      <div class="signupbox"></div>
      <div class="signupboxtext">가입하기</div>
    </div>
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
.background {
  float: left top;
  width: 1920px;
  height: 1080px;
  z-index: -5;

  background: #ffffff;
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
.validationcheckbox {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 52px;
  left: 1202px;
  top: 508px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.aliasisuniquebox {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 52px;
  left: 1202px;
  top: 760px;

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
.validationchecktext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 1212px;
  top: 524px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: center;

  color: #000000;
}
.aliasisuniquetext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 1212px;
  top: 776px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: center;

  color: #000000;
}
.signupbox {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 891px;
  top: 876px;

  background: #62b878;
  border-radius: 5px;
}
.signupboxtext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 900px;
  top: 892px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
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
