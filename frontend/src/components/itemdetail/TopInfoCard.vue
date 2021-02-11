<template>
  <v-card
    class="mx-auto"
    height="500"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-card-title class="pb-1">{{ item.name }} </v-card-title>

    <v-card-text>
      <v-container class="mb-5 pl-0">
        <v-row
          align="center"
          class="mx-0"
        >
          <v-rating
            :value="item.rating"
            color="amber"
            dense
            half-increments
            readonly
            size="20"
          ></v-rating>

          <div class="grey--text ml-4">
            ({{ item.reviewCnt }})
          </div>
        </v-row>
      </v-container>

      <v-divider></v-divider>
      <div class="mt-5">{{ discountRate }}%<span class="text-decoration-line-through ml-2">{{ item.options[0].price | comma}}원</span></div>
      <div class="subtitle-1 red--text text--darken-2">{{ item.options[0].discountPrice | comma}}원</div>
      <div class="my-4 subtitle-1">무료배송</div>
      <div>
        <!-- 선택한 옵션이 object로 리턴된다. -->
        <v-select
          v-model="select"
          :items="item.options"
          label="사이즈"
          item-text="name"
          solo
          return-object
        ></v-select>  
      </div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title class="pb-0">상품주문</v-card-title>

    <v-container>
      <v-row>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
          >
            장바구니 담기
          </v-btn>
          <v-btn
            color="blue darken-1"
          >
            바로구매
          </v-btn>
        </v-card-actions>
      </v-row>
    </v-container>
  </v-card>  
</template>

<script>
export default {
  name: 'TopInfoCard',
  props: {
    item: {
      type: Object,
    }
  },
  data: () => ({
    select: '',
  }),
  computed: {
    discountRate: function() {
      return Math.round(((this.item.options[0].price - this.item.options[0].discountPrice) / this.item.options[0].price) * 100)
    }
  }
}
</script>

<style>

</style>