<template>
  <div>
    <div class="modalsize">
      <div
        style="
          display: flex;
          margin-top: 15px;
          width: 370px;
          margin: auto;
          justify-content: space-between;
        "
      >
        <img
          class="onpoint"
          src="@/assets/내역서.png"
          style="
            width: 50px;
            height: 50px;
            box-sizing: border-box;
            margin-top: 7px;
          "
          alt=""
        />
        <div class="headline">메이커 등록</div>
      </div>

      <div>
        <div class="registrationbox">
          <input
            type="text"
            class="registrationinput"
            placeholder="법인명"
            v-model="companyName"
          />
        </div>
        <div class="registrationbox">
          <input
            type="text"
            class="registrationinput"
            placeholder="사업자 등록번호"
            v-model="companyNo"
          />
        </div>
        <div class="registrationbox">
          <input
            type="text"
            class="registrationinput"
            placeholder="대표자 이름"
            v-model="managerName"
          />
        </div>
        <div class="registrationbox">
          <input
            type="text"
            class="registrationinput"
            placeholder="개업일(YYYYMMDD)"
            v-model="openDay"
          />
        </div>
      </div>

      <button class="submit" @click="getApi()">등록하기</button>
      <button class="goback" @click="back()">취소</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
const serverUrl = "j7a306.p.ssafy.io/api";
export default {
  name: "business order",
  props: {
    companyName: String,
    companyNo: String,
    managerName: String,
    openDay: Date,
  },
  computed: {
    ...mapState(["user", "Authorization"]),
  },
  methods: {
    getApi() {
      const result = {
        companyName: this.companyName,
        companyNo: this.companyNo,
        managerName: this.managerName,
        openDay: this.openDay,
      };
      console.log(result);
      var headers = { Authorization: this.$store.state.Authorization };
      axios
        .post("https://" + serverUrl + "/maker/join", result, {
          headers: headers,
        })
        .then((response) => {
          if (!response.data) {
            alert("올바른 값을 입력해 주세요");
          } else {
            this.$router.go(-1);
          }
        })
        .catch(() => {
          alert("올바른 값을 입력해 주세요");
        });
    },
    back() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.modalsize {
  /* Frame 10 */

  width: 600px;
  height: 800px;

  background: #ffffff;
}
.headline {
  width: 300px;
  height: 64px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 600;
  font-size: 50px;
  line-height: 73px;

  color: #000000;
}
.fundinginfomation {
  /* 펀딩 제목 */
  position: relative;
  width: 400px;
  height: 82px;
  left: 0px;
  top: 20px;
  margin: auto;
  text-align: center;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 41px;

  color: #000000;
}
.hrtop {
  border: 0px;
  border-top: 5px solid #62b878;
  width: 90%;
}
.orderdivHr {
  border: 0px;
  border-top: 3px dashed #62b878;
}

.rewardsum {
  /* 총 참여 금액 : {nnnnn} 원 */
  width: 400px;
  height: 36px;
  left: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 41px;
  text-align: center;

  color: #000000;
}
.totalOrders {
  width: 400px;
  font-size: 30px;
  margin: auto;
}
.goback {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 125px;
  top: 740px;

  background: #ec4040;
  border-radius: 5px;
  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;

  color: #ffffff;
}
.submit {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 350px;
  top: 740px;

  background: #62b878;
  border-radius: 5px;
  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;

  color: #ffffff;
}
.price {
  position: absolute;
  top: 670px;
  width: 400px;
  align-items: center;
  left: 120px;
}
.registrationinput {
  /* position: absolute; */
  width: 400px;
  height: 60px;
  /* left: 760px;
  top: 452px; */

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

.registrationbox {
  margin: auto;
  margin-top: 50px;
  box-sizing: border-box;
  /* position: absolute; */
  width: 420px;
  height: 64px;

  /* left: 750px;
  top: 450px; */

  border: 0.5px solid #000000;
  border-radius: 5px;
}
</style>
