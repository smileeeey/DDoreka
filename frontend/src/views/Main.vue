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
import TopCarousels from '@/components/Main/TopCarousels.vue'
import TodayItemsList from '@/components/Main/TodayItemsList.vue'
import HotItem from '@/components/Main/HotItem.vue'
import SteadySeller from '@/components/Main/SteadySeller.vue'
import CategoryRecommend from '@/components/Main/CategoryRecommend.vue'
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
