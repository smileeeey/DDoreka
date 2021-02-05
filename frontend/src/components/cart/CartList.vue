<template>
  <table class="simple_table">
    <thead>
      <tr class="head">
        <th class="checkbox">
          <v-checkbox v-model="selectAll" @click="checkedAll"></v-checkbox>
        </th>
        <th>전체선택</th>
        <th>상품정보</th>
        <th>상품금액</th>
        <th>배송비</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th scope="row" class="checkbox">
          <v-checkbox v-model="select1" @click="selected"></v-checkbox>
        </th>
        <td>
          <v-img src="https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4fcd/162c5d78b17078cfb5c2759e809c320b051b2989d2e6256bbab43cee3393.jpg"
            max-height="120"
            max-width="120"
          >
          </v-img>
        </td>
        <td>민트위니 여성용 미키기모셋</td>
        <td class="rightborder">22,900원</td>
        <td rowspan="2" style="text-align: center;">무료</td>
      </tr>
      <tr>
        <th scope="row" class="checkbox">
          <v-checkbox v-model="select2" @click="selected"></v-checkbox>
        </th>
        <td>
          <v-img src="https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86815902988186-18c5ec56-8775-4476-ae6f-d1bae7e2dcc5.jpg"
            max-height="120"
            max-width="120"
          ></v-img>
        </td>
        <td>캐럿 여성 와이드 밴딩 팬츠</td>
        <td class="rightborder">9,900원</td>
      </tr>
      <tr class="total" style="">
        
        <td colspan="5" style="border-top: 2px solid #969696; text-align: right !important; font-size: 16px;">상품가격 {{totalCost}}원 + 배송비 <strong>무료</strong> = 주문금액 <strong>{{totalCost}}</strong>원</td>
      </tr>

    </tbody>
  </table>
</template>

<script>
export default {
  name: 'CartList',
  data: () => ({
    selectAll: true,
    select1: true,
    select2: true,
  }),
  computed: {
    totalCost() {
      let cost = 0;
      if (this.select1 == true) {
        cost = cost + 22900
      }
      if (this.select2 == true) {
        cost = cost + 9900
      }
      return cost
    }
  },
  methods: {
    checkedAll() {
      this.select1 = this.selectAll;
      this.select2 = this.selectAll;
    },
    selected() {
      if (this.select1 == true && this.select2 == true) {
        this.selectAll = true
      } else {
        this.selectAll = false
      }
    }
  }
}
</script>

<style scoped>
  .total {
    background-color: #F7F7F7;
  }

  strong {
    font-size: 20px;
  }

  .simple_table { 
    width: 75%; 
    border-top: 2px solid #969696;
    border-bottom: 2px solid #969696;
    border-collapse: separate; 
    border-spacing: 1px;
  }

  .head th {
    background: #FAFAFA !important;
    border-bottom: 2px solid #969696 !important;
    padding-bottom: 0 !important;
    padding-top: 0 !important;
  }


  .simple_table th { 
    
    padding: 20px; 
    background: white; 
    font-weight: bold; 
    text-align: left; 
    border-bottom: 1px solid #ddd;
    
  }

  .rightborder {
    border-right: 1px solid #ddd !important;
  }

  .simple_table td { 
    padding: 15px; 
    border: none; 
    border-bottom: 1px solid #ddd;
    text-align: left; 
  }

  table {
    border-right: 1px solid #ddd;
  }

  >>> .v-label {
    color: black !important;
  }


  
</style>