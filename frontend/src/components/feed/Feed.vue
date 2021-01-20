<template>
  <div>
    
    <UsernameBar />
    <PhotoCarousels />
    <TimeInfo />
  </div>
</template>

<script>
import PhotoCarousels from './PhotoCarousels.vue'
import TimeInfo from './TimeInfo.vue'
import UsernameBar from './UsernameBar.vue'
export default {
  name: 'Feed',
  components: {
    UsernameBar,
    PhotoCarousels,
    TimeInfo,
  },
}
</script>

<style>

</style>