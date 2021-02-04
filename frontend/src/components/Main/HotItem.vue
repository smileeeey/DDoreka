<template>
  <div>
    <h3 class="my-5">요즘 뜨는 상품 | <span style="font-size: 70%">최근 가장 HOT한 상품</span></h3>
    <v-sheet
      class="mx-auto"
      flat
      outlined
      rounded
    >
      <v-slide-group
        class="pa-2"
        show-arrows
      >
        <v-slide-item
          v-for="n in 15"
          :key="n"
        >
          <v-card
            class="ma-4"
            height="200"
            width="200"
          >
            <template slot="progress">
              <v-progress-linear
                color="deep-purple"
                height="10"
                indeterminate
              ></v-progress-linear>
            </template>            
            <v-img
              :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
              height="150"
              width="200"
            ></v-img>
            <v-card-text class="pa-1 ml-2">
              <div class="mb-1 subtitle-1 font-weight-bold text-truncate" color="black">
                <!-- 상품이름 -->
                상품이름
              </div>
              <v-row
                align="center"
                justify="start"
                class="mx-0"
              >
                <!-- 평균평점 -->
                <v-rating
                  :value="4.5"
                  color="amber"
                  dense
                  half-increments
                  readonly
                  size="16"
                ></v-rating>

                <!-- 평균평점, 리뷰개수 -->
                <div class="ml-2">
                  4.5 (413)
                </div>
              </v-row>
            </v-card-text>            
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>    
  </div>
</template>

<script>
export default {
  name: 'HotItem',
}
</script>

<style>

</style>