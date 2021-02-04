<template>
  <div>
    <h3 class="my-5"><span style="color: blue;">카테고리별 추천 상품</span> | <span style="font-size: 70%">카테고리별 인기상품을 한눈에</span></h3>
    <CategoryRecommendDetail 
      v-for="(category, idx) in categories"
      :key="idx"
      :category="category"
    />
  </div>
</template>

<script>
import CategoryRecommendDetail from './CategoryRecommendDetail.vue'
export default {
  name: 'CategoryRecommend',
  components: {
    CategoryRecommendDetail,
  },
  data: () => ({
    categories: [
      { 
        name: '여성패션',
        bigItems: [
          {
            image: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/709460694546671-b54bc3f1-af0b-484f-9337-bb7d99006242.jpg',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/04/17/14/4/de225e5c-f00c-4f85-9b50-d3d12901916b.jpg',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/980a/b36fea628724578092ee8f3356d4fd26e14cca97bb1e914903714907b64a.jpg',
            itemUrl: 'http://localhost:8080/'
          }
        ],
        smallItems: [
          {
            smallItem: [
              {
                name: '큰언니 브이넥',
                image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4d8b/66fdfe8bcd6ecd3fc5e682917060a5b5369781c40d8ebca799c723011d98.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '14,200원',
                rating: 4,
                reviews: 7,
              },
              {
                name: '루나걸 여성용 니트',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/358954270343111-663ca6b1-cd2c-4c03-9228-1ffbf43b93cb.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '22,900원',
                rating: 4.5,
                reviews: 21,
              },
              {
                name: '데일리앤 골지 니트',
                image: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/10/14/10/9/d1462a59-d316-450b-9a11-a832854e3a84.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '14,700원',
                rating: 4.5,
                reviews: 21,
              },
              {
                name: '제니트 루즈핏 스웨터',
                image: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/e6ea/35d6a7b8c88fa5b83b94ec331bfa4d27103a1edeb07e819d02535e807bee.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '19,800원',
                rating: 4.5,
                reviews: 59,
              },
              {
                name: '양털 후리스 여성용',
                image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/e141/c3e1d494375d959a2e1e8d7610e6d72de79388c283f45303a59b54dde18b.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '9,700원',
                rating: 4.5,
                reviews: 118,
              },
              {
                name: '티탁 여성용 겟일폴라',
                image: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/10/19/10/7/b229ea0d-fe03-4db0-97c3-6babcc0a6ae1.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '6,730원',
                rating: 4,
                reviews: 95,         
              },
            ]
          },
          {
            smallItem: [
              {
                name: '제니트 브이넥 니트',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/c38c/d631ac1b8d7e256434b159ac4f4bcec3618cb9c3bda087413232a04809e7.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '13,900원',
                rating: 4,
                reviews: 95,
              },
              {
                name: '캐럿 스트라이프 스웨터',
                image: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/14935937956959-c63ef1d1-f830-4f8d-9ee5-aa285201622c.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '14,990원',
                rating: 4.5,
                reviews: 90,
              },
              {
                name: '제니트 루즈핏 브이넥',
                image: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/03a5/9efc40abcfd47b0d92fbb17676638d45577426d97c6d5012a73897658c09.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '18,900원',
                rating: 4.5,
                reviews: 28,
              },
              {
                name: '여성조끼 니트베스트',
                image: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/b638/7b6177a2458106643fbdfc35189bbc51e018394744805c9d78ede59d187b.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '9,400원',
                rating: 4,
                reviews: 22,
              },
              {
                name: '포플러앤씨 가디건',
                image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/09/15/12/7/4e59ad5b-d74e-47ee-ab90-1a99c1131567.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '24,900원',
                rating: 4,
                reviews: 11,
              },
              {
                name: '오버핏 그라이데션 스웨터',
                image: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/images/2018/12/19/18/7/8dc59a65-8748-4428-9748-8cfa7537e828.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '15,100원',
                rating: 4,
                reviews: 100,
              },
            ]
          }          
        ]
      },
      { 
        name: '남성패션',
        bigItems: [
          {
            image: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/362619057889704-6fa186f2-dc00-460c-83f8-ea1febd0abe5.jpg',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/8863/308384831e5fd24d89bf2e4ffa2132a91d794f625b1d57b6ae77fbfb3717.jpg',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/146094977699220-ff9dd1de-5c93-4725-ad48-b38a84db0ba4.jpg',
            itemUrl: 'http://localhost:8080/'
          }
        ],
        smallItems: [
          {
            smallItem: [
              {
                name: '매드문 트레이닝 팬츠',
                image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/09/18/11/1/47ecabdc-febc-4c6f-b017-e01825aeddee.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '10,900원',
                rating: 4.5,
                reviews: 123,
              },
              {
                name: '업클라스 남성용 기모',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2019/10/10/11/8/3e32f114-5438-471e-ae6e-452bcff0da77.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '12,900원',
                rating: 4,
                reviews: 453,
              },
              {
                name: '조거 트레이닝 팬츠',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/cbe2/ac9e354809e239e7e07e8dd3300ba42c9f297d1394978f7d30887a6d462e.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '16,800원',
                rating: 5,
                reviews: 64,
              },
              {
                name: '룩플랜 오버핏 맨투맨',
                image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/5d99/486169a6551e0531a5f5861e25ee1d2b7963c82a041bc39c95fc1d850a3a.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '19,900원',
                rating: 4.5,
                reviews: 46,
              },
              {
                name: '오가닉 코튼 티셔츠',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/bcb3/17957478269c3483a00d031c321e2ad0d09ac5a13dc8c3cdfee0aae0fa1b.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '8,900원',
                rating: 4.5,
                reviews: 180,
              },
              {
                name: '휠라 리니어 로고 후디',
                image: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/08/04/19/1/34f1dbb2-525d-4604-acde-52eedebfdcad.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '15,830원',
                rating: 4.5,
                reviews: 112,
              },
            ]
          },
          {
            smallItem: [
              {
                name: '맨타임 와이드 밴딩 팬츠',
                image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/05/25/17/5/2f79fc71-95f6-4d73-990a-4c28f1cf756a.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '11,600원',
                rating: 4,
                reviews: 101,
              },
              {
                name: '스트릿제이 후드티',
                image: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/c482/1397ace17f3c485ba829aaaf7829a028d49801096cc9785abed2a7c70d28.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '28,000원',
                rating: 4.5,
                reviews: 75,
              },
              {
                name: '엠클로 맨투맨',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/318e/73e032cd64113e86263faff8c46bb62da212a5ee35c795213ab308ac97d8.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '25,800원',
                rating: 4.5,
                reviews: 352,
              },
              {
                name: '캐럿 가디건 스웨터',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1235146795145667-3b04124b-ac59-4761-b173-4a8ecd30af9d.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '21,890원',
                rating: 4.5,
                reviews: 91,
              },
              {
                name: '보들보들 시바 맨투맨',
                image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/operator/2355962531/e446e51b-2bd8-d554-8054-6ed0a15ea6d5.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '22,800원',
                rating: 4.5,
                reviews: 86,
              },
              {
                name: '앨빈클로 무지 후드티',
                image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/11557413482550-a509ed3b-a41e-483a-a8db-b47db7c68a52.jpg',
                itemUrl: 'http://localhost:8080/',
                price: '27,890원',
                rating: 4.5,
                reviews: 127,
              },                                          
            ]
          }          
        ]
      }      
    ]    
  })
}
</script>

<style>

</style>