<template>
  <div>
    <p class="ma-0 mt-3 ml-3 font-weight-bold">카테고리</p>
    <v-treeview
      class="text-subtitle-1"
      hoverable
      :items="items"
      dense
    ></v-treeview>
    <v-divider></v-divider>
  </div>
</template>

<script>
export default {
  name: 'SideBarTree',
  data: () => ({
    items: [
      {
        id: 1,
        name: '여성패션',
        children: [
          { id: 2, name: '의류' },
          { id: 3, name: '속옷/잠옷' },
          { id: 4, name: '신발' },
          { id: 5, name: '가방/잡화' },
        ],
      },
      {
        id: 6,
        name: '남성패션',
        children: [
          { id: 7, name: '의류' },
          { id: 8, name: '속옷/잠옷' },
          { id: 9, name: '신발' },
          { id: 10, name: '가방/잡화' },
        ],
      },
      {
        id: 11,
        name: '유아동패션',
        children: [
          { id: 12, name: '베이비' },
          { id: 13, name: '여아' },
          { id: 14, name: '남아' },
        ],
      },            
    ],
  })
}
</script>

<style>

</style>