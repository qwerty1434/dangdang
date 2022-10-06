<template>
  <div>
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
        v-show="deadlinenowPage != 0"
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
        v-show="deadlinenowPage == 0"
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
          v-for="funding in deadlinefundings"
          :key="funding.id"
          style="margin-right: 30px">
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
            <div class="percentage">{{ funding.achieveRate * 100 }}%</div>
            <div class="total">{{ funding.nowPrice }}원</div>
            <div class="remain">{{ funding.remainDays }}일 남음</div>
          </div>
        </div>
      </div>
      <img
        v-if="deadlinenextfundings.length != 0"
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
        v-if="deadlinenextfundings.length == 0"
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

export default {
  name: "DeadLineFundings",

  data() {
    return {
      deadlinenowPage: 0,
      deadlinefundings: [],
      deadlinenextfundings: [],
    };
  },
  created() {
    this.getFundings();
  },
  methods: {
    getFundings() {
      this.checknext(this.deadlinenowPage + 1);
      // const url = "http://localhost:8080/api/funding/search"
      const url = "https://j7a306.p.ssafy.io/api/funding/search";
      axios
        .get(url, {
          params: {
            page: this.deadlinenowPage,
            size: 4,
            sort: "endDate",
            type: "endedAt",
          },
        })
        .then(({ data }) => {
          this.deadlinefundings = data.fundingList;
          console.log(data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    left() {
      this.deadlinenowPage = this.deadlinenowPage - 1;
      if (this.deadlinenowPage < 0) {
        this.deadlinenowPage = 0;
      }
      console.log(this.deadlinenowPage);
      this.getFundings();
    },
    right() {
      this.deadlinenowPage = this.deadlinenowPage + 1;
      if (this.deadlinefundings.length == 4) {
        if (this.deadlinenextfundings.length != 0) {
          this.getFundings();
        } else {
          this.deadlinenowPage = this.deadlinenowPage - 1;
        }
      } else {
        this.deadlinenowPage = this.deadlinenowPage - 1;
      }
    },
    checknext(next) {
      // const url = "http://localhost:8080/api/funding/search"
      const url = "https://j7a306.p.ssafy.io/api/funding/search";
      axios
        .get(url, {
          params: {
            page: next,
            size: 4,
            sort: "endDate",
            type: "endedAt",
          },
        })
        .then(({ data }) => {
          this.deadlinenextfundings = data.fundingList;
          console.log(data);
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.background {
  float: left top;
  width: 1900px;
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
</style>
