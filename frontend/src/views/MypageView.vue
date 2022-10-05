<template>
  <div>
    <div>
      <div class="pointer"></div>
      <div class="supporter">서포터</div>
      <div class="bar"></div>
      <router-link :to="{ path: '/mypage/maker/prefunding' }">
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
          style="display: none"
        />
      </form>
      <img :src="image" alt="none" class="profileimg" />
    </div>

    <div>
      <div class="aliastext">닉네임</div>
      <input
        type="text"
        class="alias"
        style="border: none"
        :placeholder="this.user.nickname"
        v-model="nick"
      />
      <div @click="changeNickname()" class="aliasedit"></div>
    </div>

    <div>
      <div class="balancetext">잔고</div>
      <div class="balance">{{ remainCoin }}</div>
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
    <div
      style="
        display: flex;
        justify-content: space-between;
        width: 1000px;
        margin: auto;
        margin-top: 1100px;
      "
    >
      <router-link :to="{ path: '/mypage/onfunding' }">
        <div class="fundinghistory">진행 중 펀딩</div>
      </router-link>
      <router-link :to="{ path: '/mypage/endfunding' }">
        <div class="fundinghistory">종료 된 펀딩</div>
      </router-link>
    </div>
    <div>
      <router-view />
    </div>
    <div class="background"></div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import AWS from "aws-sdk";
import UserJoinOnFundings from "../components/UserJoinOnFundings.vue";

const serverUrl = "j7a306.p.ssafy.io/api";
export default {
  components: { UserJoinOnFundings },
  data() {
    return {
      albumBucketName: "dangdang-bucket",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:81a948c5-f0c2-4e4b-ac0c-6ed0ffbce8b8",
      image: "",
      nick: "",
      isCheckNick: true,
      remainCoin: 0,
      nowPage: 0,
      fundings: [],
      state: 1,
      nextfundings: [],
    };
  },
  computed: {
    ...mapState(["user", "Authorization"]),
  },
  created() {
    this.getUserRemainCoin();
    this.getFiles();
  },
  methods: {
    uploadImg() {
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId,
        }),
      });

      const S3 = new AWS.S3({
        apiVersion: "2012-10-17",
        params: {
          Bucket: this.albumBucketName,
        },
      });
      const fileName = this.$refs["image"].files[0].name;
      const fileArr = fileName.split(".");
      const fileExtension = fileArr[fileArr.length - 1];
      let photoKey =
        "user/" + this.user.email + "/profile/supporter/" + "0." + "jpg";
      S3.upload({
        Key: photoKey,
        Body: this.$refs["image"].files[0],
        ACL: "public-read",
      })
        .promise()
        .then((data) => {
          this.image = data.Location;
        })
        .catch((err) => {
          conosle.log(err);
        });

      // 이게 있으면 유저 입장에서는 빨리 바뀐다고 느껴짐 근데 빨리 바꿧다고 생각하고 페이지를 벗어나버리면 문제가 생김
      var image = this.$refs["image"].files[0];
      const url = URL.createObjectURL(image);
      this.image = url;
      alert("이미지가 변경되었습니다.");
    },

    getFiles() {
      // 파일 불러오기
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId,
        }),
      });

      const S3 = new AWS.S3({
        apiVersion: "2012-10-17",
        params: {
          Bucket: this.albumBucketName,
        },
      });

      console.log("start");
      S3.listObjects(
        {
          Prefix: "user/" + this.user.email + "/profile/supporter",
        },
        (err, data) => {
          if (err) {
            return alert("에러");
          } else {
            try {
              console.log(data);
              this.image =
                "https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/" +
                data.Contents[0].Key;
            } catch (err) {
              this.image =
                "https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/basic_image/seaotter.png";
            }
          }
        }
      );
    },

    changeNickname() {
      // const url = "http://localhost:8080/api/user/change/nick";
      const url = "https://" + serverUrl + "/user/change/nick";
      axios
        .patch(
          url,
          {
            nickname: this.nick,
          },
          {
            headers: {
              // 토큰도 state에서 user 정보 가져와서 쓰도록 수정해야함
              Authorization: this.Authorization,
            },
          }
        )
        .then(({ data }) => {
          this.isCheckNick = data;
          if (this.isCheckNick) {
            // state의 유저의 닉네임 변경
            this.$store.commit("SET_CHANGENICK", this.nick);
            alert("닉네임 변경이 완료되었습니다 :)");
          } else {
            alert("이미 사용중인 닉네임입니다 :(");
            this.nick = "";
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getUserRemainCoin() {
      const url = "https://" + serverUrl + "/user/checkcoin";
      axios
        .get(url, {
          headers: {
            // 토큰도 state에서 user 정보 가져와서 쓰도록 수정해야함
            Authorization: this.Authorization,
          },
        })
        .then(({ data }) => {
          console.log(data);
          this.remainCoin = data.won;
        })
        .catch((err) => {
          console.log(err);
        });
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
  z-index: 1;

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
  cursor: pointer;

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
  width: 200px;
  height: 28px;
  left: 259px;
  cursor: pointer;

  /* background: rgba(98, 184, 120, 0.5); */
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
.thumbnail {
  width: 300px;
  height: 400px;
  left: 0px;
  top: 0px;
  box-sizing: border-box;
}
.title {
  width: 300px;
  height: 20px;
  left: 0px;
  top: 406px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.category {
  width: 160px;
  height: 14px;
  left: 0px;
  top: 436px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;

  color: #000000;
}
.makername {
  width: 160px;
  height: 14px;
  left: 120px;
  top: 436px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;
  text-align: right;

  color: #000000;
}
.progressbar {
  width: 300px;
  height: 20px;
  left: 0px;
  top: 458px;

  background: #67be7e;
}
.bars {
  width: 300px;
  height: 20px;
  left: 0px;
  top: 458px;

  background: #f7f7f7;
}
.percentage {
  width: 60px;
  height: 14px;
  left: 0px;
  top: 484px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;

  color: #000000;
}
.total {
  width: 140px;
  height: 14px;
  left: 60px;
  top: 484px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;

  color: #000000;
}
.remain {
  width: 100px;
  height: 14px;
  left: 200px;
  top: 484px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;
  text-align: right;

  color: #000000;
}
#fundingList {
  display: flex;
  flex-flow: wrap;
  justify-content: space-between;
  /* gap: 10px 1%; */
  width: 1320px;

  margin-top: 70px;
}
.onpoint {
  cursor: pointer;
}
</style>
