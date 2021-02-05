<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr>
            <td>제품 소재</td>
            <td>폴리에스테르 100</td>
            <td>색상</td>
            <td>블랙</td>
          </tr>
          <tr>
            <td>치수</td>
            <td>Free</td>
            <td>제조자(수입자)</td>
            <td>주식회사 퍼스트플로어</td>
          </tr>
          <tr>
            <td>제조국</td>
            <td>대한민국</td>
            <td>세탁방법 및 취급시 주의사항</td>
            <td>손세탁 권장</td>
          </tr>
          <tr>
            <td>제조연월</td>
            <td>2020.05</td>
            <td>품질보증기준</td>
            <td>제품 이상 시 공정거래위원회 기준에 의거 보상</td>
          </tr>
          <tr>
            <td>AS 책임자와 전화번호</td>
            <td>0000-0000</td>
            <td></td>
            <td></td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <v-img
      src="https://thumbnail9.coupangcdn.com/thumbnails/remote/q89/image/retail/images/64059785646730-5ded04e1-33ef-4ea3-975e-e35b1b3417a9.jpg"
    ></v-img>
    <v-img
      src="https://thumbnail10.coupangcdn.com/thumbnails/remote/q89/image/retail/images/64059590756473-1720ee5d-e979-4b7a-ab3c-216d9963f8b8.jpg"
    ></v-img>
    <v-img
      src="https://thumbnail7.coupangcdn.com/thumbnails/remote/q89/image/retail/images/64059660725985-3955092f-1a92-4506-83ad-4413141c6573.jpg"
    ></v-img>
  </div>
</template>

<script>
export default {
  name: 'ProductDetail',
}
</script>

<style>

</style>