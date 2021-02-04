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
            <span v-text="item.text" style="font-size: 14px; color: black; letter-spacing: -1px;" :class="{ select: idx === selectedItem }"></span>
          </ul>
        </div>

        <div class="box" style="position: absolute; top: -1px; left: 148px; width: 400px; height: 492px; background-color: white;" 
          v-show="SubShow" @mouseleave="SubShow=false"
        >
          <div v-if="selectedItem > -1">
            <div v-for="(subitem, idx) in items[selectedItem].subtext" :key="idx" @mouseover="selectedSubitem=idx; ContentShow=true">
              <ul style="padding-left: 0px;" class="my-2">
                <p style="color: black; margin-left: 10px;" :class="{ select: idx === selectedSubitem }">{{subitem.subtitle}}</p>
              </ul>
            </div>

            <div class="box" v-if="selectedSubitem > -1" style="position: absolute; top: -1px; left: 148px; width: 251px; height: 492px;">
              <div v-for="(content, idx) in items[selectedItem].subtext[selectedSubitem].contents" :key="idx" @mouseover="selectedContent=idx">
                <ul @click="$router.push({ name: 'Category', params: { name: content } }); CategoriesShow=false;" style="padding-left: 0px;" class="my-2">
                  <!-- <p>{{content}}</p> -->
                  <p style="color: black; margin-left: 10px;" :class="{ select: idx === selectedContent }">{{content}}</p>
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
      items: [
        { text: '패션의류/잡화', icon: 'mdi-human-male', subtext: [
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

        { text: '뷰티', icon: 'mdi-fountain-pen', subtext: [
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
        {
          text: '출산/유아동', icon: 'mdi-baby-bottle-outline'
        },
        { text: '식품', icon: 'mdi-food-apple-outline', subtext: [
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
        {
          text: '주방용품', icon: 'mdi-pot-mix-outline'
        },
        {
          text: '생활용품', icon: 'mdi-bottle-wine-outline'
        },
        {
          text: '홈인테리어', icon: 'mdi-sofa-outline'
        },
        {
          text: '가전디지털', icon: 'mdi-camera-outline'
        },
        {
          text: '스포츠/레저', icon: 'mdi-soccer'
        },
        {
          text: '자동차용품', icon: 'mdi-car-outline'
        },
        {
          text: '도서/음반/DVD', icon: 'mdi-book-open-outline'
        },
        {
          text: '완구/취미', icon: 'mdi-rocket-launch-outline'
        },
        {
          text: '문구/오피스', icon: 'mdi-pencil-outline'
        },
        {
          text: '반려동물용품', icon: 'mdi-bone'
        },
        {
          text: '헬스/건강식품', icon: 'mdi-heart-settings-outline'
        },
      ],
      
    }),
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