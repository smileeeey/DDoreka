<template>
  <v-form>
    <v-container style="width: 500px;">
      <v-row>
        <v-col
          cols="12"
          style="padding-bottom: 0;"
        >
          <v-text-field
            style="margin-top: 1rem;"
            solo
            flat
            dense
            outlined
            prepend-inner-icon="mdi-account-outline"
            label="받는 사람"
            v-model="address.name"
          ></v-text-field> 
          
          <v-text-field
            v-model="address.address"
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-map-marker-outline"
            append-icon="mdi-magnify"
            label="주소 검색"
            @click="searchAddress"
          ></v-text-field> 

          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-cellphone"
            v-model="address.phonenumber"
            label="휴대폰 번호"
            
          ></v-text-field> 
          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-message-processing-outline"
            label="배송 요청사항"
            v-model="address.comment"
          ></v-text-field> 
        </v-col>
        <v-col cols="12">
          <v-btn style="background-color: #0275d8; color: white; 
            font-size: 1.5rem; font-weight: bold; 
            width: 100%; height: 150%;"
          >
            저장
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>


<script>
export default {
  name: 'AddForm',
  data: () => ({
    address: {
      name: '',
      address: '',
      nickname: '',
      phonenumber: '',
      comment: '',
    }
  }),
  methods: {
    searchAddress: function () {

    }
  }
}
</script>

<style scoped>
  >>> .v-text-field__slot {
    margin: 10px;;
  }

  >>> .v-messages__message {
    margin-top: 2px;
  }
  
</style>