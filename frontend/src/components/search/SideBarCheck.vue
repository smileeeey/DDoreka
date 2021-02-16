<template>
  <div>
    <p class="ma-0 my-3 ml-3 font-weight-bold">브랜드</p>
    <v-checkbox
      v-for="(box, idx) in boxes"
      :key="idx"
      class="ml-3 ma-0 pa-0"
      v-model="box.checkbox"
      @click="selected"
      dense
    >
      <template v-slot:label>
        <div style="">
          {{box.text}}
        </div>
      </template>    
    </v-checkbox>
    <v-divider></v-divider>        
  </div>
</template>

<script>
export default {
  name: 'SideBarCheck',
  data: () => ({
    selected: false,
    boxes: [
      {
        checkbox: false, text: '캐럿'
      },
      {
        checkbox: false, text: '잘빠진'
      },
      {
        checkbox: false, text: '티탁'
      },
      {
        checkbox: false, text: '토마토'
      },
      {
        checkbox: false, text: '이힝'
      },
    ],    
  })
}
</script>

<style scoped>
  .v-input__slot {
    font-size: 5px;
  }
</style>