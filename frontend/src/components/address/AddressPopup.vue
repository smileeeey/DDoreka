<template>
  <v-app>
    <h2 style="text-align: center; margin: 1rem 0;">배송지선택</h2>
    
    <div v-for="(address, idx) in addresses" :key="idx">
      <AddressCardSelect :address="address" @deleteAddress="deleteAddress" />
    </div>
  </v-app>
</template>

<script>
import AddressCardSelect from './AddressCardSelect.vue'
export default {
  name: 'AddressPopup',
  data () {
    return {
      addresses: [
        {
          name: '권세진',
          address: '경상북도 구미시 진평동 526, IWC 302호',
          nickname: '자취방',
          phonenumber: '01077269318',
          destination: '문 앞',
        },
        {
          name: '권세진',
          address: '대구광역시 수성구 욱수천로 27, 105동 1005호',
          nickname: '본가',
          phonenumber: '01077269318',
          destination: '경비실',
        },
        {
          name: '권세진',
          address: '멀티캠퍼스',
          nickname: '',
          phonenumber: '01077269318',
          destination: '입구',
        },
      ]
    }
  },
  components: {
    AddressCardSelect,
  },
  methods: {
    deleteAddress: function (idx) {
      this.addresses.splice(idx, 1)
    }
  }
}
</script>

<style>

</style>