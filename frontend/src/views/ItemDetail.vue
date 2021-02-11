<template>
  <div>
    <TopInfo :item="item" />
    <OtherItems />
    <ProductDetail :item="item" />
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
  data: () => ({
    item: {
      "id": 1,
      "storeId": 1,
      "name": "하트맨투맨",
      "category1Id": "001",
      "category2Id": "101",
      "category3Id": "201",
      "category4Id": "302",
      "category5Id": "401",
      "registerDate": "2021-02-08T02:17:09.882+00:00",
      "updateDate": "2021-02-08T02:17:09.882+00:00",
      "detailInfo": '{"제품소재": "폴리에스테르 100", "치수": "Free", "제조국": "대한민국", "제조연월": "2020.05", "AS 책임자와 전화번호": "0000-0000", "색상": "블랙", "제조자(수입자)": "주식회사 퍼스트플로어", "세탁방법 및 취급시 주의사항": "손세탁 권장", "품질보증기준": "제품 이상 시 공정거래위원회 기준에 의거 보상"}',
      "rating": 4.5,
      "reviewCnt": 200,
      "options": [
          {
              "optionId": 2,
              "discountPrice": 13000,
              "price": 15000,
              "name": "XL",
              "stockQuantity": 150,
              "fileId": 0
          },
          {
              "optionId": 3,
              "discountPrice": 11000,
              "price": 15000,
              "name": "L",
              "stockQuantity": 100,
              "fileId": 0
          }
      ],
      "images": [
          {
              "fileId": 1,
              "imageType": "S"
          },
          {
              "fileId": 2,
              "imageType": "M"
          }
      ]
    }  
  })
}
</script>

<style>

</style>