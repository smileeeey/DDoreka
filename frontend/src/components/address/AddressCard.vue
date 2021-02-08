<template>
  <v-card
    class="mx-auto"
    max-width="100%"
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
          {{address.address}}
        </v-list-item-title>
        
        <v-list-item-title class="my-1 sizeup">{{address.comment}}</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-card-actions>
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
  name: 'AddressCard',
  props: {
    address: Object,
    idx: Number,
  },
  methods: {
    deleteAddress: function () {
      this.$emit('deleteAddress', this.idx)
    }
  }
}
</script>

<style scoped>
  .sizeup {
    font-size: 1.2rem;
  }
</style>