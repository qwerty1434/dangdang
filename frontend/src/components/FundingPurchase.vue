<template>
  <div>
    <div class="headline"></div>

    <!-- component랑 div 동시 for문 가능한지 확인 -->
    <RewardBox
      :class="{
        reward1: i == 0,
        reward2: i == 1,
        reward3: i == 2,
        reward4: i == 3,
      }"
      v-for="(item, i) in fundingDetail.rewards"
      :reward="fundingDetail.rewards[i]"
      :key="i"
    />
    <div
      :class="{
        reward1: i == 0,
        reward2: i == 1,
        reward3: i == 2,
        reward4: i == 3,
      }"
      v-for="(item, i) in fundingDetail.rewards"
      :reward="fundingDetail.rewards[i]"
      :key="i"
    >
      <div class="productnumbertext">수량:</div>
      <input class="productnumber" type="number" v-model="orderItem[i]" />
    </div>

    <!-- <div class="이거는 컴퍼넌트 들어가는 자리">
      <div class="productnumbertext">수량:</div>
      <div class="productnumber"></div>
    </div> -->

    <div class="orderinfomationtext">주문 정보</div>
    <div class="addresstext">주소</div>
    <div class="addressfindtext">주소 찾기</div>
    <div class="addressdetailtext">상세 주소</div>
    <div class="contacttext">연락처</div>
    <div class="emailtext">이메일</div>
    <div class="anonymoustext">익명</div>
    <!-- <div class="ordertext">주문하기</div> -->
    <div class="addressbox"></div>
    <button @click="showApi()" class="addressfindbox">주소 찾기</button>

    <div class="addressdetailbox"></div>
    <div class="contactbox"></div>
    <div class="emailbox"></div>

    <button @click="orderFunding()" class="orderbox">주문하기</button>
    <div>
      <input
        type="text"
        v-model="address"
        placeholder="주소 찾기를 통해 찾아주세요"
        readonly="readonly"
        class="addressinput"
      />
    </div>
    <div>
      <input
        type="text"
        v-model="detailAddress"
        placeholder="상세 주소를 입력해주세요."
        class="addressdetailinput"
      />
    </div>
    <div>
      <input
        type="text"
        v-model="phoneNum"
        placeholder="연락이 가능한 휴대전화번호를 남겨주세요"
        class="contactinput"
      />
    </div>
    <div>
      <input
        type="text"
        v-model="email"
        placeholder="이메일을 통해 주문처리과정을 보내드립니다."
        class="emailinput"
      />
    </div>
    <div>
      <input type="checkbox" v-model="anonymous" class="anonymousinput" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import RewardBox from "../components/RewardBox.vue";
import { mapState } from "vuex";

export default {
  components: {
    RewardBox: RewardBox,
  },

  data() {
    return {
      orderItem: [0, 0, 0, 0],
      address: "",
      detailAddress: "",
      phoneNum: "",
      email: "",
      anonymous: "",
      zip: "",
      fundingId: "",

      // staticUrl: "http://localhost:8080",
      staticUrl: "https://j7a306.p.ssafy.io",
    };
  },
  computed: {
    ...mapState(["fundingDetail", "supporters"]),
  },
  methods: {
    getRewards: function () {
      var rewards = [];
      for (var i = 0; i < this.fundingDetail.rewards.length; i++) {
        var object = {
          rewardId: this.fundingDetail.rewards[i].id,
          count: this.orderItem[i],
        };
        rewards.push(object);
      }

      return rewards;
    },

    getTotalPrice: function () {
      var totalPrice = 0;
      for (var i = 0; i < this.fundingDetail.rewards.length; i++) {
        totalPrice +=
          Number(this.fundingDetail.rewards[i].price) *
          Number(this.orderItem[i]);
      }
      return totalPrice;
    },

    orderFunding: function () {
      var headers = { Authorization: this.$store.state.Authorization };
      const url = this.staticUrl + "/api/funding/order";

      if (this.getTotalPrice() == 0) {
        alert("물품을 선택하지 않았습니다.");
        return;
      }

      axios
        .post(
          url,
          {
            fundingId: this.fundingId,
            totalPrice: this.getTotalPrice(),
            address: this.address + " " + this.detailAddress,
            phoneNumber: this.phoneNum,
            rewards: this.getRewards(),
            anonymous: this.anonymous,
          },
          { headers: headers }
        )
        .then(({ data }) => {
          if (data) {
            alert("펀딩 구매가 완료되었습니다.");
            this.$router.go(-1);
          }
        });
    },

    showApi() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
          let extraRoadAddr = ""; // 도로명 조합형 주소 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== "" && data.apartment === "Y") {
            extraRoadAddr +=
              extraRoadAddr !== ""
                ? ", " + data.buildingName
                : data.buildingName;
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraRoadAddr !== "") {
            extraRoadAddr = " (" + extraRoadAddr + ")";
          }
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          if (fullRoadAddr !== "") {
            fullRoadAddr += extraRoadAddr;
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          this.zip = data.zonecode; //5자리 새우편번호 사용
          this.address = fullRoadAddr;
        },
      }).open();
    },
  },

  created() {
    this.fundingId = this.$route.query.id;
  },
};
</script>

