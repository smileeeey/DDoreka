<template>
  <v-app>
    <div v-if="!addState">
      <h2 style="text-align: center; margin: 1rem 0;">배송지선택</h2>
      <div v-for="(address, idx) in addresses.slice().reverse()" :key="idx">
        <AddressCardSelect :address="address" @deleteAddress="deleteAddress" />
      </div>
      <v-btn
        block
        color="#0073E9"
        style="margin: 2rem 0; padding: 2rem 0;"
        @click="addState = true"
        >
          <v-icon large color="white">mdi-plus</v-icon>
          <span style="font-size: 1.5rem; color: white">배송지 추가</span>
      </v-btn>
    </div>
    <div v-else>
      <AddForm @saveAddress="saveAddress" />
    </div>
  </v-app>
</template>

<script>
import AddForm from './AddForm.vue'
import AddressCardSelect from './AddressCardSelect.vue'
export default {
  name: 'AddressPopup',
  data () {
    return {
      addState: false,
      addresses: [
        {
          name: '권세진',
          main_address: '경상북도 구미시 진평동 526',
          sub_address: 'IWC 302호',
          nickname: '자취방',
          phonenumber: '01077269318',
          comment: '문 앞',
        },
        {
          name: '권세진',
          main_address: '대구광역시 수성구 욱수천로 27',
          sub_address: '105동 1005호',
          nickname: '본가',
          phonenumber: '01077269318',
          comment: '경비실',
        },
        {
          name: 'ㅇㅇ',
          main_address: '너네집',
          sub_address: '멀티캠퍼스',
          nickname: '',
          phonenumber: '01077269318',
          comment: '빨리가져다주세요',
        },
      ]
    }
  },
  components: {
    AddressCardSelect,
    AddForm,
  },
  methods: {
    deleteAddress: function (idx) {
      this.addresses.splice(idx, 1)
    },
    saveAddress: function (newAddress) {
      this.addresses.push(newAddress)
      this.addState = false
    }
  }
}
</script>

<style>

</style>