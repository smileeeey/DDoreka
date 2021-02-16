<template>
  <div>
    <p class="ma-0 mt-3 ml-3 font-weight-bold">가격</p>
    <v-list>
      <v-list-item>
        <v-list-item-content>
          <a href=""><v-list-item-title>가격 전체</v-list-item-title></a>
          <v-list-item-title>7천원 이하</v-list-item-title>
          <v-list-item-title>7천원 ~</v-list-item-title>
          <v-list-item-title>1만 4천원 ~ </v-list-item-title>
          <v-list-item-title>2만 1천원 ~ </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
    <v-input
      background-color="black"
      dark
      dense
    ></v-input>
  </div>
</template>

<script>
export default {
  name: 'SideBarList',
}
</script>

<style>

</style>