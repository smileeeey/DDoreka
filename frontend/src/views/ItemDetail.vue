<template>
  <div>
    <!-- 아래 face app -->
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
      crossorigin="anonymous"
    />
    <balloon :title="mood" position="bottom-right" :zooming="true">
      <!-- balloon content goes here.. for example a youtube video with the vue-youtube-embed plugin -->
      <template slot="header">Custom Header</template>
      <RwvCamera @pictureTaken="setImage($event)" />
    </balloon>
    <!-- 위 face app -->
    
    <!-- 여기부터 ItemDetial -->
    <TopInfo v-if="Object.keys(item).length && sFiles.length" :item="item" :sFiles="sFiles" />
    <OtherItems />
    <ProductDetail v-if="Object.keys(item).length  && mFiles.length" :item="item" :mFiles="mFiles" />
    <Reviews v-if="Object.keys(item).length" :item="item" />
    <!-- <ProductInquiry v-if="Object.keys(item).length" :item="item" /> -->
    <Guidance />
  </div>
</template>

<script>
// import { mapActions } from 'vuex'
import axios from 'axios'
import TopInfo from '@/components/itemdetail/TopInfo.vue'
import OtherItems from '@/components/itemdetail/OtherItems.vue'
import ProductDetail from '@/components/itemdetail/ProductDetail.vue'
import Reviews from '@/components/itemdetail/Reviews.vue'
// import ProductInquiry from '@/components/itemdetail/ProductInquiry.vue'
//
// --------- 아래 face app ---------------------------
import Guidance from '@/components/itemdetail/Guidance.vue'
import RwvCamera from "@/components/TheCamera.vue";
import { Balloon } from "vue-balloon";
import * as tf from "@tensorflow/tfjs";
import * as faceapi from "face-api.js";
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
  // ----------- 아래 face app ----------------------
    mood: "로딩중...",
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
  }),
  mounted(){
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
    getItem() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/detail/${this.productId}`)
        .then(res => {
          console.log(res.data.data)
          this.item = res.data.data
          this.$store.dispatch('SELECTITEM', this.item)
          this.item.images.forEach(image => {
            if (image.imageType === 'S') {
              this.sFileIds.push(image.fileId)
            } else {
              this.mFileIds.push(image.fileId)
            }
          })
          axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
            params: {
              fileIds: this.sFileIds.join(',')
            },
            // paramsSerializer: params => {
            //   return qs.stringify(params)
            // }
          })
            .then(res => {
              // console.log(res.data)
              this.sFiles = res.data.data
            })
            .catch(err => {
              console.log(err)
            })
          axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
            params: {
              fileIds: this.mFileIds.join(',')
            },
          })
            .then(res => {
              // console.log(res.data)
              this.mFiles = res.data.data
            })
            .catch(err => {
              console.log(err)
            })          
        })
        .catch(err => {
          console.log(err)
        })
    },

    // ----------------------- 아래 face app---------------------------------
    async init(callback) {
      var self = this;
      // load the face detection api & emotion detection model
      await faceapi.loadSsdMobilenetv1Model("/models/features/");
      await faceapi.loadFaceLandmarkModel("/models/features");
      await faceapi.loadFaceExpressionModel("/models/features");
      this.emotionModel = await tf.loadLayersModel(
        "/models/emotion/model.json"
      );
      callback();
    },
    setLoading() {
      this.show = true;
    },
    async getEmotion() {
      var self = this;
      const image = this.$children[0].$children[0].webcam.webcamElement;
      this.polling = setInterval(async () => {
        const userExpression = await faceapi
          .detectSingleFace(image)
          .withFaceLandmarks()
          .withFaceExpressions();
        if (typeof userExpression === "undefined") {
          this.show = false;
          this.$refs["error-modal"].show();
        } else {
          // console.log(this.timer);
          this.show = false;
          var expression = Object.keys(userExpression.expressions).reduce(
            function(a, b) {
              return userExpression.expressions[a] >
                userExpression.expressions[b]
                ? a
                : b;
            }
          );
        }
        this.setMood(expression);
        this.timer += 0.1;
        this.neutral += userExpression.expressions["neutral"];
        this.happy += userExpression.expressions["happy"];
        this.sad += userExpression.expressions["sad"];
        this.angry += userExpression.expressions["angry"];
        this.fearful += userExpression.expressions["fearful"];
        this.disgusted += userExpression.expressions["disgusted"];
        this.surprised += userExpression.expressions["surprised"];
        if (this.timer >= 10) this.stopAnalysis();
      }, 100);
    },
    setMood(mood) {
      var self = this;
      this.mood = mood;
    },
    setImage(image) {
      console.log("picture taken");
      var self = this;
      this.image = image;
    },
    sendData: function() {
      axios
        .post("http://localhost:8088/face/add", {
          product: 123,
          username: "sskim",
          happy: this.happy.toFixed(2),
          neutral: this.neutral.toFixed(2),
          sad: this.sad.toFixed(2),
          angry: this.angry.toFixed(2),
          fearful: this.fearful.toFixed(2),
          disgusted: this.disgusted.toFixed(2),
          surprised: this.surprised.toFixed(2),
          time: Math.floor(this.timer),
        })
        .then((response) => {
          console.log(response);
        })
        .catch((ex) => {
          console.warn("ERROR!!!!!!!!!!! : ", ex);
        });
    },
    stopAnalysis() {
      clearInterval(this.polling);
      // alert(
      //   this.happy.toFixed(2) +
      //     "\n" +
      //     this.neutral.toFixed(2) +
      //     "\n" +
      //     this.sad.toFixed(2) +
      //     "\n" +
      //     this.angry.toFixed(2) +
      //     "\n" +
      //     this.fearful.toFixed(2) +
      //     "\n" +
      //     this.disgusted.toFixed(2) +
      //     "\n" +
      //     this.surprised.toFixed(2)
      // );
      //axios call
      this.sendData();
      this.timer = 0;
      this.mood = "감정분석 종료";
    },
  },
  // -----------------위 내용 face app---------------------------------
  //
  //
  //
  created() {
    this.productId = this.$route.params.productid
    // console.log(this.productId)
    this.getItem()
    // this.getFileIds()
  },
}
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