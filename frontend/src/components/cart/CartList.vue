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
        <th style="text-align: center;">배송비</th>
      </tr>
    </thead>
    <tbody v-if="items">
      <tr v-for="(item, idx) in items" :key="idx">
        <th scope="row" class="checkbox">
          <v-checkbox v-model="items[idx].select" @click="selected"></v-checkbox>
        </th>
        <td>
          <v-img
            :src="`data:image/jpeg;base64,${item.img}`"
            height="120"
            width="120"
          >
          </v-img>
        </td>
        <td>
          <div style="display: flex; justify-content: space-between;">
            {{item.name}}
            <div style="display: flex;">
              <span style="color: #888; vertical-aling: top; font-size: 16px;">{{item.cost | comma}}원</span>
              <select
                class="quantity-select"
                style="margin-left: 1rem; margin-right: 3rem;"
                v-model="items[idx].amount"
              >
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
              </select>
            </div>
          </div>
        </td>
        <td class="rightborder">
          <div style="display: flex; justify-content: space-between;">
            <span style="font-weight: bold; margin-left: 0.5rem;">{{item.cost*item.amount | comma}}원</span>
            <a @click="deleteCartitem(idx)"><v-icon>mdi-close</v-icon></a>
          </div>
        </td>
        <td v-if="idx==0" :rowspan="items.length" style="text-align: center; font-weight: bold;">무료</td>
      </tr>
      
      <tr class="total" style="">
        <td colspan="5" style="border-top: 2px solid #969696; text-align: right !important; font-size: 16px;">상품가격 <strong>{{totalCost | comma}}</strong>원 + 배송비 <strong>무료</strong> = 주문금액 <strong style="color: red;">{{totalCost | comma}}</strong>원</td>
      </tr>

    </tbody>
    <tbody v-else>
      <tr>
        <td colspan="5">장바구니에 담은 상품이 없습니다.</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import axios from 'axios'
export default {
  name: 'CartList',
  data: () => ({
    selectAll: true,
    quantity: [1, 2, 3, 4, 5],
  }),
  props: {
    items: Array,
  },
  computed: {
    totalCost() {
      let cost = 0;
      
      for (let i in this.items) {
        if (this.items[i].select) {
          cost += (this.items[i].cost * this.items[i].amount)
        }
      }
      this.$emit('updateTotalCost', cost)
      return cost
    }
  },
  methods: {
    checkedAll() {
      for (let i in this.items) {
        this.items[i].select = this.selectAll
      }
    },
    selected() {
      for (let i in this.items) {
        if(! this.items[i].select) {
          this.selectAll = false;
          return
        } else {
          this.selectAll = true;
        }
      }
    },
    deleteCartitem(idx) {
      // delete axios request
      console.log(this.items[idx].cartId)
      const token = localStorage.getItem('eureka-authorization')
      axios.delete(`http://i4d106.p.ssafy.io:8080/user/cart/${this.items[idx].cartId}`, {}, {
        headers: {
          'eureka-authorization': token,
        }
      })
        .then(res => {
          console.log(res)
          this.$store.dispatch("DELETEWISHLIST", idx)
        })
      this.items.splice(idx, 1)
    }
  }
}
</script>

<style scoped>
  .total {
    background-color: #F7F7F7;
  }

  strong {
    font-size: 18px;
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

  .quantity-select {
    width: 60px;
    -webkit-writing-mode: horizontal-tb !important;
    text-rendering: auto;
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    appearance: menulist;
    box-sizing: border-box;
    text-align: start;
    align-items: center;
    white-space: pre;
    border-radius: 0px;;
    border-width: 1px;
    border-style: solid;
    border-color: rgb(118, 118, 118);
  }

  >>> .v-label {
    color: black !important;
  }

  >>> .v-text-field__details {
    display: none;
  }
  
</style>