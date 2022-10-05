<template>
  <div>
    <div>
      <div class="pointer"></div>
      <div class="maker">메이커</div>
    </div>

    <div>
      <img :src="image" alt="none" class="profileimg" />
    </div>

    <div>
      <div class="companytext">회사이름</div>
      <div class="company">{{this.companyName}}</div>
    </div>

    <div>
      <div class="registrationnumber">사업자 등록번호</div>
      <div type="text" class="registrationinput">{{ this.companyNo }}</div>
    </div>

    <div>
      <modal-view v-if="isModalViewed" @close-modal="isModalViewed = false">
        <business-license></business-license>
      </modal-view>

    </div>

    <div class="borderline"></div>
    <div>
      <router-link :to="{ path: '/maker/prefunding' , query: {id: makerId}}">
        <div class="fundingonwaitbox"></div>
        <div class="fundingonwaittext">대기 중인 펀딩</div>
      </router-link>
      <router-link :to="{ path: '/maker/onfunding' , query: {id: makerId}}">
        <div class="fundingongoingbox"></div>
        <div class="fundingongoingtext">진행 중인 펀딩</div>
      </router-link>
      <router-link :to="{ path: '/maker/endfunding' , query: {id: makerId}}">
        <div class="fundingendbox"></div>
        <div class="fundingendtext">종료 된 펀딩</div>
      </router-link>
    </div>
    <div class="routerposition">
      <router-view />
    </div>

    <div class="background"></div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import AWS from "aws-sdk";
import BusinessLicense from "@/components/BusinessLicense.vue";
import ModalView from "@/components/ModalView.vue";

const serverUrl = "j7a306.p.ssafy.io/api";
export default {
  data() {
    return {
      albumBucketName: "dangdang-bucket",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:81a948c5-f0c2-4e4b-ac0c-6ed0ffbce8b8",
      image: "",
      isModalViewed: false,
      companyNo: "",
      companyName: "",
      makerId: "",
    };
  },
  components: {
    BusinessLicense,
    ModalView,
  },

  computed: {
    ...mapState(["user", "Authorization"]),
  },
  created() {
    this.makerId = this.$route.query.id;
    this.getFiles();
    const headers = { Authorization: this.$store.state.Authorization };
    axios
      .get("https://" + serverUrl + "/maker/my-info", { headers: headers })
      .then((response) => {
        this.companyNo = response.data.companyNo;
        this.companyName = response.data.companyName;
      })
      .catch(() => {
        console.log("error");
      });
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
        "user/" + this.user.email + "/profile/maker/" + "0." + "jpg";
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

      var image = this.$refs["image"].files[0];
      const url = URL.createObjectURL(image);
      this.image = url;
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
          Prefix: "user/" + this.user.email + "/profile/maker",
        },
        (err, data) => {
          if (err) {
            return alert("에러");
          } else {
            console.log(data);
            try {
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
    modal() {
      this.isModalViewed = !this.isModalViewed;
      console.log(this.isModalViewed);
    },
  },
};
</script>

<style scoped>
.pointer {
  position: absolute;
  width: 90px;
  height: 82.51px;
  left: 810px;
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
  left: 900px;
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
  background-size: cover;
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
.companytext {
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
.company {
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
.registrationnumber {
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
.registrationbox {
  box-sizing: border-box;

  position: absolute;
  width: 320px;
  height: 60px;
  left: 932px;
  top: 930px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}

.fundingBtn {
  position: absolute;
  width: 160px;
  height: 60px;
  left: 890px;
  top: 1030px;

  background: #62b878;
  border-radius: 5px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 58px;
  text-align: center;

  text-decoration: none;
  color: #ffffff;
}

.registrationinput {
  position: absolute;
  width: 280px;
  height: 40px;
  left: 954px;
  top: 940px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 45px;

  color: #000000;
}
.registrationcheckbox {
  position: absolute;
  width: 80px;
  height: 60px;
  left: 1268px;
  top: 930px;

  background: #62b878;
  border-radius: 5px;
}
.registrationchecktext {
  position: absolute;
  width: 80px;
  height: 20px;
  left: 1268px;
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
  top: 1125px;

  background: linear-gradient(
    180deg,
    rgba(0, 0, 0, 0.11) 0%,
    rgba(0, 0, 0, 0) 100%
  );
}
.fundingonwaitbox {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 470px;
  top: 1164px;

  background: rgba(98, 184, 120, 0.5);
}
.fundingonwaittext {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 470px;
  top: 1164px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  text-align: center;

  color: #000000;
}
.fundingonwaitcarousel {
  position: absolute;
  width: 1380px;
  height: 490px;
  left: 376px;
  top: 1360px;
  background: url("@/assets/캐러셀1.png");
}
.fundingongoingbox {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 874px;
  top: 1164px;

  background: rgba(98, 184, 120, 0.5);
}
.fundingongoingtext {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 874px;
  top: 1164px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  text-align: center;

  color: #000000;
}
.fundingongoingcarousel {
  position: absolute;
  width: 1380px;
  height: 490px;
  left: 276px;
  top: 1360px;
  background: url("@/assets/캐러셀2.png");
}
.fundingendbox {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 1300px;
  top: 1164px;

  background: rgba(98, 184, 120, 0.5);
}
.fundingendtext {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 1300px;
  top: 1164px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  text-align: center;

  color: #000000;
}
.fundingendcarousel {
  position: absolute;
  width: 1380px;
  height: 490px;
  left: 276px;
  top: 1360px;
  background: url("@/assets/캐러셀3.png");
}
.fundingunauthorizedbox {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 1468px;
  top: 1164px;

  background: rgba(98, 184, 120, 0.5);
}
.fundingunauthorizedtext {
  position: absolute;
  width: 200px;
  height: 28px;
  left: 1468px;
  top: 1164px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 32px;
  text-align: center;

  color: #000000;
}
.fundingunauthorizedcarousel {
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
.routerposition {
  position: absolute;
  top: 1200px;
}
</style>
