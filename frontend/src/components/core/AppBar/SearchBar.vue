<template>
  <div style="display: flex; border: 3px skyblue solid">
    <!-- {{ e1 }} -->
    <v-row>
      <v-col class="" cols="3">
        <!-- return-object -->
        <v-select
          v-model="category1id"
          :items="categoryNames"
          solo
          flat
          item-text="name"
          item-value="id"
        ></v-select>
      </v-col>
      <v-col class="" cols="9">
        <v-text-field
          v-model="keyword"
          placeholder="찾고 싶은 상품을 검색해보세요!"
          clearable
          solo
          flat
          append-icon="mdi-magnify"
          @keydown.enter="searchItem"
        ></v-text-field>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SearchBar',
  data: () => ({
    categoryNames: [],
    category1id: 'ALL',
    keyword: '',
  }),
  methods: {
    getCategoryName() {
      axios.get(`http://i4d106.p.ssafy.io:8081/category/mainCategory`)
      .then(res => {
        this.categoryNames = res.data.data
        this.categoryNames.unshift({
          name: 'ALL',
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
    searchItem() {
      // console.log('검색실행')
      // console.log(this.keyword)
      this.$router.push({name:'Search', params: {category1id: this.category1id, keyword: this.keyword}})
    }
  },
  created() {
    this.getCategoryName()
  },
}
</script>

<style scoped>
  >>> .v-text-field__details {
    display: none;
  }

  >>> .v-input__slot {
    margin: 0;
  }

  >>> .mdi-magnify {
    color: #0275d8 !important;
  }
</style>