<template>
  <div class="my-10">
    <h3 class="my-5">배송정보</h3>
    <v-card>
      <v-simple-table>
        <template v-slot:default>
          <tbody>
            <tr>
              <td style="background-color: #dee6ed;">배송방법</td>
              <td>순차배송</td>
            </tr>
            <tr>
              <td style="background-color: #dee6ed;">배송비</td>
              <td>전 제품 무료배송</td>
            </tr>
            <tr>
              <td style="background-color: #dee6ed;">배송기간</td>
              <td>
                <p class="pt-4">- 경기 및 수도권 : 주문 및 결제 완료 후, 1-2일 이내 도착</p>
                <p>- 수도권 외 지방 : 주문 및 결제 완료 후, 2-3일 이내 도착</p>
                <p>- 도서산간 지역 : 주문 및 결제 완료 후, 3-4일 이내 도착</p>
                <p>- 천재지변, 물량 수급 변동 등 예외적인 사유 발생 시, 다소 지연될 수 있는 점 양해 부탁드립니다.</p>
              </td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-card>
    <h3 class="my-5">교환/반품 안내</h3>
    <v-card>
      <v-simple-table>
        <template v-slot:default>
          <tbody>
            <tr>
              <td style="background-color: #dee6ed;">교환/반품 비용</td>
              <td>
                <p class="pt-4">- 0원</p>
                <p>- 단, 고객 변심의 경우에는 왕복 택배비</p>
              </td>
            </tr>
            <tr>
              <td style="background-color: #dee6ed;">교환/반품 신청 기준일</td>
              <td>
                <p class="pt-4">- 단순변심에 의한 상품의 교환/반품은 제품 수령 후 7일 이내</p>
                <p>- 상품하자 의한 상품의 교환/반품은 제품 수령 후 30일 이내</p>
              </td>
            </tr>
            <tr>
              <td style="background-color: #dee6ed;">교환/반품 제한사항</td>
              <td>
                <p class="pt-4">- 주문/제작 상품의 경우, 상품의 제작이 이미 진행된 경우</p>
                <p>- 상품 포장을 개봉하여 사용 또는 설치 완료되어 상품의 가치가 훼손된 경우</p>
                <p>- 고객의 사용, 시간경과, 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우</p>
              </td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-card>    
  </div>
</template>

<script>
export default {
  name: 'Guidance',
}
</script>

<style>

</style>