<template>
  <div>
    <div class="highlight"></div>
    <div
      class="fundings"
      style="
        display: flex;
        justify-content: space-between;
        width: 1500px;
        margin: auto;
      ">
      <img
        @click="left()"
        v-show="nowPage != 0"
        class="onpoint"
        src="@/assets/left.png"
        style="
          width: 20px;
          height: 20px;
          box-sizing: border-box;
          margin-top: 270px;
        "
        alt="" />
      <img
        @click="left()"
        v-show="nowPage == 0"
        class="onpoint"
        src="@/assets/left.png"
        style="
          width: 20px;
          height: 20px;
          box-sizing: border-box;
          margin-top: 270px;
          visibility: hidden;
        "
        alt="" />
      <div id="fundingList">
        <div
          v-for="funding in fundings"
          :key="funding.id"
          style="margin-right: 30px">
          <img
            @click="checkOrder(funding.id, funding.title)"
            class="cart"
            src="@/assets/장바구니.png"
            style="width: 30px; height: 30px; box-sizing: border-box"
            alt="" />
          <modal-view v-if="isModalViewed" @close-modal="isModalViewed = false">
            <my-order
              :orderprice="this.orderprice"
              :fundingTitle="this.orderfundingTitle"
              :userOrderList="this.userOrderList"
              @close-modal="isModalViewed = false"></my-order>
          </modal-view>
          <div class="thumbnail">
            <router-link
              :to="{ path: '/product/story', query: { id: funding.id } }">
              <img
                :src="funding.img"
                style="width: 300px; height: 400px; box-sizing: border-box"
                alt="" />
            </router-link>
          </div>
          <div class="title" style="margin-top: 5px">{{ funding.title }}</div>
          <div
            style="
              display: flex;
              justify-content: space-between;
              width: 300px;
              margin-top: 5px;
            ">
            <div class="category">{{ funding.category }}</div>
            <div class="makername">{{ funding.companyName }}</div>
          </div>
          <progress
            id="progress"
            :value="funding.achieveRate"
            min="0"
            max="100"
            class="progressbar"
            style="margin-top: 5px"></progress>
          <div
            style="
              display: flex;
              justify-content: space-between;
              margin-top: 5px;
            ">
            <div class="percentage">{{ funding.achieveRate }}%</div>
            <div class="total">{{ funding.nowPrice }}원</div>
            <div class="remain">{{ funding.remainDays }}일 남음</div>
          </div>
        </div>
      </div>
      <img
        v-if="nextfundings.length != 0"
        class="onpoint"
        src="@/assets/right.png"
        style="
          width: 20px;
          height: 20px;
          box-sizing: border-box;
          margin-top: 270px;
        "
        alt=""
        @click="right()" />
      <img
        v-if="nextfundings.length == 0"
        class="onpoint"
        src="@/assets/right.png"
        style="
          width: 20px;
          height: 20px;
          box-sizing: border-box;
          margin-top: 270px;
          visibility: hidden;
        "
        alt=""
        @click="right()" />
    </div>

    <div class="background"></div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import MyOrder from "@/components/MyOrder.vue";
import ModalView from "./ModalView.vue";

export default {
  name: "UserJoinOnFundings",

  data() {
    return {
      image: "",
      nowPage: 0,
      fundings: [],
      state: 1,
      nextfundings: [],
      isModalViewed: false,
      orderprice: 0,
      userOrderList: [],
      orderfundingTitle: "",
    };
  },
  components: {
    MyOrder,
    ModalView,
  },
  computed: {
    ...mapState(["user", "Authorization"]),
  },
  created() {
    this.getFundings();
  },
  methods: {
    getFundings() {
      this.checknext(this.nowPage + 1);
      console.log(this.Authorization + "토큰확인");
      // const url = "http://localhost:8080/api/user/funding-list"
      const url = "https://j7a306.p.ssafy.io/api/user/funding-list";
      axios
        .get(url, {
          params: {
            state: this.state,
            page: this.nowPage,
            size: 4,
          },
          headers: {
            Authorization: this.Authorization,
          },
        })
        .then(({ data }) => {
          this.fundings = data;
          console.log(data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    left() {
      this.nowPage = this.nowPage - 1;
      if (this.nowPage < 0) {
        this.nowPage = 0;
      }
      console.log(this.nowPage);
      this.getFundings();
    },
    right() {
      this.nowPage = this.nowPage + 1;
      console.log(this.nowPage);
      if (this.fundings.length == 4) {
        if (this.nextfundings.length != 0) {
          this.getFundings();
        } else {
          this.nowPage = this.nowPage - 1;
        }
      } else {
        this.nowPage = this.nowPage - 1;
      }
    },
    checknext(next) {
      // const url = "http://localhost:8080/api/user/funding-list"
      const url = "https://j7a306.p.ssafy.io/api/user/funding-list";
      axios
        .get(url, {
          params: {
            state: this.state,
            page: next,
            size: 4,
          },
          headers: {
            Authorization: this.Authorization,
          },
        })
        .then(({ data }) => {
          this.nextfundings = data;
          console.log(data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    checkOrder(fundingId, fundingTitle) {
      this.isModalViewed = true;
      console.log(fundingId);
      // const url = "http://localhost:8080/api/funding/user/order"
      const url = "https://j7a306.p.ssafy.io/api/funding/user/order";
      axios
        .get(url, {
          params: {
            fundingId: fundingId,
          },
          headers: {
            Authorization: this.Authorization,
          },
        })
        .then(({ data }) => {
          console.log(data);
          (this.orderprice = data.orderTotalPrice),
            console.log(this.orderprice + "총주문금액");
          this.userOrderList = data.userOrderList;
          this.orderfundingTitle = fundingTitle;
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
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

  background: none;
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
#progress::-webkit-progress-bar {
  background: #f7f7f7;
}
#progress::-webkit-progress-value {
  background: #62b878;
}
.progressbar {
  width: 300px;
  height: 20px;
  left: 0px;
  top: 458px;

  background: #62b878;
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
  /* justify-content: space-between; */
  /* gap: 10px 1%; */
  width: 1320px;

  margin-top: 70px;
}
.onpoint {
  cursor: pointer;
}
.cart {
  cursor: pointer;
  position: absolute;
  margin-top: 20px;
  margin-left: 250px;
}
.highlight {
  z-index: -3;
  position: absolute;
  width: 200px;
  height: 28px;
  left: 450px;
  top: 1100px;

  background: rgba(98, 184, 120, 0.5);
}
</style>
