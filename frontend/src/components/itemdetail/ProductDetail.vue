<template>
  <!-- <div>{{ detailInfoList }}</div> -->
  <div>
    <div class="my-10">
      <h3 class="my-5">필수표기정보</h3>
      <v-card>
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr v-for="n in detailInfoLength" :key="n">
                <td class="" style="background-color: #dee6ed;">{{ detailInfoList[2 * n - 2] }}</td>
                <td>{{ detailInfoList[2 * n - 1] }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card>
    </div>
    <v-img v-for="(detailMainImage, idx) in detailMainImages" :key="idx" :src="detailMainImage"></v-img>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'ProductDetail',
  computed: {
    ...mapState('mainStore', ['detailMainImages', 'detailProductInfo']),
  },
  data: () => ({
    detailInfo: '',
    detailInfoList: [],
    detailInfoLength: 0,
  }),
  methods: {
    getDetailInfo: function() {
      this.detailInfo = this.detailProductInfo.detailInfo;
      this.detailInfoList = this.detailProductInfo.detailInfo.split('&^%');
      this.detailInfoLength = this.detailInfoList.length / 2;
    },
  },
  created: function() {
    this.getDetailInfo();
  },
};
</script>

<style></style>
