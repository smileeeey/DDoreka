<template>
  <v-card
    class="mx-auto"
    width="100%"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="my-3">
          
          <span style="font-weight: bold; font-size: 18px;">{{address.name}}
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
          {{address.phonenumber}}
        </v-list-item-title>
        <v-list-item-title class="my-1 sizeup">
          {{address.main_address}}, {{address.sub_address}}
        </v-list-item-title>
        
        <v-list-item-title class="my-1 sizeup">{{address.comment}}</v-list-item-title>
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
      window.opener.document.getElementById("name").textContent = this.address.name
      window.opener.document.getElementById("address").textContent = this.address.main_address + ', ' + this.address.sub_address
      window.opener.document.getElementById("phonenumber").textContent = this.address.phonenumber
      
      
      window.close()
    },
    deleteAddress: function () {
      this.$emit('deleteAddress', this.idx)
    }
  }
}
</script>

<style>

</style>