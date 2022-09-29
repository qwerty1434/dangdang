<template>
  <div>
    <div class="fundings" style="display:flex; justify-content: space-between; width: 1500px; margin:auto;">
         <img
         @click="left()"
        class="onpoint"
        src="@/assets/left.png"
        style="width: 20px; height: 20px; box-sizing: border-box; margin-top : 270px"
        alt=""
      />

    <div id="fundingList">
      
      <div v-for="funding in fundings" :key="funding.id" >
      <div class="thumbnail">
      <img
        :src="funding.img"
        style="width: 300px; height: 400px; box-sizing: border-box"
        alt=""
      />

    </div>
    <div class="title" style="margin-top:5px">{{funding.title}}</div>
    <div style="display:flex; justify-content: space-between; width: 300px; margin-top:5px">
      <div class="category">{{funding.category}}</div>
      <div class="makername" >{{funding.companyName}}</div>
    </div>
    <progress
      id="progress"
      :value="funding.achieveRate*100"
      min="0"
      max="100"
      class="progressbar"
      style="margin-top : 5px"
    ></progress>
    <div style="display:flex; justify-content: space-between; margin-top:5px">
    <div class="percentage">{{funding.achieveRate*100}}%</div>
    <div class="total">{{funding.nowPrice}}원(코인)</div>
    <div class="remain">{{funding.remainDays}}일 남음</div>
    </div>
    </div>

    </div>
    <img
        class="onpoint"
        src="@/assets/right.png"
        style="width: 20px; height: 20px; box-sizing: border-box; margin-top : 270px"
        alt=""
        @click="right()"
      />

    </div>
 
    

    <div class="background"></div>
  </div>
</template>

<script>
import axios from "axios";
import {mapState} from 'vuex';


export default {
    name: "OnFundings",
  
  data() {
    return {
      image: "",
      nowPage: 0,
      fundings: [],
      state: 1,
      nextfundings: [],
    };
  },
  computed:{
    ...mapState(
      ["user", "Authorization"]
    )

  },
  created(){
    this.getFundings();
  },
  methods: {
    getFundings(){
      const url = "http://localhost:8080/api/user/funding-list"
      axios.get(url, {
        params: {
          state : this.state,
          page : this.nowPage,
          size : 4
        },headers: {
        // 토큰도 state에서 user 정보 가져와서 쓰도록 수정해야함
        Authorization: 'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MzBiOTI5Zi1iYzJhLTQ5ZmEtYjM1OC0yZjg3NmRhZTZhZDgiLCJpYXQiOjE2NjQ0MTIzMTEsImV4cCI6MTY2NDQ5ODcxMX0.plOeTBDm_XFyHw-1U2qlRfoaY07VtBaZScqDlkZ3ktM'
      },
    })
      .then(({data}) => {
        this.fundings = data;
        console.log(data)
      }).catch((err)=> {
      
        console.log(err)
        
      })
    },
    left(){
      this.nowPage = this.nowPage -1;
      if(this.nowPage <0){
        this.nowPage = 0;
      }
      console.log(this.nowPage);
      this.getFundings()
    },
    right(){
      this.nowPage = this.nowPage + 1;
      console.log(this.nowPage)
      if(this.fundings.length == 4){
        this.checknext()
        if(this.nextfundings.length != 0){
            this.getFundings()
        }else{
          this.nowPage = this.nowPage-1;
        }
        
      }else{
        this.nowPage = this.nowPage - 1;
      }
    },
    checknext(){
      const url = "http://localhost:8080/api/user/funding-list"
      axios.get(url, {
        params: {
          state : this.state,
          page : this.nowPage,
          size : 4
        },headers: {
        // 토큰도 state에서 user 정보 가져와서 쓰도록 수정해야함
        Authorization: 'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MzBiOTI5Zi1iYzJhLTQ5ZmEtYjM1OC0yZjg3NmRhZTZhZDgiLCJpYXQiOjE2NjQ0MTIzMTEsImV4cCI6MTY2NDQ5ODcxMX0.plOeTBDm_XFyHw-1U2qlRfoaY07VtBaZScqDlkZ3ktM'
      },
    })
      .then(({data}) => {
        this.nextfundings = data;
        console.log(data)
      }).catch((err)=> {
      
        console.log(err)
        
      })

    }
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
.fundinghistory{
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
#fundingList{
  display: flex ;
  flex-flow: wrap;
  justify-content: space-between;
  /* gap: 10px 1%; */
  width: 1320px;

  margin-top: 70px;

}
.onpoint{
  cursor: pointer;
}
</style>
