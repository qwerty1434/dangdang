<template>
  <div>
    <div class="placeholder"></div>
    <div>
      <div class="mainTitle" v-if="this.id == 'endedAt'">
        마감임박순 펀딩리스트
      </div>
      <div class="mainTitle" v-if="this.id == 'popular'">인기순 펀딩리스트</div>
      <div class="mainTitle" v-if="this.id == 'new'">신규상품순 펀딩리스트</div>
      <div class="mainTitle" v-if="this.id == 'plan'">펀딩예정 리스트</div>
      <div class="mainTitle" v-if="this.category">{{ this.id }} 펀딩리스트</div>
      <div class="mainTitle" v-if="this.keyword != undefined">
        {{ this.keyword }} 관련 펀딩리스트
      </div>
      <div
        id="fundingList"
        style="width: 1320px; margin: auto; margin-top: 260px">
        <div
          v-for="funding in fundingList"
          :key="funding.id"
          style="margin-right: 30px; margin-top: 30px">
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
            <div class="total" v-if="this.id != 'plan'">
              {{ funding.nowPrice }}원
            </div>
            <div class="startDay" v-if="this.id == 'plan'">
              시작일 : {{ funding.startDate }}
            </div>
            <div class="remain" v-if="this.id != 'plan'">
              {{ funding.remainDays }}일 남음
            </div>
          </div>
        </div>
      </div>
      <div class="numposition">
        <div
          class="pageNum"
          style="
            display: flex;
            justify-content: space-between;
            width: 200px;
            margin: auto;
            margin-top: 70px;
          ">
          <div @click="change(0)">1</div>
          <div @click="change(1)">2</div>
          <div @click="change(2)">3</div>
          <div @click="change(3)">4</div>
          <div @click="change(4)">5</div>
        </div>
      </div>
      <div class="background"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  name: "FundingView",
  data() {
    return {
      image: "",
      nowPage: 0,
      fundingList: [],
      nextfundings: [],
      state: 0,
      size: 12,
      id: "",
      category: false,
      keyword: "",
    };
  },
  computed: {
    ...mapState(["searchFundings"]),
  },
  mounted() {
    window.scrollTo(0, 0);
  },
  created() {
    this.id = this.$route.query.id;
    this.keyword = this.$route.query.search;
    console.log(this.id);
    if (
      this.id != "endedAt" &&
      this.id != "new" &&
      this.id != "popular" &&
      this.id != "plan" &&
      this.id != undefined
    ) {
      this.category = true;
    }
    console.log(this.keyword + " 키워드");
    if (this.keyword == undefined) {
      this.getFundings();
    } else {
      this.fundingList = this.searchFundings;
    }
  },
  methods: {
    getFundings() {
      console.log(this.id);
      // const dayurl = "http://localhost:8080/api/funding/search"
      // const categoryurl = "http://localhost:8080/api/funding/search/category"
      const dayurl = "https://j7a306.p.ssafy.io/api/funding/search";
      const categoryurl =
        "https://j7a306.p.ssafy.io/api/funding/search/category";
      if (this.id == "endedAt") {
        axios
          .get(dayurl, {
            params: {
              page: this.nowPage,
              size: this.size,
              type: this.id,
              sort: "endDate",
            },
          })
          .then(({ data }) => {
            this.fundingList = data.fundingList;
            console.log(data);
          })
          .catch(err => {
            console.log(err);
          });
      } else if (this.id == "new") {
        axios
          .get(dayurl, {
            params: {
              page: this.nowPage,
              size: this.size,
              type: this.id,
              sort: "startDate,Desc",
            },
          })
          .then(({ data }) => {
            this.fundingList = data.fundingList;
            console.log(data);
          })
          .catch(err => {
            console.log(err);
          });
      } else if (this.id == "popular") {
        axios
          .get(dayurl, {
            params: {
              page: this.nowPage,
              size: this.size,
              type: this.id,
            },
          })
          .then(({ data }) => {
            this.fundingList = data.fundingList;
            console.log(data);
          })
          .catch(err => {
            console.log(err);
          });
      } else if (this.id == "plan") {
        axios
          .get(dayurl, {
            params: {
              page: this.nowPage,
              size: this.size,
              type: this.id,
              sort: "startDate",
            },
          })
          .then(({ data }) => {
            this.fundingList = data.fundingList;
            console.log(data);
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        axios
          .get(categoryurl, {
            params: {
              page: this.nowPage,
              size: this.size,
              category: this.id,
              sort: "startDate",
            },
          })
          .then(({ data }) => {
            this.fundingList = data.fundingList;
            console.log(data);
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    change(page) {
      this.nowPage = page;
      this.getFundings();
    },
  },
};
</script>

<style scoped>
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
.pageNum {
  cursor: pointer;
  font-family: "NanumSquare";
  font-style: normal;
  font-size: 30px;
  font-weight: 600;
}
.mainTitle {
  position: relative;
  font-family: "NanumSquare";
  font-style: normal;
  font-size: 60px;
  font-weight: 600;
  margin: auto;
  width: 700px;
  top: 260px;
  text-align: center;
}
.numposition {
  position: absolute;
  top: 1900px;
  left: 840px;
}
.startDay {
  width: 200px;
  height: 14px;
  left: 60px;
  top: 484px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;
  text-align: right;

  color: #000000;
}
</style>
