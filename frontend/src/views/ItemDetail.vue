<template>
  <div>
    <div v-if="isLogin">
      <!-- 아래 face app -->
      <link
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
        rel="stylesheet"
        integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous"
      />
    </div>
    <!-- 위 face app -->

    <!-- 여기부터 ItemDetial -->
    <TopInfo />
    <OtherItems />
    <ProductDetail />
    <Guidance />
    <Reviews v-if="Object.keys(item).length" :item="item" />

    <balloon :title="mood" position="bottom-right" :zooming="true">
      <!-- balloon content goes here.. for example a youtube video with the vue-youtube-embed plugin -->
      <template slot="header">Custom Header</template>
      <RwvCamera @pictureTaken="setImage($event)" />
    </balloon>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
// import { mapActions } from 'vuex'
import axios from 'axios';
import TopInfo from '@/components/itemdetail/TopInfo.vue';
import OtherItems from '@/components/itemdetail/OtherItems.vue';
import ProductDetail from '@/components/itemdetail/ProductDetail.vue';
import Reviews from '@/components/itemdetail/Reviews.vue';
// import ProductInquiry from '@/components/itemdetail/ProductInquiry.vue'
//
// --------- 아래 face app ---------------------------
import Guidance from '@/components/itemdetail/Guidance.vue';
import RwvCamera from '@/components/TheCamera.vue';
import { Balloon } from 'vue-balloon';
import * as tf from '@tensorflow/tfjs';
import * as faceapi from 'face-api.js';
const params = {
  minConfidence: 0.5,
};
// --------- 위 face app ---------------------------
export default {
  name: 'ItemDetail',
  components: {
    TopInfo,
    OtherItems,
    ProductDetail,
    Reviews,
    // ProductInquiry,
    Guidance,
    RwvCamera,
    Balloon,
  },
  data: () => ({
    productId: '',
    item: {},
    sFileIds: [],
    mFileIds: [],
    sFiles: [],
    mFiles: [],
    storeId: '',
    // ----------- 아래 face app ----------------------
    mood: '로딩중...',
    class: null,
    neutral: 0.0,
    happy: 0.0,
    sad: 0.0,
    angry: 0.0,
    fearful: 0.0,
    disgusted: 0.0,
    surprised: 0.0,
    faceModel: null,
    emotionModel: null,
    image: null,
    show: false,
    timer: 0,
    faceCreatedAt: '',
  }),
  mounted() {
    this.init(this.getEmotion);
  },
  beforeDestroy() {
    this.stopAnalysis();
    console.log(this.timer);
  },
  // ----------- 위 face app ----------------------
  //
  //
  //
  methods: {
    ...mapActions('mainStore', ['FETCH_DETAIL_PRODUCT']),
    getItem() {
      this.FETCH_DETAIL_PRODUCT(this.productId);
    },

    // ----------------------- 아래 face app---------------------------------
    async init(callback) {
      var self = this;
      // load the face detection api & emotion detection model
      await faceapi.loadSsdMobilenetv1Model('/models/features/');
      await faceapi.loadFaceLandmarkModel('/models/features');
      await faceapi.loadFaceExpressionModel('/models/features');
      this.emotionModel = await tf.loadLayersModel('/models/emotion/model.json');
      callback();
    },
    setLoading() {
      this.show = true;
    },
    async getEmotion() {
      var self = this;
      console.log('칠드런');
      console.log(this);
      console.log(this.$children[0]);
      console.log(this.$children[0].$children[0]);
      const image = this.$children[5].$children[0].webcam.webcamElement;
      this.faceCreatedAt = new Date();
      this.polling = setInterval(async () => {
        const userExpression = await faceapi
          .detectSingleFace(image)
          .withFaceLandmarks()
          .withFaceExpressions();
        if (typeof userExpression === 'undefined') {
          this.show = false;
        } else {
          this.show = false;
          var expression = Object.keys(userExpression.expressions).reduce(function(a, b) {
            return userExpression.expressions[a] > userExpression.expressions[b] ? a : b;
          });
        }

        if (expression == 'neutral') expression = '😃 중립';
        else if (expression == 'happy') expression = '😁 행복';
        else if (expression == 'sad') expression = '😭 슬픔';
        else if (expression == 'angry') expression = '😡 분노';
        else if (expression == 'fearful') expression = '😱 두려움';
        else if (expression == 'disgusted') expression = '😵 역겨움';
        else expression = '😲 놀람';

        this.setMood(expression);
        this.timer += 0.1;
        this.neutral += userExpression.expressions['neutral'];
        this.happy += userExpression.expressions['happy'];
        this.sad += userExpression.expressions['sad'];
        this.angry += userExpression.expressions['angry'];
        this.fearful += userExpression.expressions['fearful'];
        this.disgusted += userExpression.expressions['disgusted'];
        this.surprised += userExpression.expressions['surprised'];
        if (this.timer >= 60) this.stopAnalysis();
      }, 100);
    },
    setMood(mood) {
      var self = this;
      this.mood = mood;
    },
    setImage(image) {
      console.log('picture taken');
      var self = this;
      this.image = image;
    },
    sendData: function() {
      axios
        .post('http://k4d104.p.ssafy.io:8088/face/add', {
          product: this.productId,
          user: this.userData.userId,
          happy: this.happy.toFixed(2),
          neutral: this.neutral.toFixed(2),
          sad: this.sad.toFixed(2),
          angry: this.angry.toFixed(2),
          fearful: this.fearful.toFixed(2),
          disgusted: this.disgusted.toFixed(2),
          surprised: this.surprised.toFixed(2),
          time: Math.floor(this.timer),
          createdAt: new Date(),
        })
        .then((response) => {
          console.log(response);
        })
        .catch((ex) => {
          console.warn('ERROR!!!!!!!!!!! : ', ex);
        });
    },
    stopAnalysis() {
      clearInterval(this.polling);
      this.sendData();
      this.mood = '감정분석 종료';
      this.timer = 9999;
    },
  },
  created() {
    this.productId = this.$route.params.productid; // 시작하면서 라우터에서 아이디 뽑아오고
    this.getItem();
  },
  computed: {
    ...mapState('accountStore', ['isLogin', 'userData']),
  },
  beforeDestroy() {
    if (this.timer >= 5 && this.timer <= 60) this.stopAnalysis();
  },
};
</script>

<style scoped>
/* face app css */
RwvRecommendations {
  max-height: 720px;
  overflow-y: scroll;
  border: black;
}

#modal {
  opacity: 0.5 !important;
}
</style>
