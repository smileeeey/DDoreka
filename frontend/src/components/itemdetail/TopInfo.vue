<template>
  <v-container class="mt-10">
    <v-row>
      <v-col cols="6">
        <v-img
          src="https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86760606173009-26999de7-87ba-4b6c-943f-db238a523499.jpg"
        ></v-img>
      </v-col>
      <v-col cols="6">
        <v-card
          :loading="loading"
          class="mx-auto"
        >
          <template slot="progress">
            <v-progress-linear
              color="deep-purple"
              height="10"
              indeterminate
            ></v-progress-linear>
          </template>

          <v-img
            height="250"
            src="https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86761935323114-aeddfa72-5550-4ac2-be6e-e3781053f380.jpg"
          ></v-img>

          <v-card-title>캐럿 여성 와이드 밴딩 팬츠</v-card-title>

          <v-card-text>
            <v-row
              align="center"
              class="mx-0"
            >
              <v-rating
                :value="4.5"
                color="amber"
                dense
                half-increments
                readonly
                size="14"
              ></v-rating>

              <div class="grey--text ml-4">
                4.5 (413)
              </div>
            </v-row>

            <div class="mt-4 subtitle-1">
              무료배송
            </div>
            <div>사이즈 : Free</div>
            <div>색상 : 블랙</div>
          </v-card-text>

          <v-divider class="mx-4"></v-divider>

          <v-card-title class="pb-0">상품주문</v-card-title>

          <v-container>
            <v-row>
              <v-col cols=6>
                <v-card-text>
                  <v-chip-group
                    v-model="selection"
                    active-class="deep-purple accent-4 white--text"
                    column
                  >
                    <v-chip>블랙</v-chip>

                    <v-chip>그레이</v-chip>
                  </v-chip-group>
                </v-card-text>
              </v-col>

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
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
export default {
  name: 'TopInfo',
}
</script>

<style>

</style>