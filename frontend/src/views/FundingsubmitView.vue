<template>
  <div>
    <h1>this is a /funding/submit page</h1>
    <div>
      <div>
        <select v-model="category" class="categorybox">
          <option v-for="c in categories" v-bind:key="c">{{ c }}</option>
        </select>
        <div class="categoryselect"></div>
        <div class="categorybutton"></div>
      </div>
      <div>
        <div class="fundingtitlebox"></div>
        <input
          type="text"
          class="fundingtitleinput"
          placeholder="제목을 입력해주세요"
          v-model="title" />
      </div>
      <div>
        <div class="goalbox"></div>
        <input
          type="text"
          class="goalinput"
          placeholder="목표 금액을 입력해주세요"
          v-model="targetPrice" />
      </div>
      <div>
        <div class="">
          <form method="post" enctype="multipart/form-data">
            <div>
              <label for="file0" class="thumbnail1"> </label>
            </div>
            <input
              type="file"
              ref="file0"
              id="file0"
              accept="image/*"
              style="display: none"
              @change="UploadThumbnail(0)" />
          </form>
        </div>

        <div class="">
          <form method="post" enctype="multipart/form-data">
            <div>
              <label for="file1" class="thumbnail2"> </label>
            </div>
            <input
              type="file"
              ref="file1"
              id="file1"
              accept="image/*"
              style="display: none"
              @change="UploadThumbnail(1)" />
          </form>
        </div>

        <div class="">
          <form method="post" enctype="multipart/form-data">
            <div>
              <label for="file2" class="thumbnail3"> </label>
            </div>
            <input
              type="file"
              ref="file2"
              id="file2"
              accept="image/*"
              style="display: none"
              @change="UploadThumbnail(2)" />
          </form>
        </div>

        <div class="">
          <form method="post" enctype="multipart/form-data">
            <div>
              <label for="file3" class="detail1"> </label>
            </div>
            <input
              type="file"
              ref="file3"
              id="file3"
              accept="image/*"
              style="display: none"
              @change="UploadContentImage(0)" />
          </form>
        </div>

        <div class="">
          <form method="post" enctype="multipart/form-data">
            <div>
              <label for="file4" class="detail2"> </label>
            </div>
            <input
              type="file"
              ref="file4"
              id="file4"
              accept="image/*"
              style="display: none"
              @change="UploadContentImage(1)" />
          </form>
        </div>

        <div class="">
          <form method="post" enctype="multipart/form-data">
            <div>
              <label for="file5" class="detail3"> </label>
            </div>
            <input
              type="file"
              ref="file5"
              id="file5"
              accept="image/*"
              style="display: none"
              @change="UploadContentImage(2)" />
          </form>
        </div>
      </div>
    </div>

    <!-- 리워드 -->
    <div>
      <div>
        <div class="rewardtitlebox"></div>
        <input
          type="text"
          class="rewardtitleinput"
          placeholder="리워드 제목을 입력해주세요"
          v-model="reward.title" />
      </div>
      <div>
        <div class="rewardcontentbox"></div>
        <input
          type="text"
          class="rewardcontentinput"
          placeholder="리워드 내용을 입력해주세요"
          v-model="reward.content" />
      </div>
      <div>
        <div class="rewardpricebox"></div>
        <input
          type="text"
          class="rewardpriceinput"
          placeholder="리워드에 해당하는 금액을 입력해주세요"
          v-model="reward.price" />
      </div>
      <button @click="makeReward()" class="rewardaddbutton Btn">
        리워드 추가
      </button>

      <div>
        <div
          v-for="(reward, index) in rewards"
          v-bind:key="reward"
          :class="`reward${index + 1}`">
          <div @click="deleteReward(index)">{{ index }} 삭제</div>
          <div class="title">제목: {{ reward.title }}</div>
          <div class="contents">내용: {{ reward.content }}</div>
          <div class="price">가격: {{ reward.price }}</div>
        </div>
      </div>
    </div>
    <!-- 기타정보 -->
    <div>
      <div class="registrationnumberbox"></div>
      <div class="registrationnumber">{{ companyNo }}</div>
      <div class="companynamebox"></div>
      <div class="companyname">{{ companyName }}</div>

      <!-- <div class="tagbox"></div>
      <input type="text" class="taginput" placeholder="#검색용 #태그" /> -->
      <!-- 펀딩일정 -->

      <div>
        <div class="calendar">
          <div>
            시작일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;종료일
          </div>

          <Datepicker v-model="fromDate" inline autoApply style="float: left" />
          <Datepicker v-model="toDate" inline autoApply />
        </div>
        <div class=""></div>
        <div class=""></div>
        <div class=""></div>
        <div class=""></div>
      </div>
      <!-- 변경사항 취소 임시저장 등록 버튼 -->
      <div>
        <!-- <button @click="clearData()" class="revert">변경사항취소</button>
        <button @click="b()" class="tempsave">임시저장</button> -->
        <button @click="revert()" class="revert Btn">취소</button>
        <button @click="submitData()" class="submit Btn">등록하기</button>
      </div>
    </div>
    <div>
      <div class="a">펀딩</div>
      <div class="b">카테고리</div>
      <div class="c">펀딩 제목</div>
      <div class="d">목표 금액</div>
      <div class="e">리워드</div>
      <div class="f">제목</div>
      <div class="g">내용</div>
      <div class="h">금액</div>
      <div class="i">기타정보</div>
      <div class="j">사업자 등록번호</div>
      <div class="k">회사명</div>
      <!-- <div class="l">검색용 태그</div> -->
      <div class="m">펀딩 일정</div>
      <div class="n">썸네일</div>
      <div class="o">본문</div>
      <!-- <div class="p">리워드추가</div> -->
      <!-- <div class="q">변경사항취소</div>
      <div class="r">임시저장</div>
      <div class="s">등록하기</div> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
