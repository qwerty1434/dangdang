<template>
  <div>
    <h1>this is a my page</h1>
    <div>
      <div class="pointer"></div>
      <div class="supporter">서포터</div>
      <div class="bar"></div>
      <router-link :to="{ name: 'maker' }">
        <div class="maker">메이커</div></router-link
      >
    </div>

    <div>
      <form method="post" enctype="multipart/form-data">
        <div>
          <label for="chooseFile" class="profileimgedit"> </label>
        </div>
        <input
          ref="image"
          @change="uploadImg()"
          type="file"
          id="chooseFile"
          name="chooseFile"
          accept="image/*"
          style="display: none" />
      </form>
      <img :src="image" alt="none" class="profileimg" />
    </div>

    <div>
      <div class="aliastext">닉네임</div>
      <div class="alias">남궁싸피</div>
      <div class="aliasedit"></div>
    </div>

    <div>
      <div class="balancetext">잔고</div>
      <div class="balance">300000</div>
      <div class="balanceunit">SSF</div>
    </div>

    <div>
      <div class="depositbox"></div>
      <div class="deposittext">입금</div>
    </div>

    <div>
      <div class="withdrawbox"></div>
      <div class="withdrawtext">출금</div>
    </div>

    <div class="borderline"></div>
    <div class="fundinghistory">펀딩 내역</div>
    <div class="carousel"></div>
    <div class="background"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      image: "",
    };
  },
  methods: {
    uploadImg() {
      console.log("들어왔다");
      var image = this.$refs["image"].files[0];

      const url = URL.createObjectURL(image);
      this.image = url;
      console.log(url);
      console.log(this.image);
    },
  },
};
</script>

<style scoped>
.pointer {
  position: absolute;
  width: 90px;
  height: 82.51px;
  left: 492px;
  top: 290px;

  background: url("@/assets/flower.png");
  transform: rotate(1.9deg);
}
.supporter {
  position: absolute;
  width: 110px;
  height: 45px;
  left: 582px;
  top: 310px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;

  color: #000000;
}
.bar {
  position: absolute;
  width: 4px;
  height: 64px;
  left: 964px;
  top: 300px;

  background: #000000;
}
.maker {
  position: absolute;
  width: 110px;
  height: 45px;
  left: 1240px;
  top: 310px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;

  color: #000000;
}
.profileimg {
  box-sizing: border-box;

  position: absolute;
  width: 320px;
  height: 320px;
  left: 806px;
  top: 438px;
  border-radius: 50%;
  border: 1px solid #000000;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.profileimgedit {
  position: absolute;
  width: 60px;
  height: 62.45px;
  left: 1052px;
  top: 678px;
  z-index: 300;

  background: url("@/assets/edit.png");
}
.aliastext {
  position: absolute;
  width: 200px;
  height: 40px;
  left: 608px;
  top: 820px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;

  color: #000000;
}
.alias {
  position: absolute;
  width: 280px;
  height: 40px;
  left: 820px;
  top: 820px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.aliasedit {
  position: absolute;
  width: 60px;
  height: 62.45px;
  left: 1128px;
  top: 809px;

  background: url("@/assets/edit.png");
}
.balancetext {
  position: absolute;
  width: 320px;
  height: 40px;
  left: 608px;
  top: 940px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;

  color: #000000;
}
.balance {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 778px;
  top: 940px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.balanceunit {
  position: absolute;
  width: 82px;
  height: 40px;
  left: 1018px;
  top: 940px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.depositbox {
  position: absolute;
  width: 80px;
  height: 40px;
  left: 1128px;
  top: 940px;

  background: #62b878;
  border-radius: 5px;
}
.deposittext {
  position: absolute;
  width: 80px;
  height: 20px;
  left: 1128px;
  top: 950px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
}
.withdrawbox {
  position: absolute;
  width: 80px;
  height: 40px;
  left: 1224px;
  top: 940px;

  background: #ffa4a4;
  border-radius: 5px;
}
.withdrawtext {
  position: absolute;
  width: 80px;
  height: 20px;
  left: 1224px;
  top: 950px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
}
.borderline {
  position: absolute;
  width: 1400px;
  height: 8px;
  left: 260px;
  top: 1080px;

  background: linear-gradient(
    180deg,
    rgba(0, 0, 0, 0.11) 0%,
    rgba(0, 0, 0, 0) 100%
  );
}
.fundinghistory {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 259px;
  top: 1160px;

  background: rgba(98, 184, 120, 0.5);
  /* 텍스트 */
  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  text-align: center;

  color: #000000;
}
.carousel {
  position: absolute;
  width: 1380px;
  height: 490px;
  left: 276px;
  top: 1360px;
  background: url("@/assets/캐러셀1.png");
}
.background {
  float: left top;
  width: 1920px;
  height: 2160px;
  z-index: -5;

  background: #ffffff;
}
</style>
