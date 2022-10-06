<template>
  <div>
    <div class="modalsize">
      <div style="display: flex; margin-top : 15px; width: 370px; margin: auto; justify-content: space-between;">
        <img
        class="onpoint"
        src="@/assets/내역서.png"
        style="width: 50px; height: 50px; box-sizing: border-box; margin-top: 7px "
        alt=""
      />
      <div class="headline">펀딩참여 내역</div>
      </div>
    <div class="fundinginfomation">
      {{this.fundingTitle}}
    </div>
    <hr class="hrtop">
    <div class="Orders">
      <div class="totalOrders" v-for="(order,idx) in userOrderList" :key="idx">
         <div class="order" v-for="(reward, idx) in order.orderReward" :key="idx">
            <div>{{reward.title}}</div>
            <div>{{reward.totalPrice}}원 / {{reward.count}}개</div>
          </div>  
        <div>주소 : {{order.orderHistory.address}}</div>
        <div>총 금액 : {{order.orderHistory.totalPrice}}원</div>
        <hr class="orderdivHr">
       </div>

    </div>
    <div style="display:flex; justify-content: space-between;margin:auto;" class="price">
      <img
        class="onpoint"
        src="@/assets/금액.png"
        style="width: 40px; height: 40px; box-sizing: border-box; "
        alt=""
      />
    <div class="rewardsum">총 참여 금액 : {{this.orderprice}} 원</div>

    </div>
    
     <button class="goback"  @click="$emit('close-modal')">뒤로가기</button>

    </div>
  
  </div>
 
</template>

<script>
import axios from "axios";
import {mapState} from 'vuex';
export default {
  name: "MyOrder",
  props: {
    orderprice: Number,
    userOrderList: Array,
    fundingTitle : String,
  },
  computed:{
    ...mapState(
      ["user", "Authorization"]
    )

  },
  // created(){
  //   this.getOrderInfo()
  // },
  methods : {
    goback(){

    }
  }
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
  text-align : center;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 41px;

  color: #000000;
}
.hrtop{
  border : 0px;
  border-top: 5px solid #62b878;
  width: 90%;
}
.orderdivHr{
  border : 0px;
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
.totalOrders{
  width: 400px;
  font-size: 30px;
  margin: auto;
}
.goback {
  position: absolute;
  width: 140px;
  height: 52px;
  left: 250px;
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

  cursor: pointer;

  color: #ffffff;
}
.price{
  position: absolute;
  top: 670px;
  width: 400px;
  align-items: center;
  left: 120px;
  
}
</style>
