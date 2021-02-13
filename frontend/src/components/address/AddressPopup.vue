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
import axios from 'axios'
import { mapState } from 'vuex'
import AddForm from './AddForm.vue'
import AddressCardSelect from './AddressCardSelect.vue'
export default {
  name: 'AddressPopup',
  data () {
    return {
      addState: false,
      addresses: [],
    }
  },
  computed: {
    ...mapState([
      'email',
    ])
  },
  components: {
    AddressCardSelect,
    AddForm,
  },
  created () {
    axios.get(`http://i4d106.p.ssafy.io:8080/user/address/${this.email}`)
      .then(res => {
        this.addresses = res.data.data
      })
  },
  methods: {
    deleteAddress: function (addressId) {
      axios.delete(`http://i4d106.p.ssafy.io:8080/user/address/${this.email}/${addressId}`)
        .then(res => {
          this.addresses = res.data.data
        })
    },
    saveAddress: function (newAddress) {
      this.addresses = newAddress
      this.addState = false
    }
  }
}
</script>

<style>

</style>