import { ref } from "vue";
import AWS from "aws-sdk";

const serverUrl = "j7a306.p.ssafy.io/api";
export default {
  data() {
    return {
      // 글로벌 변수로 빼는거 추천 + 정보를 숨기는 걸 추천
      albumBucketName: "dangdang-bucket",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:81a948c5-f0c2-4e4b-ac0c-6ed0ffbce8b8",
      uuid: "",
      title: "",
      targetPrice: "",
      thumbnail: {
        img: "",
        sequence: "",
      },
      thumbnailUrl: [],
      contentImage: {
        img: "",
        sequence: "",
      },
      contentImageUrl: [],
      categories: [],
      category: "",
      rewards: [],
      reward: {
        title: "",
        content: "",
        price: "",
      },
      fromDate: ref(new Date()),
      toDate: ref(new Date()),
      companyName: "",
      companyNo: "",
    };
  },
  components: {
    Datepicker,
  },

  created() {
    this.createUuid(); // uuid생성
    axios
      .get("https://" + serverUrl + "/category/list", {})
      .then(response => {
        this.categories = response.data;
      })
      .catch(() => {
        console.log("error");
      });
    const headers = { Authorization: this.$store.state.Authorization };
    axios
      .get("https://" + serverUrl + "/maker/my-info", { headers: headers })
      .then(response => {
        this.companyNo = response.data.companyNo;
        this.companyName = response.data.companyName;
      })
      .catch(() => {
        console.log("error");
      });
  },
  methods: {
    UploadThumbnail(num) {
      var s = "file" + num;
      var data = eval(`this.$refs.${s}.files[0]`);
      var className = "thumbnail" + (num + 1);
      const thumbUrl = URL.createObjectURL(data);
      this.thumbnail.sequence = num;
      this.thumbnail.img = data;
      // this.thumbnailUrl[num] = JSON.parse(JSON.stringify(this.thumbnail));
      this.thumbnailUrl[num] = this.thumbnail;
      this.thumbnail = {
        img: "",
        sequence: "",
      };
      document.getElementsByClassName(className)[0].style.backgroundImage =
        "url('" + thumbUrl + "')";
    },
    UploadContentImage(num) {
      var s = "file" + (3 + num);
      var data = eval(`this.$refs.${s}.files[0]`);
      var className = "detail" + (num + 1);
      const contentUrl = URL.createObjectURL(data);
      this.contentImage.sequence = num;
      this.contentImage.img = data;
      this.contentImageUrl[num] = this.contentImage;
      this.contentImage = {
        img: "",
        sequence: "",
      };
      document.getElementsByClassName(className)[0].style.backgroundImage =
        "url('" + contentUrl + "')";
    },
    async SaveImages() {
      console.log("이미지 저장 시작");
      // 파일 업로드
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
      console.log(this.thumbnailUrl);
      for (let index = 0; index < this.thumbnailUrl.length; index++) {
        let photoKey = "funding/" + this.uuid + "/thumbnails/" + index + ".jpg";
        console.log(this.thumbnailUrl[index].img);
        await S3.upload({
          Key: photoKey,
          Body: this.thumbnailUrl[index].img,
          ACL: "public-read",
        })
          .promise()
          .then(data => {
            console.log("hello");
            console.log(data.Location);
            this.thumbnailUrl[index].img = data.Location;
          })
          .catch(err => {
            console.log(err);
            console.log("world");
          });
      }
      for (let index = 0; index < this.contentImageUrl.length; index++) {
        let photoKey = "funding/" + this.uuid + "/contents/" + index + ".jpg";
        await S3.upload({
          Key: photoKey,
          Body: this.contentImageUrl[index].img,
          ACL: "public-read",
        })
          .promise()
          .then(data => {
            this.contentImageUrl[index].img = data.Location;
          })
          .catch(err => {
            conosle.log(err);
            console.log("world");
          });
        // await S3.upload(
        //   {
        //     Key: photoKey,
        //     Body: this.contentImageUrl[index].img,
        //     ACL: "public-read",
        //   },
        //   (err, data) => {
        //     if (err) {
        //       console.log(err);
        //       return alert("에러");
        //     } else {
        //       this.contentImageUrl[index].img = data.Location;
        //     }
        //   }
        // );
      }
      console.log("이미지 저장 끝");
    },

    makeReward() {
      if (this.rewards.length >= 4) {
        alert("최대 4개만 가능함");
      }
      this.rewards.push(JSON.parse(JSON.stringify(this.reward))); // 깊은 복사를 위해 JSON 활용
      this.reward.title = "";
      this.reward.content = "";
      this.reward.price = "";
      console.log(this.rewards);
    },

    createUuid() {
      this.uuid = ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (
          c ^
          (crypto.getRandomValues(new Uint8Array(1))[0] & (15 >> (c / 4)))
        ).toString(16)
      );
    },

    changeDateFormat(a) {
      var b = String(a).split(" ");
      var month;
      if (b[1] == "Jan") {
        month = "01";
      } else if (b[1] == "Feb") {
        month = "02";
      } else if (b[1] == "Mar") {
        month = "03";
      } else if (b[1] == "Apr") {
        month = "04";
      } else if (b[1] == "May") {
        month = "05";
      } else if (b[1] == "Jun") {
        month = "06";
      } else if (b[1] == "Jul") {
        month = "07";
      } else if (b[1] == "Aug") {
        month = "08";
      } else if (b[1] == "Sep") {
        month = "09";
      } else if (b[1] == "Oct") {
        month = "10";
      } else if (b[1] == "Nov") {
        month = "11";
      } else if (b[1] == "Dec") {
        month = "12";
      }
      return b[3] + "-" + month + "-" + b[2] + " " + b[4];
    },

    async submitData() {
      await this.SaveImages();
      var result = {
        category: this.category,
        title: this.title,
        targetPrice: this.targetPrice,
        projectIntroduction: "temp",
        startDate: this.changeDateFormat(this.fromDate), // this.fromDate,
        endDate: this.changeDateFormat(this.toDate), //this.toDate,
        rewards: this.rewards,
        thumbnails: this.thumbnailUrl,
        bodyImgs: this.contentImageUrl,
      };
      var headers = { Authorization: this.$store.state.Authorization };
      console.log(this.$store.state.Authorization);
      console.log(result);

      axios
        .post("https://" + serverUrl + "/funding/regist", result, {
          headers: headers,
        })
        // axios
        //   .post("http://" + "localhost:8080/api" + "/funding/regist", result, {
        //     headers: headers,
        //   })
        .then(response => {
          console.log(response);
        })
        .catch(() => {
          console.log("error");
        });
      this.$router.go(-1);
    },

    revert() {
      this.$router.go(-1);
    },
    deleteReward(index) {
      this.rewards.splice(index, 1);
    },
  },
};
</script>

