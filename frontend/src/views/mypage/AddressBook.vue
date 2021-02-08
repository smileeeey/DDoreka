<template>
  <div style="width: 60%">
    <div v-if="!addState">
      <v-btn
      block
      color="#0073E9"
      style="margin: 2rem 0; padding: 2rem 0;"
      @click="addState = true"
      >
        <v-icon large color="white">mdi-plus</v-icon>
        <span style="font-size: 1.5rem; color: white">배송지 추가</span>
      </v-btn>

      <div v-for="(address, idx) in addresses" :key="idx">
        <AddressCard :address="address" :idx="idx" 
          @deleteAddress="deleteAddress"
        />
      </div>
    </div>
    <div v-else>
      <AddForm />
    </div>
  </div>
</template>

<script>
import AddForm from '../../components/address/AddForm.vue'
import AddressCard from '../../components/address/AddressCard.vue'
export default {
  components: { AddressCard, AddForm },
  name: 'AddressBook',
  data () {
    return {
      addresses: [
        {
          name: '권세진',
          address: '경상북도 구미시 진평동 526, IWC 302호',
          nickname: '자취방',
          phonenumber: '01077269318',
          comment: '문 앞',
        },
        {
          name: '권세진',
          address: '대구광역시 수성구 욱수천로 27, 105동 1005호',
          nickname: '본가',
          phonenumber: '01077269318',
          comment: '경비실',
        },
        {
          name: '권세진',
          address: '멀티캠퍼스',
          nickname: '',
          phonenumber: '01077269318',
          comment: '부재시 연락주세요',
        },
      ],
      addState: false,
    }
  },
  methods: {
    addAddress: function () {
      // var win = window.open("", "PopupWin", "width=500, height=600");
      // win.document.write("<p>새창에 표시될 내용 입니다.</p>");
    },
    deleteAddress: function (idx) {
      this.addresses.splice(idx, 1)
    }
  }
}
</script>

<style>

</style>