<style scoped>
.reward1 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: -50px;
  top: 10px;
}
.reward2 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: 176px;
  top: 10px;
}
.reward3 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: 402px;
  top: 10px;
}
.reward4 {
  position: absolute;
  width: 216px;
  height: 216px;
  left: 628px;
  top: 10px;
}

.headline {
  /* 펀딩참여 */

  position: absolute;
  width: 860px;
  height: 64px;
  left: 0px;
  top: 0px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 64px;
  line-height: 73px;

  color: #000000;
}
.productnumber {
  /* {nn} */

  position: absolute;
  width: 120px;
  height: 20px;
  left: 56px;
  top: 232px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #000000;
}
.productnumbertext {
  /* 수량 : */

  position: absolute;
  width: 60px;
  height: 20px;
  left: 0px;
  top: 232px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.orderinfomationtext {
  /* 주문 정보 */

  position: absolute;
  width: 180px;
  height: 20px;
  left: 378px;
  top: 490px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: center;

  color: #000000;
}
.addresstext {
  /* 주소 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 592px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.addressdetailtext {
  /* 상세 주소 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 692px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.contacttext {
  /* 연락처 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 792px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.emailtext {
  /* 이메일 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 892px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.anonymoustext {
  /* 이메일 */

  position: absolute;
  width: 100px;
  height: 20px;
  left: 70px;
  top: 992px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: right;

  color: #000000;
}
.addressfindtext {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 736px;
  top: 592px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #62b878;
}
.addressbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 570px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.addressfindbox {
  box-sizing: border-box;

  position: absolute;
  width: 140px;
  height: 62px;
  left: 726px;
  top: 570px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  color: #62b878;

  background: #ffffff;
  border: 0.5px solid #62b878;
  border-radius: 5px;

  outline: 0;
}
.addressdetailbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 670px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.contactbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 770px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.emailbox {
  box-sizing: border-box;

  position: absolute;
  width: 460px;
  height: 64px;
  left: 230px;
  top: 870px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.orderbox {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 398px;
  top: 1070px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;

  color: #ffffff;

  background: #62b878;
  border-radius: 5px;
  border: 0;
  outline: 0;
}
.addressinput {
  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 572px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;

  border: none;
  background: none;
  outline: none;
}
.addressdetailinput {
  /* 상세 주소를 입력해주세요. */

  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 672px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;

  border: none;
  background: none;
  outline: none;
}
.contactinput {
  /* 연락이 가능한 휴대전화번호를 남겨주세요 */

  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 772px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;

  border: none;
  background: none;
  outline: none;
}
.emailinput {
  /* 이메일을 통해 주문처리과정을 보내드립니다. */

  position: absolute;
  width: 430px;
  height: 60px;
  left: 240px;
  top: 872px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;

  border: none;
  background: none;
  outline: none;
}

.anonymousinput {
  position: absolute;
  width: 430px;
  height: 30px;
  left: 30px;
  top: 982px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  display: flex;
  align-items: center;

  color: #a8a8a8;
}
</style>
