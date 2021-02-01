<template>
  <div>
    <v-sheet
      color="blue"
      min-height="120"
      min-width="120"
      style="color: white; display: flex; 
      justify-content:center; align-items: center; 
      flex-direction: column;"
      @mouseover="CategoriesShow=true"
    >
      <v-icon x-large style="color: white">
        mdi-menu
      </v-icon>
      <span>카테고리</span>
    </v-sheet>
    <div style="position: relative; z-index: 1" v-show="CategoriesShow" @mouseleave="CategoriesShow=false; SubShow=false;">
      <div
        class="mx-auto"
        style="position: absolute; top: 0px; width: 150px; height: 400px; border: 1px solid gray;"
      >
        <div>
          <ul v-for="(item, idx) in items" :key="idx" style="padding-left: 0px;" class="my-2" @mouseover="selectedItem=idx; SubShow=true">
            <v-icon v-text="item.icon" class="mx-2" color="black"></v-icon>
            <span v-text="item.text" style="font-size: 15px; color: black;" :class="{ select: idx === selectedItem }"></span>
          </ul>
        </div>

        <div style="position: absolute; top: -2px; left: 148px; width: 400px; height: 400px; border: 1px solid gray;" 
          v-show="SubShow" @mouseleave="SubShow=false"
        >
          <div v-if="selectedItem > -1">
            <div v-for="(subitem, idx) in items[selectedItem].subtext" :key="idx" @mouseover="selectedSubitem=idx">
              <ul style="padding-left: 0px;" class="my-2">
                <p style="font-size: 15px; color: black; margin-left: 10px;" :class="{ select: idx === selectedSubitem }">{{subitem.subtitle}}</p>
              </ul>
            </div>
          </div>
        </div>
          
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Categories',
  data: () => ({
      selectedItem: -1,
      selectedSubitem: -1,
      SubShow: false,
      CategoriesShow: false,
      selectedItemStatus: false,
      items: [
        { text: '패션의류/잡화', icon: 'mdi-clock-outline', subtext: [
          {
            subtitle: '여성패션', contents: ['의류', '속옷/잠옷', '신발', '가방/잡화',],
          },
          {
            subtitle: '남성패션', contents: ['의류', '속옷/잠옷', '신발', '가방/잡화',],
          },
          {
            subtitle: '유아동패션', contents: ['베이비', '여아', '남아',],
          },
        ], },

        { text: '뷰티', icon: 'mdi-account-outline', subtext: [
          {
            subtitle: '스킨케어', contents: ['스킨', '로션', '미스트',]
          },
          {
            subtitle: '클렌징/필링', contents: ['클렌징폼', '클렌징비누', '클렌징오일',]
          },
          {
            subtitle: '메이크업', contents: ['아이 메이크업', '립 메이크업', '베이스 메이크업',]
          },
          {
            subtitle: '향수', contents: ['여성향수', '남성향수', '드레스퍼퓸',]
          },
          {
            subtitle: '네일', contents: ['네일팁', '스티커', '일반네일', '젤네일',]
          },
        ], },
        { text: '식품', icon: 'mdi-flag-outline', subtext: [
          {
            subtitle: '유기농/친환경', contents: ['과일', '견과', '채소',]
          },
          {
            subtitle: '과일', contents: ['사과', '귤', '오렌지',]
          },
          {
            subtitle: '채소', contents: ['두부', '콩나물', '배추',]
          },
          {
            subtitle: '주방용품', contents: ['칼', '가위', '냄비',]
          },
        ] },
      ],
      
    }),
}
</script>

<style>
  .select {
    font-weight: bold;
    text-decoration: underline;
    color: lightskyblue !important;
  }
</style>