<template>
  <div style="height: 115px;">
    <v-sheet
      color="blue"
      style="color: white; display: flex; 
      justify-content:center; align-items: center; 
      flex-direction: column; height: 100%;"
      @mouseover="CategoriesShow=true"
    >
      <v-icon x-large style="color: white">
        mdi-menu
      </v-icon>
      <span>카테고리</span>
    </v-sheet>
    <div style="position: relative; z-index: 2;" v-show="CategoriesShow" @mouseleave="CategoriesShow=false; SubShow=false;">
      <div
        class="mx-auto box"
        style="position: absolute; top: 0px; width: 150px; height: 492px; background-color: white;"
      >
        <div>
          <ul v-for="(item, idx) in items" :key="idx" style="padding-left: 0px;" class="my-2" @mouseover="selectedItem=idx; SubShow=true">
            <v-icon v-text="item.icon" class="mx-2" color="black"></v-icon>
            <span v-text="item.name" style="font-size: 14px; color: black; letter-spacing: -1px;" :class="{ select: idx === selectedItem }"></span>
          </ul>
        </div>

        <div class="box" style="position: absolute; top: -1px; left: 148px; width: 400px; height: 492px; background-color: white;" 
          v-show="SubShow" @mouseleave="SubShow=false; selectedSubitem=-1;"
        >
          <div v-if="selectedItem > -1">
            <div v-for="(subitem, idx) in items[selectedItem].subCategory" :key="idx" @mouseover="selectedSubitem=idx; ContentShow=true">
              <ul style="padding-left: 0px;" class="my-2">
                <p style="color: black; margin-left: 10px;" :class="{ select: idx === selectedSubitem }">{{subitem.name}}</p>
              </ul>
            </div>

            <div class="box" v-if="selectedSubitem > -1" style="position: absolute; top: -1px; left: 148px; width: 251px; height: 492px;">
              <div v-for="(content, idx) in items[selectedItem].subCategory[selectedSubitem].subCategory" :key="idx" @mouseover="selectedContent=idx">
                <ul @click="$router.push({ name: 'Category', params: { id: content.id, depth: 3 } }); CategoriesShow=false;" style="padding-left: 0px;" class="my-2">
                  <!-- <p>{{content}}</p> -->
                  <p style="color: black; margin-left: 10px;" :class="{ select: idx === selectedContent }">{{content.name}}</p>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Categories',
  data: () => ({
      selectedItem: -1,
      selectedSubitem: -1,
      selectedContent: -1,
      SubShow: false,
      ContentShow: false,
      CategoriesShow: false,
      selectedItemStatus: false,
      model: 0,
      submodel: 0,
      detailmodel: 0,
      items: [

      ],
      
    }),
    created: function () {
      axios.get(`http://i4d106.p.ssafy.io:8081/category/mainCategory`)
        .then(res => {
          this.items = res.data.data
        })
    }
}
</script>

<style scoped>
  .select {
    font-weight: bold;
    text-decoration: underline;
    color: #4285F4 !important;
  }

  .box {
    border: 1px solid #ddd;
    box-shadow: -1px 4px 5px rgb(0 0 0 / 30%);
  }

  p {
    font-size: 14px;
    letter-spacing: -1px;
  }
</style>