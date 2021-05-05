<template>
  <v-card
    class="mx-auto"
    width="100%"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="my-3">
          
          <span style="font-weight: bold; font-size: 18px;">{{address.recipientName}}
            <v-chip
              v-if="address.nickname"
              style="margin: 0 1rem;"
              outlined
            >
              {{address.nickname}}
            </v-chip>
          </span>
          
        </v-list-item-title>
        <v-list-item-title class="my-1 sizeup">
          {{address.recipientPhone|phone}}
        </v-list-item-title>
        <v-list-item-title class="my-1 sizeup">
          {{address.mainAddress}}, {{address.subAddress}}
        </v-list-item-title>
        
        <v-list-item-title class="my-1 sizeup">{{address.deliveryMsg}}</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-card-actions>
      <v-btn
        outlined
        rounded
        text
        style="border: 1px solid #0073E9; color: #0073E9; margin: 0.25rem 0.25rem;"
        @click="selectAddress"
      >
        선택
      </v-btn>
      <v-btn
        outlined
        rounded
        text
        style="border: 1px solid red; color: red; margin: 0.25rem 0.25rem;"
        @click="deleteAddress"
      >
        삭제
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'AddressCardSelect',
  props: {
    address: Object,
    idx: Number,
  },
  methods: {
    selectAddress: function () {
      window.opener.document.getElementById("uniquename").textContent = this.address.recipientName
      window.opener.document.getElementById("uniqueaddress").textContent = this.address.mainAddress + ', ' + this.address.subAddress
      window.opener.document.getElementById("uniquephonenumber").textContent = this.address.recipientPhone.replace(/[^0-9]/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
      window.opener.document.getElementById("uniquecomment").textContent = this.address.deliveryMsg;
      
      
      window.close()
    },
    deleteAddress: function () {
      this.$emit('deleteAddress', this.address.addressId)
    }
  }
}
</script>

<style>

</style>