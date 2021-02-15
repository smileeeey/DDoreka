<template>
  <div>
    <!-- {{ this.$store.state.currentItem }} -->
    <TopInfo v-if="Object.keys(item).length && sFiles.length" :item="item" :sFiles="sFiles" />
    <OtherItems />
    <ProductDetail v-if="Object.keys(item).length  && mFiles.length" :item="item" :mFiles="mFiles" />
    <Reviews v-if="Object.keys(item).length" :item="item" />
    <!-- <ProductInquiry v-if="Object.keys(item).length" :item="item" /> -->
    <Guidance />
  </div>
</template>

<script>
// import { mapActions } from 'vuex'
import axios from 'axios'
import TopInfo from '@/components/itemdetail/TopInfo.vue'
import OtherItems from '@/components/itemdetail/OtherItems.vue'
import ProductDetail from '@/components/itemdetail/ProductDetail.vue'
import Reviews from '@/components/itemdetail/Reviews.vue'
// import ProductInquiry from '@/components/itemdetail/ProductInquiry.vue'
import Guidance from '@/components/itemdetail/Guidance.vue'
export default {
  name: 'ItemDetail',
  components: {
    TopInfo,
    OtherItems,
    ProductDetail,
    Reviews,
    // ProductInquiry,
    Guidance,
  },
  data: () => ({
    productId: '',
    item: {},
    sFileIds: [],
    mFileIds: [],
    sFiles: [],
    mFiles: [],
  }),
  methods: {
    getItem() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/detail/${this.productId}`)
        .then(res => {
          // console.log(res.data.data)
          this.item = res.data.data
          this.$store.dispatch('SELECTITEM', this.item)
          this.item.images.forEach(image => {
            if (image.imageType === 'S') {
              this.sFileIds.push(image.fileId)
            } else {
              this.mFileIds.push(image.fileId)
            }
          })
          axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
            params: {
              fileIds: this.sFileIds.join(',')
            },
            // paramsSerializer: params => {
            //   return qs.stringify(params)
            // }
          })
            .then(res => {
              // console.log(res.data)
              this.sFiles = res.data.data
            })
            .catch(err => {
              console.log(err)
            })
          axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
            params: {
              fileIds: this.mFileIds.join(',')
            },
          })
            .then(res => {
              // console.log(res.data)
              this.mFiles = res.data.data
            })
            .catch(err => {
              console.log(err)
            })          
        })
        .catch(err => {
          console.log(err)
        })
    },
    // getFileIds() {
    //   this.item.images.forEach(image => {
    //     if (image.imageType === 'S') {
    //       this.sFileIds.push(image.fileId)
    //     } else {
    //       this.mFileIds.push(image.fileId)
    //     }
    //   })
    // },
    // getSFiles() {
    //   axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
    //     params: {
    //       fileIds: this.sFileIds.join(',')
    //     },
    //     // paramsSerializer: params => {
    //     //   return qs.stringify(params)
    //     // }
    //   })
    //   .then(res => {
    //     // console.log(res.data)
    //     this.sFiles = res.data.data
    //   })
    //   .catch(err => {
    //     console.log(err)
    //   })
    // },
    // getMFiles() {
    //   axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
    //     params: {
    //       fileIds: this.mFileIds.join(',')
    //     },
    //   })
    //   .then(res => {
    //     // console.log(res.data)
    //     this.mFiles = res.data.data
    //   })
    //   .catch(err => {
    //     console.log(err)
    //   })
    // },
  },
  created() {
    this.productId = this.$route.params.productid
    // console.log(this.productId)
    this.getItem()
    // this.getFileIds()
  },
}
</script>

<style>

</style>