<template>
  <div>
    <div>
      <video style="transform: scaleX(-1);" autoplay playsinline muted id="webcam" width="5-0" height="500"></video>
    </div>
    <div>
      <!--<canvas ref="canvas" id="canvas" width="640" height="480" style="border:1px solid #BBB;"></canvas>-->
    </div>
  </div>
</template>

<script>
import { Webcam } from "../assets/webcam";

export default {
  name: "RwvCamera",
  data() {
    return {
      webcam: null
    };
  },
  mounted() {
    this.loadWebCam();
    /*this.video = this.$refs.video;
        if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
        navigator.mediaDevices.getUserMedia({ video: true }).then((stream) => {
            this.video.srcObject = stream;
            this.video.play();
        });*/
  },
  methods: {
    loadWebCam: function() {
      this.webcam = new Webcam(document.getElementById("webcam"));
      this.webcam.setup();
    },
    async getImage() {
      const img = await webcam.capture();
      const processedImg = tf.tidy(() =>
        img
          .expandDims(0)
          .toFloat()
          .div(127)
          .sub(1)
      );
      img.dispose();
      return processedImg;
    }
  }
};
</script>

<style>
#webcam {
  width: 100%;
  height: auto;
}
#video {
  background-color: #000000;
}
#canvas {
  display: none;
}
li {
  display: inline;
  padding: 5px;
}
</style>
