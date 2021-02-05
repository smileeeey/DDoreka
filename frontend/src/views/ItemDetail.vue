<template>
  <div>
    <TopInfo />
    <OtherItems />
    <ProductDetail />
    <Reviews />
    <ProductInquiry />
    <Guidance />
  </div>
</template>

<script>
import TopInfo from '@/components/itemdetail/TopInfo.vue'
import OtherItems from '@/components/itemdetail/OtherItems.vue'
import ProductDetail from '@/components/itemdetail/ProductDetail.vue'
import Reviews from '@/components/itemdetail/Reviews.vue'
import ProductInquiry from '@/components/itemdetail/ProductInquiry.vue'
import Guidance from '@/components/itemdetail/Guidance.vue'
export default {
  name: 'ItemDetail',
  components: {
    TopInfo,
    OtherItems,
    ProductDetail,
    Reviews,
    ProductInquiry,
    Guidance,
  },
}
</script>

<style>

</style>