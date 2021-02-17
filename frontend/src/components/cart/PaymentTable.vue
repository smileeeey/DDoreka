<template>
  <div>
    <strong style="font-size: 1.5rem;">결제정보</strong>
      <table class="simple_table" style="margin-top: 1rem; margin-bottom: 3rem;">
        <tbody>
          <tr>
            <th scope="row">총상품가격</th>
            <td><strong>{{totalCost | comma}}</strong>원</td>
          </tr>
          <tr>
            <th scope="row">배송비</th>
            <td><strong>0</strong>원</td>
          </tr>
          <tr>
            <th scope="row">총결제금액</th>
            <td><strong>{{totalCost | comma}}</strong>원</td>
          </tr>
          <!-- <tr>
            <th scope="row">결제방법</th>
            <td>
              <v-container fluid>
                <v-radio-group v-model="radios">
                  <v-radio value="kakaopay">
                    <template v-slot:label>
                      <div>
                        <span style="color: black">
                        카카오페이
                          <v-img
                            src="@/assets/kakaopay/payment_icon_yellow_large.png"
                            max-width="60"
                            max-height="25"
                            style="display: inline-block"
                          ></v-img>
                        </span>
                      </div>
                    </template>
                  </v-radio>
                </v-radio-group>
              </v-container>
            </td>
          </tr> -->
        </tbody>
      </table>
  </div>
</template>

<script>
export default {
  name: 'PaymentTable',
  data () {
    return {
      radios: 'kakaopay',
    }
  },
  props: {
    totalCost: Number,
  }
}
</script>

<style scoped>
  .simple_table { 
    width: 100%; 
    border-top: 2px solid #969696;
    border-bottom: 2px solid #969696;
    border-collapse: separate; 
    border-spacing: 1px;
  }
  
  .simple_table th { 
    width: 25%;
    padding: 0px 20px; 
    background: #F4F4F4; 
    font-weight: bold; 
    text-align: right; 
    
    border-bottom: 1px solid #ddd;
    border-right: 1px solid #ddd;
  }

  .simple_table td { 
    padding: 10px; 
    border: none; 
    border-bottom: 1px solid #ddd;
    text-align: left; 
  }

  table {
    border-right: 1px solid #ddd;
  }

  
</style>