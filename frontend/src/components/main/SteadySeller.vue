<template>
  <div>
    <h3 class="my-5">스테디 셀러 | <span style="font-size: 70%">꾸준히 잘 팔리는 상품</span></h3>    
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
          v-for="(hotItem, idx) in hotItems"
          :key="idx"
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
              :src="hotItem.src"
              height="150"
              width="200"
            ></v-img>
            <v-card-text class="pa-1 ml-2">
              <div class="mb-1 subtitle-1 font-weight-bold text-truncate" color="black">
                <!-- 상품이름 -->
                {{ hotItem.name }}
              </div>
              <v-row
                align="center"
                justify="start"
                class="mx-0"
              >
                <!-- 평균평점 -->
                <v-rating
                  :value="hotItem.rating"
                  color="amber"
                  dense
                  half-increments
                  readonly
                  size="16"
                ></v-rating>

                <!-- 평균평점, 리뷰개수 -->
                <div class="ml-1">
                  ({{ hotItem.reviews }})
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
  name: 'SteadySeller',
  data: () => ({
    hotItems: [
      {
        id: 1,
        name: '나이키 더플백',
        src: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/06/19/16/9/d135f4b9-827c-4faf-951a-24b9ddebf7e9.jpg',
        rating: 4.5,
        reviews: 331,
      },
      {
        id: 2,
        name: '애플워치3',
        src: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/product/image/vendoritem/2019/03/14/4222443123/ad1c1829-be3b-476c-acae-bfdf7998a67b.jpg',
        rating: 5,
        reviews: 5904,
      },
      {
        id: 3,
        name: '나이키 미들삭',
        src: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/db2e/5858e0223b07a20a52649c01d2f71ce5b51655d55b86ed7cac47f467a72f.jpg',
        rating: 5,
        reviews: 51,
      },
      {
        id: 4,
        name: '코멧 스포츠 풀업밴드',
        src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/3787655370621-3ea77d3f-39f4-4197-a46e-f2e66f6f1eef.jpg',
        rating: 4.5,
        reviews: 641,
      },
      {
        id: 5,
        name: '휠라 레이 운동화',
        src: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/product/image/vendoritem/2018/12/10/4094282745/763848d6-77af-4075-ba03-d70e7db2243f.jpg',
        rating: 4.5,
        reviews: 776,
      },
      {
        id: 6,
        name: '아이엠 마스크',
        src: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/dc69/d966e623141492dafc63f476d32d387f9cb77fcb10be579fd4c45561bb3d.jpg',
        rating: 4.5,
        reviews: 178,
      },
      {
        id: 7,
        name: '샤오미 체지방 체중계',
        src: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/194796565658788-6155d421-a1e8-4f40-b877-99b8ae1e06d9.jpg',
        rating: 4.5,
        reviews: 1609,
      },      
    ]
  }),
}
</script>

<style>

</style>