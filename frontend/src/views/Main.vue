<template>
  <div class="in-layout mx-auto" style="width: 100%">
    <TopCarousels />
    <TodayItemsList />
    <HotItem />
    <SteadySeller />
    <CategoryRecommend />
  </div>
</template>

<script>
import TopCarousels from '@/components/main/TopCarousels.vue'
import TodayItemsList from '@/components/main/TodayItemsList.vue'
import HotItem from '@/components/main/HotItem.vue'
import SteadySeller from '@/components/main/SteadySeller.vue'
import CategoryRecommend from '@/components/main/CategoryRecommend.vue'
// @ is an alias to /src

export default {
  name: 'Main',
  components: {
    TopCarousels,
    TodayItemsList,
    HotItem,
    SteadySeller,
    CategoryRecommend,
  }
}
</script>
