<template>
  <div style="width: 100%;">
    <v-row>
      <v-col cols="2" style="padding-right: 0; padding-bottom: 0;">
        <v-sheet
          class="box"
        >
          <span>MY유레카</span>
        </v-sheet>
      </v-col>
      <v-col cols="10" style="padding-left: 0; padding-bottom: 0;">
        <div style="height: 150px;">
          <v-row>
            <v-col cols="3" style="padding-bottom: 0; padding-right: 0;">
              <div style="" class="box2">
                <p>결제완료</p>
                <p>{{prepareCnt + deliveryCnt + completeCnt}}개</p>
              </div>
            </v-col>
            <v-col cols="3" style="padding-bottom: 0; padding-left: 0; padding-right: 0;">
              <div style="" class="box2">
                <p>상품준비중</p>
                <p>{{prepareCnt}}개</p>
              </div>
            </v-col>
            <v-col cols="3" style="padding-bottom: 0; padding-left: 0; padding-right: 0;">
              <div style="" class="box2">
                <p>배송시작</p>
                <p>{{deliveryCnt}}개</p>
              </div>
            </v-col>
            <v-col cols="3" style="padding-bottom: 0; padding-left: 0; padding-right: 0;">
              <div style="" class="box2">
                <p>배송완료</p>
                <p>{{completeCnt}}개</p>
              </div>
            </v-col>
          </v-row>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  name: 'MypageAppBar',
  data() {
    return {
      prepareCnt: 0,
      deliveryCnt: 0,
      completeCnt: 0,
    }
  },
  created() {
    axios.get(`http://i4d106.p.ssafy.io:8084/order/userid/${this.userId}/status/0`)
      .then(res => {
        this.prepareCnt = res.data.data.length
      })
    axios.get(`http://i4d106.p.ssafy.io:8084/order/userid/${this.userId}/status/1`)
      .then(res => {
        this.deliveryCnt = res.data.data.length
      })
    axios.get(`http://i4d106.p.ssafy.io:8084/order/userid/${this.userId}/status/2`)
      .then(res => {
        this.completeCnt = res.data.data.length
      })
  },
  computed: {
    ...mapState([
      'userId',
    ])
  }
}
</script>

<style scoped>
  .box {
    display: flex; 
    justify-content:center; 
    align-items: center; 
    flex-direction: column;
    background-color: #286DB4 !important;
    font-size: 2rem;;
    color: white !important;
    height: 150px;
  }

  .box2 {
    display: flex; 
    justify-content:center; 
    align-items: center; 
    flex-direction: column;
    background-color: rgb(130, 188, 226);
    height: 150px;
    border-right: 1px solid rgb(155, 201, 232);
    border-left: 1px solid rgb(120, 173, 208);
    
    
  }

  p {
    font-size: 1.25rem;
    font-weight: bold;
    text-shadow: rgb(0 0 0 / 20%) 0px 1px 0px;
    color: white;
    margin-top: 10px;
    margin-bottom: 0px;
  }
</style>