<style scoped>
.Btn {
  background: #62b878;
  border-radius: 5px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;
  border: 0;
  outline: 0;

  color: #ffffff;
}

.categorybox {
  box-sizing: border-box;

  position: absolute;
  width: 480px;
  height: 64px;
  left: 592px;
  top: 478px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.fundingtitlebox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 578px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.fundingtitleinput {
  position: absolute;
  width: 900px;
  height: 60px;
  left: 606px;
  top: 580px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
  border: none;
  background: none;
  outline: none;
}
.goalbox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 678px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.goalinput {
  position: absolute;
  width: 900px;
  height: 60px;
  left: 606px;
  top: 680px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  color: #000000;
  border: none;
  background: none;
  outline: none;
}
.thumbnail1 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 670px;
  top: 818px;
  background: url("@/assets/picture.png");
  background-size: 100% 100%;
}
.thumbnail2 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 811px;
  top: 818px;
  background: url("@/assets/picture.png");
  background-size: 100% 100%;
}
.thumbnail3 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 952px;
  top: 818px;
  background: url("@/assets/picture.png");
  background-size: 100% 100%;
}
.detail1 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 1170px;
  top: 818px;

  background: url("@/assets/picture.png");
  background-size: 100% 100%;
}
.detail2 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 1311px;
  top: 818px;
  background: url("@/assets/picture.png");
  background-size: 100% 100%;
}
.detail3 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 1452px;
  top: 818px;

  background: url("@/assets/picture.png");
  background-size: 100% 100%;
}
.rewardtitlebox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 1156px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.rewardtitleinput {
  position: absolute;
  width: 900px;
  height: 60px;
  left: 606px;
  top: 1158px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  color: #000000;
  border: none;
  background: none;
  outline: none;
}
.rewardcontentbox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 1256px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.rewardcontentinput {
  position: absolute;
  width: 900px;
  height: 60px;
  left: 606px;
  top: 1258px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  color: #000000;
  border: none;
  background: none;
  outline: none;
}
.rewardpricebox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 1356px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.rewardpriceinput {
  position: absolute;
  width: 900px;
  height: 60px;
  left: 606px;
  top: 1358px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
  border: none;
  background: none;
  outline: none;
}
.rewardaddbutton {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 1412px;
  top: 1452px;

  background: #62b878;
  border-radius: 5px;
}
.title {
  position: absolute;
  width: 200px;
  height: 20px;
  left: 16px;
  top: 20px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 32px;
  display: flex;
  align-items: center;

  color: #000000;
}
.contents {
  position: absolute;
  width: 200px;
  height: 120px;
  left: 8px;
  top: 78px;

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
.price {
  position: absolute;
  width: 160px;
  height: 20px;
  left: 16px;
  top: 47px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 14px;
  display: flex;
  align-items: center;

  color: #000000;
}
.reward1 {
  box-sizing: border-box;

  position: absolute;
  width: 216px;
  height: 216px;
  left: 592px;
  top: 1536px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.reward2 {
  box-sizing: border-box;

  position: absolute;
  width: 216px;
  height: 216px;
  left: 840px;
  top: 1536px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}

.reward3 {
  box-sizing: border-box;

  position: absolute;
  width: 216px;
  height: 216px;
  left: 1088px;
  top: 1536px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}

.reward4 {
  box-sizing: border-box;

  position: absolute;
  width: 216px;
  height: 216px;
  left: 1336px;
  top: 1536px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}

/* 기타정보  */
.registrationnumberbox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 1990px;

  background: #d9d9d9;
  border: 0.5px solid #000000;
  border-radius: 5px;
}
.registrationnumber {
  position: absolute;
  width: 900px;
  height: 20px;
  left: 620px;
  top: 2012px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.companynamebox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 2090px;

  background: #d9d9d9;
  border: 0.5px solid #000000;
  border-radius: 5px;
}
.companyname {
  position: absolute;
  width: 460px;
  height: 20px;
  left: 620px;
  top: 2112px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.tagbox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 2190px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.taginput {
  position: absolute;
  width: 870px;
  height: 60px;
  left: 606px;
  top: 2192px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  border: none;
  background: none;
  outline: none;
}

.tempsave {
  position: absolute;
  width: 240px;
  height: 80px;
  left: 1312px;
  top: 2687px;

  background: #a4bdff;
  border-radius: 5px;
}
.submit {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 1116px;
  top: 2950px;

  background: #62b878;
  border-radius: 5px;
}
.revert {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 880px;
  top: 2950px;

  background: #ec4040;
  border-radius: 5px;
}

.calendar {
  box-sizing: border-box;

  position: absolute;
  width: 680px;
  height: 426px;
  left: 812px;
  top: 2514px;

  /* background: url("@/assets/calendar.png"); */
  /* border: 1px solid #000000; */
}
.a {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 380px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.b {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 500px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.c {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 600px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.d {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 700px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.e {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 1058px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.f {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 1178px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.g {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 1278px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.h {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 1378px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.i {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 1892px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.j {
  position: absolute;
  width: 180px;
  height: 20px;
  left: 250px;
  top: 2012px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.k {
  position: absolute;
  width: 180px;
  height: 20px;
  left: 250px;
  top: 2112px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.l {
  position: absolute;
  width: 180px;
  height: 20px;
  left: 250px;
  top: 2212px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.m {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 2404px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.n {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 592px;
  top: 818px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.o {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 1092px;
  top: 818px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.p {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 1422px;
  top: 1468px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
}
.q {
  position: absolute;
  width: 240px;
  height: 32px;
  left: 1313px;
  top: 2538px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 36px;
  text-align: center;

  color: #000000;
}
.r {
  position: absolute;
  width: 240px;
  height: 32px;
  left: 1312px;
  top: 2711px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 36px;
  text-align: center;

  color: #000000;
}
.s {
  position: absolute;
  width: 240px;
  height: 32px;
  left: 1313px;
  top: 2884px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 36px;
  text-align: center;

  color: #000000;
}
.profileimg {
  box-sizing: border-box;

  position: absolute;
  width: 320px;
  height: 320px;
  left: 806px;
  top: 438px;
  /* border-radius: 50%; */
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
</style>
