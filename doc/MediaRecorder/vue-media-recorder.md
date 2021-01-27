# vue-media-recorder

> WebCamera를 이용해 현재 나의 모습을 live로 Web 화면에 보여주고 Capture 할 수 있다.



##### How to use

```
vue create exercise

cd exercise

npm install vue-media-recorder
or
npm i vue-media-recorder

npm i sass-loader
npm i node-sass


npm run serve
```



##### App.vue

```vue
<template>
    <div>
        <PhotoCapture v-model="imageBase64" />
        <VideoCapture uploadUrl="<example-server-address.com>" v-model="videoUrl" />
    </div>
</template>

<script>
import 'vue-media-recorder/src/assets/scss/main.scss'
import {PhotoCapture, VideoCapture} from 'vue-media-recorder'

export default {
    data(){
        return {
            imageBase64: null,
            videoUrl: null,
        }
    },
    components:{
        PhotoCapture,
        VideoCapture
    }
}
</script>
```



![](vue-media-recorder.png)



##### source

https://www.npmjs.com/package/vue-media-recorder

https://github.com/MikeCheng1208/vue-metamask/issues/1