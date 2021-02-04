<template>
  <v-carousel
    cycle
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <!-- smallItem 6개가 한 슬라이드에 들어 오도록 구성해야한다. -->
    <v-carousel-item
      v-for="(slide, idx) in smallItems"
      :key="idx"
    >
      <v-container class="fill-height" style="box-sizing: border-box;">
        <v-row
          class=""
          align="center"
          justify="center"
          style="height: 400px;"
        >
          <v-col
            v-for="(smallItem, idx) in slide.smallItem"
            :key="idx"
            class="pa-0"
            cols="4"
            style="height: 200px;"
          >
            <v-img
              :src="smallItem.image"
              style="height: 150px;"
            ></v-img>
            <v-card-text class="pa-0" style="height: 50px;">
              <div class="black--text ml-3">
                <!-- 상품이름 -->
                {{ smallItem.name }}
              </div>
              <v-container>
                <v-row>
                  <div class="red--text text--darken-4 ml-3">
                    <!-- 상품가격 -->
                    <p style="margin-top: 1px;">{{ smallItem.price }}</p>
                  </div>
                  <!-- 평균평점 -->
                  <div class="ml-2">
                    <v-rating
                      :value="smallItem.rating"
                      color="amber"
                      dense
                      half-increments
                      readonly
                      size="16"
                    ></v-rating>                 
                  </div>
                </v-row>
              </v-container>
            </v-card-text>            
          </v-col>
        </v-row>
      </v-container>
    </v-carousel-item>
  </v-carousel>  
</template>

<script>
export default {
  name: 'DetailSmallItem',
  props: {
    smallItems: Array,
  }
}
</script>

<style>

</style>