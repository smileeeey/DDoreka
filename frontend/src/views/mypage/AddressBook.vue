<template>
  <div style="width: 60%">
    <div v-if="!addState">
      <v-btn block color="#0073E9" style="margin: 2rem 0; padding: 2rem 0;" @click="addState = true">
        <v-icon large color="white">mdi-plus</v-icon>
        <span style="font-size: 1.5rem; color: white">배송지 추가</span>
      </v-btn>

      <div v-for="(address, idx) in addresses" :key="idx">
        <AddressCard :address="address" :idx="idx" @deleteAddress="deleteAddress" />
      </div>
    </div>
    <div v-else>
      <AddForm @saveAddress="saveAddress" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';
import AddForm from '../../components/address/AddForm.vue';
import AddressCard from '../../components/address/AddressCard.vue';
export default {
  components: { AddressCard, AddForm },
  name: 'AddressBook',
  computed: {
    ...mapState('accountStore', ['userData']),
  },
  data() {
    return {
      addresses: [],
      addState: false,
    };
  },
  methods: {
    deleteAddress: function(addressId) {
      // this.addresses.splice(idx, 1)
      axios.delete(`http://k4d104.p.ssafy.io:8085/user/address/${this.userData.email}/${addressId}`).then((res) => {
        console.log(res);
        this.addresses = res.data.data;
      });
    },
    saveAddress: function(newAddress) {
      this.addresses = newAddress;
      this.addState = false;
    },
  },
  computed: {
    ...mapState('accountStore', ['userData']),
  },
  created() {
    console.log('dasfsadfafsd');
    axios.get(`http://k4d104.p.ssafy.io:8085/user/address/${this.userData.email}`).then((res) => {
      this.addresses = res.data.data;
    });
  },
};
</script>
