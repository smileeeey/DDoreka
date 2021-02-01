<template>
  <v-app>
    <AppBar />
    <v-main class="in-layout mx-auto">
      <TopCarousels />
      <TodayItemsList />
      <HotItem />
      <SteadySeller />
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import AppBar from '../components/core/AppBar.vue'
import Footer from '../components/core/Footer.vue'
import TopCarousels from '@/components/Main/TopCarousels.vue'
import TodayItemsList from '@/components/Main/TodayItemsList.vue'
import HotItem from '@/components/Main/HotItem.vue'
import SteadySeller from '@/components/Main/SteadySeller.vue'
// @ is an alias to /src

export default {
  name: 'Main',
  components: {
    AppBar,
    Footer,
    TopCarousels,
    TodayItemsList,
    HotItem,
    SteadySeller,
  }
}
</script>
